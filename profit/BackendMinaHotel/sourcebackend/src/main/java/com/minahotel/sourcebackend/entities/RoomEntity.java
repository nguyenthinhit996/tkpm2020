package com.minahotel.sourcebackend.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

@Entity
@Table(name = "room")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idRoom")
public class RoomEntity extends MinaHoTelPojo {

	@Id
	@Column(name = "idroom")
	private Integer idRoom;

	@Column(name = "status", length = 45)
	private String statusRoom;

	// FK RoomEntity to TypeOfRoomEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "nametyperoom")
	private TypeOfRoomEntity typeOfRoom;

	/**
	 * @return the idRoom
	 */
	public Integer getIdRoom() {
		return idRoom;
	}

	/**
	 * @param idRoom the idRoom to set
	 */
	public void setIdRoom(Integer idRoom) {
		this.idRoom = idRoom;
	}

	/**
	 * @return the statusRoom
	 */
	public String getStatusRoom() {
		return statusRoom;
	}

	/**
	 * @param statusRoom the statusRoom to set
	 */
	public void setStatusRoom(String statusRoom) {
		this.statusRoom = statusRoom;
	}

	/**
	 * @return the typeOfRoom
	 */
	public TypeOfRoomEntity getTypeOfRoom() {
		return typeOfRoom;
	}

	/**
	 * @param typeOfRoom the typeOfRoom to set
	 */
	public void setTypeOfRoom(TypeOfRoomEntity typeOfRoom) {
		this.typeOfRoom = typeOfRoom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idRoom == null) ? 0 : idRoom.hashCode());
		result = prime * result + ((statusRoom == null) ? 0 : statusRoom.hashCode());
		result = prime * result + ((typeOfRoom == null) ? 0 : typeOfRoom.hashCode());
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
		RoomEntity other = (RoomEntity) obj;
		if (idRoom == null) {
			if (other.idRoom != null)
				return false;
		} else if (!idRoom.equals(other.idRoom))
			return false;
		if (statusRoom == null) {
			if (other.statusRoom != null)
				return false;
		} else if (!statusRoom.equals(other.statusRoom))
			return false;
		if (typeOfRoom == null) {
			if (other.typeOfRoom != null)
				return false;
		} else if (!typeOfRoom.equals(other.typeOfRoom))
			return false;
		return true;
	}

	public RoomEntity() {
		super();
	}
}
