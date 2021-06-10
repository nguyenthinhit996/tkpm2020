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

import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.pojo.StatusServiceTableFontEnd;
import com.minahotel.sourcebackend.services.DetailservicesRepositoryServices;

/**
 * DetailservicesController is class @RestController working on Entity {@link DetailsServicesEntity} 
 * @author Peter
 *
 */
@RestController
public class DetailservicesController {

	@Autowired
	DetailservicesRepositoryServices detailservicesRepositoryServices;
	

	@GetMapping("/detailservices")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "idticketbooking", defaultValue = "All") String idticketbooking,
			@RequestParam(name = "idproduct", defaultValue = "All") String idproduct) {
		if("All".equals(idticketbooking)) {
			return detailservicesRepositoryServices.getAll();
		}
		 return Arrays.asList(detailservicesRepositoryServices.getObjectById(idticketbooking,idproduct));
	}

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/detailservices")
    boolean newObject(@RequestBody DetailsServicesEntity object ) {
        return detailservicesRepositoryServices.createObject(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/detailservices")
    boolean saveOrUpdate(@RequestBody DetailsServicesEntity object ) {
        return detailservicesRepositoryServices.saveOrUpdate(object);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/detailservices")
    boolean deleteObject(@RequestBody DetailsServicesEntity object ) {
    	return detailservicesRepositoryServices.deleteObjectById(object);
    }
    
    @GetMapping("/detailservicesByChecking")
	List<? extends MinaHoTelPojo> detailservicesByChecking(@RequestParam(name = "idticketbooking", defaultValue = "All") String idticketbooking) {
		 return detailservicesRepositoryServices.getDetailsServiceByIdTicketCheckingRoom(idticketbooking);
	}
    
    
    @PostMapping("/detailservicesUpdateOneProduct")
    Boolean updateFulldetailservices(@RequestBody DetailsServicesEntity detailservieUpdate) {
    	return detailservicesRepositoryServices.onLyUpdateDetailsServicesEntity(detailservieUpdate);
    }
    
    @GetMapping("/getAllDetailsServicesEntity")
    public List<StatusServiceTableFontEnd> getAllDetailsServicesEntity(){
    	return detailservicesRepositoryServices.getAllDetailsServicesEntity();
    }
    
	@PostMapping("/updateStatusServicesByUserServices")
	Boolean updateStatusServicesByUserServices(@RequestBody StatusServiceTableFontEnd detail) {
		 return detailservicesRepositoryServices.updateStatusServicesByUserServices(detail);
	}
	
	@GetMapping("/DetailservicesInforDrinkAndFood")
	String getObjectByIdResponseString(@RequestParam(name = "idticketbooking", defaultValue = "All") String idticketbooking,
			@RequestParam(name = "idproduct", defaultValue = "All") String idproduct) {
		 return detailservicesRepositoryServices.getObjectByIdResponseString(idticketbooking,idproduct);
	}
}
