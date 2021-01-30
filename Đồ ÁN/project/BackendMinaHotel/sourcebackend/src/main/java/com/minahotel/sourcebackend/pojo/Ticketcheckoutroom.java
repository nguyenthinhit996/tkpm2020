package com.minahotel.sourcebackend.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ticketcheckoutroom extends MinaHoTelPojo{

	@Id
	private String idticketcheckoutroom;
	private String idticketbooking;
	private LocalDateTime timeendrent;
	private String idstaffreceptionsupport;
	private int numberroomrent;
	private BigDecimal sumaryratesandservices;
	
	public Ticketcheckoutroom() {
		super();
	}

	public Ticketcheckoutroom(String idticketcheckoutroom, String idticketbooking, LocalDateTime timeendrent,
			String idstaffreceptionsupport, int numberroomrent, BigDecimal sumaryratesandservices) {
		super();
		this.idticketcheckoutroom = idticketcheckoutroom;
		this.idticketbooking = idticketbooking;
		this.timeendrent = timeendrent;
		this.idstaffreceptionsupport = idstaffreceptionsupport;
		this.numberroomrent = numberroomrent;
		this.sumaryratesandservices = sumaryratesandservices;
	}

	public String getIdticketcheckoutroom() {
		return idticketcheckoutroom;
	}

	public void setIdticketcheckoutroom(String idticketcheckoutroom) {
		this.idticketcheckoutroom = idticketcheckoutroom;
	}

	public String getIdticketbooking() {
		return idticketbooking;
	}

	public void setIdticketbooking(String idticketbooking) {
		this.idticketbooking = idticketbooking;
	}

	public LocalDateTime getTimeendrent() {
		return timeendrent;
	}

	public void setTimeendrent(LocalDateTime timeendrent) {
		this.timeendrent = timeendrent;
	}

	public String getIdstaffreceptionsupport() {
		return idstaffreceptionsupport;
	}

	public void setIdstaffreceptionsupport(String idstaffreceptionsupport) {
		this.idstaffreceptionsupport = idstaffreceptionsupport;
	}

	public int getNumberroomrent() {
		return numberroomrent;
	}

	public void setNumberroomrent(int numberroomrent) {
		this.numberroomrent = numberroomrent;
	}

	public BigDecimal getSumaryratesandservices() {
		return sumaryratesandservices;
	}

	public void setSumaryratesandservices(BigDecimal sumaryratesandservices) {
		this.sumaryratesandservices = sumaryratesandservices;
	}
	
	@Override
	public int hashCode() {
		super.setIdHashCode(idticketcheckoutroom);
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		super.setIdHashCode(idticketcheckoutroom);
		return super.equals(obj);
	}
	
}
