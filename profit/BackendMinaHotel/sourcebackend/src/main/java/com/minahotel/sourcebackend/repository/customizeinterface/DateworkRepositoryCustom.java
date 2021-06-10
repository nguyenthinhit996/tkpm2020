package com.minahotel.sourcebackend.repository.customizeinterface;

import java.util.List;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

/**
 * DateworkRepositoryCustom is interface to extend method to custom in repository 
 * @author Peter
 *
 */
public interface DateworkRepositoryCustom {
	
	List<? extends MinaHoTelPojo> findByidDateWork(String id);
	
}
