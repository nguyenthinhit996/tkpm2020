package com.minahotel.sourcebackend.enums;

/**
 * EnumDetailservicesStatus is enum of table DetailservicesStatus
 * @author Peter
 *
 */
public enum EnumDetailservicesStatus {

	PREPARE("Prepare"),
	SHIPPING("Shipping"),
	TODO("ToDo"),
	DONE("Done"),
	CANCEL("Cancel");
	
	private String name;
	
	private EnumDetailservicesStatus(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
