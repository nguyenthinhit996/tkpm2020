package com.minahotel.sourcebackend.entities.compositekey;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKeyDailyWorkingEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7326507640168973180L;

	@Column(name = "idtoday")
	private LocalDate idToDay;
	
	@Column(name="idstaffwork", length = 50)
	private String idStaffWork;

	/**
	 * @return the idToDay
	 */
	public LocalDate getIdToDay() {
		return idToDay;
	}

	/**
	 * @param idToDay the idToDay to set
	 */
	public void setIdToDay(LocalDate idToDay) {
		this.idToDay = idToDay;
	}

	/**
	 * @return the idStaffWork
	 */
	public String getIdStaffWork() {
		return idStaffWork;
	}

	/**
	 * @param idStaffWork the idStaffWork to set
	 */
	public void setIdStaffWork(String idStaffWork) {
		this.idStaffWork = idStaffWork;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idStaffWork == null) ? 0 : idStaffWork.hashCode());
		result = prime * result + ((idToDay == null) ? 0 : idToDay.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeKeyDailyWorkingEntity other = (CompositeKeyDailyWorkingEntity) obj;
		if (idStaffWork == null) {
			if (other.idStaffWork != null)
				return false;
		} else if (!idStaffWork.equals(other.idStaffWork))
			return false;
		if (idToDay == null) {
			if (other.idToDay != null)
				return false;
		} else if (!idToDay.equals(other.idToDay))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CompositeKeyDailyWorkingEntity [idToDay=" + idToDay + ", idStaffWork=" + idStaffWork + "]";
	}

	public CompositeKeyDailyWorkingEntity() {
		super();
	}
	
}
