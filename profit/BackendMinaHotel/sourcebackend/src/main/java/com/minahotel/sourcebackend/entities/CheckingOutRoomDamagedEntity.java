package com.minahotel.sourcebackend.entities;

import java.math.BigDecimal;

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

@Entity
@Table(name = "checkingoutroomdamaged")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idCheckoutRoomDamaged")
public class CheckingOutRoomDamagedEntity extends MinaHoTelPojo {

	@Id
	@Column(name = "idcheckingoutroomdamaded", length = 50)
	private String idCheckoutRoomDamaged;

	@Column(name = "listproductdamaded", length = 1000)
	private String listProductDamaded;

	@Column(name = "sumaryindemnify")
	private BigDecimal sumaryIndemnify;

	@Column(name = "idticketbooking", length = 50)
	private String idTicketBooking;

	@Column(name = "status", length = 45)
	private String status;

	// FK TicketCheckOutRoomEntity
	@OneToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "idcheckoutroom")
	private TicketCheckOutRoomEntity ticketCheckoutObject;

	// FK StaffEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idstaffchecking") // idstaffchecking
	private StaffEntity staffCheckOutRoomDamaged;

	/**
	 * @return the idCheckoutRoomDamaged
	 */
	public String getIdCheckoutRoomDamaged() {
		return idCheckoutRoomDamaged;
	}

	/**
	 * @param idCheckoutRoomDamaged the idCheckoutRoomDamaged to set
	 */
	public void setIdCheckoutRoomDamaged(String idCheckoutRoomDamaged) {
		this.idCheckoutRoomDamaged = idCheckoutRoomDamaged;
	}

	/**
	 * @return the listProductDamaded
	 */
	public String getListProductDamaded() {
		return listProductDamaded;
	}

	/**
	 * @param listProductDamaded the listProductDamaded to set
	 */
	public void setListProductDamaded(String listProductDamaded) {
		this.listProductDamaded = listProductDamaded;
	}

	/**
	 * @return the sumaryIndemnify
	 */
	public BigDecimal getSumaryIndemnify() {
		return sumaryIndemnify;
	}

	/**
	 * @param sumaryIndemnify the sumaryIndemnify to set
	 */
	public void setSumaryIndemnify(BigDecimal sumaryIndemnify) {
		this.sumaryIndemnify = sumaryIndemnify;
	}

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
	 * @return the ticketCheckoutObject
	 */
	public TicketCheckOutRoomEntity getTicketCheckoutObject() {
		return ticketCheckoutObject;
	}

	/**
	 * @param ticketCheckoutObject the ticketCheckoutObject to set
	 */
	public void setTicketCheckoutObject(TicketCheckOutRoomEntity ticketCheckoutObject) {
		this.ticketCheckoutObject = ticketCheckoutObject;
	}

	/**
	 * @return the staffCheckOutRoomDamaged
	 */
	public StaffEntity getStaffCheckOutRoomDamaged() {
		return staffCheckOutRoomDamaged;
	}

	/**
	 * @param staffCheckOutRoomDamaged the staffCheckOutRoomDamaged to set
	 */
	public void setStaffCheckOutRoomDamaged(StaffEntity staffCheckOutRoomDamaged) {
		this.staffCheckOutRoomDamaged = staffCheckOutRoomDamaged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idCheckoutRoomDamaged == null) ? 0 : idCheckoutRoomDamaged.hashCode());
		result = prime * result + ((idTicketBooking == null) ? 0 : idTicketBooking.hashCode());
		result = prime * result + ((listProductDamaded == null) ? 0 : listProductDamaded.hashCode());
		result = prime * result + ((staffCheckOutRoomDamaged == null) ? 0 : staffCheckOutRoomDamaged.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((sumaryIndemnify == null) ? 0 : sumaryIndemnify.hashCode());
		result = prime * result + ((ticketCheckoutObject == null) ? 0 : ticketCheckoutObject.hashCode());
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
		CheckingOutRoomDamagedEntity other = (CheckingOutRoomDamagedEntity) obj;
		if (idCheckoutRoomDamaged == null) {
			if (other.idCheckoutRoomDamaged != null)
				return false;
		} else if (!idCheckoutRoomDamaged.equals(other.idCheckoutRoomDamaged))
			return false;
		if (idTicketBooking == null) {
			if (other.idTicketBooking != null)
				return false;
		} else if (!idTicketBooking.equals(other.idTicketBooking))
			return false;
		if (listProductDamaded == null) {
			if (other.listProductDamaded != null)
				return false;
		} else if (!listProductDamaded.equals(other.listProductDamaded))
			return false;
		if (staffCheckOutRoomDamaged == null) {
			if (other.staffCheckOutRoomDamaged != null)
				return false;
		} else if (!staffCheckOutRoomDamaged.equals(other.staffCheckOutRoomDamaged))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (sumaryIndemnify == null) {
			if (other.sumaryIndemnify != null)
				return false;
		} else if (!sumaryIndemnify.equals(other.sumaryIndemnify))
			return false;
		if (ticketCheckoutObject == null) {
			if (other.ticketCheckoutObject != null)
				return false;
		} else if (!ticketCheckoutObject.equals(other.ticketCheckoutObject))
			return false;
		return true;
	}

	public CheckingOutRoomDamagedEntity() {
		super();
	}
	
}
