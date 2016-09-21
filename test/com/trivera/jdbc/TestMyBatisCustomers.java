package com.trivera.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMyBatisCustomers {
	CustomerDAO dao;
	
	@BeforeClass
	public static void setupDB() {
		// Reset the DB to known defaults
	}
	
	@Before
	public void setup() {
		dao = new CustomerMyBatisDAO();
	}
	
	@Test
	public void testFindAll() {
		List<Customer> customers = dao.findAll();
//		System.out.println(customers);
		assertTrue("Wrong number of customers found", customers.size() >= 3);
	}
	
	@Test
	public void testFindByLastName() {
		List<Customer> customers = dao.findByLastName("Weasley");
//		System.out.println(customers);
		assertTrue("Wrong number of customers found", customers.size() >= 1);
	}
	
	@Test
	public void testFindByFirstNameLastName() {
		List<Customer> customers = dao.findByFirstNameLastName("Ron", "Weasley");
//		System.out.println(customers);
		assertEquals("Wrong number of customers found", 1, customers.size());
		assertEquals("Wrong lastName", "Weasley", customers.get(0).getLastName());
		assertEquals("Wrong firtName", "Ron", customers.get(0).getFirstName());
	}
	
	@Test
	public void testFindById() {
		Customer harry = dao.findById(1L);
//		System.out.println(harry);
		assertNotNull(harry);
		assertEquals("Wrong firstName", "Harry", harry.getFirstName());
	}
	
	@Test
	public void testUpdate() {
		Customer hermione = dao.findById(3L);
//		System.out.println(hermione);
		assertNotNull(hermione);
		hermione.setLastName("Granger-Weasley");
		dao.update(hermione);
		Customer hermioneAgain = dao.findById(3L);
		assertNotNull(hermioneAgain);
		assertEquals("Last Name Not Updated", "Granger-Weasley", hermioneAgain.getLastName());
	}

	@Test
	public void testInsert() {
		Customer neville = new Customer("Neville", "Longbottom", "+44 0206 482-2941", "neville.longbottom@hogwarts.ac.uk");
		dao.insert(neville);
//		System.out.println(neville);
		assertTrue("CustomerID was not set in the bean", neville.getCustomerId() != -1L);
	}
	
	@Test
	public void testDelete() {
		Customer draco = new Customer("Draco", "Malfoy", "+44 0206 910-1299", "draco@malfoy.co.uk");
		dao.insert(draco);
//		System.out.println(draco);
		assertTrue("CustomerID was not set in the inserted bean prepping for delete", draco.getCustomerId() != -1L);
		Long customerId = draco.getCustomerId();
		dao.delete(draco);
		Customer foundDraco = dao.findById(customerId);
		assertNull("Draco not deleted!", foundDraco);
		
	}
}
