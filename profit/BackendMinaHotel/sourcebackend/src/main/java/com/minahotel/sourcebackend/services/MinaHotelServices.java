package com.minahotel.sourcebackend.services;

import java.util.List;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * MinaHotelServices is interface definition logic common for all classes service in application
 * @author Peter
 *
 */
public interface MinaHotelServices {

	/**
	 * Get all object in table database
	 * @return List MinaHoTelPojo
	 */
	public List<? extends MinaHoTelPojo> getAll();

	/**
	 * Get object MinaHoTelPojo from identity
	 * @param id
	 * @return MinaHoTelPojo
	 */
	public MinaHoTelPojo getObjectById(Object ...id) ;
	
	/**
	 * Save Object into Database
	 * @param minapojo
	 * @return Boolean
	 */
	public boolean createObject(MinaHoTelPojo minapojo);
	
	/**
	 * Save or update object into Database
	 * @param minapojo
	 * @return Boolean
	 */
	public boolean saveOrUpdate(MinaHoTelPojo minapojo);
	
	/**
	 * Delete object in Database by identity
	 * @param id
	 * @return Boolean
	 */
	public boolean deleteObjectById(Object ...id);
	
}
