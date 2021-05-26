package com.minahotel.sourcebackend.common.customizeexception;

public class JWTExceptionCustomize extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1255395482832894671L;
	
	private String messageRealException;
	private CodeErrorException codeErrorException;
	
	
	public CodeErrorException getCodeErrorException() {
		return codeErrorException;
	}

	public void setCodeErrorException(CodeErrorException codeErrorException) {
		this.codeErrorException = codeErrorException;
	}

	public String getMessageRealException() {
		return messageRealException;
	}

	public void setMessageRealException(String messageRealException) {
		this.messageRealException = messageRealException;
	}
	
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
}
