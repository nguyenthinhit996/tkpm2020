package com.minahotel.sourcebackend.common.customizeexception.exception;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeInterFace;


/**
 * CRUDExceptionCustomize class handle relative action select , create , update, delete on entities.
 * @author Peter
 *
 */
public class CRUDExceptionCustomize extends ExceptionAppCustomizeAstract {
	
	private static final long serialVersionUID = -9141441147183002322L;
	
	/**
	 * Constructor CRUDExceptionCustomize class from an CodeErrorException customize
	 * @param CodeErrorException e is a error code information {@link CodeErrorException} start with code CRUD_00X ...
	 */
	public CRUDExceptionCustomize(CodeErrorException e) {
		super(e.getMessageError());
		this.messageRealException=e.getMessageError();
		this.codeErrorException = e;
    }
	
	/**
	 * Constructor CRUDExceptionCustomize class from Exception system 
	 * @param Throwable cause
	 */
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
