package com.minahotel.sourcebackend.repository.customizeinterface;

import java.util.List;

import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;

/**
 * TicketcheckoutroomRepositoryCustom is interface to extend method to custom in repository
 * @author Peter
 *
 */
public interface TicketcheckoutroomRepositoryCustom {
	
	public List<TicketCheckOutRoomEntity> finAllTicketCheckoutStatusClean();
}
