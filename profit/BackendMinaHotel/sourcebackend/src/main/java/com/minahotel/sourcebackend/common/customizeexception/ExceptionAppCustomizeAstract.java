package com.minahotel.sourcebackend.common.customizeexception;


/**
 * 
 * ExceptionAppCustomizeAstract is abstract class Customize Exception Applicaion
 * @author Peter
 *
 */
public abstract class ExceptionAppCustomizeAstract extends java.lang.RuntimeException implements ExceptionAppCustomizeInterFace{

	private static final long serialVersionUID = 4660869870079898927L;

	/**
	 * messageRealException is message content error
	 */
	protected String messageRealException;

	/**
	 * codeErrorException is code represent of error
	 */
	protected CodeErrorException codeErrorException;

	/**
	 * Construct ExceptionAppCustomizeAstract has one argument content
	 * @param string
	 */
	public ExceptionAppCustomizeAstract(String string){
		super(string);
	}
	
	/**
	 * Construct ExceptionAppCustomizeAstract has one argument Throwable
	 * @param cause
	 */
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
