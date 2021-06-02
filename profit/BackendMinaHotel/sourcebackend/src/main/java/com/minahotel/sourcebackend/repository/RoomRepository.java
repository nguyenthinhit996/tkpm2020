package com.minahotel.sourcebackend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.RoomRepositoryCustom;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long>, RoomRepositoryCustom {

	public Optional<RoomEntity> findByidRoom(Integer idRoom);
}
