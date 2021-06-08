package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;

public class NotFoundItemException extends ExceptionAppCustomizeAstract{

	private static final long serialVersionUID = 4348852652076716506L;

	 
	public NotFoundItemException(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException = e.getMessageError();
		this.codeErrorException = e;
	}

	public NotFoundItemException(Throwable cause) {
		super(cause);
		this.messageRealException = cause.getMessage();
		this.codeErrorException = CodeErrorException.EN_001;
	}
	
	@Override
	public String getInstanceOfType() {
		return ExceptionAppCustomizeInterFace.NotFoundItemException;
	}
}
