package com.minahotel.sourcebackend.security.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.JWTExceptionCustomize;

/**
 * ErrorFilterCustome is class final handle exception from application and parse exception to {@link ErrorMessage}
 * return client Code and MessageError 
 * @author Peter
 *
 */
public class ErrorFilterCustome extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		try {
			filterChain.doFilter(request, response);
			
			// 404 resource not found chua lam duoc
			if(response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
				throw new BusinessException(CodeErrorException.ER_001);
			}
			// 403 fobiden header not token ok
			else if(response.getStatus() == HttpServletResponse.SC_FORBIDDEN) {
				throw new BusinessException(CodeErrorException.EJ_004);
			}
		} catch (JWTExceptionCustomize ex) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			String contentError = ObjectJsonUtils.getStringFromErrorCode(ex);
			//response.getWriter().write(contentError);
			PrintWriter printWriter = response.getWriter();
	    	printWriter.write(contentError);
	    	printWriter.flush();
	    	printWriter.close();
		} catch (Exception e) { 
			// error in controller return
			String contentError = "";
			if( (e.getCause() instanceof  ExceptionAppCustomizeInterFace)) {
				
				// default handled all exception
				Integer codeError = HttpStatus.FORBIDDEN.value();
				contentError = ObjectJsonUtils.getStringFromErrorCode(e);

				// exception from controler return here (RestResponseEntityExceptionHandler)
				codeError = (Integer) request.getAttribute(DefinationCommon.HTTP_CODE_EXCEPTION_FROM_CONTROLLER);
				contentError = (String) request.getAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER);
				//response.getWriter().write(messgeError);
				response.setStatus(codeError);
			}else {
				response.setStatus(HttpStatus.FORBIDDEN.value());
				contentError = ObjectJsonUtils.getStringFromErrorCode(e);
			}
			//response.getWriter().write(contentError);
			PrintWriter printWriter = response.getWriter();
	    	printWriter.write(contentError);
	    	printWriter.flush();
	    	printWriter.close();
		}finally {
			response.flushBuffer();
		}
	}
}
