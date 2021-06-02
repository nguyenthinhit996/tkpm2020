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
import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDetailsServicesEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.DetailservicesRepository;

@Service
public class DetailservicesRepositoryServices implements MinaHotelServices {

	@Autowired
	DetailservicesRepository detailservicesRepository;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<DetailsServicesEntity> dsAll = new ArrayList<DetailsServicesEntity>();
		detailservicesRepository.findAll().forEach(dsAll::add);
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		//idticketbooking,idproduct
		CompositeKeyDetailsServicesEntity key = new CompositeKeyDetailsServicesEntity(); 
		key.setIdTicketBooking(String.valueOf(id[0]));
		key.setIdProduct(String.valueOf(id[1]));	
//		Optional<DetailsServicesEntity> option = detailservicesRepository.selectByidTicketBooking(key.getIdTicketBooking(),key.getIdProduct());	
		Optional<DetailsServicesEntity> option = detailservicesRepository.findByidDetailsServicesEntity(key);
		
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 DetailsServicesEntity dateWorkEntity =  (DetailsServicesEntity) minapojo;
			 detailservicesRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
//			DetailsServicesEntity detailsServicesEntity =  (DetailsServicesEntity) minapojo;
//			Optional<DetailsServicesEntity> option = detailservicesRepository.findByidDetailsServicesEntity(detailsServicesEntity.getIdDetailsServicesEntity());
//			if(option.isPresent()) {
//				DetailsServicesEntity objectGeted = option.get();
//				if(!detailsServicesEntity.equals(objectGeted)) {
//					objectGeted.setAmount(detailsServicesEntity.getAmount());
//					objectGeted.setBigdesumaryMoneySerives(detailsServicesEntity.getBigdesumaryMoneySerives());
//					objectGeted.setEndRent(detailsServicesEntity.getEndRent());
//					objectGeted.setProductDetail(detailsServicesEntity.getProductDetail());
//					objectGeted.setStaffService(detailsServicesEntity.getStaffService());
//					objectGeted.setStartRent(detailsServicesEntity.getStartRent());
//					objectGeted.setStatus(detailsServicesEntity.getStatus());
//					objectGeted.setTicketBookingindetail(detailsServicesEntity.getTicketBookingindetail());
//					 detailservicesRepository.save(objectGeted);
//				}
//			}else {
//				detailservicesRepository.save(detailsServicesEntity);
//			}
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
			CompositeKeyDetailsServicesEntity key = new CompositeKeyDetailsServicesEntity(); 
			key.setIdTicketBooking(String.valueOf(id[0]));
			key.setIdProduct(String.valueOf(id[1]));	
			 Optional<DetailsServicesEntity> optionGeted = detailservicesRepository.selectByidTicketBooking(key.getIdTicketBooking(),key.getIdProduct());
			 if(optionGeted.isPresent()) {
				 detailservicesRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}
}
