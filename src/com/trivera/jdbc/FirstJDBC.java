package com.trivera.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FirstJDBC {

	public static void main(String[] args) {
		System.out.println("Hello, World");
		
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
			connection = DriverManager.getConnection(
					"jdbc:derby://localhost:1527/weasley;create=true",
					"user",
					"password"
					);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
			while(resultSet.next()) {
				//resultSet.getLong(1);
				Long customerId = resultSet.getLong("customerId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String phoneNumber = resultSet.getString("phoneNumber");
				String email = resultSet.getString("email");
				System.out.println(
						"#" + customerId 
						+ firstName + " " 
						+ lastName + " "
						+ phoneNumber + " "
						+ lastName + " "
						+ email + " "
				);
			}
			resultSet.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					if (statement != null) {
						statement.close();
					}
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}
}
