package com.minahotel.sourcebackend.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.JWTExceptionCustomize;
import com.minahotel.sourcebackend.common.logs.MessCodeUtils;
import com.minahotel.sourcebackend.common.logs.Message;
import com.minahotel.sourcebackend.security.JwtUtilsCustomize;
import com.minahotel.sourcebackend.security.SecurityConstants;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		LOG.info(MessCodeUtils.TransferMessCode(Message.IN_001, JWTAuthorizationFilter.class.getName()));

		String header = req.getHeader(SecurityConstants.HEADER_STRING);
		// khong co thi cho filter security ở sau chặn lại
		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			LOG.info(MessCodeUtils.TransferMessCode(Message.IN_003));
			chain.doFilter(req, res);
			return;
		}

		// 1 refresh token
		if (SecurityConstants.REFRESH_TOKEN_URL.equals(req.getServletPath())) {
			LOG.info(MessCodeUtils.TransferMessCode(Message.IN_001, SecurityConstants.REFRESH_TOKEN_URL));
			processRefreshToken(req,res);			
			LOG.info(MessCodeUtils.TransferMessCode(Message.IN_002, SecurityConstants.REFRESH_TOKEN_URL));
		}
		// 2 get resource
		else {
			LOG.info(MessCodeUtils.TransferMessCode(Message.IN_001, Message.IN_004));
			UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			chain.doFilter(req, res);
			LOG.info(MessCodeUtils.TransferMessCode(Message.IN_002, Message.IN_004));
		}
	}

	// Reads the JWT from the Authorization header, and then uses JWT to validate
	// the token
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(SecurityConstants.HEADER_STRING);
		Map<String, Claim> mapClaim = null;
		
		try {
			// parse the token.
			mapClaim = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
					.verify(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getClaims();
			
		} catch (TokenExpiredException e) {	
			throw new JWTExceptionCustomize(CodeErrorException.EJ_002);
			
		} catch (Exception e) {
			throw new JWTExceptionCustomize(e);
		}

		if (mapClaim != null && !mapClaim.isEmpty()) {
			
			Claim sub = mapClaim.get(DefinationCommon.SUBJECT);
			Claim role = mapClaim.get(DefinationCommon.ROLE);
			String[] asArray = role.asArray(String.class);
			List<GrantedAuthority> lsAuthorization = AuthorityUtils.createAuthorityList(asArray);
			return new UsernamePasswordAuthenticationToken(sub.asString(), null, lsAuthorization);
		}
		return null;
	}

	public void processRefreshToken(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		String token = req.getHeader(SecurityConstants.HEADER_STRING);
		Map<String, Claim> mapClaim = null;
		
		try {
			
			mapClaim = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET_REFRESH)).build()
					.verify(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getClaims();
		}catch (TokenExpiredException e) {
			throw new JWTExceptionCustomize(CodeErrorException.EJ_003);
		}
		catch (Exception e) {
			throw new JWTExceptionCustomize(e);
		}
		
		// get claim 
		if(mapClaim != null && !mapClaim.isEmpty()) {

			String iduser = mapClaim.get(DefinationCommon.SUBJECT).asString();
			String accessToken = JwtUtilsCustomize.createAccessTokenByIdUser(iduser);			
			res.setStatus(HttpStatus.CREATED.value());
			Map<String,String> dataResponse = Collections.singletonMap("accesstoken", accessToken);
			res.getWriter().write(ObjectJsonUtils.convertObjectToJson(dataResponse));
			res.flushBuffer();
		}
	}
	
}