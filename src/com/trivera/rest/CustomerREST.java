package com.trivera.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.fabric.Response;
import com.trivera.jdbc.CustomerDAO;
import com.trivera.jdbc.CustomerMyBatisDAO;
import com.trivera.model.Customer;


// http://localhost:8080/DemoJDBC/rest/customers
@RestController // Spring 4, @Controller + @ResponseBody
@RequestMapping(
	value="/customers"
	//,
//	produces={"application/xml", "application/json"},
//	produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
//	consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
)
public class CustomerREST {
	// TODO - Inject this in; don't hard-code it!
	@Autowired
	private CustomerDAO customerDao; // = new CustomerMyBatisDAO();

//	// http://localhost:8080/DemoJDBC/rest/customers/hello
	@RequestMapping(value="/hello", method=RequestMethod.GET
//			,
//			produces={"application/xml", "application/json"},
//			consumes={"application/xml", "application/json"}
			)
	public Customer hello() {
		return new Customer("Unknown", "Customer", "555-1212", "foo@bar.com");
	}

	// http://localhost:8080/DemoJDBC/rest/customers/1234
	@RequestMapping(method=RequestMethod.GET,
			value="/{customerId}"
			)
	public Customer findById(@PathVariable("customerId") Long customerId ) {
		return customerDao.findById(customerId);
	}

	// http://localhost:8080/DemoJDBC/rest/customers
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Customer>> findAll() {
		return new ResponseEntity<List<Customer>>(customerDao.findAll(), HttpStatus.OK);
	}
	
	// http://localhost:8080/DemoJDBC/rest/customers/lastName/Weasley
	@RequestMapping(method=RequestMethod.GET,
			value="/lastName/{lastName}"
	)
	public ResponseEntity<List<Customer>> findByLastName(@PathVariable("lastName") String lastName) {
		return new ResponseEntity<List<Customer>>(customerDao.findByLastName(lastName), HttpStatus.OK);
	}

	// http://localhost:8080/DemoJDBC/rest/customers/lastName/Weasley/firstName/Ron
	@RequestMapping(method=RequestMethod.GET,
			value="/lastName/{lastName}/firstName/{firstName}"
	)
	public ResponseEntity<List<Customer>> findByFirstNameLastName(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		return new ResponseEntity<List<Customer>>(customerDao.findByFirstNameLastName(firstName, lastName), HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Customer> insert(@RequestBody Customer customer) {
		customer = customerDao.insert(customer);
		Long customerId = customer.getCustomerId();
		HttpHeaders headers = new HttpHeaders();
		// TODO - return ETAG and Location
		// TODO - Don't hardwire this base URL - dynamically figure it out!!
		headers.add("Location", "http://localhost:8080/DemoJDBC/rest/customers/" + customerId);
		return new ResponseEntity<Customer>(customer, headers, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public Customer update(@RequestBody Customer customer) {
		return customerDao.update(customer);
	}

	// TODO - add JEE JAAS Security
	//	@RolesAllowed({"admin", "customerServiceRep"})
	// Configure JAAS WebSecurity in web.xml
	@RequestMapping(method=RequestMethod.DELETE)
	public Customer delete(@RequestBody Customer customer) {
		return customerDao.delete(customer);
	}
	

}









