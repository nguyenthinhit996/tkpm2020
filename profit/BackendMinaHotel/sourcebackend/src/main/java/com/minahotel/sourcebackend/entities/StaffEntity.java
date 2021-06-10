package com.minahotel.sourcebackend.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * StaffEntity is mapping with table staff in Database
 * @author Peter
 *
 */
@Entity
@Table(name = "staff")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idStaff")
public class StaffEntity extends MinaHoTelPojo {

	/**
	 * idstaff is primary key column in Database
	 */
	@Id
	@Column(name = "idstaff")
	private String idStaff;

	@Column(name = "username", length = 255)
	private String nameStaff;

	@Column(name = "pass", length = 500)
	private String passWordStaff;

	@Column(name = "role", length = 45)
	private String roleOfStaff;

	@Column(name = "datework")
	private LocalDate dateStartWork;

	@Column(name = "salarymonth")
	private BigDecimal salaryStaffByMonth;

	@Column(name = "bonussalary")
	private BigDecimal bonussalary;

	@Column(name = "status", length = 45)
	private String status;

	// Reference
	// staff checking room damaged wok
	@JsonIgnore
	@OneToMany(mappedBy = "staffCheckOutRoomDamaged", targetEntity = CheckingOutRoomDamagedEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<CheckingOutRoomDamagedEntity> dsCheckRoomDamaged;

	// staff managerment wok
	@JsonIgnore
	@OneToMany(mappedBy = "staffManagement", targetEntity = DailyWorkingEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<DailyWorkingEntity> dsManagementStaffWorking;

	// staff daily woking
	@JsonIgnore
	@OneToMany(mappedBy = "staffWoking", targetEntity = DailyWorkingEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<DailyWorkingEntity> dsStaffwork;
	
	// staff service woking
	@JsonIgnore
	@OneToMany(mappedBy = "staffService", targetEntity = DetailsServicesEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<DetailsServicesEntity> dsDetailsServices;
	
	// staff reception
	@JsonIgnore
	@OneToMany(mappedBy = "staffReception", targetEntity = TicketBookingEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<TicketBookingEntity> dsTicketBooking;
	
	// staff checking room checkout
	@JsonIgnore
	@OneToMany(mappedBy = "staffCheckoutRoom", targetEntity = TicketCheckOutRoomEntity.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<TicketCheckOutRoomEntity> dsRoomCheckout;

	
	/**
	 * @return the idStaff
	 */
	public String getIdStaff() {
		return idStaff;
	}

	/**
	 * @param idStaff the idStaff to set
	 */
	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}

	/**
	 * @return the nameStaff
	 */
	public String getNameStaff() {
		return nameStaff;
	}

	/**
	 * @param nameStaff the nameStaff to set
	 */
	public void setNameStaff(String nameStaff) {
		this.nameStaff = nameStaff;
	}

	/**
	 * @return the passWordStaff
	 */
	public String getPassWordStaff() {
		return passWordStaff;
	}

	/**
	 * @param passWordStaff the passWordStaff to set
	 */
	public void setPassWordStaff(String passWordStaff) {
		this.passWordStaff = passWordStaff;
	}

	/**
	 * @return the roleOfStaff
	 */
	public String getRoleOfStaff() {
		return roleOfStaff;
	}

	/**
	 * @param roleOfStaff the roleOfStaff to set
	 */
	public void setRoleOfStaff(String roleOfStaff) {
		this.roleOfStaff = roleOfStaff;
	}

	/**
	 * @return the dateStartWork
	 */
	public LocalDate getDateStartWork() {
		return dateStartWork;
	}

	/**
	 * @param dateStartWork the dateStartWork to set
	 */
	public void setDateStartWork(LocalDate dateStartWork) {
		this.dateStartWork = dateStartWork;
	}

	/**
	 * @return the salaryStaffByMonth
	 */
	public BigDecimal getSalaryStaffByMonth() {
		return salaryStaffByMonth;
	}

	/**
	 * @param salaryStaffByMonth the salaryStaffByMonth to set
	 */
	public void setSalaryStaffByMonth(BigDecimal salaryStaffByMonth) {
		this.salaryStaffByMonth = salaryStaffByMonth;
	}

	/**
	 * @return the bonussalary
	 */
	public BigDecimal getBonussalary() {
		return bonussalary;
	}

	/**
	 * @param bonussalary the bonussalary to set
	 */
	public void setBonussalary(BigDecimal bonussalary) {
		this.bonussalary = bonussalary;
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
	 * @return the dsCheckRoomDamaged
	 */
	public List<CheckingOutRoomDamagedEntity> getDsCheckRoomDamaged() {
		return dsCheckRoomDamaged;
	}

	/**
	 * @param dsCheckRoomDamaged the dsCheckRoomDamaged to set
	 */
	public void setDsCheckRoomDamaged(List<CheckingOutRoomDamagedEntity> dsCheckRoomDamaged) {
		this.dsCheckRoomDamaged = dsCheckRoomDamaged;
	}

	/**
	 * @return the dsManagementStaffWorking
	 */
	public List<DailyWorkingEntity> getDsManagementStaffWorking() {
		return dsManagementStaffWorking;
	}

	/**
	 * @param dsManagementStaffWorking the dsManagementStaffWorking to set
	 */
	public void setDsManagementStaffWorking(List<DailyWorkingEntity> dsManagementStaffWorking) {
		this.dsManagementStaffWorking = dsManagementStaffWorking;
	}

	/**
	 * @return the dsStaffwork
	 */
	public List<DailyWorkingEntity> getDsStaffwork() {
		return dsStaffwork;
	}

	/**
	 * @param dsStaffwork the dsStaffwork to set
	 */
	public void setDsStaffwork(List<DailyWorkingEntity> dsStaffwork) {
		this.dsStaffwork = dsStaffwork;
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
	 * @return the dsTicketBooking
	 */
	public List<TicketBookingEntity> getDsTicketBooking() {
		return dsTicketBooking;
	}

	/**
	 * @param dsTicketBooking the dsTicketBooking to set
	 */
	public void setDsTicketBooking(List<TicketBookingEntity> dsTicketBooking) {
		this.dsTicketBooking = dsTicketBooking;
	}

	/**
	 * @return the dsRoomCheckout
	 */
	public List<TicketCheckOutRoomEntity> getDsRoomCheckout() {
		return dsRoomCheckout;
	}

	/**
	 * @param dsRoomCheckout the dsRoomCheckout to set
	 */
	public void setDsRoomCheckout(List<TicketCheckOutRoomEntity> dsRoomCheckout) {
		this.dsRoomCheckout = dsRoomCheckout;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bonussalary == null) ? 0 : bonussalary.hashCode());
		result = prime * result + ((idStaff == null) ? 0 : idStaff.hashCode());
		result = prime * result + ((nameStaff == null) ? 0 : nameStaff.hashCode());
		result = prime * result + ((passWordStaff == null) ? 0 : passWordStaff.hashCode());
		result = prime * result + ((roleOfStaff == null) ? 0 : roleOfStaff.hashCode());
		result = prime * result + ((salaryStaffByMonth == null) ? 0 : salaryStaffByMonth.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		StaffEntity other = (StaffEntity) obj;
		if (bonussalary == null) {
			if (other.bonussalary != null)
				return false;
		} else if (!bonussalary.equals(other.bonussalary))
			return false;
		if (idStaff == null) {
			if (other.idStaff != null)
				return false;
		} else if (!idStaff.equals(other.idStaff))
			return false;
		if (nameStaff == null) {
			if (other.nameStaff != null)
				return false;
		} else if (!nameStaff.equals(other.nameStaff))
			return false;
		if (passWordStaff == null) {
			if (other.passWordStaff != null)
				return false;
		} else if (!passWordStaff.equals(other.passWordStaff))
			return false;
		if (roleOfStaff == null) {
			if (other.roleOfStaff != null)
				return false;
		} else if (!roleOfStaff.equals(other.roleOfStaff))
			return false;
		if (salaryStaffByMonth == null) {
			if (other.salaryStaffByMonth != null)
				return false;
		} else if (!salaryStaffByMonth.equals(other.salaryStaffByMonth))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	public StaffEntity() {
		super();
	}

	@Override
	public String toString() {
		return "StaffEntity [idStaff=" + idStaff + ", nameStaff=" + nameStaff + ", passWordStaff=" + passWordStaff
				+ ", roleOfStaff=" + roleOfStaff + ", dateStartWork=" + dateStartWork + ", salaryStaffByMonth="
				+ salaryStaffByMonth + ", bonussalary=" + bonussalary + ", status=" + status + "]";
	}
}
