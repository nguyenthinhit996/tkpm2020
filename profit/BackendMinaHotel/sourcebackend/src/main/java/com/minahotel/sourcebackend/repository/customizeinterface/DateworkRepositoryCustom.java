package com.minahotel.sourcebackend.repository.customizeinterface;

import java.util.List;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;

public interface DateworkRepositoryCustom {
	
	List<? extends MinaHoTelPojo> findByidDateWork(String id);
	
}
