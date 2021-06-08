package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;

public class JWTExceptionCustomize extends ExceptionAppCustomizeAstract{

	 
	private static final long serialVersionUID = -139181243148721104L;

	public JWTExceptionCustomize(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException=e.getMessageError();
		this.codeErrorException = e;
    }

	public JWTExceptionCustomize(Throwable cause) {
		super(cause);
        this.messageRealException = cause.getMessage();
        this.codeErrorException = CodeErrorException.ES_001;
    }
	
	@Override
	public String getInstanceOfType() {
		return ExceptionAppCustomizeInterFace.JWTExceptionCustomize;
	}
}
