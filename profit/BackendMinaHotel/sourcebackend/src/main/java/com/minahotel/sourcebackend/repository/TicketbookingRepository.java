package com.minahotel.sourcebackend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.TicketbookingRepositoryCustom;

/**
 * TicketbookingRepository is interface to action CrudRepository with Entity 
 * @author Peter
 *
 */
@Repository
public interface TicketbookingRepository extends CrudRepository<TicketBookingEntity, Long>, TicketbookingRepositoryCustom {

	public Optional<TicketBookingEntity> findByidTicketBooking(String idTicketBooking);
	
	/**
	 * Find a TicketBookingEntity from two properties idTicketBooking and idRoom
	 * @param idTicketBooking
	 * @param idRoom
	 * @return Optional<TicketBookingEntity>
	 */
	public Optional<TicketBookingEntity> findByidTicketBookingAndRoomRent(String idTicketBooking, RoomEntity idRoom);
}
