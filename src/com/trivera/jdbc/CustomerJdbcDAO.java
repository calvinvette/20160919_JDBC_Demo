package com.trivera.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class CustomerJdbcDAO implements CustomerDAO {
	private Connection connection = null;
	PreparedStatement findByIdStatement = null;
	PreparedStatement findByLastNameStatement = null;
	PreparedStatement insertCustomerStatement = null;
	PreparedStatement updateStatement = null;
	PreparedStatement deleteStatement = null;
	
	static {
		Class.forName("org.apache.derby.jdbc.ClientDriver");
	}

	public CustomerJdbcDAO() {
		connection = DriverManager.getConnection("jdbc:derby://localhost:1527/weasley;create=true", "user", "password");
		findByIdStatement = connection.prepareStatement(
					"SELECT * FROM customer WHERE customerId = ?"
					);
		findByLastNameStatement = connection.prepareStatement(
				"SELECT * FROM customer WHERE lastName = ?"
				);
		insertCustomerStatement = connection.prepareStatement(
				"INSERT INTO customer (firstName, lastName, phoneNumber, email) "
				+ "VALUES (?, ?, ?, ?)");
		updateStatement = connection.prepareStatement(
				"UPDATE CUSTOMER SET firstName = ?, lastName = ?, phoneNumber = ?, email = ?"
				+ " WHERE customerId = ?");
		deleteStatement = connection.prepareStatement(
				"DELETE FROM CUSTOMER WHERE customerId = ?");
	}

	/* (non-Javadoc)
	 * @see com.trivera.jdbc.CustomerDAO#insert(com.trivera.jdbc.Customer)
	 */
	@Override
	public Customer insert(Customer customer) {
		insertCustomerStatement.setString(1, customer.getFirstName());
		insertCustomerStatement.setString(2, customer.getLastName());
		insertCustomerStatement.setString(3, customer.getPhoneNumber());
		insertCustomerStatement.setString(4, customer.getEmail());
		ResultSet keys = insertCustomerStatement.getGeneratedKeys();
		if (keys.next()) {
			Long customerId = keys.getLong(1);
			customer.setCustomerId(customerId);
		}
		return customer;
	}

	/* (non-Javadoc)
	 * @see com.trivera.jdbc.CustomerDAO#findById(java.lang.Long)
	 */
	@Override
	public Customer findById(Long customerId) {
		findByIdStatement.setLong(1, customerId);
		ResultSet resultSet = findByIdStatement.executeQuery();
		if (resultSet.next()) {
			return rowToCustomer(resultSet);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.trivera.jdbc.CustomerDAO#findByLastName(java.lang.String)
	 */
	@Override
	public List<Customer> findByLastName(String lastName) {
		List<Customer> customers = new Vector<>();
		// Validate lastName here!
		// See JSR 303 Bean Validation ("hibernate-validator") or Spring validation
		findByLastNameStatement.setString(1, lastName);
		ResultSet resultSet = findByLastNameStatement.executeQuery();
		while (resultSet.next()) {
			customers.add(rowToCustomer(resultSet));
		}
		return customers;
		
		// SQL Injection vulnerable!
		// Expecting: Weasley
		// Weasley' or 1=1 or lastName = 'Weasley
		// Weasley'; DELETE FROM customer; SELECT * FROM customer WHERE lastName = 'Weasley
		// SELECT * FROM customer WHERE lastName = 'Weasley'; DELETE FROM customer; SELECT * FROM customer WHERE lastName = 'Weasley'
		// Statement badStatement = connection.createStatement();
		// badStatement.executeQuery("SELECT * FROM customer WHERE lastName = '" + lastName + "'");
	}

	/* (non-Javadoc)
	 * @see com.trivera.jdbc.CustomerDAO#findAll()
	 */
	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new Vector<>();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
		while (resultSet.next()) {
			// resultSet.getLong(1);
			Customer c = rowToCustomer(resultSet);
			customers.add(c);
		}
		resultSet.close();
		statement.close();
		return customers;
	}

	private Customer rowToCustomer(ResultSet resultSet) throws SQLException {
		Long customerId = resultSet.getLong("customerId");
		String firstName = resultSet.getString("firstName");
		String lastName = resultSet.getString("lastName");
		String phoneNumber = resultSet.getString("phoneNumber");
		String email = resultSet.getString("email");
		Customer c = new Customer(customerId, firstName, lastName, phoneNumber, email);
		return c;
	}

	/* (non-Javadoc)
	 * @see com.trivera.jdbc.CustomerDAO#update(com.trivera.jdbc.Customer)
	 */
	@Override
	public Customer update(Customer customer) {
		// TODO - consider adding optimistic locking (use verion# or timestamp for last-modified or "all-fields")

		updateStatement.setString(1, customer.getFirstName());
		updateStatement.setString(2, customer.getLastName());
		updateStatement.setString(3, customer.getPhoneNumber());
		updateStatement.setString(4, customer.getEmail());
		updateStatement.setLong(5, customer.getCustomerId());

		return findById(customer.getCustomerId());
	}

	/* (non-Javadoc)
	 * @see com.trivera.jdbc.CustomerDAO#delete(com.trivera.jdbc.Customer)
	 */
	@Override
	public Customer delete(Customer customer) {
		try {
			connection.setAutoCommit(false);
			deleteStatement.setLong(1, customer.getCustomerId());
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return customer;
	}
}
