package com.minahotel.sourcebackend.common.logs;

import java.util.Arrays;
import java.util.List;

/**
 * MessCodeUtils is class to convert a {@linkplain Message} to String output log 
 * @author Peter
 *
 */
public class MessCodeUtils {

	/**
	 * Convert Message to String 
	 * @param Message messcode
	 * @return String 
	 */
	public static String TransferMessCode(Message messcode) {
		String strOrigin = messcode.getString();
		return strOrigin;
	}

	/**
	 * Convert Message and argument to String  
	 * @param Message messcode
	 * @param String... arg array argument to message log
	 * @return content of Message
	 */
	public static String TransferMessCode(Message messcode, String... arg) {
		String strOrigin = messcode.getString();
		List<String> argumentList = Arrays.asList(arg);
		for (var i = 0; i < argumentList.size(); i++) {
			strOrigin = strOrigin.replace(String.valueOf(i), argumentList.get(i));
		}
		return strOrigin;
	}

	/**
	 * Merge multi Message then convert to String
	 * @param Message messcode
	 * @param Message... arg
	 * @return content of Message 
	 */
	public static String TransferMessCode(Message messcode, Message... arg) {
		String strOrigin = messcode.getString();
		List<Message> argumentList = Arrays.asList(arg);
		for (var i = 0; i < argumentList.size(); i++) {
			strOrigin = strOrigin.replace(String.valueOf(i), argumentList.get(i).getString());
		}
		return strOrigin;
	}
}
