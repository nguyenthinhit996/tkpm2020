package com.minahotel.sourcebackend.controller;

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

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.Staff;
import com.minahotel.sourcebackend.services.StaffRepositoryServices;

@RestController
public class StaffController {

	@Autowired
	StaffRepositoryServices staffRepositoryServices;
	
	// get all or get by idstaff
	@GetMapping("/staff")
	List<? extends MinaHoTelPojo> getStaffById(@RequestParam(name = "id", defaultValue = "All") String idStaff) {
		if("All".equals(idStaff)) {
			return staffRepositoryServices.getAll();
		}
		 return staffRepositoryServices.getObjectById(idStaff);
	}
	
	//Save
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/staff")
    boolean newObject(@RequestBody Staff staff ) {
        return staffRepositoryServices.createObject(staff);
    }
	
    //update or insert 
    //return 200
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/staff")
    boolean saveOrUpdate(@RequestBody Staff staff ) {
        return staffRepositoryServices.saveOrUpdate(staff);
    }
    
    // delete
    //return 200
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/staff")
    void deleteStaff(@RequestBody Staff staff ) {
        staffRepositoryServices.deleteObject(staff);
    }
    
    
}
