package com.minahotel.sourcebackend.common.customizeexception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.minahotel.sourcebackend.common.DefinationCommon;
import com.minahotel.sourcebackend.common.ObjectJsonUtils;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;

/**
 * RestResponseEntityExceptionHandler class handle exception from ExceptionAppCustomizeInterFace in player Controller
 * @author Peter
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	/**
	 * catch CRUDExceptionCustomize and handle set attribute mess and status code
	 * to get in filter ErrorFilterCustome for return an {@link ErrorMessage} client
	 * @param CRUDExceptionCustomize ex
	 * @param WebRequest request
	 * @return ResponseStatusException 
	 */
	@ExceptionHandler(CRUDExceptionCustomize.class)
	public ResponseStatusException handleCRUDExceptionCustomize(CRUDExceptionCustomize ex, WebRequest request) { 
		String error =  ObjectJsonUtils.getStringFromErrorCode(ex);
		request.setAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER, error,0);
		request.setAttribute(DefinationCommon.HTTP_CODE_EXCEPTION_FROM_CONTROLLER, HttpStatus.NOT_IMPLEMENTED.value(), 0);
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED,error);
	}
	
	/**
	 * catch NotFoundItemException and handle set attribute mess and status code into WebRequest 
	 * to get in filter ErrorFilterCustome for return an {@link ErrorMessage} client
	 * @param NotFoundItemException ex
	 * @param WebRequest request
	 * @return ResponseStatusException
	 */
	@ExceptionHandler(NotFoundItemException.class)
	public ResponseStatusException handleNotFoundItemException(NotFoundItemException ex, WebRequest request) { 
		String error =  ObjectJsonUtils.getStringFromErrorCode(ex);
		request.setAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER, error,0);
		request.setAttribute(DefinationCommon.HTTP_CODE_EXCEPTION_FROM_CONTROLLER, HttpStatus.NOT_FOUND.value(),0);
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,error);
	}
	
	/**
	 * catch BusinessException and handle set attribute mess and status code into WebRequest 
	 * to get in filter ErrorFilterCustome for return an {@link ErrorMessage} client
	 * @param BusinessException ex
	 * @param WebRequest request
	 * @return ResponseStatusException
	 */
	@ExceptionHandler(BusinessException.class)
	public ResponseStatusException handleBusinessException(BusinessException ex, WebRequest request) { 
		String error =  ObjectJsonUtils.getStringFromErrorCode(ex);
		request.setAttribute(DefinationCommon.EXCEPTION_FROM_CONTROLLER, error, 0);
		request.setAttribute(DefinationCommon.HTTP_CODE_EXCEPTION_FROM_CONTROLLER, HttpStatus.NOT_IMPLEMENTED.value(),0);
		throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, error);
	}
}
