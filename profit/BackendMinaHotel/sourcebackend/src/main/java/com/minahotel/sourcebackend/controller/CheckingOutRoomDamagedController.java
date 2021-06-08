package com.minahotel.sourcebackend.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minahotel.sourcebackend.entities.CheckingOutRoomDamagedEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.services.CheckingOutRoomDamagedRepositoryServices;

@RestController
public class CheckingOutRoomDamagedController {

	@Autowired
	CheckingOutRoomDamagedRepositoryServices checkingOutRoomDamagedRepositoryServices;

	@GetMapping("/checkingOutRoomDamaged")
	List<? extends MinaHoTelPojo> getObjectById(@RequestParam(name = "idtoday", defaultValue = "All") String idtoday) {
		if ("All".equals(idtoday)) {
			return checkingOutRoomDamagedRepositoryServices.getAll();
		}
		return Arrays.asList(checkingOutRoomDamagedRepositoryServices.getObjectById(idtoday));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/checkingOutRoomDamaged")
	Boolean updateObject(@RequestBody CheckingOutRoomDamagedEntity object) {
		return checkingOutRoomDamagedRepositoryServices.saveOrUpdate(object);
	}
}
