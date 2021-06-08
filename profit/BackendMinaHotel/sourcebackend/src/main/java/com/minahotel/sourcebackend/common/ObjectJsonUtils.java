package com.minahotel.sourcebackend.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.ExceptionAppCustomizeAstract;
import com.minahotel.sourcebackend.common.customizeexception.exception.ErrorMessage;

public class ObjectJsonUtils {

	public static String convertObjectToJson(Object object) {
		String result = DefinationCommon.STRING_INITIAL;
		if (object == null) {
			return result;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getStringFromErrorCode(Exception ex) {
		
		final String FORMAT_TIME="dd-M-yyyy hh:mm:ss";
		DateTimeFormatter formater = DateTimeFormatter.ofPattern(FORMAT_TIME); 
		String timeCurrent = LocalDateTime.now().format(formater);
		
		ErrorMessage message = new ErrorMessage();
		if(ex instanceof ExceptionAppCustomizeAstract) {	
			ExceptionAppCustomizeAstract exJwt = (ExceptionAppCustomizeAstract) ex;						
			message.setCode_error(exJwt.getCodeErrorException().getCodeError());
			message.setTime(timeCurrent);
			message.setContent_error(exJwt.getMessageRealException());
			message.setHelp("Error from bussiness, contact admin ");
		}else { // Exception remain		
//			String mess = ex.getMessage() != null ? ex.getMessage() : CodeErrorException.ES_001.getMessageError();	
			String mess = CodeErrorException.ES_001.getMessageError();	
			message.setCode_error(CodeErrorException.ES_001.getCodeError());
			message.setTime(timeCurrent);
			message.setContent_error(mess);
			message.setHelp("Error from login application java, contact admin");
		}
		
		return  convertObjectToJson(message);
	}
	
	 
}
