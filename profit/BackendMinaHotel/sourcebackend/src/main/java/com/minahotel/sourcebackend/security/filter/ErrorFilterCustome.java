package com.minahotel.sourcebackend.security.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.JWTExceptionCustomize;

public class ErrorFilterCustome extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
		} catch (JWTExceptionCustomize ex) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			Map<String, String> mapError = Collections.singletonMap(ex.getCodeErrorException().getCodeError(),
					ex.getMessageRealException());
			response.getWriter().write(ObjectJsonUtils.convertObjectToJson(mapError));

		} catch (Exception e) { // default handled all exception
			response.setStatus(HttpStatus.FORBIDDEN.value());
			String mess = e.getMessage() != null ? e.getMessage() : CodeErrorException.ES_001.getMessageError();
			Map<String, String> mapError = Collections.singletonMap(CodeErrorException.ES_001.getCodeError(), mess);
			response.getWriter().write(ObjectJsonUtils.convertObjectToJson(mapError));
		}
	}

}
