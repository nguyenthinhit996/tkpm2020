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
import com.minahotel.sourcebackend.entities.TypeOfRoomEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.TypeofroomRepository;

@Service
public class TypeofroomRepositoryServies implements MinaHotelServices{

	@Autowired
	TypeofroomRepository typeofroomRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<TypeOfRoomEntity> dsAll = new ArrayList<TypeOfRoomEntity>();
		typeofroomRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<TypeOfRoomEntity> option = typeofroomRepository.findByidNameTypeOfRoom(String.valueOf(id[0]));
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 TypeOfRoomEntity dateWorkEntity =  (TypeOfRoomEntity) minapojo;
			 typeofroomRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			TypeOfRoomEntity productionEntity =  (TypeOfRoomEntity) minapojo;
			Optional<TypeOfRoomEntity> option = typeofroomRepository.findByidNameTypeOfRoom(productionEntity.getIdNameTypeOfRoom());
			if(option.isPresent()) {
				TypeOfRoomEntity objectGeted = option.get();
				if(!productionEntity.equals(objectGeted)) {
					 
					objectGeted.setIdNameTypeOfRoom(productionEntity.getIdNameTypeOfRoom());
					objectGeted.setNumberInRoom(productionEntity.getNumberInRoom());
					objectGeted.setRoomRateDate(productionEntity.getRoomRateDate());
					objectGeted.setRoomRatesCharge(productionEntity.getRoomRatesCharge());
					objectGeted.setRoomRatesHour(productionEntity.getRoomRatesCharge());
					
					typeofroomRepository.save(objectGeted);
				}
			}else {
				typeofroomRepository.save(productionEntity);
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
			 Optional<TypeOfRoomEntity> optionGeted = typeofroomRepository.findByidNameTypeOfRoom(String.valueOf(id[0]));
			 if(optionGeted.isPresent()) {
				 typeofroomRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}
	 
}
