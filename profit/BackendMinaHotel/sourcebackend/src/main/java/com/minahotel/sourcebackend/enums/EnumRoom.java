package com.minahotel.sourcebackend.enums;

/**
 * EnumRoom is enum of table Room
 * @author Peter
 *
 */
public enum EnumRoom {

	idroom("idroom"),
	status("status"),
	nametyperoom("nametyperoom"),
	TABLENAME("room");
	
	private String name;
	
	private EnumRoom(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
