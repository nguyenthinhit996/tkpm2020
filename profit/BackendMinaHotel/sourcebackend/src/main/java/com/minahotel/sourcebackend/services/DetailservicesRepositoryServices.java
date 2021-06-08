package com.minahotel.sourcebackend.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.CheckingOutRoomDamagedEntity;
import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.entities.ProductionEntity;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDetailsServicesEntity;
import com.minahotel.sourcebackend.enums.EnumDetailservicesStatus;
import com.minahotel.sourcebackend.enums.EnumTicketAndRoom;
import com.minahotel.sourcebackend.enums.EnumTypeServices;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.StatusServiceTableFontEnd;
import com.minahotel.sourcebackend.repository.CheckingOutRoomDamagedRepository;
import com.minahotel.sourcebackend.repository.DetailservicesRepository;
import com.minahotel.sourcebackend.repository.ProductionRepository;
import com.minahotel.sourcebackend.repository.StaffRepository;
import com.minahotel.sourcebackend.repository.TicketbookingRepository;
import com.minahotel.sourcebackend.repository.TicketcheckoutroomRepository;

@Service
public class DetailservicesRepositoryServices implements MinaHotelServices {

	@Autowired
	DetailservicesRepository detailservicesRepository;

	@Autowired
	ProductionRepository productionRepository;

	@Autowired
	TicketbookingRepository ticketbookingRepository;

	@Autowired
	StaffRepository staffRepository;

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	CheckingOutRoomDamagedRepository checkingOutRoomDamagedRepository;
	
	@Autowired
	TicketcheckoutroomRepository ticketcheckoutroomRepository;
	

