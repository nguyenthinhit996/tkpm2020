package com.minahotel.sourcebackend.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.TicketbookingRepositoryServices;

@RestController
public class TicketbookingController {

	@Autowired
	TicketbookingRepositoryServices ticketbookingRepositoryServices;
	

	@GetMapping("/ticketbooking")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "id", defaultValue = "All") String id) {
		if("All".equals(id)) {
			return ticketbookingRepositoryServices.getAll();
		}
		 return Arrays.asList(ticketbookingRepositoryServices.getObjectById(id));
	}
	

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/ticketbooking")
    boolean newObject(@RequestBody TicketBookingEntity object ) {
        return ticketbookingRepositoryServices.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/ticketbooking")
    boolean saveOrUpdate(@RequestBody TicketBookingEntity object ) {
        return ticketbookingRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/ticketbooking")
    boolean deleteObject(@RequestBody TicketBookingEntity object ) {
    	return ticketbookingRepositoryServices.deleteObjectById(object);
    }
}
