package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.TicketbookingRepositoryCustom;

@Repository
public interface TicketbookingRepository extends CrudRepository<TicketBookingEntity, Long>, TicketbookingRepositoryCustom {

	public Optional<TicketBookingEntity> findByidTicketBooking(String idTicketBooking);
	
	public Optional<TicketBookingEntity> findByidTicketBookingAndRoomRent(String idTicketBooking, RoomEntity idRoom);
}
