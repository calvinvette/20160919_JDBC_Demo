package com.trivera.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CustomerMyBatisDAO implements CustomerDAO {
	public static final String QUERY_CUSTOMER_FIND_BY_FIRST_NAME_LAST_NAME = "Customer_findByFirstNameLastName";

	
	// This should be static so all instances share the same configuration
	private static SqlSessionFactory factory = null;
	// Should this be static? 
	// An instance variable will allow each instance to have its own session
	// This would prevent crossover in data operations per thread/request
	private static SqlSession session = null;
	
	static {
		try {
			String resource="SqlMapConfig.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(is);
			session = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Customer insert(Customer customer) {
		session.insert("insertCustomer", customer);
		return customer;
	}

	@Override
	public Customer findById(Long customerId) {
		return session.selectOne("findCustomerById", customerId);
	}

	@Override
	public List<Customer> findByLastName(String lastName) {
		// You can use lastName as a single parameter like the findById method does, 
		// or you can use a key-value hash
		// Note here we're using the "Double Brace Initialization" for expediency 
		// (The outer {} is an anonymous inner class, the inner {} is an instance initializer)
//		return session.selectList("findCustomersByLastName", lastName);
		return session.selectList("findCustomersByLastName", new HashMap<String, String>() {{ put("lastName", lastName); }});
	}
	
	@Override
	public List<Customer> findByFirstNameLastName(String firstName, String lastName) {
		return session.selectList(QUERY_CUSTOMER_FIND_BY_FIRST_NAME_LAST_NAME, 
				new HashMap<String, String>() {{ 
			put("lastName", lastName); 
			put("firstName", firstName); 
		}});
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new Vector<Customer>();
		try {
			customers = session.selectList("findAllCustomers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public Customer update(Customer customer) {
		// Similar to the findByLastName, we can supply the entire instance.
		// Instance properties are available in the Mapper (eg #{firstName})
		session.update("updateCustomer", customer);
		// TODO - make a copy of the original customer and return the customer
		// Possible the original is stale due to merge/conflict resolution
		// or optimistic lock detection
		return customer;
	}

	@Override
	public Customer delete(Customer customer) {
		session.delete("deleteCustomer", customer);
		return customer;
	}

}
