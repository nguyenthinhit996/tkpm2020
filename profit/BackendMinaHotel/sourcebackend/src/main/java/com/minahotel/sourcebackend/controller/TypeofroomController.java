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

import com.minahotel.sourcebackend.entities.TypeOfRoomEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.TypeofroomRepositoryServies;

/**
 * TypeofroomController is class @RestController  working on Entity {@link TypeOfRoomEntity} 
 * @author Peter
 *
 */
@RestController
public class TypeofroomController {

	@Autowired
	TypeofroomRepositoryServies typeofroomRepositoryServies;
	

	@GetMapping("/typeofroom")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "id", defaultValue = "All") String id) {
		if("All".equals(id)) {
			return typeofroomRepositoryServies.getAll();
		}
		 return Arrays.asList(typeofroomRepositoryServies.getObjectById(id));
	}
	

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/typeofroom")
    boolean newObject(@RequestBody TypeOfRoomEntity object ) {
        return typeofroomRepositoryServies.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/typeofroom")
    boolean saveOrUpdate(@RequestBody TypeOfRoomEntity object ) {
        return typeofroomRepositoryServies.saveOrUpdate(object);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/typeofroom")
    boolean deleteObject(@RequestBody TypeOfRoomEntity object ) {
    	return typeofroomRepositoryServies.deleteObjectById(object);
    }
}
