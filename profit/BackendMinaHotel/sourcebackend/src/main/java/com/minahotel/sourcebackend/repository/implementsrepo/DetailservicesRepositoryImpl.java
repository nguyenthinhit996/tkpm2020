package com.minahotel.sourcebackend.repository.implementsrepo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.minahotel.sourcebackend.entities.DetailsServicesEntity;
import com.minahotel.sourcebackend.repository.customizeinterface.DetailservicesRepositoryCustom;

/**
 * DetailservicesRepositoryImpl is class implements method from interface repository custom 
 * @author Peter
 *
 */
public class DetailservicesRepositoryImpl implements DetailservicesRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<DetailsServicesEntity> getAllDetailsServicesEntity() {
		 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		 CriteriaQuery<DetailsServicesEntity> query = builder.createQuery(DetailsServicesEntity.class);
		 Root<DetailsServicesEntity> root = query.from(DetailsServicesEntity.class);
		 return entityManager.createQuery(query).getResultList();
	}

}
