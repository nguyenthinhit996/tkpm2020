package com.minahotel.sourcebackend.common.logs;

/**
 * Message is enum information log
 * @author Peter
 *
 */
public enum Message {

	IN_001("Start PROCESS [0]"),
	IN_002("End PROCESS [0]"),
	IN_003("Not token in header"),
	IN_004("Get Resource");
	
	Message(String mess){
		this.mess= mess;
	}
	
	private String mess;

	public String getString() {
		return mess;
	}

	public void setString(String mess) {
		this.mess = mess;
	}
}
