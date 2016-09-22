package com.trivera.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.trivera.jdbc.CustomerDAO;
import com.trivera.jdbc.CustomerMyBatisDAO;
import com.trivera.model.Customer;


// http://localhost:8080/DemoJDBC/rest/customers
@RestController // Spring 4, @Controller + @ResponseBody
@RequestMapping(
	value="/customers",
//	produces={"application/xml", "application/json"},
	produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
	consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
)
public class CustomerREST {
	// TODO - Inject this in; don't hard-code it!
	private CustomerDAO dao = new CustomerMyBatisDAO();

//	// http://localhost:8080/DemoJDBC/rest/customers/hello
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public Customer hello() {
		return new Customer("Unknown", "Customer", "555-1212", "foo@bar.com");
	}

	// http://localhost:8080/DemoJDBC/rest/customers
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Customer>> findAll() {
		return new ResponseEntity<List<Customer>>(dao.findAll(), HttpStatus.OK);
	}
	
	// http://localhost:8080/DemoJDBC/rest/customers/lastName/Weasley
	@RequestMapping(method=RequestMethod.GET,
			value="/lastName/{lastName}"
	)
	public ResponseEntity<List<Customer>> findByLastName(@PathVariable("lastName") String lastName) {
		return new ResponseEntity<List<Customer>>(dao.findByLastName(lastName), HttpStatus.OK);
	}
	
}









