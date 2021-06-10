package com.minahotel.sourcebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.RoomRepositoryCustom;

import java.util.List;
import java.util.Optional;

/**
 * RoomRepository is interface to action CrudRepository with Entity 
 * @author Peter
 *
 */
@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long>, RoomRepositoryCustom {

	public Optional<RoomEntity> findByidRoom(Integer idRoom);
	
	public  List<RoomEntity> findAll();
}
