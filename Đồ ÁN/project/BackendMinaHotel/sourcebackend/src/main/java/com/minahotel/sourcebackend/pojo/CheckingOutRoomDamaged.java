package com.minahotel.sourcebackend.pojo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
 

@Entity
public class CheckingOutRoomDamaged extends MinaHoTelPojo{
	
	@Id
	private int idcheckingoutroomdamaded;
	private String idcheckoutroom;
	private String listproductdamaded;
	private String idstaffchecking;
	private BigDecimal sumaryindemnify;
	
	public CheckingOutRoomDamaged() {
		super();
	}
	
	public CheckingOutRoomDamaged(int idcheckingoutroomdamaded, String idcheckoutroom, String listproductdamaded,
			String idstaffchecking, BigDecimal sumaryindemnify) {
		super();
		this.idcheckingoutroomdamaded = idcheckingoutroomdamaded;
		this.idcheckoutroom = idcheckoutroom;
		this.listproductdamaded = listproductdamaded;
		this.idstaffchecking = idstaffchecking;
		this.sumaryindemnify = sumaryindemnify;
	}
	public int getIdcheckingoutroomdamaded() {
		return idcheckingoutroomdamaded;
	}
	public void setIdcheckingoutroomdamaded(int idcheckingoutroomdamaded) {
		this.idcheckingoutroomdamaded = idcheckingoutroomdamaded;
	}
	public String getIdcheckoutroom() {
		return idcheckoutroom;
	}
	public void setIdcheckoutroom(String idcheckoutroom) {
		this.idcheckoutroom = idcheckoutroom;
	}
	public String getListproductdamaded() {
		return listproductdamaded;
	}
	public void setListproductdamaded(String listproductdamaded) {
		this.listproductdamaded = listproductdamaded;
	}
	public String getIdstaffchecking() {
		return idstaffchecking;
	}
	public void setIdstaffchecking(String idstaffchecking) {
		this.idstaffchecking = idstaffchecking;
	}
	public BigDecimal getSumaryindemnify() {
		return sumaryindemnify;
	}
	public void setSumaryindemnify(BigDecimal sumaryindemnify) {
		this.sumaryindemnify = sumaryindemnify;
	}
	
	@Override
	public int hashCode() {
		super.setIdHashCode(idcheckingoutroomdamaded);
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		super.setIdHashCode(idcheckingoutroomdamaded);
		return super.equals(obj);
	}
}
