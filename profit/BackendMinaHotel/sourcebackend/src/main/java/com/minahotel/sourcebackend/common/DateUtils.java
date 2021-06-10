package com.minahotel.sourcebackend.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;

/**
 * DateUtils is class util to convert Date to String or reverse
 * @author Peter
 *
 */
public class DateUtils {

	public static final String PATTER_DATE_STANDARD ="yyyy-mm-dd";
	
	/**
	 * Convert String to LocalDate
	 * @param String data
	 * @param String ...pattern
	 * @return
	 */
	public static LocalDate convertStringToLocalDateTime(String data, String ...pattern) {
		try {
			DateTimeFormatter formatter = null;
			if(pattern == null  || pattern.length == 0) {
				formatter = DateTimeFormatter.ofPattern(PATTER_DATE_STANDARD);
			}
			else{
				formatter = DateTimeFormatter.ofPattern(pattern[0]);
			}
			return LocalDate.parse(data, formatter);
		}catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
