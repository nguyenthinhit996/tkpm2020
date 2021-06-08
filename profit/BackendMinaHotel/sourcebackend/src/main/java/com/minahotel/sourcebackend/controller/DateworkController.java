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

import com.minahotel.sourcebackend.entities.DateWorkEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.DateworkRepositoryServices;

@RestController
public class DateworkController {

	@Autowired
	DateworkRepositoryServices dateworkRepositoryServices;
	

	@GetMapping("/datework")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "id", defaultValue = "All") String id) {
		if("All".equals(id)) {
			return dateworkRepositoryServices.getAll();
		}
		 return Arrays.asList(dateworkRepositoryServices.getObjectById(id));
	}
	

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/datework")
    boolean newObject(@RequestBody DateWorkEntity object ) {
        return dateworkRepositoryServices.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/datework")
    boolean saveOrUpdate(@RequestBody DateWorkEntity object ) {
        return dateworkRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/datework")
    boolean deleteObject(@RequestBody String id ) {
    	return dateworkRepositoryServices.deleteObjectById(id);
    }
    
}
