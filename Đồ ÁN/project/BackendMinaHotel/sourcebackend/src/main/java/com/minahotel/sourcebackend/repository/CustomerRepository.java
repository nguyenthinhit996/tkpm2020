package com.minahotel.sourcebackend.repository;

import java.util.List;

import com.minahotel.sourcebackend.pojo.Customer;

public interface CustomerRepository {

	int count();

	List<Customer> getAllCustomer();
}
