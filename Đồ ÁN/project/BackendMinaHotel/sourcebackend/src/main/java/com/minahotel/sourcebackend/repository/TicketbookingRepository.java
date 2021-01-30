package com.minahotel.sourcebackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.minahotel.sourcebackend.pojo.Ticketbooking;


public interface TicketbookingRepository extends CrudRepository<Ticketbooking, Long>{

	String queryFindObjectById = "select * from Ticketbooking c where c.idticketbooking like :idticketbooking";
	@Query(value = queryFindObjectById, nativeQuery = true )
	public List<Ticketbooking> findObjectById(@Param("idticketbooking") String idticketbooking);
	
	@Query(value = queryFindObjectById, nativeQuery = true )
	public Optional<Ticketbooking> findObjectByIdOnlyOne(@Param("idticketbooking") String idticketbooking);
}
