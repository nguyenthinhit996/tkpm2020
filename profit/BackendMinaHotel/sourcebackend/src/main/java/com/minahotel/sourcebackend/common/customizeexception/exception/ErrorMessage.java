package com.minahotel.sourcebackend.common.customizeexception.exception;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ErrorMessage is class represent class return client if process occur error or exception
 * @author Peter
 *
 */
@Schema(name = "ErrorMessage")
public class ErrorMessage {

	/**
	 * time current appearance error  
	 */
	@Schema(description = "time occur error"
		,example = "2021-12-12"
	)
	private String time;
	
	/**
	 * content_error content of error, it can real error or self-defination error
	 */
	private String content_error;
	
	/**
	 * code_error code of error in a {@link CodeErrorException}
	 */
	private String code_error;
	
	/**
	 * help resolve error
	 */
	private String help;

	/**
	 * @return the time current appearance
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time 
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

	/**
	 * Constructor full field of class
	 * @param time
	 * @param content_error
	 * @param code_error
	 * @param help
	 */
	public ErrorMessage(String time, String content_error, String code_error, String help) {
		super();
		this.time = time;
		this.content_error = content_error;
		this.code_error = code_error;
		this.help = help;
	}

	/**
	 * Constructor not argument
	 */
	public ErrorMessage() {
		super();
	}
}
