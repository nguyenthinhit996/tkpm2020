package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Ticketcheckoutroom;
import com.minahotel.sourcebackend.repository.TicketcheckoutroomRepository;

@Service
public class TicketcheckoutroomRepositoryServices implements MinaHotelServices{

	@Autowired
	TicketcheckoutroomRepository ticketcheckoutroomRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Ticketcheckoutroom>) ticketcheckoutroomRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
		return (List<Ticketcheckoutroom>) ticketcheckoutroomRepository.findObjectById(id[0]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Ticketcheckoutroom objectConvertFromMina = (Ticketcheckoutroom) minapojo;
			ticketcheckoutroomRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Ticketcheckoutroom objectConvertFromMina = (Ticketcheckoutroom) minapojo;
		Ticketcheckoutroom result = ticketcheckoutroomRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIdticketcheckoutroom()).map( x ->{
			 x.setIdstaffreceptionsupport(objectConvertFromMina.getIdstaffreceptionsupport());
			 x.setIdticketbooking(objectConvertFromMina.getIdticketbooking());
			 x.setNumberroomrent(objectConvertFromMina.getNumberroomrent());
			 x.setSumaryratesandservices(objectConvertFromMina.getSumaryratesandservices());
			 x.setTimeendrent(objectConvertFromMina.getTimeendrent());
			return ticketcheckoutroomRepository.save(x);
		}).orElseGet(()->{
			return ticketcheckoutroomRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		Ticketcheckoutroom objectConvertFromMina = (Ticketcheckoutroom) minapojo;
		ticketcheckoutroomRepository.delete(objectConvertFromMina);
	}

}
