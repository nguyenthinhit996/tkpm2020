package com.minahotel.sourcebackend.security.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.minahotel.sourcebackend.security.SecurityConstants;

public class JWTLogoutFilterFilter extends LogoutFilter {

	public JWTLogoutFilterFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler[] handlers) {
		super(logoutSuccessHandler, handlers);
		setFilterProcessesUrl(SecurityConstants.LOG_OUT_URL);
	}
	
	
}
