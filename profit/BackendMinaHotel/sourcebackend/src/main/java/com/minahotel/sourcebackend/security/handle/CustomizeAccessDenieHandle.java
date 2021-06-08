package com.minahotel.sourcebackend.security.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomizeAccessDenieHandle implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		 Authentication auth 
         = SecurityContextHolder.getContext().getAuthentication();
       if (auth != null) {
           System.out.println("Use CustomizeAccessDenieHandle");
           response.setStatus(HttpStatus.FORBIDDEN.value());
       }
	}
}
