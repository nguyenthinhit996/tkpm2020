package com.minahotel.sourcebackend.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.minahotel.sourcebackend.pojo.Staff;

import net.bytebuddy.utility.RandomString;

@Repository
public class ComponetTestHibernate {

	@Autowired
	private Session session;

	@Autowired
	private EntityTransaction entityTransaction;

	// test
	public List<Staff> crudTest(String type) {

		List<Staff> resultList = new ArrayList<Staff>();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Staff> query = builder.createQuery(Staff.class);
		Root<Staff> contactRoot = query.from(Staff.class);
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
			
			RandomString random = new RandomString(5);			
			Staff staff = new Staff(random.nextString(), "test", "test","test" , LocalDate.now(),
					BigDecimal.valueOf(43534), BigDecimal.valueOf(43534), "one");			 
			session.save(staff);		
			
		} else if ("update".equals(type)) {
			
			// where
			CriteriaUpdate<Staff> creap =	builder.createCriteriaUpdate(Staff.class);
			Root<Staff> rootUpdate = creap.from(Staff.class);
			Predicate per2 = builder.equal(rootUpdate.get("idstaff"), "staff_02");
			creap.set("salarymonth", BigDecimal.valueOf(999999));
			creap.where(per2);
			
			int resultCount = session.createQuery(creap).executeUpdate();
			System.out.println("Result Count: "+ resultCount);
			
		} else if ("delete".equals(type)) {
			
			CriteriaDelete<Staff> createDe = builder.createCriteriaDelete(Staff.class);
			Root<Staff> rootDetele = createDe.from(Staff.class);
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
