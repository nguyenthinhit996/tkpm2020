package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.TypeOfRoomEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.TypeofroomRepositoryCustom;


@Repository
public interface TypeofroomRepository extends CrudRepository<TypeOfRoomEntity, Long>, TypeofroomRepositoryCustom{

	 public Optional<TypeOfRoomEntity> findByidNameTypeOfRoom(String idNameTypeOfRoom);
}
