package com.minahotel.sourcebackend.enums;

/**
 * EnumCommon is EnumCommon
 * @author Peter
 *
 */
public enum EnumCommon {

	ON("On"),
	CLEAN("Clean"),
	OFF("Off");
	
	private String value;
	
	EnumCommon(String status){
		this.value=status;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
}
