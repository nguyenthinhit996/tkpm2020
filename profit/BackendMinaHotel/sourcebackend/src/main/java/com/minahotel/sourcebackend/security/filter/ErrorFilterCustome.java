package com.minahotel.sourcebackend.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;
import com.minahotel.sourcebackend.common.customizeexception.exception.ErrorMessage;
import com.minahotel.sourcebackend.common.customizeexception.exception.JWTExceptionCustomize;

public class ErrorFilterCustome extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
		} catch (JWTExceptionCustomize ex) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			String contentError = ObjectJsonUtils.getStringFromErrorCode(ex);
			response.getWriter().write(contentError);
			
		} catch (Exception e) { 
			 
			if( (e.getCause() instanceof  ExceptionAppCustomizeInterFace)) {
				// default handled all exception
				String messgeError = (String) request.getAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER);
				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.getWriter().write(messgeError);
			}else {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				String contentError = ObjectJsonUtils.getStringFromErrorCode(e);
				response.getWriter().write(contentError);
			}
		}
		response.flushBuffer();
		return;
	}

}
