package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.Dailyworking;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.DailyworkingRepository;

@Service
public class DailyworkingRepositoryServices implements MinaHotelServices{
	
	@Autowired
	DailyworkingRepository dailyworkingRepository;

	public DailyworkingRepositoryServices() {
		super();
	}
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Dailyworking>) dailyworkingRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String ...listParameter) {
		return (List<Dailyworking>) dailyworkingRepository.findObjectById(listParameter[0],listParameter[1]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Dailyworking objectConvertFromMina = (Dailyworking) minapojo;
			dailyworkingRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Dailyworking objectConvertFromMina = (Dailyworking) minapojo;
		Dailyworking result = dailyworkingRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIdtoday(),objectConvertFromMina.getIdstaffwork()).map( x ->{
			 x.setIdstaffmanagement(objectConvertFromMina.getIdstaffmanagement());
			 x.setNote(objectConvertFromMina.getNote());
			 x.setTimeend(objectConvertFromMina.getTimeend());
			 x.setTimestart(objectConvertFromMina.getTimestart());
			return dailyworkingRepository.save(x);
		}).orElseGet(()->{
			return dailyworkingRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		Dailyworking objectConvertFromMina = (Dailyworking) minapojo;
		dailyworkingRepository.delete(objectConvertFromMina);
	}

	
}
