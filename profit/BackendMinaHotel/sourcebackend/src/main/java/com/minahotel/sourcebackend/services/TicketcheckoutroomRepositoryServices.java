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
import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.TicketcheckoutroomRepository;

@Service
public class TicketcheckoutroomRepositoryServices implements MinaHotelServices {

	@Autowired
	TicketcheckoutroomRepository ticketcheckoutroomRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<TicketCheckOutRoomEntity> dsAll = new ArrayList<TicketCheckOutRoomEntity>();
		ticketcheckoutroomRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<TicketCheckOutRoomEntity> option = ticketcheckoutroomRepository.findByidTicketCheckout(String.valueOf(id[0]));
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 TicketCheckOutRoomEntity dateWorkEntity =  (TicketCheckOutRoomEntity) minapojo;
			 ticketcheckoutroomRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			TicketCheckOutRoomEntity ticketCheckOutRoomEntityNeedUpdate =  (TicketCheckOutRoomEntity) minapojo;
			Optional<TicketCheckOutRoomEntity> option = ticketcheckoutroomRepository.findByidTicketCheckout(ticketCheckOutRoomEntityNeedUpdate.getIdTicketCheckout());
			if(option.isPresent()) {
				TicketCheckOutRoomEntity objectGeted = option.get();
				if(!ticketCheckOutRoomEntityNeedUpdate.equals(objectGeted)) {
					 
					objectGeted.setNumberPeopleInRoom(ticketCheckOutRoomEntityNeedUpdate.getNumberPeopleInRoom());
					objectGeted.setRateRentRoom(ticketCheckOutRoomEntityNeedUpdate.getRateRentRoom());
					objectGeted.setRateRoomDamaged(ticketCheckOutRoomEntityNeedUpdate.getRateRoomDamaged());
					objectGeted.setRateRoomSubCharge(ticketCheckOutRoomEntityNeedUpdate.getRateRoomSubCharge());
					objectGeted.setRateSevices(ticketCheckOutRoomEntityNeedUpdate.getRateSevices());
					objectGeted.setStatus(ticketCheckOutRoomEntityNeedUpdate.getStatus());
					objectGeted.setStaffReceptionCheckoutRoom(ticketCheckOutRoomEntityNeedUpdate.getStaffReceptionCheckoutRoom());
					objectGeted.setTicketBooking(ticketCheckOutRoomEntityNeedUpdate.getTicketBooking());
					
					ticketcheckoutroomRepository.save(objectGeted);
				}
			}else {
				ticketcheckoutroomRepository.save(ticketCheckOutRoomEntityNeedUpdate);
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
			 Optional<TicketCheckOutRoomEntity> optionGeted = ticketcheckoutroomRepository.findByidTicketCheckout(String.valueOf(id[0]));
			 if(optionGeted.isPresent()) {
				 ticketcheckoutroomRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	} 
}
