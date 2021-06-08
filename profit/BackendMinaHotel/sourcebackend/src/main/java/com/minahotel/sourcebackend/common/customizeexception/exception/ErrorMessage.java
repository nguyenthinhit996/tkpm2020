package com.minahotel.sourcebackend.common.customizeexception.exception;

import java.time.LocalDate;

public class ErrorMessage {

	private String time;
	
	private String content_error;
	
	private String code_error;
	
	private String help;

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return the content_error
	 */
	public String getContent_error() {
		return content_error;
	}

	/**
	 * @param content_error the content_error to set
	 */
	public void setContent_error(String content_error) {
		this.content_error = content_error;
	}

	/**
	 * @return the code_error
	 */
	public String getCode_error() {
		return code_error;
	}

	/**
	 * @param code_error the code_error to set
	 */
	public void setCode_error(String code_error) {
		this.code_error = code_error;
	}

	/**
	 * @return the help
	 */
	public String getHelp() {
		return help;
	}

	/**
	 * @param help the help to set
	 */
	public void setHelp(String help) {
		this.help = help;
	}

	@Override
	public String toString() {
		return "ErrorMessage [time=" + time + ", content_error=" + content_error + ", code_error=" + code_error
				+ ", help=" + help + "]";
	}

	public ErrorMessage(String time, String content_error, String code_error, String help) {
		super();
		this.time = time;
		this.content_error = content_error;
		this.code_error = code_error;
		this.help = help;
	}

	public ErrorMessage() {
		super();
	}
	
	
}
