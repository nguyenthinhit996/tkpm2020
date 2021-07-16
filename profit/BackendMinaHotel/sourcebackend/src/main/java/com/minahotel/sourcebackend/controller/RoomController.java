package com.minahotel.sourcebackend.controller;

import java.util.Arrays;
import java.util.List;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

import com.minahotel.sourcebackend.entities.RoomEntity;
import com.minahotel.sourcebackend.pojo.DetailRoom;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.RoomRepositoryServices;

/**
 * RoomController is class @RestController working on Entity {@link RoomEntity} 
 * @author Peter
 *
 */
@RestController
public class RoomController {

	@Autowired
	RoomRepositoryServices roomRepositoryServices;


	@GetMapping("/room")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "id", defaultValue = "All") String id) {
		if("All".equals(id)) {
			return roomRepositoryServices.getAll();
		}
		 return Arrays.asList(roomRepositoryServices.getObjectById(id));
	}
	

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/room")
    boolean newObject(@RequestBody RoomEntity object ) {
        return roomRepositoryServices.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/room")
    boolean saveOrUpdate(@RequestBody RoomEntity object ) {
        return roomRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/room")
    boolean deleteObject(@RequestBody RoomEntity object ) {
    	return roomRepositoryServices.deleteObjectById(object);
    }
    
    @GetMapping("/detailAllRoom")
    List<DetailRoom> getALlDetailAllRoom(){
    	return roomRepositoryServices.getALlDetailAllRoom();
    }
    
}
