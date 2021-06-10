package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;


/**
 * JWTExceptionCustomize is class handle error or exception of Json Web Token
 * @author Peter
 *
 */
public class JWTExceptionCustomize extends ExceptionAppCustomizeAstract{

	private static final long serialVersionUID = -139181243148721104L;

	/**
	 * Constructor of JWTExceptionCustomize from {@link CodeErrorException} 
	 * @param CodeErrorException e
	 */
	public JWTExceptionCustomize(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException=e.getMessageError();
		this.codeErrorException = e;
    }

	/**
	 * Constructor of JWTExceptionCustomize from {@link Throwable} 
	 * @param Throwable cause
	 */
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
