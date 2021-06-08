package com.minahotel.sourcebackend.repository.implementsrepo;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.DateWorkEntity;
import com.minahotel.sourcebackend.pojo.MinaHoTelPojo;
import com.minahotel.sourcebackend.repository.customizeinterface.DateworkRepositoryCustom;

@Repository
public class DateworkRepositoryImpl implements DateworkRepositoryCustom{

	@Autowired
	private SessionFactory session;
	
	@Override
	public List<? extends MinaHoTelPojo> findByidDateWork(String id) {
//		session.beginTransaction();
//		
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery query =  builder.createQuery(null)
		return null;
	}

}
