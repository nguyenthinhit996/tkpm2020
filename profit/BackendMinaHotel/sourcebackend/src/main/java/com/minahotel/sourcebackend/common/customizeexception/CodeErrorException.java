package com.minahotel.sourcebackend.common.customizeexception;

import org.apache.logging.log4j.util.Strings;


/**
 * 
 * CodeErrorException is enum contain code and value
 * @author Peter
 *
 */
public enum CodeErrorException {
	
	// EJ error jwt
	// ES error system run app
	EJ_001("EJ_001", "No param object when login"), 
	EJ_002("EJ_002", "JWT EXpried Error, Access Token Expired, Must refresh token"), 
	EJ_003("EJ_003", "JWT EXpried Error, Refresh Token Expired, Must login "), 
	EJ_004("EJ_004", "Forbiden access, You not access token header or not permission"), 

	ES_001("ES_001", "Occur Error System"),
	ES_002("ES_002", "Error Business App"),
	ES_003("ES_003", "Error Database connection or sql invaild"),
	ES_004("ES_004","Error Occur Not Change Pass"),
	
	//CRUDExceptionCustomize
	CRUD_002("CRUD_002", "An error occured, Not insert data"),
	CRUD_001("CRUD_001", "An error occured, Not create data"),
	CRUD_003("CRUD_003", "An error occured, Not update data"),
	CRUD_004("CRUD_004", "An error occured, Not delete data"),
	
	// not foud exception 
	EN_001("EN_001", "Not found data in System"),
	
	// error resource not found
	ER_001("ER_001", "Resouce Not Found, path error"),
	
	// error Change pass
	EA_001("EA_001", "Password Old invalid , please input again");
	

	/**
	 * Constructor of CodeErrorException from codeError and messageError
	 * @param codeError
	 * @param messageError
	 */
	private CodeErrorException(String codeError,String messageError) {
		this.messageError = messageError;
		this.codeError = codeError;
	}

	/**
	 * messageError content of error
	 */
	private String messageError;
	
	/**
	 * codeError code represent of error 
	 */
	private String codeError;

	
	/**
	 * 
	 * @return String content of messageError
	 */
	public String getMessageError() {
		return messageError;
	}

	/**
	 * 
	 * @return String code of represent error
	 */
	public String getCodeError() {
		return codeError;
	}

	/**
	 * 
	 * @param String codeError
	 */
	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	/**
	 * 
	 * @param String messageError
	 */
	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	/**
	 * 
	 * @param CodeErrorException code
	 * @return String code of errorCode
	 */
	String getCodeError(CodeErrorException code) {
		for (CodeErrorException in : values()) {
			if (in.getMessageError().equals(code.getMessageError())) {
				return code.getCodeError();
			}
		}
		return Strings.EMPTY;
	}
}
