package com.minahotel.sourcebackend.security.entrypoints;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

/**
 * Http403ForbiddenEntryPointCustom is class custom Http403ForbiddenEntryPoint 
 * EntryPoint for annonymous login into authenticated() catch it
 * ex token on not header or object Authentication is null
 * @author Peter
 *
 */
public class Http403ForbiddenEntryPointCustom extends Http403ForbiddenEntryPoint{

	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2)
			throws IOException {
		System.out.println("Use Http403ForbiddenEntryPointCustom");
		response.setStatus(HttpStatus.FORBIDDEN.value());
	}
	
}
