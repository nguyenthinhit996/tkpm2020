package com.minahotel.sourcebackend.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDetailsServicesEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

@Entity
@Table(name = "detailservices")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDetailsServicesEntity")
public class DetailsServicesEntity extends MinaHoTelPojo {

	@EmbeddedId
	private CompositeKeyDetailsServicesEntity idDetailsServicesEntity;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "startrent")
	private LocalDateTime startRent;

	@Column(name = "endrent")
	private LocalDateTime endRent;

	@Column(name = "status", length = 50)
	private String status;

	@Column(name = "sumaryservices")
	private BigDecimal BigdesumaryMoneySerives;

	// FK DetailsServicesEntity to StaffEntity
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "idstaffservicesrepo", insertable = true, updatable = true)
	private StaffEntity staffService; // fk

	// PK FK DetailsServicesEntity to TicketBookingEntity
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@MapsId("idTicketBooking") // primary key and foriegn key
	@JoinColumn(name = "idticketbooking", insertable = false, updatable = false)
	private TicketBookingEntity ticketBookingindetail;

	
	// PK FK DetailsServicesEntity to TicketBookingEntity
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@MapsId("idProduct")
	@JoinColumn(name = "idproduct",  insertable = false, updatable = false) // primary key and foriegn key
	private ProductionEntity productDetail;

	/**
	 * @return the idDetailsServicesEntity
	 */
	public CompositeKeyDetailsServicesEntity getIdDetailsServicesEntity() {
		return idDetailsServicesEntity;
	}

	/**
	 * @param idDetailsServicesEntity the idDetailsServicesEntity to set
	 */
	public void setIdDetailsServicesEntity(CompositeKeyDetailsServicesEntity idDetailsServicesEntity) {
		this.idDetailsServicesEntity = idDetailsServicesEntity;
	}

	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	/**
	 * @return the startRent
	 */
	public LocalDateTime getStartRent() {
		return startRent;
	}

	/**
	 * @param startRent the startRent to set
	 */
	public void setStartRent(LocalDateTime startRent) {
		this.startRent = startRent;
	}

	/**
	 * @return the endRent
	 */
	public LocalDateTime getEndRent() {
		return endRent;
	}

	/**
	 * @param endRent the endRent to set
	 */
	public void setEndRent(LocalDateTime endRent) {
		this.endRent = endRent;
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
	 * @return the bigdesumaryMoneySerives
	 */
	public BigDecimal getBigdesumaryMoneySerives() {
		return BigdesumaryMoneySerives;
	}

	/**
	 * @param bigdesumaryMoneySerives the bigdesumaryMoneySerives to set
	 */
	public void setBigdesumaryMoneySerives(BigDecimal bigdesumaryMoneySerives) {
		BigdesumaryMoneySerives = bigdesumaryMoneySerives;
	}

	/**
	 * @return the staffService
	 */
	public StaffEntity getStaffService() {
		return staffService;
	}

	/**
	 * @param staffService the staffService to set
	 */
	public void setStaffService(StaffEntity staffService) {
		this.staffService = staffService;
	}

	/**
	 * @return the ticketBookingindetail
	 */
	public TicketBookingEntity getTicketBookingindetail() {
		return ticketBookingindetail;
	}

	/**
	 * @param ticketBookingindetail the ticketBookingindetail to set
	 */
	public void setTicketBookingindetail(TicketBookingEntity ticketBookingindetail) {
		this.ticketBookingindetail = ticketBookingindetail;
	}

	/**
	 * @return the productDetail
	 */
	public ProductionEntity getProductDetail() {
		return productDetail;
	}

	/**
	 * @param productDetail the productDetail to set
	 */
	public void setProductDetail(ProductionEntity productDetail) {
		this.productDetail = productDetail;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((BigdesumaryMoneySerives == null) ? 0 : BigdesumaryMoneySerives.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((endRent == null) ? 0 : endRent.hashCode());
		result = prime * result + ((idDetailsServicesEntity == null) ? 0 : idDetailsServicesEntity.hashCode());
		result = prime * result + ((productDetail == null) ? 0 : productDetail.hashCode());
		result = prime * result + ((staffService == null) ? 0 : staffService.hashCode());
		result = prime * result + ((startRent == null) ? 0 : startRent.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((ticketBookingindetail == null) ? 0 : ticketBookingindetail.hashCode());
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
		DetailsServicesEntity other = (DetailsServicesEntity) obj;
		if (BigdesumaryMoneySerives == null) {
			if (other.BigdesumaryMoneySerives != null)
				return false;
		} else if (!BigdesumaryMoneySerives.equals(other.BigdesumaryMoneySerives))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (endRent == null) {
			if (other.endRent != null)
				return false;
		} else if (!endRent.equals(other.endRent))
			return false;
		if (idDetailsServicesEntity == null) {
			if (other.idDetailsServicesEntity != null)
				return false;
		} else if (!idDetailsServicesEntity.equals(other.idDetailsServicesEntity))
			return false;
		if (productDetail == null) {
			if (other.productDetail != null)
				return false;
		} else if (!productDetail.equals(other.productDetail))
			return false;
		if (staffService == null) {
			if (other.staffService != null)
				return false;
		} else if (!staffService.equals(other.staffService))
			return false;
		if (startRent == null) {
			if (other.startRent != null)
				return false;
		} else if (!startRent.equals(other.startRent))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (ticketBookingindetail == null) {
			if (other.ticketBookingindetail != null)
				return false;
		} else if (!ticketBookingindetail.equals(other.ticketBookingindetail))
			return false;
		return true;
	}

	public DetailsServicesEntity() {
		super();
	}

	@Override
	public String toString() {
		return "DetailsServicesEntity [idDetailsServicesEntity=" + idDetailsServicesEntity + ", amount=" + amount
				+ ", startRent=" + startRent + ", endRent=" + endRent + ", status=" + status
				+ ", BigdesumaryMoneySerives=" + BigdesumaryMoneySerives + ", staffService=" + staffService
				+ ", ticketBookingindetail=" + ticketBookingindetail + ", productDetail=" + productDetail + "]";
	}
	
	
}
