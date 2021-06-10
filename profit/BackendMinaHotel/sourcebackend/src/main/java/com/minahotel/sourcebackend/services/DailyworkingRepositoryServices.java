package com.minahotel.sourcebackend.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.common.DateUtils;
import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.DailyWorkingEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDailyWorkingEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.DailyworkingRepository;

/**
 * DailyworkingRepositoryServices is class to handle logic belong daily work of  all staff, checking, checkout time work 
 * @author Peter
 *
 */
@Service
public class DailyworkingRepositoryServices implements MinaHotelServices {

	@Autowired
	DailyworkingRepository dailyworkingRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<DailyWorkingEntity> dsAll = new ArrayList<DailyWorkingEntity>();
		dailyworkingRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {

		LocalDate date = DateUtils.convertStringToLocalDateTime(String.valueOf(id[0]));
		CompositeKeyDailyWorkingEntity key = new CompositeKeyDailyWorkingEntity();
		key.setIdToDay(date);
		key.setIdStaffWork( String.valueOf(id[1]));
		Optional<DailyWorkingEntity> option = dailyworkingRepository.findByidDailyWorkingEntity(key);
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 DailyWorkingEntity dailyWorkingEntity =  (DailyWorkingEntity) minapojo;
			 dailyworkingRepository.save(dailyWorkingEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			DailyWorkingEntity dailyWorkingEntity =  (DailyWorkingEntity) minapojo;
			Optional<DailyWorkingEntity> option = dailyworkingRepository.findByidDailyWorkingEntity(dailyWorkingEntity.getIdDailyWorkingEntity());
			if(option.isPresent()) {
				DailyWorkingEntity objectGeted = option.get();
				if(!dailyWorkingEntity.equals(objectGeted)) {
					dailyWorkingEntity.setDateWorking(objectGeted.getDateWorking());
					dailyWorkingEntity.setNote(objectGeted.getNote());
					dailyWorkingEntity.setStaffManagement(objectGeted.getStaffManagement());
					dailyWorkingEntity.setStaffWoking(objectGeted.getStaffWoking());
					dailyWorkingEntity.setTimeEndWork(objectGeted.getTimeEndWork());
					dailyWorkingEntity.setTimeStartWork(objectGeted.getTimeStartWork());
					dailyWorkingEntity.setUserNameOfStaffWorking(objectGeted.getUserNameOfStaffWorking());
					dailyworkingRepository.save(dailyWorkingEntity);
				}
			}else {
				dailyworkingRepository.save(dailyWorkingEntity);
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
			CompositeKeyDailyWorkingEntity key = (CompositeKeyDailyWorkingEntity) id[0];
			 Optional<DailyWorkingEntity> optionGeted = dailyworkingRepository.findByidDailyWorkingEntity(key);
			 if(optionGeted.isPresent()) {
				 dailyworkingRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;
	}
}
