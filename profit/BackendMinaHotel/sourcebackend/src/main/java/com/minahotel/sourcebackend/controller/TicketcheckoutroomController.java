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

import com.minahotel.sourcebackend.common.customizeexception.exception.NotFoundItemException;
import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.TicketcheckoutroomRepositoryServices;

@RestController
public class TicketcheckoutroomController {

	@Autowired
	TicketcheckoutroomRepositoryServices ticketcheckoutroomRepositoryServices;
	
	@GetMapping("/ticketcheckoutroom")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "id", defaultValue = "All") String id) throws NotFoundItemException {
		if("All".equals(id)) {
			return ticketcheckoutroomRepositoryServices.getAll();
		}
		 return Arrays.asList(ticketcheckoutroomRepositoryServices.getObjectById(id));
	}
	
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/ticketcheckoutroom")
    boolean newObject(@RequestBody TicketCheckOutRoomEntity object ) {
        return ticketcheckoutroomRepositoryServices.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/ticketcheckoutroom")
    boolean saveOrUpdate(@RequestBody TicketCheckOutRoomEntity object ) {
        return ticketcheckoutroomRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/ticketcheckoutroom")
    boolean deleteObject(@RequestBody TicketCheckOutRoomEntity object ) {
    	return ticketcheckoutroomRepositoryServices.deleteObjectById(object);
    }
    
    
}
