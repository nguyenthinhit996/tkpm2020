package com.minahotel.sourcebackend.enums;

/**
 * EnumTicketcheckoutroom is enum of table Ticketcheckoutroom
 * @author Peter
 *
 */
public enum EnumTicketcheckoutroom {

	
	IDTICKETCHECKOUTROOM("idticketcheckoutroom"),
	IDTICKETBOOKING("idticketbooking"),
	TIMEENDRENT("timeendrent"),
	IDSTAFFRECEPTIONSUPPORT("idstaffreceptionsupport"),
	NUMBERROOMRENT("numberroomrent"),
	SUMARYRATESANDSERVICES("sumaryratesandservices"),
	TABLENAME("ticketcheckoutroom"),
	CLEAN("Clean"),
	OFF("Off");
	
	private String name;
	
	private EnumTicketcheckoutroom(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
