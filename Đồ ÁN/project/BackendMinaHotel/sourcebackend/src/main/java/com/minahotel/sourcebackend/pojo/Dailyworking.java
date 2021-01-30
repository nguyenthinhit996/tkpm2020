package com.minahotel.sourcebackend.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity  @IdClass(Dailyworking.class)
public class Dailyworking extends MinaHoTelPojo implements Serializable{

	private static final long serialVersionUID = -5194306779785637854L;
	@Id
	private LocalDate  idtoday;
	@Id
	private String idstaffwork;
	private LocalTime timestart;
	private LocalTime timeend;
	private String note;
	private String idstaffmanagement;
	
	public Dailyworking(LocalDate idtoday, String idstaffwork, LocalTime timestart, LocalTime timeend, String note,
			String idstaffmanagement) {
		super();
		this.idtoday = idtoday;
		this.idstaffwork = idstaffwork;
		this.timestart = timestart;
		this.timeend = timeend;
		this.note = note;
		this.idstaffmanagement = idstaffmanagement;
	}

	public Dailyworking() {
		super();
	}

	public LocalDate getIdtoday() {
		return idtoday;
	}

	public void setIdtoday(LocalDate idtoday) {
		this.idtoday = idtoday;
	}

	public String getIdstaffwork() {
		return idstaffwork;
	}

	public void setIdstaffwork(String idstaffwork) {
		this.idstaffwork = idstaffwork;
	}

	public LocalTime getTimestart() {
		return timestart;
	}

	public void setTimestart(LocalTime timestart) {
		this.timestart = timestart;
	}

	public LocalTime getTimeend() {
		return timeend;
	}

	public void setTimeend(LocalTime timeend) {
		this.timeend = timeend;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getIdstaffmanagement() {
		return idstaffmanagement;
	}

	public void setIdstaffmanagement(String idstaffmanagement) {
		this.idstaffmanagement = idstaffmanagement;
	}
	
	@Override
	public int hashCode() {
		super.setIdHashCode(idtoday,idstaffwork);
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		super.setIdHashCode(idtoday,idstaffwork);
		return super.equals(obj);
	}
	
}
