package com.minahotel.sourcebackend.common.customizeexception;

public abstract class ExceptionAppCustomizeAstract extends java.lang.RuntimeException implements ExceptionAppCustomizeInterFace{

	private static final long serialVersionUID = 4660869870079898927L;

	protected String messageRealException;

	protected CodeErrorException codeErrorException;

	public ExceptionAppCustomizeAstract(String string){
		super(string);
	}
	
	public ExceptionAppCustomizeAstract(Throwable cause) {
		super(cause);
	}

	/**
	 * @return the messageRealException
	 */
	public String getMessageRealException() {
		return messageRealException;
	}

	/**
	 * @param messageRealException the messageRealException to set
	 */
	public void setMessageRealException(String messageRealException) {
		this.messageRealException = messageRealException;
	}

	/**
	 * @return the codeErrorException
	 */
	public CodeErrorException getCodeErrorException() {
		return codeErrorException;
	}

	/**
	 * @param codeErrorException the codeErrorException to set
	 */
	public void setCodeErrorException(CodeErrorException codeErrorException) {
		this.codeErrorException = codeErrorException;
	}

}
