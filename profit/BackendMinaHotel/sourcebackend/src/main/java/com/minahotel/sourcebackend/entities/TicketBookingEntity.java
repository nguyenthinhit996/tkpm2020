package com.minahotel.sourcebackend.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

@Entity
@Table(name = "ticketbooking")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTicketBooking")
public class TicketBookingEntity extends MinaHoTelPojo {

	@Id
	@Column(name = "idticketbooking", length = 50)
	private String idTicketBooking;

	@Column(name = "iduserrentroom", length = 45)
	private String idUserRentRoom;

	@Column(name = "usernamerentroom", length = 255)
	private String userNameRentRoom;

	@Column(name = "timestamprent")
	private LocalDateTime timeStartRentRoom;

	@Column(name = "numberinroom")
	private Integer numberPeopleInRoom;

	@Column(name = "status", length = 45)
	private String status;

	// FK Booking - Staff
	@ManyToOne
	@JoinColumn(name = "idstaffreception", insertable = true, updatable = true) // fk
	private StaffEntity staffReception;

	// FK Booking - Room
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "numberroom", insertable = true, updatable = false)
	private RoomEntity roomRent;

	@JsonIgnore
	// Reference by DetailsServicesEntity - Booking
	@OneToMany(mappedBy = "ticketBookingindetail", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = DetailsServicesEntity.class)
	private List<DetailsServicesEntity> dsDetailsServices;

	@JsonIgnore
	// Reference TicketCheckOut - Booking
	@OneToOne(mappedBy = "ticketBooking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TicketCheckOutRoomEntity ticketCheckOutRoom;

	/**
	 * @return the idTicketBooking
	 */
	public String getIdTicketBooking() {
		return idTicketBooking;
	}

	/**
	 * @param idTicketBooking the idTicketBooking to set
	 */
	public void setIdTicketBooking(String idTicketBooking) {
		this.idTicketBooking = idTicketBooking;
	}

	/**
	 * @return the idUserRentRoom
	 */
	public String getIdUserRentRoom() {
		return idUserRentRoom;
	}

	/**
	 * @param idUserRentRoom the idUserRentRoom to set
	 */
	public void setIdUserRentRoom(String idUserRentRoom) {
		this.idUserRentRoom = idUserRentRoom;
	}

	/**
	 * @return the userNameRentRoom
	 */
	public String getUserNameRentRoom() {
		return userNameRentRoom;
	}

	/**
	 * @param userNameRentRoom the userNameRentRoom to set
	 */
	public void setUserNameRentRoom(String userNameRentRoom) {
		this.userNameRentRoom = userNameRentRoom;
	}

	/**
	 * @return the timeStartRentRoom
	 */
	public LocalDateTime getTimeStartRentRoom() {
		return timeStartRentRoom;
	}

	/**
	 * @param timeStartRentRoom the timeStartRentRoom to set
	 */
	public void setTimeStartRentRoom(LocalDateTime timeStartRentRoom) {
		this.timeStartRentRoom = timeStartRentRoom;
	}

	/**
	 * @return the numberPeopleInRoom
	 */
	public Integer getNumberPeopleInRoom() {
		return numberPeopleInRoom;
	}

	/**
	 * @param numberPeopleInRoom the numberPeopleInRoom to set
	 */
	public void setNumberPeopleInRoom(Integer numberPeopleInRoom) {
		this.numberPeopleInRoom = numberPeopleInRoom;
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
	 * @return the staffReception
	 */
	public StaffEntity getStaffReception() {
		return staffReception;
	}

	/**
	 * @param staffReception the staffReception to set
	 */
	public void setStaffReception(StaffEntity staffReception) {
		this.staffReception = staffReception;
	}

	/**
	 * @return the roomRent
	 */
	public RoomEntity getRoomRent() {
		return roomRent;
	}

	/**
	 * @param roomRent the roomRent to set
	 */
	public void setRoomRent(RoomEntity roomRent) {
		this.roomRent = roomRent;
	}

	/**
	 * @return the dsDetailsServices
	 */
	public List<DetailsServicesEntity> getDsDetailsServices() {
		return dsDetailsServices;
	}

	/**
	 * @param dsDetailsServices the dsDetailsServices to set
	 */
	public void setDsDetailsServices(List<DetailsServicesEntity> dsDetailsServices) {
		this.dsDetailsServices = dsDetailsServices;
	}

	/**
	 * @return the ticketCheckOutRoom
	 */
	public TicketCheckOutRoomEntity getTicketCheckOutRoom() {
		return ticketCheckOutRoom;
	}

	/**
	 * @param ticketCheckOutRoom the ticketCheckOutRoom to set
	 */
	public void setTicketCheckOutRoom(TicketCheckOutRoomEntity ticketCheckOutRoom) {
		this.ticketCheckOutRoom = ticketCheckOutRoom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idTicketBooking == null) ? 0 : idTicketBooking.hashCode());
		result = prime * result + ((idUserRentRoom == null) ? 0 : idUserRentRoom.hashCode());
		result = prime * result + ((numberPeopleInRoom == null) ? 0 : numberPeopleInRoom.hashCode());
		result = prime * result + ((roomRent == null) ? 0 : roomRent.hashCode());
		result = prime * result + ((staffReception == null) ? 0 : staffReception.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((ticketCheckOutRoom == null) ? 0 : ticketCheckOutRoom.hashCode());
		result = prime * result + ((timeStartRentRoom == null) ? 0 : timeStartRentRoom.hashCode());
		result = prime * result + ((userNameRentRoom == null) ? 0 : userNameRentRoom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketBookingEntity other = (TicketBookingEntity) obj;
		if (idTicketBooking == null) {
			if (other.idTicketBooking != null)
				return false;
		} else if (!idTicketBooking.equals(other.idTicketBooking))
			return false;
		if (idUserRentRoom == null) {
			if (other.idUserRentRoom != null)
				return false;
		} else if (!idUserRentRoom.equals(other.idUserRentRoom))
			return false;
		if (numberPeopleInRoom == null) {
			if (other.numberPeopleInRoom != null)
				return false;
		} else if (!numberPeopleInRoom.equals(other.numberPeopleInRoom))
			return false;
		if (roomRent == null) {
			if (other.roomRent != null)
				return false;
		} else if (!roomRent.equals(other.roomRent))
			return false;
		if (staffReception == null) {
			if (other.staffReception != null)
				return false;
		} else if (!staffReception.equals(other.staffReception))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (ticketCheckOutRoom == null) {
			if (other.ticketCheckOutRoom != null)
				return false;
		} else if (!ticketCheckOutRoom.equals(other.ticketCheckOutRoom))
			return false;
		if (timeStartRentRoom == null) {
			if (other.timeStartRentRoom != null)
				return false;
		} else if (!timeStartRentRoom.equals(other.timeStartRentRoom))
			return false;
		if (userNameRentRoom == null) {
			if (other.userNameRentRoom != null)
				return false;
		} else if (!userNameRentRoom.equals(other.userNameRentRoom))
			return false;
		return true;
	}

	public TicketBookingEntity() {
		super();
	}

	@Override
	public String toString() {
		return "TicketBookingEntity [idTicketBooking=" + idTicketBooking + ", idUserRentRoom=" + idUserRentRoom
				+ ", userNameRentRoom=" + userNameRentRoom + ", timeStartRentRoom=" + timeStartRentRoom
				+ ", numberPeopleInRoom=" + numberPeopleInRoom + ", status=" + status + ", staffReception="
				+ staffReception + "]";
	}

	
	
}
