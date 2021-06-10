package com.minahotel.sourcebackend.security.filter;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.minahotel.sourcebackend.security.SecurityConstants;

/**
 * To handle logout if user access {@linkplain SecurityConstants.LOG_OUT_URL}
 * To action two things. the first LogoutHandler wil set null for SecurityContextHolder.getContext().setAuthentication(null).
 * the second to if LogoutHandler success it return {@link SecurityConstants.LOG_IN_URL}
 * @author Peter
 *
 */
public class JWTLogoutFilterFilter extends LogoutFilter {

	/**
	 * Constructor JWTLogoutFilterFilter use LogoutSuccessHandler and LogoutHandler to handle logout
	 * @param logoutSuccessHandler
	 * @param handlers
	 */
	public JWTLogoutFilterFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler[] handlers) {
		super(logoutSuccessHandler, handlers);
		setFilterProcessesUrl(SecurityConstants.LOG_OUT_URL);
	}
}
