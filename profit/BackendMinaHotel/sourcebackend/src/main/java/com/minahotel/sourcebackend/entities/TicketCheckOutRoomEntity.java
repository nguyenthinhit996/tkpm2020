package com.minahotel.sourcebackend.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * TicketCheckOutRoomEntity is mapping with table ticketcheckoutroom in Database
 * @author Peter
 *
 */
@Entity
@Table(name = "ticketcheckoutroom")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTicketCheckout")
public class TicketCheckOutRoomEntity extends MinaHoTelPojo {

	/**
	 * idticketcheckoutroom is primary key column in Database
	 */
	@Id
	@Column(name = "idticketcheckoutroom", length = 50)
	private String idTicketCheckout;

	@Column(name = "timeendrent")
	private LocalDateTime timeEndRentRoom;

	@Column(name = "numberroomrent")
	private Integer numberRoomRent;

	@Column(name = "sumaryratesandservices")
	private BigDecimal totalRateAll;

	@Column(name = "status", length = 45)
	private String status;

	@Column(name = "raterentroom")
	private BigDecimal rateRentRoom;

	@Column(name = "rateservices")
	private BigDecimal rateSevices;

	@Column(name = "roomsubcharge")
	private BigDecimal rateRoomSubCharge;

	@Column(name = "roomdamaged")
	private BigDecimal rateRoomDamaged;

	@Column(name = "timerent", length = 205)
	private String timeRent;

	// FK CheckOut - Checking
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idticketbooking", insertable = true, updatable = false)
	private TicketBookingEntity ticketBooking;

	// FK CheckOut - Staff Reception
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idstaffreceptionsupport", insertable = true, updatable = true)
	private StaffEntity staffCheckoutRoom;

	// Reference RoomDamaged to CheckOut
	 @OneToOne(mappedBy = "ticketCheckoutObject", cascade = CascadeType.ALL)
	 private CheckingOutRoomDamagedEntity roomDamaged;

	/**
	 * @return the idTicketCheckout
	 */
	public String getIdTicketCheckout() {
		return idTicketCheckout;
	}

	/**
	 * @param idTicketCheckout the idTicketCheckout to set
	 */
	public void setIdTicketCheckout(String idTicketCheckout) {
		this.idTicketCheckout = idTicketCheckout;
	}

	/**
	 * @return the timeEndRentRoom
	 */
	public LocalDateTime getTimeEndRentRoom() {
		return timeEndRentRoom;
	}

	/**
	 * @param timeEndRentRoom the timeEndRentRoom to set
	 */
	public void setTimeEndRentRoom(LocalDateTime timeEndRentRoom) {
		this.timeEndRentRoom = timeEndRentRoom;
	}

	/**
	 * @return the numberPeopleInRoom
	 */
	public Integer getnumberRoomRent() {
		return numberRoomRent;
	}

	/**
	 * @param numberPeopleInRoom the numberPeopleInRoom to set
	 */
	public void setnumberRoomRent(Integer numberPeopleInRoom) {
		this.numberRoomRent = numberPeopleInRoom;
	}

	/**
	 * @return the totalRateAll
	 */
	public BigDecimal getTotalRateAll() {
		return totalRateAll;
	}

