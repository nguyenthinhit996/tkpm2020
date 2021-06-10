package com.minahotel.sourcebackend.enums;

/**
 * EnumDetailservices is enum of table Detailservices
 * @author Peter
 *
 */
public enum EnumDetailservices {

	IDTICKETBOOKING("idticketbooking"),
	IDPRODUCT("idproduct"),
	IDSTAFFSERVICESREPO("idstaffservicesrepo"),
	AMOUNT("amount"),
	STARTRENT("startrent"),
	ENDRENT("endrent"),
	STATUS("status"),
	SUMARYSERVICES("sumaryservices"),
	TABLENAME("detailservices");
	
	private String name;
	
	private EnumDetailservices(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
