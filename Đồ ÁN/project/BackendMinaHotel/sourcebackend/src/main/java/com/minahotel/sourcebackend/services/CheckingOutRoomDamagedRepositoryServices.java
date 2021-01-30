package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.CheckingOutRoomDamaged;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.CheckingOutRoomDamagedRepository;
 
@Service
public class CheckingOutRoomDamagedRepositoryServices implements MinaHotelServices{

	@Autowired
	CheckingOutRoomDamagedRepository checkingOutRoomDamagedRepository;

	public CheckingOutRoomDamagedRepositoryServices() {
		super();
	}
 
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			CheckingOutRoomDamaged objectConvertFromMina = (CheckingOutRoomDamaged) minapojo;
			checkingOutRoomDamagedRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		CheckingOutRoomDamaged objectConvertFromMina = (CheckingOutRoomDamaged) minapojo;
		CheckingOutRoomDamaged result = checkingOutRoomDamagedRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIdcheckingoutroomdamaded()).map( x ->{
			 x.setIdcheckingoutroomdamaded(objectConvertFromMina.getIdcheckingoutroomdamaded());
			 x.setIdcheckoutroom(objectConvertFromMina.getIdcheckoutroom());
			 x.setIdstaffchecking(objectConvertFromMina.getIdstaffchecking());
			 x.setListproductdamaded(objectConvertFromMina.getListproductdamaded());
			 x.setSumaryindemnify(objectConvertFromMina.getSumaryindemnify());
			return checkingOutRoomDamagedRepository.save(x);
		}).orElseGet(()->{
			return checkingOutRoomDamagedRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}
	
	public void deleteObject(MinaHoTelPojo minapojo) {
		CheckingOutRoomDamaged objectConvertFromMina = (CheckingOutRoomDamaged) minapojo;
		checkingOutRoomDamagedRepository.delete(objectConvertFromMina);
	}


	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<CheckingOutRoomDamaged>) checkingOutRoomDamagedRepository.findAll();
	}


	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String ...id) {
		int intId= Integer.valueOf(id[0]);
		return checkingOutRoomDamagedRepository.findObjectById(intId);
	}
	
}
