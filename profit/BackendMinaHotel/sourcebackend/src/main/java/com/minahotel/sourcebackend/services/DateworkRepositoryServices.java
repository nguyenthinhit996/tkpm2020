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
import com.minahotel.sourcebackend.entities.DateWorkEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.DateworkRepository;

@Service
public class DateworkRepositoryServices implements MinaHotelServices {

	@Autowired
	DateworkRepository dateworkRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<DateWorkEntity> dsAll = new ArrayList<DateWorkEntity>();
		dateworkRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		
		LocalDate date = DateUtils.convertStringToLocalDateTime(String.valueOf(id[0]));		 
		Optional<DateWorkEntity> option = dateworkRepository.findByidDateWork(date);
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 DateWorkEntity dateWorkEntity =  (DateWorkEntity) minapojo;
			 dateworkRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			DateWorkEntity dateWorkEntity =  (DateWorkEntity) minapojo;
			Optional<DateWorkEntity> option = dateworkRepository.findByidDateWork(dateWorkEntity.getIdDateWork());
			if(option.isPresent()) {
				DateWorkEntity objectGeted = option.get();
				if(!dateWorkEntity.equals(objectGeted)) {
					 dateWorkEntity.setListUserHalfDay(objectGeted.getListUserHalfDay());
					 dateWorkEntity.setListUserWorkFullDay(objectGeted.getListUserWorkFullDay());
					 dateWorkEntity.setRegulation(objectGeted.getRegulation());
					 dateworkRepository.save(dateWorkEntity);
				}
			}else {
				dateworkRepository.save(dateWorkEntity);
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
			 LocalDate date = DateUtils.convertStringToLocalDateTime(String.valueOf(id[0]));
			 Optional<DateWorkEntity> optionGeted = dateworkRepository.findByidDateWork(date);
			 if(optionGeted.isPresent()) {
				 dateworkRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}
	
}
