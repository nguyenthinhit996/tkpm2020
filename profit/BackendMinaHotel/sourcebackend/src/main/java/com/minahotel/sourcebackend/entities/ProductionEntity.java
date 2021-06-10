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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * ProductionEntity is mapping with table production in Database
 * @author Peter
 *
 */
@Entity
@Table(name = "production")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idProduct")
public class ProductionEntity extends MinaHoTelPojo {

	/**
	 * idproduction is primary key column in Database
	 */
	@Id
	@Column(name = "idproduction", length = 50)
	private String idProduct;

	@Column(name = "nameproduct", length = 255)
	private String nameProduct;

	@Column(name = "extention", length = 455)
	private String extensionProduct;

	@Column(name = "productrates")
	private BigDecimal productRate;

	@Column(name = "img", length = 1024)
	private String imageProduct;

	@Column(name = "type", length = 45)
	private String typeProduct;

	@Column(name = "status", length = 45)
	private String status;

	@JsonIgnore
	// Reference by DetailsServicesEntity to ProductionEntity
	@OneToMany(mappedBy = "productDetail", cascade = CascadeType.MERGE, targetEntity = DetailsServicesEntity.class, fetch = FetchType.LAZY)
	List<DetailsServicesEntity> dsDetailsServices;

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
	 * @return the nameProduct
	 */
	public String getNameProduct() {
		return nameProduct;
	}

	/**
	 * @param nameProduct the nameProduct to set
	 */
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	/**
	 * @return the extensionProduct
	 */
	public String getExtensionProduct() {
		return extensionProduct;
	}

	/**
	 * @param extensionProduct the extensionProduct to set
	 */
	public void setExtensionProduct(String extensionProduct) {
		this.extensionProduct = extensionProduct;
	}

	/**
	 * @return the productRate
	 */
	public BigDecimal getProductRate() {
		return productRate;
	}

	/**
	 * @param productRate the productRate to set
	 */
	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	/**
	 * @return the imageProduct
	 */
	public String getImageProduct() {
		return imageProduct;
	}

	/**
	 * @param imageProduct the imageProduct to set
	 */
	public void setImageProduct(String imageProduct) {
		this.imageProduct = imageProduct;
	}

	/**
	 * @return the typeProduct
	 */
	public String getTypeProduct() {
		return typeProduct;
	}

	/**
	 * @param typeProduct the typeProduct to set
	 */
	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((extensionProduct == null) ? 0 : extensionProduct.hashCode());
		result = prime * result + ((idProduct == null) ? 0 : idProduct.hashCode());
		result = prime * result + ((imageProduct == null) ? 0 : imageProduct.hashCode());
		result = prime * result + ((nameProduct == null) ? 0 : nameProduct.hashCode());
		result = prime * result + ((productRate == null) ? 0 : productRate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((typeProduct == null) ? 0 : typeProduct.hashCode());
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
		ProductionEntity other = (ProductionEntity) obj;
		if (extensionProduct == null) {
			if (other.extensionProduct != null)
				return false;
		} else if (!extensionProduct.equals(other.extensionProduct))
			return false;
		if (idProduct == null) {
			if (other.idProduct != null)
				return false;
		} else if (!idProduct.equals(other.idProduct))
			return false;
		if (imageProduct == null) {
			if (other.imageProduct != null)
				return false;
		} else if (!imageProduct.equals(other.imageProduct))
			return false;
		if (nameProduct == null) {
			if (other.nameProduct != null)
				return false;
		} else if (!nameProduct.equals(other.nameProduct))
			return false;
		if (productRate == null) {
			if (other.productRate != null)
				return false;
		} else if (!productRate.equals(other.productRate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (typeProduct == null) {
			if (other.typeProduct != null)
				return false;
		} else if (!typeProduct.equals(other.typeProduct))
			return false;
		return true;
	}

	public ProductionEntity() {
		super();
	}
}
