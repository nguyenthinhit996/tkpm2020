package com.minahotel.sourcebackend.entities.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKeyDetailsServicesEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5440899625525947199L;
	
	@Column(name = "idticketbooking")
	private String idTicketBooking;
	
	@Column(name = "idproduct")
	private String idProduct;

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
	 * @return the idProduct
	 */
	public String getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct the idProduct to set
	 */
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
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
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
		result = prime * result + ((idTicketBooking == null) ? 0 : idTicketBooking.hashCode());
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
		CompositeKeyDetailsServicesEntity other = (CompositeKeyDetailsServicesEntity) obj;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		if (idTicketBooking == null) {
			if (other.idTicketBooking != null)
				return false;
		} else if (!idTicketBooking.equals(other.idTicketBooking))
			return false;
		return true;
	}

	public CompositeKeyDetailsServicesEntity() {
		super();
	}
}
