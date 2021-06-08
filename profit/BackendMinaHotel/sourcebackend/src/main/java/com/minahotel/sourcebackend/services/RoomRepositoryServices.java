package com.minahotel.sourcebackend.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.pojo.DetailRoom;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.RoomRepository;
import com.minahotel.sourcebackend.repository.TicketbookingRepository;
import com.minahotel.sourcebackend.repository.TicketcheckoutroomRepository;

@Service
public class RoomRepositoryServices implements MinaHotelServices {

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	TicketbookingRepository ticketbookingRepository;

	@Autowired
	TicketcheckoutroomRepository ticketcheckoutroomRepository;

	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		List<RoomEntity> dsAll = new ArrayList<RoomEntity>();
		roomRepository.findAll().forEach(dsAll::add);
		if (dsAll.size() == 0) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object... id) {
		Optional<RoomEntity> option = roomRepository.findByidRoom((Integer) id[0]);
		if (option.isPresent()) {
			return option.get();
		} else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			RoomEntity dateWorkEntity = (RoomEntity) minapojo;
			roomRepository.save(dateWorkEntity);
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
//			RoomEntity productionEntity =  (RoomEntity) minapojo;
//			Optional<RoomEntity> option = roomRepository.findByidProduct(productionEntity.getIdProduct());
//			if(option.isPresent()) {
//				ProductionEntity objectGeted = option.get();
//				if(!productionEntity.equals(objectGeted)) {
//					 
//				}
//			}else {
//				roomRepository.save(productionEntity);
//			}
		} catch (IllegalArgumentException e) {
			throw new BusinessException(CodeErrorException.ES_003);
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean deleteObjectById(Object... id) {
		try {
			Optional<RoomEntity> optionGeted = roomRepository.findByidRoom((Integer) id[0]);
			if (optionGeted.isPresent()) {
				roomRepository.delete(optionGeted.get());
				return true;
			}
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}

	public List<DetailRoom> getALlDetailAllRoom() {
	 
		List<DetailRoom> resultReturnList = new ArrayList<DetailRoom>();
		try {
			// get all Rooom
			List<RoomEntity> lsAllRoom = roomRepository.findAll();

			// get all ticket checking room status on
			List<TicketBookingEntity> allTickStatusOn = ticketbookingRepository.findAllTicketBookingStatusON();

			// get all ticket checkout status clean
			List<TicketCheckOutRoomEntity> allTicketCheckOutCLean = ticketcheckoutroomRepository
					.finAllTicketCheckoutStatusClean();

			lsAllRoom.forEach(room -> {

				DetailRoom detailRoom = new DetailRoom();

				Optional<TicketBookingEntity> roomChecking = allTickStatusOn.stream().filter(ticketBooking -> {
					return ticketBooking.getRoomRent().getIdRoom() == room.getIdRoom();
				}).findFirst();

				if (roomChecking.isPresent()) {
					detailRoom.setIsClean(false);
					detailRoom.setIsEmpty(false);
					detailRoom.setIsFull(true);
					detailRoom.setIdticketbooking(roomChecking.get().getIdTicketBooking());
					detailRoom.setIdHashCode("none");
					detailRoom.setNameRent(roomChecking.get().getUserNameRentRoom());
					detailRoom.setNumberRoom(roomChecking.get().getRoomRent().getIdRoom());
					detailRoom.setTimeClean("none");
					Duration timeRent = Duration.between(roomChecking.get().getTimeStartRentRoom(),
							LocalDateTime.now());
					String time = timeRent.toDaysPart() + ":" + timeRent.toHoursPart() + ":" + timeRent.toMinutesPart();
					detailRoom.setTimeStartRent(roomChecking.get().getTimeStartRentRoom());
					detailRoom.setTimeRent(time);
					detailRoom.setTimeStartClean(null);
					detailRoom.setTypeRoom(room.getTypeOfRoom().getIdNameTypeOfRoom());
				} else {
					// find room in list room clean
					Optional<TicketCheckOutRoomEntity> cleanRoom = allTicketCheckOutCLean.stream().filter(cleanroom -> {
						return cleanroom.getnumberRoomRent() == room.getIdRoom();
					}).findFirst();

					if (cleanRoom.isPresent()) {
						detailRoom.setIsClean(true);
						detailRoom.setIsEmpty(false);
						detailRoom.setIsFull(false);
						detailRoom.setIdticketbooking(cleanRoom.get().getTicketBooking().getIdTicketBooking());
						detailRoom.setNameRent(cleanRoom.get().getTicketBooking().getUserNameRentRoom());
						detailRoom.setNumberRoom(cleanRoom.get().getnumberRoomRent());
						Duration timeRent = Duration.between(cleanRoom.get().getTimeEndRentRoom(), LocalDateTime.now());
						String time = timeRent.toDaysPart() + ":" + timeRent.toHoursPart() + ":"
								+ timeRent.toMinutesPart();
						detailRoom.setTimeClean(time);
						detailRoom.setTimeRent("none");
						detailRoom.setTypeRoom(
								cleanRoom.get().getTicketBooking().getRoomRent().getTypeOfRoom().getIdNameTypeOfRoom());
						detailRoom.setTimeStartClean(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
						detailRoom.setNameCLean(cleanRoom.get().getStaffCheckoutRoom().getNameStaff());
					} else {
						// Room is emplty
						detailRoom.setIsClean(false);
						detailRoom.setIsEmpty(true);
						detailRoom.setIsFull(false);
						detailRoom.setNumberRoom(room.getIdRoom());
						detailRoom.setTypeRoom(room.getTypeOfRoom().getIdNameTypeOfRoom());
					}
				}

				resultReturnList.add(detailRoom);
			});
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		
		return resultReturnList;
	}
}
