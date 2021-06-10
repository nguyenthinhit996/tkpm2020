package com.minahotel.sourcebackend.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.minahotel.sourcebackend.common.customizeexception.exception.JWTExceptionCustomize;
import com.minahotel.sourcebackend.common.logs.MessCodeUtils;
import com.minahotel.sourcebackend.common.logs.Message;
import com.minahotel.sourcebackend.security.JwtUtilsCustomize;
import com.minahotel.sourcebackend.security.SecurityConstants;

/**
 * JWTAuthorizationFilter is class authentication on per request (access resource, refreshToken, ...)
 * except path (SecurityConstants.LOG_IN_URL) only handle in JWTAuthenticationFilter
 * @author Peter
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private static Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

	/**
	 * Constructor JWTAuthorizationFilter is used by secutity config WebSecurity.java
	 * @param authManager
	 */
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	
	/**
	 * Do filter every request to server catch check accesstoken if use want access resouce or
	 * check refresh token if use want refresh new accesstoke because access token expired .
	 * if request not contain token in header, the processing send request to next filter, finally it is caught in ExceptionTranslationFilter.class .
	 * in ExceptionTranslationFilter class i customized Http403ForbiddenEntryPointCustom and CustomizeAccessDenieHandle to only set response.setStatus(HttpStatus.FORBIDDEN.value())
	 * that foribiden is handle in {@link ErrorFilterCustome}
	 * @param HttpServletRequest req
	 * @param HttpServletResponse res
	 * @param FilterChain chain
 	 */
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


	/**
	 * Reads the JWT from the Authorization header, and then uses JWT to validate. 
	 * To verify token and get claims to set UsernamePasswordAuthenticationToken for authentication Manager
	 * @param request
	 * @return UsernamePasswordAuthenticationToken
	 */
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

	
	/**
	 * To handle refresh token that is use provider new accesstoken if acesstoken expired
	 * Write new access token into body response and return client
	 * @param req
	 * @param res
	 * @throws IOException
	 */
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
			Map<String,String> dataResponse = Collections.singletonMap(SecurityConstants.ACCESS_TOKEN, accessToken);
//			res.getWriter().write(ObjectJsonUtils.convertObjectToJson(dataResponse));
//			res.flushBuffer();
			PrintWriter printWriter = res.getWriter();
	    	printWriter.write(ObjectJsonUtils.convertObjectToJson(dataResponse));
	    	printWriter.flush();
	    	printWriter.close();
		}
	}
	
}