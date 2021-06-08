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
import com.minahotel.sourcebackend.entities.CheckingOutRoomDamagedEntity;
import com.minahotel.sourcebackend.entities.TypeOfRoomEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.CheckingOutRoomDamagedRepository;
import com.minahotel.sourcebackend.repository.TypeofroomRepository;
 
@Service
public class CheckingOutRoomDamagedRepositoryServices implements MinaHotelServices{

	@Autowired
	CheckingOutRoomDamagedRepository checkingOutRoomDamagedRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {	
		List<CheckingOutRoomDamagedEntity> dsAll = new ArrayList<CheckingOutRoomDamagedEntity>();
		try {
			checkingOutRoomDamagedRepository.findAll().forEach(dsAll::add);
			if(dsAll.size() == 0 ) {
				throw new NotFoundItemException(CodeErrorException.EN_001);
			}
		}catch (Exception e) {
			throw new BusinessException(e);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<CheckingOutRoomDamagedEntity> option = checkingOutRoomDamagedRepository.findByidCheckoutRoomDamaged(String.valueOf(id[0]));
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 CheckingOutRoomDamagedEntity dateWorkEntity =  (CheckingOutRoomDamagedEntity) minapojo;
			 checkingOutRoomDamagedRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			CheckingOutRoomDamagedEntity damaged =  (CheckingOutRoomDamagedEntity) minapojo;
			Optional<CheckingOutRoomDamagedEntity> option = checkingOutRoomDamagedRepository.findByidCheckoutRoomDamaged(damaged.getIdCheckoutRoomDamaged());
			if(option.isPresent()) {
				CheckingOutRoomDamagedEntity objectGeted = option.get();
				if(!damaged.equals(objectGeted)) {
					objectGeted.setListProductDamaded(damaged.getListProductDamaded());
					objectGeted.setSumaryIndemnify(damaged.getSumaryIndemnify());					
					checkingOutRoomDamagedRepository.save(objectGeted);
				}
			}else {
				checkingOutRoomDamagedRepository.save(damaged);
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
			 Optional<CheckingOutRoomDamagedEntity> optionGeted = checkingOutRoomDamagedRepository.findByidCheckoutRoomDamaged(String.valueOf(id[0]));
			 if(optionGeted.isPresent()) {
				 checkingOutRoomDamagedRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}
}
