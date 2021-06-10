package com.minahotel.sourcebackend.repository.implementsrepo;

import java.util.List;

import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.customizeinterface.DateworkRepositoryCustom;

/**
 * DateworkRepositoryImpl is class implements method from interface repository custom
 * @author Peter
 *
 */
public class DateworkRepositoryImpl implements DateworkRepositoryCustom{

	
	@Override
	public List<? extends MinaHoTelPojo> findByidDateWork(String id) {
//		session.beginTransaction();
//		
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery query =  builder.createQuery(null)
		return null;
	}

}
