package com.minahotel.sourcebackend.services;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.CheckingOutRoomDamagedEntity;
import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.entities.TypeOfRoomEntity;
import com.minahotel.sourcebackend.enums.EnumCommon;
import com.minahotel.sourcebackend.enums.EnumDetailservicesStatus;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.TicketcheckoutroomFontEnd;
import com.minahotel.sourcebackend.repository.CheckingOutRoomDamagedRepository;
import com.minahotel.sourcebackend.repository.TicketbookingRepository;
import com.minahotel.sourcebackend.repository.TicketcheckoutroomRepository;

/**
 * TicketcheckoutroomRepositoryServices is class to handle logic Ticketcheckoutroom room .
 * @author Peter
 *
 */
@Service
public class TicketcheckoutroomRepositoryServices implements MinaHotelServices {

	@Autowired
	TicketcheckoutroomRepository ticketcheckoutroomRepository;

	@Autowired
	TicketbookingRepository ticketbookingRepository;

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	CheckingOutRoomDamagedRepository checkingOutRoomDamagedRepository;

	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		List<TicketCheckOutRoomEntity> dsAll = new ArrayList<TicketCheckOutRoomEntity>();
		ticketcheckoutroomRepository.findAll().forEach(dsAll::add);
		if (dsAll.size() == 0) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object... id) {
		Optional<TicketCheckOutRoomEntity> option = ticketcheckoutroomRepository
				.findByidTicketCheckout(String.valueOf(id[0]));
		if (option.isPresent()) {
			return option.get();
		} else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			TicketCheckOutRoomEntity ticketCheckOutRoomEntity = (TicketCheckOutRoomEntity) minapojo;
			StaffEntity staff = entityManager.getReference(StaffEntity.class, ticketCheckOutRoomEntity.getStaffCheckoutRoom().getIdStaff());
			TicketBookingEntity ticketBooking = entityManager.getReference(TicketBookingEntity.class, ticketCheckOutRoomEntity.getTicketBooking().getIdTicketBooking());
			ticketCheckOutRoomEntity.setIdTicketCheckout(String.valueOf(LocalDateTime.now()));
			ticketCheckOutRoomEntity.setStaffCheckoutRoom(staff);
			ticketCheckOutRoomEntity.setTicketBooking(ticketBooking);
			// cap nhat trang thai checking table off
			ticketBooking.setStatus(EnumCommon.OFF.getValue());
			ticketcheckoutroomRepository.save(ticketCheckOutRoomEntity);
			
			// update ticket checkout into table checkoudamged
			Optional<CheckingOutRoomDamagedEntity> damged =  checkingOutRoomDamagedRepository.findByidTicketBooking(ticketCheckOutRoomEntity.getTicketBooking().getIdTicketBooking());
			if(damged.isPresent()) {
				CheckingOutRoomDamagedEntity damagedObject = damged.get();
				damagedObject.setTicketCheckoutObject(ticketCheckOutRoomEntity);
				checkingOutRoomDamagedRepository.save(damagedObject);
			}else {
				return false;
			}
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			TicketCheckOutRoomEntity ticketCheckOutRoomEntityNeedUpdate = (TicketCheckOutRoomEntity) minapojo;
			Optional<TicketCheckOutRoomEntity> option = ticketcheckoutroomRepository
					.findByidTicketCheckout(ticketCheckOutRoomEntityNeedUpdate.getIdTicketCheckout());
			if (option.isPresent()) {
				TicketCheckOutRoomEntity objectGeted = option.get();
				if (!ticketCheckOutRoomEntityNeedUpdate.equals(objectGeted)) {

//					objectGeted.setNumberPeopleInRoom(ticketCheckOutRoomEntityNeedUpdate.getNumberPeopleInRoom());
//					objectGeted.setRateRentRoom(ticketCheckOutRoomEntityNeedUpdate.getRateRentRoom());
//					objectGeted.setRateRoomDamaged(ticketCheckOutRoomEntityNeedUpdate.getRateRoomDamaged());
//					objectGeted.setRateRoomSubCharge(ticketCheckOutRoomEntityNeedUpdate.getRateRoomSubCharge());
//					objectGeted.setRateSevices(ticketCheckOutRoomEntityNeedUpdate.getRateSevices());
//					objectGeted.setStatus(ticketCheckOutRoomEntityNeedUpdate.getStatus());
//					objectGeted.setStaffReceptionCheckoutRoom(ticketCheckOutRoomEntityNeedUpdate.getStaffReceptionCheckoutRoom());
//					objectGeted.setTicketBooking(ticketCheckOutRoomEntityNeedUpdate.getTicketBooking());

					ticketcheckoutroomRepository.save(objectGeted);
				}
			} else {
				ticketcheckoutroomRepository.save(ticketCheckOutRoomEntityNeedUpdate);
			}
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
			Optional<TicketCheckOutRoomEntity> optionGeted = ticketcheckoutroomRepository
					.findByidTicketCheckout(String.valueOf(id[0]));
			if (optionGeted.isPresent()) {
				ticketcheckoutroomRepository.delete(optionGeted.get());
				return true;
			}
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;
	}

	public TicketcheckoutroomFontEnd getObjectTicketcheckoutroomFontEnd(final String idTicketChecking,
			final Integer idroom) {

		TicketcheckoutroomFontEnd objectTicketCheckOutFontEnd = new TicketcheckoutroomFontEnd();
		objectTicketCheckOutFontEnd.setIdticketcheckoutroom(null);
		objectTicketCheckOutFontEnd.setIdticketbooking(idTicketChecking);
		objectTicketCheckOutFontEnd.setIdstaffreceptionsupport(null);
		objectTicketCheckOutFontEnd.setTimeendrent(LocalDateTime.now());

		// get RoomEntity from manager entity not get from DB
		RoomEntity roomFromManager = entityManager.getReference(RoomEntity.class, idroom);

		// get ticket check booking and room value
		Optional<TicketBookingEntity> booking = ticketbookingRepository
				.findByidTicketBookingAndRoomRent(idTicketChecking, roomFromManager);

		if (booking.isPresent()) {
			TicketBookingEntity ticketBookingEntity = booking.get();
			// set ticket booking
			// CREATE ticketBookingEntity to use return client
			TicketBookingEntity ticketBookingEntityReturn = new TicketBookingEntity();
			ticketBookingEntityReturn.setIdTicketBooking(idTicketChecking);
			ticketBookingEntityReturn.setUserNameRentRoom(ticketBookingEntity.getUserNameRentRoom());
			ticketBookingEntityReturn.setIdUserRentRoom(ticketBookingEntity.getIdUserRentRoom());
			ticketBookingEntityReturn.setNumberPeopleInRoom(ticketBookingEntity.getNumberPeopleInRoom());
			ticketBookingEntityReturn.setTimeStartRentRoom(ticketBookingEntity.getTimeStartRentRoom());
			//ticketBookingEntityReturn.setRoomRent(ticketBookingEntity.getRoomRent());
			objectTicketCheckOutFontEnd.setTicketbooking(ticketBookingEntityReturn);
			objectTicketCheckOutFontEnd.setRoomnumber(ticketBookingEntity.getRoomRent().getIdRoom());

			// set about room
			objectTicketCheckOutFontEnd.setMaxRentNumberInRoom(roomFromManager.getTypeOfRoom().getNumberInRoom());
			objectTicketCheckOutFontEnd.setNumberroomrent(ticketBookingEntity.getNumberPeopleInRoom());

			// calculator roomSubCharge
			int numberRoomOver = 0;
			if (ticketBookingEntity.getNumberPeopleInRoom() > roomFromManager.getTypeOfRoom().getNumberInRoom()) {
				numberRoomOver = ticketBookingEntity.getNumberPeopleInRoom()
						- roomFromManager.getTypeOfRoom().getNumberInRoom();
			}
			objectTicketCheckOutFontEnd.setRateSubChargeInRoom(roomFromManager.getTypeOfRoom().getRoomRatesCharge());
			objectTicketCheckOutFontEnd.setRoomSubCharge(
					roomFromManager.getTypeOfRoom().getRoomRatesCharge().multiply(BigDecimal.valueOf(numberRoomOver)));

			// set rate services
			BigDecimal rateSumService = new BigDecimal(0);
			for (DetailsServicesEntity in : ticketBookingEntity.getDsDetailsServices()) {
				rateSumService = rateSumService.add(in.getBigdesumaryMoneySerives());
			}
			objectTicketCheckOutFontEnd.setRateservices(rateSumService);

			// set rate rent room
			Duration timeRent = Duration.between(ticketBookingEntity.getTimeStartRentRoom(), LocalDateTime.now());
			String time = "Time rent room: " + timeRent.toDaysPart() + ":" + timeRent.toHoursPart() + ":"
					+ timeRent.toMinutesPart();
			objectTicketCheckOutFontEnd.setTimeRent(time);

			BigDecimal rateRent = calculatorRentRoom(roomFromManager.getTypeOfRoom(), timeRent.toDaysPart(),
					timeRent.toHoursPart(), timeRent.toMinutesPart());
			objectTicketCheckOutFontEnd.setRateRent(rateRent);

			// RoomDamaged
			// get roomDamaged /getObjectByIdCheckIn
			Optional<CheckingOutRoomDamagedEntity> damaged = checkingOutRoomDamagedRepository
					.findByidTicketBooking(idTicketChecking);
			if (damaged.isPresent()) {
				// get from damaged
				CheckingOutRoomDamagedEntity checkingOutRoomDamagedEntity = damaged.get();
				if (EnumDetailservicesStatus.DONE.getName().equals(checkingOutRoomDamagedEntity.getStatus())) {//
					objectTicketCheckOutFontEnd.setRoomDamaged(checkingOutRoomDamagedEntity.getSumaryIndemnify());
					objectTicketCheckOutFontEnd.setStatus(EnumDetailservicesStatus.DONE.getName()); // checking clean
					objectTicketCheckOutFontEnd.setListDamaged(checkingOutRoomDamagedEntity.getListProductDamaded());																	// off
				} else {
					objectTicketCheckOutFontEnd.setRoomDamaged(BigDecimal.ZERO);
					objectTicketCheckOutFontEnd.setStatus(EnumDetailservicesStatus.SHIPPING.getName()); // checking																									// clean off
				}
			} else {
				// create Dameged
				CheckingOutRoomDamagedEntity checkingOutRoomDamagedEntity = new CheckingOutRoomDamagedEntity();
				Long timeLong = System.currentTimeMillis();
				checkingOutRoomDamagedEntity.setIdCheckoutRoomDamaged(String.valueOf(timeLong));
				checkingOutRoomDamagedEntity.setIdTicketBooking(idTicketChecking);
				checkingOutRoomDamagedEntity.setListProductDamaded("");
				checkingOutRoomDamagedEntity.setStaffCheckOutRoomDamaged(null);
				checkingOutRoomDamagedEntity.setStatus(EnumDetailservicesStatus.SHIPPING.getName());
				checkingOutRoomDamagedEntity.setSumaryIndemnify(BigDecimal.ZERO);
				checkingOutRoomDamagedEntity.setTicketCheckoutObject(null);
				checkingOutRoomDamagedRepository.save(checkingOutRoomDamagedEntity);
				objectTicketCheckOutFontEnd.setRoomDamaged(BigDecimal.ZERO);
				objectTicketCheckOutFontEnd.setStatus(EnumDetailservicesStatus.SHIPPING.getName()); // checking
			}
			objectTicketCheckOutFontEnd.setSumaryratesandservices(
					objectTicketCheckOutFontEnd.getRateRent().add(objectTicketCheckOutFontEnd.getRateservices())
							.add(objectTicketCheckOutFontEnd.getRoomDamaged())
							.add(objectTicketCheckOutFontEnd.getRoomSubCharge()));
		} else {
			// throw error
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return objectTicketCheckOutFontEnd;
	}

	BigDecimal calculatorRentRoom(TypeOfRoomEntity type, long day, long h, long mi) {
		BigDecimal rentRoom = new BigDecimal(0);
		BigDecimal dayRent = type.getRoomRateDate().multiply(BigDecimal.valueOf(day));
		BigDecimal hourToday = new BigDecimal(0);
		if (h < 4) {
			hourToday = type.getRoomRatesHour().multiply(BigDecimal.valueOf(h));
		} else if (h >= 4 && h <= 24) {
			hourToday = type.getRoomRateDate();
		}
		BigDecimal minutes = new BigDecimal(0);
		if (mi > 0) {
			minutes = type.getRoomRatesHour();
		}
		return rentRoom.add(dayRent).add(hourToday).add(minutes);
	}
}
