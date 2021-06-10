package com.minahotel.sourcebackend.enums;

/**
 * EnumTypeServices is enum of table TypeServices
 * @author Peter
 *
 */
public enum EnumTypeServices {

	CHECKOUTDAMAGED("Checkoutdamaged"),
	MOTOBIKE("MotoBike"),
	DRINKANDFOOD("Drinkandfood"),
	CLEANROOM("CleanRoom");
	
	private String name;
	
	private EnumTypeServices(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
