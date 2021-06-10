package com.minahotel.sourcebackend.enums;

/**
 * EnumProduction is enum of table Production
 * @author Peter
 *
 */
public enum EnumProduction {

	IDPRODUCTION("idproduction"),
	NAMEPRODUCT("nameproduct"),
	EXTENTION("extention"),
	PRODUCTRATES("productrates"),
	TABLENAME("production");
	
	private String name;
	
	private EnumProduction(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
