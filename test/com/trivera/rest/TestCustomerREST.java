package com.trivera.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.trivera.model.Customer;

public class TestCustomerREST {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testFindById() {
		Customer found = restTemplate.getForObject("http://localhost:8080/DemoJDBC/rest/customers/1", Customer.class);
		assertNotNull(found);
		assertEquals("Customer 1 was not Harry!", "Harry", found.getFirstName());
		assertEquals("Customer 1 was not Harry Potter!", "Potter", found.getLastName());
	}
	
	@Test
	public void testFindAll() {
		ResponseEntity<Customer[]> foundEntity = restTemplate.getForEntity("http://localhost:8080/DemoJDBC/rest/customers", Customer[].class); // Can this syntax be fixed?
		Customer[] found = foundEntity.getBody();
		assertNotNull(found);
		assertTrue("Not enough customers!", found.length > 0);
		assertEquals("Customer 1 was not Harry!", "Harry", found[0].getFirstName());
		assertEquals("Customer 1 was not Potter!", "Potter", found[0].getLastName());
	}
	
	@Test
	public void testInsert() {
		Customer snape = new Customer("Severus", "Snape", "+44 0206 199-1931", "severus.snape@hogwarts.ac.uk");
		Customer result = restTemplate.postForObject("http://localhost:8080/DemoJDBC/rest/customers", snape, Customer.class);
		//System.out.println(result);
		assertNotNull(result);
		assertTrue("Bad Customer Id Returned!", result.getCustomerId() > 0);
	}
	
	@Test
	public void testUpdate() {
		Customer harry = restTemplate.getForObject("http://localhost:8080/DemoJDBC/rest/customers/1", Customer.class);
		assertNotNull("Couldn't find customer to update! " + harry, harry);
		String email = harry.getEmail();
		harry.setEmail("harry@aurors.mom.gov.uk");
		restTemplate.put("http://localhost:8080/DemoJDBC/rest/customers/", harry);
		Customer harryAgain = restTemplate.getForObject("http://localhost:8080/DemoJDBC/rest/customers/1", Customer.class);
		assertEquals("Harry's Email NOT changed!", "harry@aurors.mom.gov.uk", harryAgain.getEmail());
		harryAgain.setEmail(email);
		restTemplate.put("http://localhost:8080/DemoJDBC/rest/customers/", harryAgain);
		
	}
	
	
}
