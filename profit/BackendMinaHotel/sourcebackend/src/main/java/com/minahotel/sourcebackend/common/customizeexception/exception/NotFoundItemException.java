package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;


/**
 * 
 * NotFoundItemException is class handle error or exception relative not found from resource or not found item.
 * @author Peter
 *
 */
public class NotFoundItemException extends ExceptionAppCustomizeAstract{

	private static final long serialVersionUID = 4348852652076716506L;

	/**
	 * Constructor NotFoundItemException from a {@link CodeErrorException}
	 * @param CodeErrorException e
	 */
	public NotFoundItemException(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException = e.getMessageError();
		this.codeErrorException = e;
	}

	/**
	 * Constructor NotFoundItemException from a {@link Throwable}
	 * @param cause
	 */
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
