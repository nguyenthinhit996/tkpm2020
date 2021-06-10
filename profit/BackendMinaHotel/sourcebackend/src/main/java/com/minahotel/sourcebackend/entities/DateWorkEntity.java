package com.minahotel.sourcebackend.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * DateWorkEntity is mapping with table datework in Database
 * @author Peter
 *
 */
@Entity
@Table(name = "datework")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDateWork")
public class DateWorkEntity extends MinaHoTelPojo {

	/**
	 * iddatework is primary key column in Database
	 */
	@Id
	@Column(name = "iddatework")
	private LocalDate idDateWork;

	@Column(name = "listuserworkfullday", length = 5000)
	private String listUserWorkFullDay;

	@Column(name = "listuserhalfday", length = 5000)
	private String listUserHalfDay;

	@Column(name = "regulation")
	private Integer regulation;

	// Reference DateWorkEntity to DailyWorkingEntity
	@OneToMany(mappedBy = "dateWorking", cascade = CascadeType.ALL)
	List<DailyWorkingEntity> lsDailyWorkingEntity;

	/**
	 * @return the idDateWork
	 */
	public LocalDate getIdDateWork() {
		return idDateWork;
	}

	/**
	 * @param idDateWork the idDateWork to set
	 */
	public void setIdDateWork(LocalDate idDateWork) {
		this.idDateWork = idDateWork;
	}

	/**
	 * @return the listUserWorkFullDay
	 */
	public String getListUserWorkFullDay() {
		return listUserWorkFullDay;
	}

	/**
	 * @param listUserWorkFullDay the listUserWorkFullDay to set
	 */
	public void setListUserWorkFullDay(String listUserWorkFullDay) {
		this.listUserWorkFullDay = listUserWorkFullDay;
	}

	/**
	 * @return the listUserHalfDay
	 */
	public String getListUserHalfDay() {
		return listUserHalfDay;
	}

	/**
	 * @param listUserHalfDay the listUserHalfDay to set
	 */
	public void setListUserHalfDay(String listUserHalfDay) {
		this.listUserHalfDay = listUserHalfDay;
	}

	/**
	 * @return the regulation
	 */
	public Integer getRegulation() {
		return regulation;
	}

	/**
	 * @param regulation the regulation to set
	 */
	public void setRegulation(Integer regulation) {
		this.regulation = regulation;
	}

	/**
	 * @return the lsDailyWorkingEntity
	 */
	public List<DailyWorkingEntity> getLsDailyWorkingEntity() {
		return lsDailyWorkingEntity;
	}

	/**
	 * @param lsDailyWorkingEntity the lsDailyWorkingEntity to set
	 */
	public void setLsDailyWorkingEntity(List<DailyWorkingEntity> lsDailyWorkingEntity) {
		this.lsDailyWorkingEntity = lsDailyWorkingEntity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((idDateWork == null) ? 0 : idDateWork.hashCode());
		result = prime * result + ((listUserHalfDay == null) ? 0 : listUserHalfDay.hashCode());
		result = prime * result + ((listUserWorkFullDay == null) ? 0 : listUserWorkFullDay.hashCode());
		result = prime * result + ((regulation == null) ? 0 : regulation.hashCode());
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
		DateWorkEntity other = (DateWorkEntity) obj;
		if (idDateWork == null) {
			if (other.idDateWork != null)
				return false;
		} else if (!idDateWork.equals(other.idDateWork))
			return false;
		if (listUserHalfDay == null) {
			if (other.listUserHalfDay != null)
				return false;
		} else if (!listUserHalfDay.equals(other.listUserHalfDay))
			return false;
		if (listUserWorkFullDay == null) {
			if (other.listUserWorkFullDay != null)
				return false;
		} else if (!listUserWorkFullDay.equals(other.listUserWorkFullDay))
			return false;
		if (regulation == null) {
			if (other.regulation != null)
				return false;
		} else if (!regulation.equals(other.regulation))
			return false;
		return true;
	}

	public DateWorkEntity() {
		super();
	}
}
