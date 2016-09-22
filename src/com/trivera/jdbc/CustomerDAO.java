package com.trivera.jdbc;

import java.util.List;

import com.trivera.model.Customer;

public interface CustomerDAO {

	/**
	 * Takes a proto-customer and inserts it into the database. Any customer Id
	 * in the proto-customer will be ignored
	 *
	 * @param customer
	 * @return Slightly modified Customer with the DB-generated primary key
	 */
	Customer insert(Customer customer);

	/**
	 * Takes the customer's primary key and returns back the whole record
	 * 
	 * @param customerId
	 * @return full customer for that customerId or null
	 */
	Customer findById(Long customerId);

	/**
	 * Takes the customer's primary key and returns back the whole record
	 * 
	 * @param customerId
	 * @return full customer for that customerId or null
	 */
	List<Customer> findByLastName(String lastName);
	
	/**
	 * Find a list of customers with the given first and last name
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	List<Customer> findByFirstNameLastName(String firstName, String lastName);

	/**
	 * 
	 * @return all customers in the database
	 */
	List<Customer> findAll();

	/**
	 * Takes an customer that's already been saved in the database but has been
	 * modifed externally (while "disconnected") and updates the database with
	 * the new values
	 * 
	 * @param customer
	 * @return A new customer object that is current with the DB and may be
	 *         "managed"
	 */
	Customer update(Customer customer);

	/**
	 * Deletes the given customer from the DB
	 * 
	 * @param customer
	 * @return The last copy of the customer, for program reference or logging
	 */
	Customer delete(Customer customer);

}