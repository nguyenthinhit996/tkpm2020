package com.minahotel.sourcebackend.hibernates;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HibernateUtils config hibernate get Utils
 * @author Peter
 *
 */
@Configuration
public class HibernateUtils {

	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean("session")
	public Session session() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		return session;
	}
	
	
	@Bean("transaction")
	public Transaction getTransaction() {
		Transaction  entityManager = session().getTransaction();
		return entityManager;
	}
}