	/**
	 * @param totalRateAll the totalRateAll to set
	 */
	public void setTotalRateAll(BigDecimal totalRateAll) {
		this.totalRateAll = totalRateAll;
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
	 * @return the rateRentRoom
	 */
	public BigDecimal getRateRentRoom() {
		return rateRentRoom;
	}

	/**
	 * @param rateRentRoom the rateRentRoom to set
	 */
	public void setRateRentRoom(BigDecimal rateRentRoom) {
		this.rateRentRoom = rateRentRoom;
	}

	/**
	 * @return the rateSevices
	 */
	public BigDecimal getRateSevices() {
		return rateSevices;
	}

	/**
	 * @param rateSevices the rateSevices to set
	 */
	public void setRateSevices(BigDecimal rateSevices) {
		this.rateSevices = rateSevices;
	}

	/**
	 * @return the rateRoomSubCharge
	 */
	public BigDecimal getRateRoomSubCharge() {
		return rateRoomSubCharge;
	}

	/**
	 * @param rateRoomSubCharge the rateRoomSubCharge to set
	 */
	public void setRateRoomSubCharge(BigDecimal rateRoomSubCharge) {
		this.rateRoomSubCharge = rateRoomSubCharge;
	}

	/**
	 * @return the rateRoomDamaged
	 */
	public BigDecimal getRateRoomDamaged() {
		return rateRoomDamaged;
	}

	/**
	 * @param rateRoomDamaged the rateRoomDamaged to set
	 */
	public void setRateRoomDamaged(BigDecimal rateRoomDamaged) {
		this.rateRoomDamaged = rateRoomDamaged;
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
	 * @return the ticketBooking
	 */
	public TicketBookingEntity getTicketBooking() {
		return ticketBooking;
	}

	/**
	 * @param ticketBooking the ticketBooking to set
	 */
	public void setTicketBooking(TicketBookingEntity ticketBooking) {
		this.ticketBooking = ticketBooking;
	}

	/**
	 * @return the staffReceptionCheckoutRoom
	 */
	public StaffEntity getStaffCheckoutRoom() {
		return staffCheckoutRoom;
	}

	/**
	 * @param staffReceptionCheckoutRoom the staffReceptionCheckoutRoom to set
	 */
	public void setStaffCheckoutRoom(StaffEntity staffReceptionCheckoutRoom) {
		this.staffCheckoutRoom = staffReceptionCheckoutRoom;
	}

	/**
	 * @return the roomDamaged
	 */
	public CheckingOutRoomDamagedEntity getRoomDamaged() {
		return roomDamaged;
	}

	/**
	 * @param roomDamaged the roomDamaged to set
	 */
	public void setRoomDamaged(CheckingOutRoomDamagedEntity roomDamaged) {
		this.roomDamaged = roomDamaged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idTicketCheckout == null) ? 0 : idTicketCheckout.hashCode());
		result = prime * result + ((numberRoomRent == null) ? 0 : numberRoomRent.hashCode());
		result = prime * result + ((rateRentRoom == null) ? 0 : rateRentRoom.hashCode());
		result = prime * result + ((rateRoomDamaged == null) ? 0 : rateRoomDamaged.hashCode());
		result = prime * result + ((rateRoomSubCharge == null) ? 0 : rateRoomSubCharge.hashCode());
		result = prime * result + ((rateSevices == null) ? 0 : rateSevices.hashCode());
		result = prime * result + ((roomDamaged == null) ? 0 : roomDamaged.hashCode());
		result = prime * result + ((staffCheckoutRoom == null) ? 0 : staffCheckoutRoom.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((ticketBooking == null) ? 0 : ticketBooking.hashCode());
		result = prime * result + ((timeEndRentRoom == null) ? 0 : timeEndRentRoom.hashCode());
		result = prime * result + ((timeRent == null) ? 0 : timeRent.hashCode());
		result = prime * result + ((totalRateAll == null) ? 0 : totalRateAll.hashCode());
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
		TicketCheckOutRoomEntity other = (TicketCheckOutRoomEntity) obj;
		if (idTicketCheckout == null) {
			if (other.idTicketCheckout != null)
				return false;
		} else if (!idTicketCheckout.equals(other.idTicketCheckout))
			return false;
		if (numberRoomRent == null) {
			if (other.numberRoomRent != null)
				return false;
		} else if (!numberRoomRent.equals(other.numberRoomRent))
			return false;
		if (rateRentRoom == null) {
			if (other.rateRentRoom != null)
				return false;
		} else if (!rateRentRoom.equals(other.rateRentRoom))
			return false;
		if (rateRoomDamaged == null) {
			if (other.rateRoomDamaged != null)
				return false;
		} else if (!rateRoomDamaged.equals(other.rateRoomDamaged))
			return false;
		if (rateRoomSubCharge == null) {
			if (other.rateRoomSubCharge != null)
				return false;
		} else if (!rateRoomSubCharge.equals(other.rateRoomSubCharge))
			return false;
		if (rateSevices == null) {
			if (other.rateSevices != null)
				return false;
		} else if (!rateSevices.equals(other.rateSevices))
			return false;
		if (roomDamaged == null) {
			if (other.roomDamaged != null)
				return false;
		} else if (!roomDamaged.equals(other.roomDamaged))
			return false;
		if (staffCheckoutRoom == null) {
			if (other.staffCheckoutRoom != null)
				return false;
		} else if (!staffCheckoutRoom.equals(other.staffCheckoutRoom))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (ticketBooking == null) {
			if (other.ticketBooking != null)
				return false;
		} else if (!ticketBooking.equals(other.ticketBooking))
			return false;
		if (timeEndRentRoom == null) {
			if (other.timeEndRentRoom != null)
				return false;
		} else if (!timeEndRentRoom.equals(other.timeEndRentRoom))
			return false;
		if (timeRent == null) {
			if (other.timeRent != null)
				return false;
		} else if (!timeRent.equals(other.timeRent))
			return false;
		if (totalRateAll == null) {
			if (other.totalRateAll != null)
				return false;
		} else if (!totalRateAll.equals(other.totalRateAll))
			return false;
		return true;
	}

	public TicketCheckOutRoomEntity() {
		super();
	}
}
