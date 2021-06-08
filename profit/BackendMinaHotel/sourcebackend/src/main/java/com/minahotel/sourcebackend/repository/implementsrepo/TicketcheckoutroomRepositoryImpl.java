package com.minahotel.sourcebackend.repository.implementsrepo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.minahotel.sourcebackend.entities.TicketCheckOutRoomEntity;
import com.minahotel.sourcebackend.enums.EnumCommon;
import com.minahotel.sourcebackend.enums.EnumTicketbooking;
import com.minahotel.sourcebackend.repository.customizeinterface.TicketcheckoutroomRepositoryCustom;

public class TicketcheckoutroomRepositoryImpl implements TicketcheckoutroomRepositoryCustom{
 
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<TicketCheckOutRoomEntity> finAllTicketCheckoutStatusClean() {
		 
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TicketCheckOutRoomEntity> querySelect = builder.createQuery(TicketCheckOutRoomEntity.class);
		Root<TicketCheckOutRoomEntity> root = querySelect.from(TicketCheckOutRoomEntity.class);
		Predicate isEqualStatusOn = builder.equal(root.get(EnumTicketbooking.STATUS.getValue()), EnumCommon.CLEAN.getValue());
		return entityManager.createQuery(querySelect.where(isEqualStatusOn)).getResultList();
	}

}
