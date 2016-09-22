package com.trivera.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trivera.model.Customer;


// http://localhost:8080/DemoJDBC/rest/customers
@Component
@RequestMapping(
	value="/customers"
//	,
//	produces={"application/xml", "application/json"},
//	consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
)
public class CustomerREST {

//	// http://localhost:8080/DemoJDBC/rest/customers/hello
	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public Customer hello() {
		return new Customer("Unknown", "Customer", "555-1212", "foo@bar.com");
	}

}
