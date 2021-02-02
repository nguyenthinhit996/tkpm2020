package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Ticketbooking;
import com.minahotel.sourcebackend.repository.TicketbookingRepository;

@Service
public class TicketbookingRepositoryServices implements MinaHotelServices{

	@Autowired
	TicketbookingRepository ticketbookingRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Ticketbooking>) ticketbookingRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
		return (List<Ticketbooking>) ticketbookingRepository.findObjectById(id[0]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Ticketbooking objectConvertFromMina = (Ticketbooking) minapojo;
			ticketbookingRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Ticketbooking objectConvertFromMina = (Ticketbooking) minapojo;
		Ticketbooking result = ticketbookingRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIdticketbooking()).map( x ->{
			 x.setIdstaffreception(objectConvertFromMina.getIdstaffreception());
			 x.setIduserrentroom(objectConvertFromMina.getIduserrentroom());
			 x.setNumberroom(objectConvertFromMina.getNumberroom());
			 x.setTimestamprent(objectConvertFromMina.getTimestamprent());
			 x.setUsernamerentroom(objectConvertFromMina.getUsernamerentroom());
			 x.setNumberinroom(objectConvertFromMina.getNumberinroom());
			return ticketbookingRepository.save(x);
		}).orElseGet(()->{
			return ticketbookingRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		Ticketbooking objectConvertFromMina = (Ticketbooking) minapojo;
		ticketbookingRepository.delete(objectConvertFromMina);
	}
}