	@Override
	public List<? extends MinaHoTelPojo> getAll() {
		List<DetailsServicesEntity> dsAll = new ArrayList<DetailsServicesEntity>();
		detailservicesRepository.findAll().forEach(dsAll::add);
		if (dsAll.size() == 0) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object... id) {
		// idticketbooking,idproduct
		CompositeKeyDetailsServicesEntity key = new CompositeKeyDetailsServicesEntity();
		key.setIdTicketBooking(String.valueOf(id[0]));
		key.setIdProduct(String.valueOf(id[1]));
//		Optional<DetailsServicesEntity> option = detailservicesRepository.selectByidTicketBooking(key.getIdTicketBooking(),key.getIdProduct());	
		Optional<DetailsServicesEntity> option = detailservicesRepository.findByidDetailsServicesEntity(key);

		if (option.isPresent()) {
			return option.get();
		} else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		try {
			DetailsServicesEntity dateWorkEntity = (DetailsServicesEntity) minapojo;
			detailservicesRepository.save(dateWorkEntity);
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			DetailsServicesEntity detailsServicesEntity = (DetailsServicesEntity) minapojo;

			Optional<DetailsServicesEntity> option = detailservicesRepository
					.findByidDetailsServicesEntity(detailsServicesEntity.getIdDetailsServicesEntity());
			if (option.isPresent()) {
				DetailsServicesEntity objectGeted = option.get();
				if (!detailsServicesEntity.equals(objectGeted)) {
					StaffEntity staff = entityManager.getReference(StaffEntity.class,
							detailsServicesEntity.getStaffService().getIdStaff());
					objectGeted.setAmount(objectGeted.getAmount() + detailsServicesEntity.getAmount());
					objectGeted.setBigdesumaryMoneySerives(objectGeted.getBigdesumaryMoneySerives()
							.add(objectGeted.getProductDetail().getProductRate()));
					objectGeted.setStaffService(staff);
					objectGeted.setStatus(detailsServicesEntity.getStatus());
					detailservicesRepository.save(objectGeted);
				}
			} else {
				// get rate of product
				Optional<TicketBookingEntity> ticketBooking = ticketbookingRepository
						.findByidTicketBooking(detailsServicesEntity.getIdDetailsServicesEntity().getIdTicketBooking());
				Optional<ProductionEntity> product = productionRepository
						.findByidProduct(detailsServicesEntity.getIdDetailsServicesEntity().getIdProduct());
				Optional<StaffEntity> staff = staffRepository
						.findByidStaff(detailsServicesEntity.getStaffService().getIdStaff());
				detailsServicesEntity.setProductDetail(product.get());
				detailsServicesEntity.setTicketBookingindetail(ticketBooking.get());
				detailsServicesEntity.setAmount(detailsServicesEntity.getAmount());
				detailsServicesEntity.setStartRent(LocalDateTime.now());
				detailsServicesEntity.setBigdesumaryMoneySerives(product.get().getProductRate());
				detailsServicesEntity.setStaffService(staff.get());
				detailservicesRepository.save(detailsServicesEntity);
			}
		} catch (IllegalArgumentException e) {
			throw new BusinessException(CodeErrorException.ES_003);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean deleteObjectById(Object... id) {
		try {
			CompositeKeyDetailsServicesEntity key = new CompositeKeyDetailsServicesEntity();
			key.setIdTicketBooking(String.valueOf(id[0]));
			key.setIdProduct(String.valueOf(id[1]));
			Optional<DetailsServicesEntity> optionGeted = detailservicesRepository
					.selectByidTicketBooking(key.getIdTicketBooking(), key.getIdProduct());
			if (optionGeted.isPresent()) {
				detailservicesRepository.delete(optionGeted.get());
				return true;
			}
		} catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}

	public List<? extends MinaHoTelPojo> getDetailsServiceByIdTicketCheckingRoom(String idChecking) {
		try {
			List<DetailsServicesEntity> dsDetailService = detailservicesRepository
					.findByidDetailsServicesEntityIdTicketBooking(idChecking);
			List<DetailsServicesEntity> dsDetailServiceAfterMap = dsDetailService.stream().map(detailServer -> {
				if (EnumDetailservicesStatus.TODO.getName().equals(detailServer.getStatus())) {
					detailServer.setStatus(EnumDetailservicesStatus.SHIPPING.getName());
				}
				return detailServer;
			}).collect(Collectors.toList());
			return dsDetailServiceAfterMap;
		} catch (Exception e) {
			throw new NotFoundItemException(e);
		}

	}

	public Boolean onLyUpdateDetailsServicesEntity(DetailsServicesEntity detailsServicesEntity) {
		try {
			StaffEntity staff = entityManager.getReference(StaffEntity.class,
					detailsServicesEntity.getStaffService().getIdStaff());
			Optional<DetailsServicesEntity> opDetail = detailservicesRepository
					.findByidDetailsServicesEntity(detailsServicesEntity.getIdDetailsServicesEntity());
			if (opDetail.isPresent()) {
				DetailsServicesEntity objectGeted = opDetail.get();
				objectGeted.setStaffService(staff);
				objectGeted.setAmount(detailsServicesEntity.getAmount());
				objectGeted.setBigdesumaryMoneySerives(objectGeted.getProductDetail().getProductRate()
						.multiply(BigDecimal.valueOf(detailsServicesEntity.getAmount())));
				objectGeted.setStatus(detailsServicesEntity.getStatus());
				if (EnumDetailservicesStatus.DONE.getName().equals(detailsServicesEntity.getStatus())) {
					objectGeted.setEndRent(LocalDateTime.now());
				}
				detailservicesRepository.save(objectGeted);
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	public List<StatusServiceTableFontEnd> getAllDetailsServicesEntity() {
		
		List<StatusServiceTableFontEnd> resultReponse = new ArrayList<StatusServiceTableFontEnd>();
		// 3 table
		// checkingoutroomdamaged check hu hai Todo -> Done
		
		List<CheckingOutRoomDamagedEntity> dsCheckingOutRoomDamaged = (List<CheckingOutRoomDamagedEntity>) checkingOutRoomDamagedRepository
				.findAll();
		if (dsCheckingOutRoomDamaged != null && dsCheckingOutRoomDamaged.size() > 0) {
			for (CheckingOutRoomDamagedEntity in : dsCheckingOutRoomDamaged) {
				StatusServiceTableFontEnd statusServiceTableFontEnd = new StatusServiceTableFontEnd();
				statusServiceTableFontEnd.setDetailservicesidproduct(null);
				statusServiceTableFontEnd.setDetailservicesIdticketbooking(null);
				statusServiceTableFontEnd.setIdcheckingoutroomdamaded(in.getIdCheckoutRoomDamaged());

				StaffEntity staff = in.getStaffCheckOutRoomDamaged();
				if (staff != null) {
					statusServiceTableFontEnd.setUsername(staff.getNameStaff());
					statusServiceTableFontEnd.setIdstaff(in.getStaffCheckOutRoomDamaged().getIdStaff()); // update to Todo status
				}
				statusServiceTableFontEnd.setIdticketcheckoutroom(in.getIdTicketBooking());
				// get checkin to order to get numberRoom
				TicketBookingEntity ticketbooking =  entityManager.getReference(TicketBookingEntity.class, in.getIdTicketBooking());
				if (ticketbooking != null) {
					statusServiceTableFontEnd.setNumberroom(ticketbooking.getRoomRent().getIdRoom());
				} else {
					statusServiceTableFontEnd.setNumberroom(-1);
				}
				statusServiceTableFontEnd.setTypeservices(EnumTypeServices.CHECKOUTDAMAGED.getName());
				statusServiceTableFontEnd.setStatus(in.getStatus());
				resultReponse.add(statusServiceTableFontEnd);
			}
		}

		// ticketcheckoutroom cap nhat tu clean -> off
		List<TicketCheckOutRoomEntity> dsTicketcheckoutroom = (List<TicketCheckOutRoomEntity>) ticketcheckoutroomRepository.findAll();
		if (dsTicketcheckoutroom != null && dsTicketcheckoutroom.size() > 0) {
			for (TicketCheckOutRoomEntity in : dsTicketcheckoutroom) {
//				if(EnumTicketAndRoom.OFF.getName().equals(in.getStatus())) {
//					
//				}
				StatusServiceTableFontEnd statusServiceTableFontEnd = new StatusServiceTableFontEnd();
				statusServiceTableFontEnd.setDetailservicesidproduct(null);
				statusServiceTableFontEnd.setDetailservicesIdticketbooking(null);
				statusServiceTableFontEnd.setIdticketcheckoutroom(in.getIdTicketCheckout());
				if (EnumTicketAndRoom.CLEAN.getName().equals(in.getStatus())) {
					statusServiceTableFontEnd.setStatus(EnumDetailservicesStatus.TODO.getName()); // set ToDo not set																									// Shipping
				} else {
					statusServiceTableFontEnd.setStatus(in.getStatus());
				}
				statusServiceTableFontEnd.setTypeservices(EnumTypeServices.CLEANROOM.getName());
				statusServiceTableFontEnd.setNumberroom(in.getnumberRoomRent());
				Optional<CheckingOutRoomDamagedEntity> checkingOutRoomDamaged = checkingOutRoomDamagedRepository.findByidTicketBooking(in.getTicketBooking().getIdTicketBooking());
				if (checkingOutRoomDamaged.isPresent()) {   
					statusServiceTableFontEnd
							.setIdcheckingoutroomdamaded(checkingOutRoomDamaged.get().getIdCheckoutRoomDamaged());
					statusServiceTableFontEnd.setIdstaff(checkingOutRoomDamaged.get().getStaffCheckOutRoomDamaged().getIdStaff());
					statusServiceTableFontEnd.setUsername(checkingOutRoomDamaged.get().getStaffCheckOutRoomDamaged().getNameStaff());
				}
				resultReponse.add(statusServiceTableFontEnd);
			}
		}

		// detailservices prepare-> shipping ( reception send to user service) ->
		// Todo(userservices doing) -> Done
		List<DetailsServicesEntity> dsDetailservices = (List<DetailsServicesEntity>) detailservicesRepository.findAll();
		if (dsDetailservices != null && dsDetailservices.size() > 0) {
			for (DetailsServicesEntity in : dsDetailservices) {

				// only get shipping
				//if (!EnumDetailservicesStatus.PREPARE.getName().equals(in.getStatus())) {
					StatusServiceTableFontEnd statusServiceTableFontEnd = new StatusServiceTableFontEnd();
					statusServiceTableFontEnd.setDetailservicesidproduct(in.getProductDetail().getIdProduct());
					statusServiceTableFontEnd.setDetailservicesIdticketbooking(in.getTicketBookingindetail().getIdTicketBooking());
					statusServiceTableFontEnd.setIdticketcheckoutroom(null);
					statusServiceTableFontEnd.setStatus(in.getStatus());
					// get type product
					ProductionEntity production = in.getProductDetail();
					if (production != null) {
						statusServiceTableFontEnd.setTypeservices(production.getTypeProduct());
					} else {
						statusServiceTableFontEnd.setTypeservices(EnumTypeServices.DRINKANDFOOD.getName());
					}

					// get checkin to order to get numberRoom
					TicketBookingEntity ticketbooking =  in.getTicketBookingindetail();
					if (ticketbooking != null) {
						statusServiceTableFontEnd.setNumberroom(ticketbooking.getRoomRent().getIdRoom());
					} else {
						statusServiceTableFontEnd.setNumberroom(-1);
					}
					statusServiceTableFontEnd.setIdcheckingoutroomdamaded(null);
					StaffEntity staff = in.getStaffService();
					if (staff != null) {
						statusServiceTableFontEnd.setUsername(staff.getNameStaff());
					}
					statusServiceTableFontEnd.setIdstaff(staff.getIdStaff());
					resultReponse.add(statusServiceTableFontEnd);
				//}

			}
		}

		return resultReponse;
	}

	public Boolean updateStatusServicesByUserServices(@RequestBody StatusServiceTableFontEnd object) {
		
		try {
			StaffEntity staff = entityManager.getReference(StaffEntity.class, object.getIdstaff()); 
			if (EnumTypeServices.CHECKOUTDAMAGED.getName().equals(object.getTypeservices())) {
				CheckingOutRoomDamagedEntity checkingOutRoomDamaged =  entityManager.getReference(CheckingOutRoomDamagedEntity.class, object.getIdcheckingoutroomdamaded());
				if (checkingOutRoomDamaged != null) {
					checkingOutRoomDamaged.setStatus(object.getStatus());
					checkingOutRoomDamaged.setStaffCheckOutRoomDamaged(staff);
					checkingOutRoomDamagedRepository.save(checkingOutRoomDamaged);
				}
			} else if (EnumTypeServices.CLEANROOM.getName().equals(object.getTypeservices())) {
				TicketCheckOutRoomEntity ticketcheckoutroom = entityManager.getReference(TicketCheckOutRoomEntity.class, object.getIdticketcheckoutroom());
				if (ticketcheckoutroom != null) {
					if (EnumDetailservicesStatus.DONE.getName().equals(object.getStatus())) {
						ticketcheckoutroom.setStatus(EnumTicketAndRoom.OFF.getName());
						ticketcheckoutroom.setStaffCheckoutRoom(staff);
						ticketcheckoutroomRepository.save(ticketcheckoutroom);
					}
				}
			} else {
				CompositeKeyDetailsServicesEntity compositeKey = new CompositeKeyDetailsServicesEntity();
				compositeKey.setIdProduct(object.getDetailservicesidproduct());
				compositeKey.setIdTicketBooking(object.getDetailservicesIdticketbooking());
				DetailsServicesEntity detailservices = entityManager.getReference(DetailsServicesEntity.class,compositeKey);
						
				if (detailservices != null) {
					detailservices.setStaffService(staff);
					detailservices.setStatus(object.getStatus());
					if(EnumDetailservicesStatus.DONE.getName().equals(object.getStatus())) {
						LocalDateTime localDateNow = LocalDateTime.now();
						detailservices.setEndRent(localDateNow);
					}
					detailservicesRepository.save(detailservices);
				}
			}
		} catch (Exception e) {
			return false;
		}

		return true;
		 
	}
	
	public String getObjectByIdResponseString(String ...id) {		
		
		CompositeKeyDetailsServicesEntity composite = new CompositeKeyDetailsServicesEntity();
		composite.setIdTicketBooking(id[0]);
		composite.setIdProduct(id[1]); 
		Optional<DetailsServicesEntity> detailservices = detailservicesRepository.findByidDetailsServicesEntity(composite);
		if(detailservices.isPresent()) {
			
			// get id product
			ProductionEntity production = detailservices.get().getProductDetail();
			
			TicketBookingEntity ticketbooking = detailservices.get().getTicketBookingindetail();
			
			String message = "Send ("+ detailservices.get().getAmount() +") Product ["+
			production.getNameProduct() +"] To Room: "+ 
					ticketbooking.getRoomRent().getIdRoom() + " ("+production.getExtensionProduct()+")";
		
			return message;
		}
		return "Error missing ! refesh page";
	}
	
}
