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

import com.minahotel.sourcebackend.entities.DailyWorkingEntity;
import com.minahotel.sourcebackend.entities.compositekey.CompositeKeyDailyWorkingEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.DailyworkingRepositoryServices;

/**
 * DailyworkingController is class @RestController working on Entity {@link DailyWorkingEntity} 
 * @author Peter
 *
 */
@RestController
public class DailyworkingController {

	@Autowired
	DailyworkingRepositoryServices dailyworkingRepositoryServices;
	

	@GetMapping("/dailyworking")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "idtoday", defaultValue = "All") String idtoday
			, @RequestParam(name = "idStaffWork", defaultValue = "All") String idStaffWork) {
		if("All".equals(idtoday)) {
			return dailyworkingRepositoryServices.getAll();
		}
		 return Arrays.asList(dailyworkingRepositoryServices.getObjectById(idtoday, idStaffWork));
	}
	

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/dailyworking")
    boolean newObject(@RequestBody DailyWorkingEntity object ) {
        return dailyworkingRepositoryServices.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/dailyworking")
    boolean saveOrUpdate(@RequestBody DailyWorkingEntity object ) {
        return dailyworkingRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/dailyworking")
    boolean deleteObject(@RequestBody CompositeKeyDailyWorkingEntity object ) {
    	return dailyworkingRepositoryServices.deleteObjectById(object);
    }
    
	// get all or get by idstaff
	@GetMapping("/autotime")
	void getStaffById() {
		// dailyworkingRepositoryServices.autotime();
	}
	
}
