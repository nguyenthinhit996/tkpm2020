package com.minahotel.sourcebackend.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.minahotel.sourcebackend.security.SecurityConstants;


/**
 * LogoutHandlerCustomize is class to hanle if use access path {@link SecurityConstants.LOG_OUT_URL}
 * @author Peter
 *
 */
public class LogoutHandlerCustomize implements LogoutHandler{

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// delete user login
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}
