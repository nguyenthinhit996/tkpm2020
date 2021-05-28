package com.minahotel.sourcebackend.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staff extends MinaHoTelPojo{

	@Id
	private String idstaff;
	private String username;
	private String pass;
	private String role;
	private LocalDate datework;
	private BigDecimal salarymonth;
	private BigDecimal bonussalary;
	private String status;
	
 

	public Staff(String idstaff, String username, String pass, String role, LocalDate datework, BigDecimal salarymonth,
			BigDecimal bonussalary, String status) {
		super();
		this.idstaff = idstaff;
		this.username = username;
		this.pass = pass;
		this.role = role;
		this.datework = datework;
		this.salarymonth = salarymonth;
		this.bonussalary = bonussalary;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Staff() {
		super();
	}

	public String getIdstaff() {
		return idstaff;
	}

	public void setIdstaff(String idstaff) {
		this.idstaff = idstaff;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getDatework() {
		return datework;
	}

	public void setDatework(LocalDate datework) {
		this.datework = datework;
	}

	public BigDecimal getSalarymonth() {
		return salarymonth;
	}

	public void setSalarymonth(BigDecimal salarymonth) {
		this.salarymonth = salarymonth;
	}

	public BigDecimal getBonussalary() {
		return bonussalary;
	}

	public void setBonussalary(BigDecimal bonussalary) {
		this.bonussalary = bonussalary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bonussalary == null) ? 0 : bonussalary.hashCode());
		result = prime * result + ((datework == null) ? 0 : datework.hashCode());
		result = prime * result + ((idstaff == null) ? 0 : idstaff.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((salarymonth == null) ? 0 : salarymonth.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Staff other = (Staff) obj;
		if (bonussalary == null) {
			if (other.bonussalary != null)
				return false;
		} else if (!bonussalary.equals(other.bonussalary))
			return false;
		if (datework == null) {
			if (other.datework != null)
				return false;
		} else if (!datework.equals(other.datework))
			return false;
		if (idstaff == null) {
			if (other.idstaff != null)
				return false;
		} else if (!idstaff.equals(other.idstaff))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (salarymonth == null) {
			if (other.salarymonth != null)
				return false;
		} else if (!salarymonth.equals(other.salarymonth))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Staff [idstaff=" + idstaff + ", username=" + username + ", pass=" + pass + ", role=" + role
				+ ", datework=" + datework + ", salarymonth=" + salarymonth + ", bonussalary=" + bonussalary
				+ ", status=" + status + "]";
	}
	
	
	
	
}
