package com.minahotel.sourcebackend.security.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.exception.JWTExceptionCustomize;
import com.minahotel.sourcebackend.common.logs.MessCodeUtils;
import com.minahotel.sourcebackend.common.logs.Message;
import com.minahotel.sourcebackend.pojo.LoginPojo;
import com.minahotel.sourcebackend.pojo.UserCustomize;
import com.minahotel.sourcebackend.security.SecurityConstants;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private static Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {	
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl(SecurityConstants.LOG_IN_URL); 
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	
        	LOG.info(MessCodeUtils.TransferMessCode(Message.IN_001 , this.getClass().getName()));
        	
        	LoginPojo creds = new ObjectMapper().readValue(req.getInputStream(), LoginPojo.class);
        	
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getIduser(),
                            creds.getPassword(),
                            new ArrayList<>()));
            
        } catch (IOException e) {
        	e.printStackTrace();
        	LOG.error(e.getLocalizedMessage());
            throw new JWTExceptionCustomize(e);
        }catch(Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getLocalizedMessage());
            throw new JWTExceptionCustomize(e);
        }
        
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
    	 
    	LoginPojo loginPojo = new LoginPojo();	
    	UserCustomize user = (UserCustomize) auth.getPrincipal();
    	
    	
    	List<String> lsAuthorization =List.copyOf(AuthorityUtils.authorityListToSet(user.getAuthorities()));
    	
        String accessToken = JWT.create()
                .withSubject(user.getIdStaff())
                .withClaim(DefinationCommon.ROLE,lsAuthorization)
                .withClaim(DefinationCommon.AUTHENTICATED,Boolean.TRUE)
                .withClaim(DefinationCommon.FULLNAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_ACCESSTOKEN))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        
        String refreshToken = JWT.create()
                .withSubject(user.getIdStaff())
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME_REFERSHTOKEN))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET_REFRESH.getBytes()));
    	
    	loginPojo.setIduser(user.getIdStaff());
    	loginPojo.setPassword(DefinationCommon.STRING_INITIAL);
    	loginPojo.setAccessToken(accessToken);
    	loginPojo.setRefreshToken(refreshToken);
    	loginPojo.setRole(user.parseCollectionGrantedAuthorizationToString());
    	loginPojo.setFullName(user.getUsername());
    	loginPojo.setAuthenticated(Boolean.TRUE);

    	//res.getWriter().write(ObjectJsonUtils.convertObjectToJson(loginPojo));
    	//res.getWriter().flush();
    	PrintWriter printWriter = res.getWriter();
    	printWriter.write(ObjectJsonUtils.convertObjectToJson(loginPojo));
    	printWriter.flush();
    	printWriter.close();
    }
    

}