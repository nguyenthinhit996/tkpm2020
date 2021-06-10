package com.minahotel.sourcebackend.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.minahotel.sourcebackend.common.customizeexception.CodeErrorException;
import com.minahotel.sourcebackend.common.customizeexception.exception.BusinessException;
import com.minahotel.sourcebackend.common.customizeexception.exception.CRUDExceptionCustomize;
import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.pojo.ChangePassPojo;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.UserCustomize;
import com.minahotel.sourcebackend.repository.StaffRepository;

/**
 * StaffRepositoryServices is class to handle logic belong to staff, ex login page, CRUD staff
 * @author Peter
 *
 */
@Service
public class StaffRepositoryServices implements MinaHotelServices, UserDetailsService {
	
	@Autowired
	StaffRepository staffRepository;
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<? extends MinaHoTelPojo> getAll() {		
		List<StaffEntity> dsAll = staffRepository.findAll();	
		if(dsAll.size() == 0 ) {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
		return dsAll;
	}

	@Override
	public MinaHoTelPojo getObjectById(Object ...id) {
		Optional<StaffEntity> option = staffRepository.findByidStaff(String.valueOf(id[0]));
		if(option.isPresent()) {
			return option.get();
		}else {
			throw new NotFoundItemException(CodeErrorException.EN_001);
		}
	}

	@Override
	public boolean createObject(MinaHoTelPojo minapojo) {
		 try {
			 StaffEntity dateWorkEntity =  (StaffEntity) minapojo;
			 staffRepository.save(dateWorkEntity);
		 }catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_002);
		}
		return true;
	}

	@Override
	public boolean saveOrUpdate(MinaHoTelPojo minapojo) {
		try {
			StaffEntity staffNeedUpdate =  (StaffEntity) minapojo;
			Optional<StaffEntity> option = staffRepository.findByidStaff(staffNeedUpdate.getIdStaff());
			if(option.isPresent()) {
				StaffEntity objectGeted = option.get();
				if(!staffNeedUpdate.equals(objectGeted)) {
					objectGeted.setBonussalary(staffNeedUpdate.getBonussalary());
					objectGeted.setDateStartWork(staffNeedUpdate.getDateStartWork());
					objectGeted.setNameStaff(staffNeedUpdate.getNameStaff());
					objectGeted.setPassWordStaff(staffNeedUpdate.getPassWordStaff());
					objectGeted.setRoleOfStaff(staffNeedUpdate.getRoleOfStaff());
					objectGeted.setSalaryStaffByMonth(staffNeedUpdate.getSalaryStaffByMonth());
					objectGeted.setStatus(staffNeedUpdate.getStatus());
					staffRepository.save(objectGeted);
				}
			}else {
				staffRepository.save(staffNeedUpdate);
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
			 Optional<StaffEntity> optionGeted = staffRepository.findByidStaff(String.valueOf(id[0]));
			 if(optionGeted.isPresent()) {
				 staffRepository.delete(optionGeted.get());
					return true;
			 }
		}catch (Exception e) {
			throw new CRUDExceptionCustomize(CodeErrorException.CRUD_004);
		}
		return false;

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<StaffEntity> staffOption = staffRepository.findByidStaff(username);
		UserCustomize user = null;
		if (staffOption.isPresent()) {
			StaffEntity staff = staffOption.get();
			SimpleGrantedAuthority simplerole = new SimpleGrantedAuthority(staff.getRoleOfStaff());
			user = new UserCustomize(staff.getIdStaff(), staff.getNameStaff(), staff.getPassWordStaff(), true, true, true, true, List.of(simplerole));
		}
		return user;
	}
	
	public Boolean changePassword(ChangePassPojo objectChangePass){
		try {
			StaffEntity staff =entityManager.getReference(StaffEntity.class, objectChangePass.getIdStaff());
			if(staff != null) {
				if(passwordEncoder.matches(objectChangePass.getPasswordOld(), staff.getPassWordStaff())) {
					String newPassWord = passwordEncoder.encode(objectChangePass.getPasswordNew());
					staff.setPassWordStaff(newPassWord);
					staffRepository.save(staff);
					return true;
				}else {
					//Password Old invalid , please input again
					throw new BusinessException(CodeErrorException.EA_001);
				}
			}
		}catch(IllegalArgumentException e) {// IllegalArgumentException getReference
			throw new BusinessException(CodeErrorException.ES_004);
		}
		return false;
	}
}
