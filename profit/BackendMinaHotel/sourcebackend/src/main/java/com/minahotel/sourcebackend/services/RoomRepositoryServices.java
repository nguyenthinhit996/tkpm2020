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
import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.RoomRepository;

@Service
public class RoomRepositoryServices implements MinaHotelServices {

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<RoomEntity> dsAll = new ArrayList<RoomEntity>();
		roomRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<RoomEntity> option = roomRepository.findByidRoom((Integer)id[0]);
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 RoomEntity dateWorkEntity =  (RoomEntity) minapojo;
			 roomRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
//			RoomEntity productionEntity =  (RoomEntity) minapojo;
//			Optional<RoomEntity> option = roomRepository.findByidProduct(productionEntity.getIdProduct());
//			if(option.isPresent()) {
//				ProductionEntity objectGeted = option.get();
//				if(!productionEntity.equals(objectGeted)) {
//					 
//				}
//			}else {
//				roomRepository.save(productionEntity);
//			}
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
			 Optional<RoomEntity> optionGeted = roomRepository.findByidRoom((Integer)id[0]);
			 if(optionGeted.isPresent()) {
				 roomRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}
	 
}
