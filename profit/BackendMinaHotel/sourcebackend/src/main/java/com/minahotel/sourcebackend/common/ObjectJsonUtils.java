package com.minahotel.sourcebackend.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
