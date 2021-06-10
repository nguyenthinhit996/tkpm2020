package com.minahotel.sourcebackend.enums;

/**
 * EnumDatework is enum of table Datework
 * @author Peter
 *
 */
public enum EnumDatework {

	IDDATEWORK("iddatework"),
	LISTUSERWORKFULLDAY("listuserworkfullday"),
	LISTUSERHALFDAY("listuserhalfday"),
	REGULATION("regulation"),
	TABLENAME("datework");
	
	private String name;
	
	private EnumDatework(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
