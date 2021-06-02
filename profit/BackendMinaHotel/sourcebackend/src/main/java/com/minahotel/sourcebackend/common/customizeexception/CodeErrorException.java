package com.minahotel.sourcebackend.common.customizeexception;

import org.apache.logging.log4j.util.Strings;

public enum CodeErrorException {
	
	// EJ error jwt
	// ES error system run app
	EJ_001("EJ_001", "No param object when login"), 
	EJ_002("EJ_002", "JWT EXpried Error, Access Token Expired, Must refresh token"), 
	EJ_003("EJ_003", "JWT EXpried Error, Refresh Token Expired, Must login "), 
	EJ_004("EJ_004", "?"), 
	ES_001("ES_001", "Error System"),
	ES_002("ES_002", "Error Business App"),
	ES_003("ES_003", "Error Database connection or sql invaild"),
	
	//CRUDExceptionCustomize
	CRUD_001("CRUD_001", "An error occured, Not create data"),
	CRUD_002("CRUD_002", "An error occured, Not insert data"),
	CRUD_003("CRUD_003", "An error occured, Not update data"),
	CRUD_004("CRUD_004", "An error occured, Not delete data"),
	
	// not foud exception 
	EN_001("EN_001", "Not found data in System");

	private CodeErrorException(String codeError,String messageError) {
		this.messageError = messageError;
		this.codeError = codeError;
	}

	private String messageError;
	private String codeError;

	public String getMessageError() {
		return messageError;
	}

	public String getCodeError() {
		return codeError;
	}

	public void setCodeError(String codeError) {
		this.codeError = codeError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	String getCodeError(CodeErrorException code) {
		for (CodeErrorException in : values()) {
			if (in.getMessageError().equals(code.getMessageError())) {
				return code.getCodeError();
			}
		}
		return Strings.EMPTY;
	}

}
