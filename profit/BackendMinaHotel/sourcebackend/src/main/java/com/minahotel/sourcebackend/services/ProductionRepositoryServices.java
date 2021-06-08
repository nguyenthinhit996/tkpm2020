package com.minahotel.sourcebackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.ProductionEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.ProductionRepository;

@Service
public class ProductionRepositoryServices implements MinaHotelServices{

	@Autowired
	ProductionRepository productionRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<ProductionEntity> dsAll = new ArrayList<ProductionEntity>();
		productionRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<ProductionEntity> option = productionRepository.findByidProduct(String.valueOf(id[0]));
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 ProductionEntity dateWorkEntity =  (ProductionEntity) minapojo;
			 productionRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			ProductionEntity productionEntity =  (ProductionEntity) minapojo;
			Optional<ProductionEntity> option = productionRepository.findByidProduct(productionEntity.getIdProduct());
			if(option.isPresent()) {
				ProductionEntity objectGeted = option.get();
				if(!productionEntity.equals(objectGeted)) {
					objectGeted.setDsDetailsServices(productionEntity.getDsDetailsServices());
					objectGeted.setExtensionProduct(productionEntity.getExtensionProduct());
					objectGeted.setImageProduct(productionEntity.getImageProduct());
					objectGeted.setNameProduct(productionEntity.getNameProduct());
					objectGeted.setProductRate(productionEntity.getProductRate());
					objectGeted.setStatus(productionEntity.getStatus());
					objectGeted.setTypeProduct(productionEntity.getTypeProduct());
					productionRepository.save(objectGeted);
				}
			}else {
				productionRepository.save(productionEntity);
			}
		}catch(IllegalArgumentException  e) {
			throw new BusinessException(CodeErrorException.ES_003);
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean deleteObjectById(Object... id) {
		try {
			 Optional<ProductionEntity> optionGeted = productionRepository.findByidProduct(String.valueOf(id[0]));
			 if(optionGeted.isPresent()) {
				 productionRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}
	 

}
