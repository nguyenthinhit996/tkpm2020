package com.minahotel.sourcebackend.enums;

public enum EnumTypeofroom {

	
	NAMETYPEOFROOM("nametypeofroom"),
	ROOMRATESHOURS("roomrateshours"),
	ROOMRATESDATES("roomratesdates"),
	NUMBERINROOM("numberinroom"),
	ROOMRATESCHARGE("roomratescharge"),
	TABLENAME("typeofroom");
	
	private String name;
	
	private EnumTypeofroom(String name) {
		 this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
