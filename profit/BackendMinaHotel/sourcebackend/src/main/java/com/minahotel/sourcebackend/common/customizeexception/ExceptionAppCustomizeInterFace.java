package com.minahotel.sourcebackend.common.customizeexception;

public interface ExceptionAppCustomizeInterFace {
	
	public static final String BusinessException = "BussinessException";
	
	public static final String CRUDExceptionCustomize = "CRUDExceptionCustomize";
	
	public static final String JWTExceptionCustomize = "JWTExceptionCustomize";
	
	public static final String NotFoundItemException = "NotFoundItemException";
	
	public String getInstanceOfType();
}
