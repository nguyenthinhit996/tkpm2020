package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Typeofroom;
import com.minahotel.sourcebackend.repository.TypeofroomRepository;

@Service
public class TypeofroomRepositoryServies implements MinaHotelServices{

	@Autowired
	TypeofroomRepository typeofroomRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Typeofroom>) typeofroomRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
		return (List<Typeofroom>) typeofroomRepository.findObjectById(id[0]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Typeofroom objectConvertFromMina = (Typeofroom) minapojo;
			typeofroomRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Typeofroom objectConvertFromMina = (Typeofroom) minapojo;
		Typeofroom result = typeofroomRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getNametypeofroom()).map( x ->{
			 x.setNumberinroom(objectConvertFromMina.getNumberinroom());
			 x.setRoomratescharge(objectConvertFromMina.getRoomratescharge());
			 x.setRoomratesdates(objectConvertFromMina.getRoomratesdates());
			 x.setRoomrateshours(objectConvertFromMina.getRoomrateshours());
			return typeofroomRepository.save(x);
		}).orElseGet(()->{
			return typeofroomRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		// TODO Auto-generated method stub
		
	}

}
