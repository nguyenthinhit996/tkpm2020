package com.minahotel.sourcebackend.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.minahotel.sourcebackend.entities.TicketBookingEntity;

public class TicketcheckoutroomFontEnd implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6215513266397484992L;
	private String idticketcheckoutroom;
	private String idticketbooking;
	private LocalDateTime timeendrent;
	private String idstaffreceptionsupport;
	private int numberroomrent;
	private BigDecimal sumaryratesandservices;
	private BigDecimal rateRent;
	private BigDecimal rateservices;
	private BigDecimal roomSubCharge;
	private BigDecimal roomDamaged;
	private String status; //checking clean off
	private TicketBookingEntity ticketbooking;
	private String timeRent;
	private BigDecimal rateSubChargeInRoom;
	private int maxRentNumberInRoom;
	private String  listDamaged;
	private int roomnumber;
	
	
	
	/**
	 * @return the roomnumber
	 */
	public int getRoomnumber() {
		return roomnumber;
	}
	/**
	 * @param roomnumber the roomnumber to set
	 */
	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the listDamaged
	 */
	public String getListDamaged() {
		return listDamaged;
	}
	/**
	 * @param listDamaged the listDamaged to set
	 */
	public void setListDamaged(String listDamaged) {
		this.listDamaged = listDamaged;
	}
	/**
	 * @return the idticketcheckoutroom
	 */
	public String getIdticketcheckoutroom() {
		return idticketcheckoutroom;
	}
	/**
	 * @param idticketcheckoutroom the idticketcheckoutroom to set
	 */
	public void setIdticketcheckoutroom(String idticketcheckoutroom) {
		this.idticketcheckoutroom = idticketcheckoutroom;
	}
	/**
	 * @return the idticketbooking
	 */
	public String getIdticketbooking() {
		return idticketbooking;
	}
	/**
	 * @param idticketbooking the idticketbooking to set
	 */
	public void setIdticketbooking(String idticketbooking) {
		this.idticketbooking = idticketbooking;
	}
	/**
	 * @return the timeendrent
	 */
	public LocalDateTime getTimeendrent() {
		return timeendrent;
	}
	/**
	 * @param timeendrent the timeendrent to set
	 */
	public void setTimeendrent(LocalDateTime timeendrent) {
		this.timeendrent = timeendrent;
	}
	/**
	 * @return the idstaffreceptionsupport
	 */
	public String getIdstaffreceptionsupport() {
		return idstaffreceptionsupport;
	}
	/**
	 * @param idstaffreceptionsupport the idstaffreceptionsupport to set
	 */
	public void setIdstaffreceptionsupport(String idstaffreceptionsupport) {
		this.idstaffreceptionsupport = idstaffreceptionsupport;
	}
	/**
	 * @return the numberroomrent
	 */
	public int getNumberroomrent() {
		return numberroomrent;
	}
	/**
	 * @param numberroomrent the numberroomrent to set
	 */
	public void setNumberroomrent(int numberroomrent) {
		this.numberroomrent = numberroomrent;
	}
	/**
	 * @return the sumaryratesandservices
	 */
	public BigDecimal getSumaryratesandservices() {
		return sumaryratesandservices;
	}
	/**
	 * @param sumaryratesandservices the sumaryratesandservices to set
	 */
	public void setSumaryratesandservices(BigDecimal sumaryratesandservices) {
		this.sumaryratesandservices = sumaryratesandservices;
	}
	/**
	 * @return the rateRent
	 */
	public BigDecimal getRateRent() {
		return rateRent;
	}
	/**
	 * @param rateRent the rateRent to set
	 */
	public void setRateRent(BigDecimal rateRent) {
		this.rateRent = rateRent;
	}
	/**
	 * @return the rateservices
	 */
	public BigDecimal getRateservices() {
		return rateservices;
	}
	/**
	 * @param rateservices the rateservices to set
	 */
	public void setRateservices(BigDecimal rateservices) {
		this.rateservices = rateservices;
	}
	/**
	 * @return the roomSubCharge
	 */
	public BigDecimal getRoomSubCharge() {
		return roomSubCharge;
	}
	/**
	 * @param roomSubCharge the roomSubCharge to set
	 */
	public void setRoomSubCharge(BigDecimal roomSubCharge) {
		this.roomSubCharge = roomSubCharge;
	}
	/**
	 * @return the roomDamaged
	 */
	public BigDecimal getRoomDamaged() {
		return roomDamaged;
	}
	/**
	 * @param roomDamaged the roomDamaged to set
	 */
	public void setRoomDamaged(BigDecimal roomDamaged) {
		this.roomDamaged = roomDamaged;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the ticketbooking
	 */
	public TicketBookingEntity getTicketbooking() {
		return ticketbooking;
	}
	/**
	 * @param ticketbooking the ticketbooking to set
	 */
	public void setTicketbooking(TicketBookingEntity ticketbooking) {
		this.ticketbooking = ticketbooking;
	}
	/**
	 * @return the timeRent
	 */
	public String getTimeRent() {
		return timeRent;
	}
	/**
	 * @param timeRent the timeRent to set
	 */
	public void setTimeRent(String timeRent) {
		this.timeRent = timeRent;
	}
	/**
	 * @return the rateSubChargeInRoom
	 */
	public BigDecimal getRateSubChargeInRoom() {
		return rateSubChargeInRoom;
	}
	/**
	 * @param rateSubChargeInRoom the rateSubChargeInRoom to set
	 */
	public void setRateSubChargeInRoom(BigDecimal rateSubChargeInRoom) {
		this.rateSubChargeInRoom = rateSubChargeInRoom;
	}
	/**
	 * @return the maxRentNumberInRoom
	 */
	public int getMaxRentNumberInRoom() {
		return maxRentNumberInRoom;
	}
	/**
	 * @param maxRentNumberInRoom the maxRentNumberInRoom to set
	 */
	public void setMaxRentNumberInRoom(int maxRentNumberInRoom) {
		this.maxRentNumberInRoom = maxRentNumberInRoom;
	}
	public TicketcheckoutroomFontEnd(String idticketcheckoutroom, String idticketbooking, LocalDateTime timeendrent,
			String idstaffreceptionsupport, int numberroomrent, BigDecimal sumaryratesandservices, BigDecimal rateRent,
			BigDecimal rateservices, BigDecimal roomSubCharge, BigDecimal roomDamaged, String status,
			TicketBookingEntity ticketbooking, String timeRent, BigDecimal rateSubChargeInRoom,
			int maxRentNumberInRoom) {
		super();
		this.idticketcheckoutroom = idticketcheckoutroom;
		this.idticketbooking = idticketbooking;
		this.timeendrent = timeendrent;
		this.idstaffreceptionsupport = idstaffreceptionsupport;
		this.numberroomrent = numberroomrent;
		this.sumaryratesandservices = sumaryratesandservices;
		this.rateRent = rateRent;
		this.rateservices = rateservices;
		this.roomSubCharge = roomSubCharge;
		this.roomDamaged = roomDamaged;
		this.status = status;
		this.ticketbooking = ticketbooking;
		this.timeRent = timeRent;
		this.rateSubChargeInRoom = rateSubChargeInRoom;
		this.maxRentNumberInRoom = maxRentNumberInRoom;
	}
	public TicketcheckoutroomFontEnd() {
		super();
	}
	
	
}
