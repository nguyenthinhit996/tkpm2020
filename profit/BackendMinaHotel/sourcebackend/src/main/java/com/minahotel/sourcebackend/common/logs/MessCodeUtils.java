package com.minahotel.sourcebackend.common.logs;

import java.util.Arrays;
import java.util.List;

public class MessCodeUtils {

	public static String TransferMessCode(Message messcode) {
		String strOrigin = messcode.getString();
		return strOrigin;
	}

	public static String TransferMessCode(Message messcode, String... arg) {
		String strOrigin = messcode.getString();
		List<String> argumentList = Arrays.asList(arg);
		for (var i = 0; i < argumentList.size(); i++) {
			strOrigin = strOrigin.replace(String.valueOf(i), argumentList.get(i));
		}
		return strOrigin;
	}

	public static String TransferMessCode(Message messcode, Message... arg) {
		String strOrigin = messcode.getString();
		List<Message> argumentList = Arrays.asList(arg);
		for (var i = 0; i < argumentList.size(); i++) {
			strOrigin = strOrigin.replace(String.valueOf(i), argumentList.get(i).getString());
		}
		return strOrigin;
	}

}
