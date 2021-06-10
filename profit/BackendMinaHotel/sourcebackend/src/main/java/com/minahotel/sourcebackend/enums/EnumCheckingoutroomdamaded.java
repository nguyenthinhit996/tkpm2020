package com.minahotel.sourcebackend.enums;

/**
 * EnumCheckingoutroomdamaded is enum of table Checkingoutroomdamaded
 * @author Peter
 *
 */
public enum EnumCheckingoutroomdamaded {
	
	IDCHECKINGOUTROOMDAMADED("idcheckingoutroomdamaded"),
	IDCHECKOUTROOM("idcheckoutroom"),
	LISTPRODUCTDAMADED("listproductdamaded"),
	SUMARYINDEMNIFY("sumaryindemnify"),
	IDSTAFFCHECKING("idstaffchecking"),
	TABLENAME("checkingoutroomdamaded");
	
	private String name;
	
	private EnumCheckingoutroomdamaded(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
