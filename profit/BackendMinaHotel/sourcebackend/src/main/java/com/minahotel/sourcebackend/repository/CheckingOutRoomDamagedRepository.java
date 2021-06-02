package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.CheckingOutRoomDamagedEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.CheckingOutRoomDamagedRepositoryCustom;

@Repository
public interface CheckingOutRoomDamagedRepository extends CrudRepository<CheckingOutRoomDamagedEntity, Long>, CheckingOutRoomDamagedRepositoryCustom{

	public Optional<CheckingOutRoomDamagedEntity> findByidCheckoutRoomDamaged(String idCheckoutRoomDamaged);
	
}