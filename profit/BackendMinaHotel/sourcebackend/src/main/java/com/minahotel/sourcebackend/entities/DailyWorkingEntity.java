package com.minahotel.sourcebackend.entities;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDailyWorkingEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * DailyWorkingEntity is mapping with table dailyworking in Database
 * @author Peter
 *
 */
@Entity
@Table(name = "dailyworking")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDailyWorkingEntity")
public class DailyWorkingEntity extends MinaHoTelPojo {

	/**
	 * idDailyWorkingEntity is composite key 
	 */
	@EmbeddedId
	private CompositeKeyDailyWorkingEntity idDailyWorkingEntity;

	@Column(name = "timestart")
	private LocalTime timeStartWork;

	@Column(name = "timeend")
	private LocalTime timeEndWork;

	@Column(name = "note", length = 255)
	private String note;

	@Column(name = "usernamestaff", length = 255)
	private String userNameOfStaffWorking;

	// FK StaffEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idstaffmanagement" , insertable = true, updatable = true)
	private StaffEntity staffManagement; // fk

	//PK FK StaffEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId(value = "idToDay") // chỉ rõ ra là copy vào key nào nếu chỉ có 1 key ko cần ghe tên ra
	@JoinColumn(name = "idtoday" , insertable = false, updatable = false)
	private DateWorkEntity dateWorking; // pk

	//PK FK StaffEntity
	@ManyToOne(cascade = CascadeType.ALL)
	@MapsId("idStaffWork" )
	@JoinColumn(name = "idstaffwork", insertable = false, updatable = false)
	private StaffEntity staffWoking; // pk

	/**
	 * @return the idDailyWorkingEntity
	 */
	public CompositeKeyDailyWorkingEntity getIdDailyWorkingEntity() {
		return idDailyWorkingEntity;
	}

	/**
	 * @param idDailyWorkingEntity the idDailyWorkingEntity to set
	 */
	public void setIdDailyWorkingEntity(CompositeKeyDailyWorkingEntity idDailyWorkingEntity) {
		this.idDailyWorkingEntity = idDailyWorkingEntity;
	}

	/**
	 * @return the timeStartWork
	 */
	public LocalTime getTimeStartWork() {
		return timeStartWork;
	}

	/**
	 * @param timeStartWork the timeStartWork to set
	 */
	public void setTimeStartWork(LocalTime timeStartWork) {
		this.timeStartWork = timeStartWork;
	}

	/**
	 * @return the timeEndWork
	 */
	public LocalTime getTimeEndWork() {
		return timeEndWork;
	}

	/**
	 * @param timeEndWork the timeEndWork to set
	 */
	public void setTimeEndWork(LocalTime timeEndWork) {
		this.timeEndWork = timeEndWork;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the userNameOfStaffWorking
	 */
	public String getUserNameOfStaffWorking() {
		return userNameOfStaffWorking;
	}

	/**
	 * @param userNameOfStaffWorking the userNameOfStaffWorking to set
	 */
	public void setUserNameOfStaffWorking(String userNameOfStaffWorking) {
		this.userNameOfStaffWorking = userNameOfStaffWorking;
	}

	/**
	 * @return the staffManagement
	 */
	public StaffEntity getStaffManagement() {
		return staffManagement;
	}

	/**
	 * @param staffManagement the staffManagement to set
	 */
	public void setStaffManagement(StaffEntity staffManagement) {
		this.staffManagement = staffManagement;
	}

	/**
	 * @return the dateWorking
	 */
	public DateWorkEntity getDateWorking() {
		return dateWorking;
	}

	/**
	 * @param dateWorking the dateWorking to set
	 */
	public void setDateWorking(DateWorkEntity dateWorking) {
		this.dateWorking = dateWorking;
	}

	/**
	 * @return the staffWoking
	 */
	public StaffEntity getStaffWoking() {
		return staffWoking;
	}

	/**
	 * @param staffWoking the staffWoking to set
	 */
	public void setStaffWoking(StaffEntity staffWoking) {
		this.staffWoking = staffWoking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateWorking == null) ? 0 : dateWorking.hashCode());
		result = prime * result + ((idDailyWorkingEntity == null) ? 0 : idDailyWorkingEntity.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((staffManagement == null) ? 0 : staffManagement.hashCode());
		result = prime * result + ((staffWoking == null) ? 0 : staffWoking.hashCode());
		result = prime * result + ((timeEndWork == null) ? 0 : timeEndWork.hashCode());
		result = prime * result + ((timeStartWork == null) ? 0 : timeStartWork.hashCode());
		result = prime * result + ((userNameOfStaffWorking == null) ? 0 : userNameOfStaffWorking.hashCode());
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
		DailyWorkingEntity other = (DailyWorkingEntity) obj;
		if (dateWorking == null) {
			if (other.dateWorking != null)
				return false;
		} else if (!dateWorking.equals(other.dateWorking))
			return false;
		if (idDailyWorkingEntity == null) {
			if (other.idDailyWorkingEntity != null)
				return false;
		} else if (!idDailyWorkingEntity.equals(other.idDailyWorkingEntity))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (staffManagement == null) {
			if (other.staffManagement != null)
				return false;
		} else if (!staffManagement.equals(other.staffManagement))
			return false;
		if (staffWoking == null) {
			if (other.staffWoking != null)
				return false;
		} else if (!staffWoking.equals(other.staffWoking))
			return false;
		if (timeEndWork == null) {
			if (other.timeEndWork != null)
				return false;
		} else if (!timeEndWork.equals(other.timeEndWork))
			return false;
		if (timeStartWork == null) {
			if (other.timeStartWork != null)
				return false;
		} else if (!timeStartWork.equals(other.timeStartWork))
			return false;
		if (userNameOfStaffWorking == null) {
			if (other.userNameOfStaffWorking != null)
				return false;
		} else if (!userNameOfStaffWorking.equals(other.userNameOfStaffWorking))
			return false;
		return true;
	}

	public DailyWorkingEntity() {
		super();
	}
}
