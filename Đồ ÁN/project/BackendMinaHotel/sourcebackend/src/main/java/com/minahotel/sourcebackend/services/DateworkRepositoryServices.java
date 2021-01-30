package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.Datework;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.DateworkRepository;

@Service
public class DateworkRepositoryServices implements MinaHotelServices{

	@Autowired
	DateworkRepository dateworkRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Datework>) dateworkRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
		return dateworkRepository.findObjectById(id[0]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Datework objectConvertFromMina = (Datework) minapojo;
			dateworkRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Datework objectConvertFromMina = (Datework) minapojo;
		Datework result = dateworkRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIddatework()).map( x ->{
				x.setListuserhalfday(objectConvertFromMina.getListuserhalfday());	
				x.setListuserworkfullday(objectConvertFromMina.getListuserworkfullday());
				x.setIddatework(null);
			return dateworkRepository.save(x);
		}).orElseGet(()->{
			return dateworkRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		Datework objectConvertFromMina = (Datework) minapojo;
		dateworkRepository.delete(objectConvertFromMina);
	}

}
