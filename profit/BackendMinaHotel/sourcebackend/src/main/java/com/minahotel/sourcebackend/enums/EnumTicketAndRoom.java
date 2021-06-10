package com.minahotel.sourcebackend.enums;

/**
 * EnumTicketAndRoom is enum of table TicketAndRoom
 * @author Peter
 *
 */
public enum EnumTicketAndRoom {

	CLEAN("Clean"),
	ON("On"),
	OFF("Off");
	
	private String name;
	
	private EnumTicketAndRoom(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
