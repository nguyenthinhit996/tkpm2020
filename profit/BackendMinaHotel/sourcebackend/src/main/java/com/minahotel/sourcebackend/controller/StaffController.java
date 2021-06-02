package com.minahotel.sourcebackend.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.minahotel.sourcebackend.entities.StaffEntity;
import com.minahotel.sourcebackend.enums.EnumTicketAndRoom;
import com.minahotel.sourcebackend.pojo.ChangePassPojo;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.StaffRepositoryServices;

 
@RestController
public class StaffController {

	private static final Logger LOG = LoggerFactory.getLogger(StaffController.class);
	
	@Autowired
	StaffRepositoryServices staffRepositoryServices;
	
	// get all or get by idstaff
	@GetMapping("/staff")
	List<? extends MinaHoTelPojo> getStaffById(@RequestParam(name = "id", defaultValue = "All") String idStaff) {
		if("All".equals(idStaff)) {
			List<StaffEntity> getListOnLyOnStatus = new ArrayList<StaffEntity>();
			
			 for (MinaHoTelPojo in: staffRepositoryServices.getAll()) {
				 StaffEntity staffObject = (StaffEntity) in;
				 if(EnumTicketAndRoom.ON.getName().equals(staffObject.getStatus())) {
					 getListOnLyOnStatus.add(staffObject);
				 }
			 }
			 
			 return getListOnLyOnStatus;
		}
		 return Arrays.asList(staffRepositoryServices.getObjectById(idStaff));
	}
	
	//Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/staff")
    boolean newObject(@RequestBody StaffEntity staff ) {
        return staffRepositoryServices.createObject(staff);
    }
	
    //update or insert 
    //return 200
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/staff")
    boolean saveOrUpdate(@RequestBody StaffEntity staff ) {
        return staffRepositoryServices.saveOrUpdate(staff);
    }
    
    // delete
    //return 200
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/staff")
    boolean deleteStaff(@RequestBody StaffEntity staff ) {
        return staffRepositoryServices.deleteObjectById(staff);
    }
    
    

}
