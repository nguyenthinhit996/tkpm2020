package com.minahotel.sourcebackend.repository.customizeinterface;

import java.util.List;

import com.minahotel.sourcebackend.entities.TicketBookingEntity;

/**
 * TicketbookingRepositoryCustom is interface to extend method to custom in repository
 * @author Peter
 *
 */
public interface TicketbookingRepositoryCustom {

	public List<TicketBookingEntity> findAllTicketBookingStatusON();
	
}
