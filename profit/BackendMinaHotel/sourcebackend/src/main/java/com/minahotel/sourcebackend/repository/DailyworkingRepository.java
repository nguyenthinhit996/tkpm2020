package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.DailyWorkingEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDailyWorkingEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.DailyworkingRepositoryCustom;

/**
 * DailyworkingRepository is interface to action CrudRepository with Entity 
 * @author Peter
 *
 */
@Repository
public interface DailyworkingRepository extends CrudRepository<DailyWorkingEntity, Long>, DailyworkingRepositoryCustom{
	
	public Optional<DailyWorkingEntity> findByidDailyWorkingEntity(CompositeKeyDailyWorkingEntity key);
	
}
