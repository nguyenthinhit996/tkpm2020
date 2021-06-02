package com.minahotel.sourcebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.StaffRepositoryCustom;

@Repository
public interface StaffRepository extends CrudRepository<StaffEntity, Long>, StaffRepositoryCustom{

	  List<StaffEntity> findAll();
	
	 Optional<StaffEntity> findByidStaff(String idStaff);
	
}
