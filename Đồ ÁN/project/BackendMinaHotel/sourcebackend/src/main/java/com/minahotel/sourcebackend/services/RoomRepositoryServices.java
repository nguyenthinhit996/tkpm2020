package com.minahotel.sourcebackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Room;
import com.minahotel.sourcebackend.repository.RoomRepository;

@Service
public class RoomRepositoryServices implements MinaHotelServices{

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		return (List<Room>) roomRepository.findAll();
	}

	@Override
	public List<? extends MinaHoTelPojo> getObjectById(String... id) {
		return (List<Room>) roomRepository.findObjectById(id[0]);
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			Room objectConvertFromMina = (Room) minapojo;
			roomRepository.save(objectConvertFromMina);
		}catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		Room objectConvertFromMina = (Room) minapojo;
		Room result = roomRepository.findObjectByIdOnlyOne(
				objectConvertFromMina.getIdroom()).map( x ->{
			 x.setNametyperoom(objectConvertFromMina.getNametyperoom());
			 x.setStatus(objectConvertFromMina.getStatus());
			return roomRepository.save(x);
		}).orElseGet(()->{
			return roomRepository.save(objectConvertFromMina);
		});
		return result != null ? true : false;
	}

	@Override
	public void deleteObject(MinaHoTelPojo minapojo) {
		Room objectConvertFromMina = (Room) minapojo;
		roomRepository.delete(objectConvertFromMina);
	}

}
