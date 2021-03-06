package com.minahotel.sourcebackend.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.TicketbookingRepository;

/**
 * TicketbookingRepositoryServices is class to handle logic Ticketbooking room. 
 * @author Peter
 *
 */
@Service
public class TicketbookingRepositoryServices implements MinaHotelServices{

	private static Logger LOG = LoggerFactory.getLogger(TicketbookingRepositoryServices.class);
	
	@Autowired
	TicketbookingRepository ticketbookingRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() throws NotFoundItemException{		
		List<TicketBookingEntity> dsAll = new ArrayList<TicketBookingEntity>();
		ticketbookingRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		LOG.info("All");
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<TicketBookingEntity> option = ticketbookingRepository.findByidTicketBooking(String.valueOf(id[0]));
		if(option.isPresent()) {
			 return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 TicketBookingEntity ticketBookingEntity =  (TicketBookingEntity) minapojo;
			 StaffEntity staff = entityManager.getReference(StaffEntity.class, ticketBookingEntity.getStaffReception().getIdStaff());
			 RoomEntity room = entityManager.getReference(RoomEntity.class, ticketBookingEntity.getRoomRent().getIdRoom());
			 ticketBookingEntity.setStaffReception(staff);
			 ticketBookingEntity.setRoomRent(room);
			 ticketBookingEntity.setIdTicketBooking(String.valueOf(LocalDateTime.now()));
			 ticketBookingEntity.setTimeStartRentRoom(LocalDateTime.now());
			 ticketbookingRepository.save(ticketBookingEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			TicketBookingEntity ticketBookingEntityNeedUpdate =  (TicketBookingEntity) minapojo;
			Optional<TicketBookingEntity> option = ticketbookingRepository.findByidTicketBooking(ticketBookingEntityNeedUpdate.getIdTicketBooking());
			if(option.isPresent()) {
				TicketBookingEntity objectGeted = option.get();
				if(!ticketBookingEntityNeedUpdate.equals(objectGeted)) {
					objectGeted.setIdUserRentRoom(ticketBookingEntityNeedUpdate.getIdUserRentRoom());
					objectGeted.setNumberPeopleInRoom(ticketBookingEntityNeedUpdate.getNumberPeopleInRoom());
					objectGeted.setRoomRent(ticketBookingEntityNeedUpdate.getRoomRent());
					objectGeted.setStaffReception(ticketBookingEntityNeedUpdate.getStaffReception());
					objectGeted.setStatus(ticketBookingEntityNeedUpdate.getStatus());
					objectGeted.setTimeStartRentRoom(ticketBookingEntityNeedUpdate.getTimeStartRentRoom());
					objectGeted.setUserNameRentRoom(ticketBookingEntityNeedUpdate.getUserNameRentRoom());
					ticketbookingRepository.save(objectGeted);
				}
			}else {
				ticketbookingRepository.save(ticketBookingEntityNeedUpdate);
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
			 Optional<TicketBookingEntity> optionGeted = ticketbookingRepository.findByidTicketBooking(String.valueOf(id[0]));
			 if(optionGeted.isPresent()) {
				 ticketbookingRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;
	}
}
