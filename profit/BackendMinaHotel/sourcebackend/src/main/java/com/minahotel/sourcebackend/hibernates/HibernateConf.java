//package com.minahotel.sourcebackend.hibernates;
//
//import java.util.Properties;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.hibernate.cfg.Environment;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
//import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
//import org.springframework.orm.jpa.JpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import com.zaxxer.hikari.HikariDataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class HibernateConf {
//
//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		 
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(new String[] {"com.minahotel.sourcebackend.pojo"});
//		sessionFactory.setHibernateProperties(getProperties());
//		return sessionFactory;
//	}
//	
//	@Bean 
//	public DataSource dataSource() {
//		Properties pro = getProperties();
//		HikariDataSource  datasource = new HikariDataSource();
//		
//		datasource.setMaximumPoolSize(100);
//		datasource.setConnectionTimeout(20000);
//		datasource.setDriverClassName(pro.getProperty(Environment.DRIVER));
//		datasource.setUsername(pro.getProperty(Environment.USER));
//		datasource.setPassword(pro.getProperty(Environment.PASS));
//		datasource.setJdbcUrl(pro.getProperty(Environment.URL));
//		
//		//?
//		datasource.addDataSourceProperty("cachePrepStmts", true);
//		datasource.addDataSourceProperty("prepStmtCacheSize", 250);
//		datasource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
//		datasource.addDataSourceProperty("useServerPrepStmts", true);
//		datasource.setDataSourceProperties(pro);
//		 
//		return datasource;
//	}
//	
//	// cung có thể sử dụng các properties của file application.properites 
//	//	@Bean
//	//	@ConfigurationProperties("spring.datasource")
//	//	public HikariDataSource dataSource() {
//	//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	//	}
//	
//	@Bean
//	PlatformTransactionManager hibernateTransactionManager() {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//		transactionManager.setSessionFactory(sessionFactory().getObject());
//		return transactionManager;
//	}
//
//	private Properties getProperties() {
//		
//		Properties pro = new Properties();
//		pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//		pro.put(Environment.URL, "jdbc:mysql://localhost:3306/hotelmina");
//		pro.put(Environment.USER, "root");
//		pro.put(Environment.PASS, "123456a");
//		pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
//		pro.put(Environment.SHOW_SQL, "true");
//		pro.put(Environment.HBM2DDL_AUTO, "update");
//		
//		// ?
//		pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
//		
//		return pro;
//	}
//	 
//	
//	@Bean(name = "entityManagerFactory")
//	public EntityManagerFactory entityManagerFactory() {
//		
//		Properties pro = getProperties();
//		LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
//		emfb.setJpaProperties(pro);
//		emfb.setDataSource(dataSource());
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setDatabasePlatform(pro.getProperty(Environment.DIALECT));
//		emfb.setJpaVendorAdapter(vendorAdapter);
//		emfb.setPackagesToScan("com.minahotel.sourcebackend.pojo");
//		emfb.setPersistenceUnitName("default");
//		emfb.afterPropertiesSet();
//		return emfb.getObject();
//		
//	}
//}
