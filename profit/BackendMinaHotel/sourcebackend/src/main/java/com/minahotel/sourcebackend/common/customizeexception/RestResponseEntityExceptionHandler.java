package com.minahotel.sourcebackend.common.customizeexception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.ErrorMessage;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CRUDExceptionCustomize.class)
	public ResponseStatusException handleCRUDExceptionCustomize(CRUDExceptionCustomize ex, WebRequest request) { 
		String error =  ObjectJsonUtils.getStringFromErrorCode(ex);
		request.setAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER, error, 0);
		throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,error);
	}
	
	@ExceptionHandler(NotFoundItemException.class)
	public ResponseStatusException handleNotFoundItemException(NotFoundItemException ex,WebRequest request) { 
		String error =  ObjectJsonUtils.getStringFromErrorCode(ex);
		request.setAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER, error,0);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,error);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseStatusException handleBusinessException(BusinessException ex, WebRequest request) { 
		String error =  ObjectJsonUtils.getStringFromErrorCode(ex);
		request.setAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER, error, 0);
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, error);
	}
}
