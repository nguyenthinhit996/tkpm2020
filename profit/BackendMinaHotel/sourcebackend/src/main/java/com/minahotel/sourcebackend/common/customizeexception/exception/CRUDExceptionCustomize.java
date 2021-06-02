package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;

public class CRUDExceptionCustomize extends ExceptionAppCustomizeAstract {
	
	private static final long serialVersionUID = -9141441147183002322L;
	
	
	public CRUDExceptionCustomize(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException=e.getMessageError();
		this.codeErrorException = e;
    }
	
	public CRUDExceptionCustomize(Throwable cause) {
		super(cause);
        this.messageRealException = cause.getMessage();
        this.codeErrorException = CodeErrorException.ES_001;
    }
	
	@Override
	public String getInstanceOfType() {
		return ExceptionAppCustomizeInterFace.CRUDExceptionCustomize;
	}
	
}
