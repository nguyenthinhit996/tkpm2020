package com.minahotel.sourcebackend.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.entities.StaffEntity;

@Repository
public class ComponetTestHibernate {

	@Autowired
	private Session session;

	@Autowired
	private EntityTransaction entityTransaction;

	 
	// test
	public List<StaffEntity> crudTest(String type) {

		List<StaffEntity> resultList = new ArrayList<StaffEntity>();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<StaffEntity> query = builder.createQuery(StaffEntity.class);
		Root<StaffEntity> contactRoot = query.from(StaffEntity.class);
		if(!entityTransaction.isActive()) {
			entityTransaction.begin();
		}		
		if ("select".equals(type)) {
			
			// where
			Predicate per1 = builder.greaterThan(contactRoot.get("salarymonth"), 5000000);		
			// sort by salarymonth
			Order order = builder.asc(contactRoot.get("salarymonth"));		
			query.select(contactRoot);
			resultList = session.createQuery(query.where(per1).orderBy(order)).getResultList();	
			
		} else if ("create".equals(type)) {
			
//			RandomString random = new RandomString(5);			
//			StaffEntity staff = new StaffEntity(random.nextString(), "test", "test","test" , LocalDate.now(),
//					BigDecimal.valueOf(43534), BigDecimal.valueOf(43534), "one");			 
//			session.save(staff);		
			
		} else if ("update".equals(type)) {
			
			// where
			CriteriaUpdate<StaffEntity> creap =	builder.createCriteriaUpdate(StaffEntity.class);
			Root<StaffEntity> rootUpdate = creap.from(StaffEntity.class);
			Predicate per2 = builder.equal(rootUpdate.get("idstaff"), "staff_02");
			creap.set("salarymonth", BigDecimal.valueOf(999999));
			creap.where(per2);
			
			int resultCount = session.createQuery(creap).executeUpdate();
			System.out.println("Result Count: "+ resultCount);
			
		} else if ("delete".equals(type)) {
			
			CriteriaDelete<StaffEntity> createDe = builder.createCriteriaDelete(StaffEntity.class);
			Root<StaffEntity> rootDetele = createDe.from(StaffEntity.class);
			createDe.where(builder.equal(rootDetele.get("idstaff"), "IEHdf"));
			int resultCount = session.createQuery(createDe).executeUpdate();
			System.out.println("Result Count: "+ resultCount);
		}

		System.out.println("resultList");
		resultList.stream().forEach(System.out::println);		
		entityTransaction.commit();
		return resultList;
	}
}
