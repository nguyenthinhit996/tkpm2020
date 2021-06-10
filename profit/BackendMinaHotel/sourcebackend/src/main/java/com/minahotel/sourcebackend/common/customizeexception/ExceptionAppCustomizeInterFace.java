package com.minahotel.sourcebackend.common.customizeexception;

/**
 * ExceptionAppCustomizeInterFace is interface of exception customize
 * @author Peter
 *
 */
public interface ExceptionAppCustomizeInterFace {
	
	/**
	 * BusinessException class represent logic error of application
	 */
	public static final String BusinessException = "BussinessException";
	
	/**
	 * CRUDExceptionCustomize class represent select, create, update, delete exception
	 */
	public static final String CRUDExceptionCustomize = "CRUDExceptionCustomize";
	
	/**
	 * JWTExceptionCustomize class represent Json Web Token exception
	 */
	public static final String JWTExceptionCustomize = "JWTExceptionCustomize";
	
	/**
	 * NotFoundItemException class represent not found item or resource not found
	 */
	public static final String NotFoundItemException = "NotFoundItemException";
	
	
	/**
	 * Type of exception in interface {@link ExceptionAppCustomizeInterFace}
	 * @return String 
	 */
	public String getInstanceOfType();
}
