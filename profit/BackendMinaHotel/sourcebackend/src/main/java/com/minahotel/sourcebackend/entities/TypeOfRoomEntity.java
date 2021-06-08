package com.minahotel.sourcebackend.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

@Entity
@Table(name = "typeofroom")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idNameTypeOfRoom")
public class TypeOfRoomEntity extends MinaHoTelPojo {

	@Id
	@Column(name = "nametypeofroom", length = 50)
	private String idNameTypeOfRoom;

	@Column(name = "roomrateshours", length = 50)
	private BigDecimal roomRatesHour;

	@Column(name = "roomratesdates")
	private BigDecimal roomRateDate;

	@Column(name = "numberinroom")
	private Integer numberInRoom;

	@Column(name = "roomratescharge")
	private BigDecimal roomRatesCharge;

	@OneToMany(mappedBy = "typeOfRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = RoomEntity.class)
	List<RoomEntity> dsRoom;

	/**
	 * @return the idNameTypeOfRoom
	 */
	public String getIdNameTypeOfRoom() {
		return idNameTypeOfRoom;
	}

	/**
	 * @param idNameTypeOfRoom the idNameTypeOfRoom to set
	 */
	public void setIdNameTypeOfRoom(String idNameTypeOfRoom) {
		this.idNameTypeOfRoom = idNameTypeOfRoom;
	}

	/**
	 * @return the roomRatesHour
	 */
	public BigDecimal getRoomRatesHour() {
		return roomRatesHour;
	}

	/**
	 * @param roomRatesHour the roomRatesHour to set
	 */
	public void setRoomRatesHour(BigDecimal roomRatesHour) {
		this.roomRatesHour = roomRatesHour;
	}

	/**
	 * @return the roomRateDate
	 */
	public BigDecimal getRoomRateDate() {
		return roomRateDate;
	}

	/**
	 * @param roomRateDate the roomRateDate to set
	 */
	public void setRoomRateDate(BigDecimal roomRateDate) {
		this.roomRateDate = roomRateDate;
	}

	/**
	 * @return the numberInRoom
	 */
	public Integer getNumberInRoom() {
		return numberInRoom;
	}

	/**
	 * @param numberInRoom the numberInRoom to set
	 */
	public void setNumberInRoom(Integer numberInRoom) {
		this.numberInRoom = numberInRoom;
	}

	/**
	 * @return the roomRatesCharge
	 */
	public BigDecimal getRoomRatesCharge() {
		return roomRatesCharge;
	}

	/**
	 * @param roomRatesCharge the roomRatesCharge to set
	 */
	public void setRoomRatesCharge(BigDecimal roomRatesCharge) {
		this.roomRatesCharge = roomRatesCharge;
	}

	/**
	 * @return the dsRoom
	 */
	public List<RoomEntity> getDsRoom() {
		return dsRoom;
	}

	/**
	 * @param dsRoom the dsRoom to set
	 */
	public void setDsRoom(List<RoomEntity> dsRoom) {
		this.dsRoom = dsRoom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idNameTypeOfRoom == null) ? 0 : idNameTypeOfRoom.hashCode());
		result = prime * result + ((numberInRoom == null) ? 0 : numberInRoom.hashCode());
		result = prime * result + ((roomRateDate == null) ? 0 : roomRateDate.hashCode());
		result = prime * result + ((roomRatesCharge == null) ? 0 : roomRatesCharge.hashCode());
		result = prime * result + ((roomRatesHour == null) ? 0 : roomRatesHour.hashCode());
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
		TypeOfRoomEntity other = (TypeOfRoomEntity) obj;
		if (idNameTypeOfRoom == null) {
			if (other.idNameTypeOfRoom != null)
				return false;
		} else if (!idNameTypeOfRoom.equals(other.idNameTypeOfRoom))
			return false;
		if (numberInRoom == null) {
			if (other.numberInRoom != null)
				return false;
		} else if (!numberInRoom.equals(other.numberInRoom))
			return false;
		if (roomRateDate == null) {
			if (other.roomRateDate != null)
				return false;
		} else if (!roomRateDate.equals(other.roomRateDate))
			return false;
		if (roomRatesCharge == null) {
			if (other.roomRatesCharge != null)
				return false;
		} else if (!roomRatesCharge.equals(other.roomRatesCharge))
			return false;
		if (roomRatesHour == null) {
			if (other.roomRatesHour != null)
				return false;
		} else if (!roomRatesHour.equals(other.roomRatesHour))
			return false;
		return true;
	}

	public TypeOfRoomEntity() {
		super();
	}

	
	
}
