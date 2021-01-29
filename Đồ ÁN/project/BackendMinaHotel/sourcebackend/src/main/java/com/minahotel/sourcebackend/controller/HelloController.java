package com.minahotel.sourcebackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minahotel.sourcebackend.pojo.Customer;
import com.minahotel.sourcebackend.repository.CustomerRepository;

@RestController
public class HelloController {
	
	@Autowired
	CustomerRepository customerRepo;
	 
	 @GetMapping("/")
		String index() {
			return "Hello";
		}
	
	@GetMapping("/count")
	int count() {
		return customerRepo.count();
	}
	
	@GetMapping("/list")
	List<Customer> list() {
		return customerRepo.getAllCustomer();
	}
}
