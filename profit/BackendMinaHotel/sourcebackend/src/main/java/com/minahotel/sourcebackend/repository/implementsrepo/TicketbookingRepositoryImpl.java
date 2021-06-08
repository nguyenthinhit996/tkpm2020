package com.minahotel.sourcebackend.repository.implementsrepo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.minahotel.sourcebackend.entities.TicketBookingEntity;
import com.minahotel.sourcebackend.enums.EnumCommon;
import com.minahotel.sourcebackend.enums.EnumTicketbooking;
import com.minahotel.sourcebackend.repository.customizeinterface.TicketbookingRepositoryCustom;

public class TicketbookingRepositoryImpl implements TicketbookingRepositoryCustom{

	@PersistenceContext
	EntityManager EntityManager;
	
	@Override
	public List<TicketBookingEntity> findAllTicketBookingStatusON() {
		// TODO Auto-generated method stub
		CriteriaBuilder builder = EntityManager.getCriteriaBuilder();
		CriteriaQuery<TicketBookingEntity> querySelect = builder.createQuery(TicketBookingEntity.class);
		Root<TicketBookingEntity> root = querySelect.from(TicketBookingEntity.class);
		Predicate isEqualStatusOn = builder.equal(root.get(EnumTicketbooking.STATUS.getValue()), EnumCommon.ON.getValue());
		return EntityManager.createQuery(querySelect.where(isEqualStatusOn)).getResultList();
	}

}
