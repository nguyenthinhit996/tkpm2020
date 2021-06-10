package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;

/**
 * BusinessException class handle exception error from application 
 * @author peter
 *
 */
public class BusinessException extends ExceptionAppCustomizeAstract {

	private static final long serialVersionUID = 4881833890038775836L;

	/**
	 * Constructor Exception BusinessException
	 * @param parameterObject an error code information {@link CodeErrorException}
	 */
	public BusinessException(CodeErrorException parameterObject) {
		super(parameterObject.getMessageError());
		this.messageRealException = parameterObject.getMessageError();
		this.codeErrorException = parameterObject;
	}

	/**
	 * Constructor Exception BusinessException from exception system 
	 * @param cause an {@link Throwable}
	 */
	public BusinessException(Throwable cause) {
		super(cause);
		//this.messageRealException = cause.getMessage();
		this.messageRealException = CodeErrorException.ES_002.getMessageError();
		this.codeErrorException = CodeErrorException.ES_002;
	}

	@Override
	public String getInstanceOfType() {
		return ExceptionAppCustomizeInterFace.BusinessException;
	}
}
