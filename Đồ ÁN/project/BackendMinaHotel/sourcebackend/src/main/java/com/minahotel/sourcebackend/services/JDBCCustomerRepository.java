package com.minahotel.sourcebackend.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.minahotel.sourcebackend.pojo.Customer;
import com.minahotel.sourcebackend.repository.CustomerRepository;

@Repository
public class JDBCCustomerRepository implements CustomerRepository{

	// Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate
                .queryForObject("select count(*) from customer", Integer.class);
    }

	@Override
	public List<Customer> getAllCustomer() {
		return jdbcTemplate.query(
                "select * from customer ",rowMappCustomer
                
        );
	}

	RowMapper<Customer> rowMappCustomer = new RowMapper<>() {
		public Customer mapRow(ResultSet set, int rowNum) throws SQLException {
			Customer cus = new Customer();
			cus.setId(set.getLong("id"));
			cus.setAddress(set.getString("address"));
			cus.setEmail(set.getString("email"));
			cus.setName(set.getString("name"));
			return cus;
		}
	};
	
	
	 
			
			
}
