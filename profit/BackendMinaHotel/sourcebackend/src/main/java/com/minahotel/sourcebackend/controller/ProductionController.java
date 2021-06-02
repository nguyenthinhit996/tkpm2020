package com.minahotel.sourcebackend.controller;

import java.util.ArrayList;
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

import com.minahotel.sourcebackend.entities.ProductionEntity;
import com.minahotel.sourcebackend.enums.EnumTicketAndRoom;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.ProductionRepositoryServices;

@RestController
public class ProductionController {

	@Autowired
	ProductionRepositoryServices productionRepositoryServices;
	

	@GetMapping("/production")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "id", defaultValue = "All") String id) {
		if("All".equals(id)) {
			
			List<ProductionEntity>  ds = (List<ProductionEntity>) productionRepositoryServices.getAll();
			List<ProductionEntity>  dsreturn = new ArrayList<ProductionEntity>();
			for(int i=0;i<ds.size();i++) {
				if(EnumTicketAndRoom.ON.getName().equals(ds.get(i).getStatus())) {
					dsreturn.add(ds.get(i));
				}
			}
			return dsreturn;
		}
		 return Arrays.asList(productionRepositoryServices.getObjectById(id));
	}
	

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/production")
    boolean newObject(@RequestBody ProductionEntity object ) {
        return productionRepositoryServices.createObject(object);
    }
	

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/production")
    boolean saveOrUpdate(@RequestBody ProductionEntity object ) {
        return productionRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/production")
    boolean deleteObject(@RequestBody ProductionEntity object ) {
    	return productionRepositoryServices.deleteObjectById(object);
    }
}
