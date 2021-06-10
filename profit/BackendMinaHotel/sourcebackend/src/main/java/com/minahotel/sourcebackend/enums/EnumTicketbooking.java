package com.minahotel.sourcebackend.enums;

/**
 * EnumTicketbooking is enum of table Ticketbooking
 * @author Peter
 *
 */
public enum EnumTicketbooking {

	
	IDTICKETBOOKING("idticketbooking"),
	IDUSERRENTROOM("iduserrentroom"),
	USERNAMERENTROOM("usernamerentroom"),
	TIMESTAMPRENT("timestamprent"),
	IDSTAFFRECEPTION("idstaffreception"),
	NUMBERROOM("numberroom"),
	STATUS("status"),
	TABLENAME("ticketbooking");
	
	private String value;
	
	private EnumTicketbooking(String name) {
		 this.value = name;
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
