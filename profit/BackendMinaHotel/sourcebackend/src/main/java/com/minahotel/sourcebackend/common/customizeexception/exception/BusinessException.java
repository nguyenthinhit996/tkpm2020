package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;

public class BusinessException extends ExceptionAppCustomizeAstract{

	private static final long serialVersionUID = 4881833890038775836L;

	public BusinessException(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException=e.getMessageError();
		this.codeErrorException = e;
    }
	
	public BusinessException(Throwable cause) {
		super(cause);
//        this.messageRealException = cause.getMessage();
		
		 this.messageRealException = CodeErrorException.ES_002.getMessageError();
        this.codeErrorException = CodeErrorException.ES_002;
    }
	
	@Override
	public String getInstanceOfType() {
		return ExceptionAppCustomizeInterFace.BusinessException;
	}
}
