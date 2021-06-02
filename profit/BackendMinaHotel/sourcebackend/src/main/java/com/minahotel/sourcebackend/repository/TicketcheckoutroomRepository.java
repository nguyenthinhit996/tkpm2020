package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.TicketcheckoutroomRepositoryCustom;

@Repository
public interface TicketcheckoutroomRepository extends CrudRepository<TicketCheckOutRoomEntity, Long>, TicketcheckoutroomRepositoryCustom{

	public Optional<TicketCheckOutRoomEntity> findByidTicketCheckout(String idTicketCheckout);
	
}
