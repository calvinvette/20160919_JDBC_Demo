package com.trivera.jdbc;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 
 * @author calvin
 *
 */
public class CustomerDAOFactory {
	// Also make it a Singleton to share the
	// dao instance across all threads
	private static CustomerDAO dao;
	private static boolean myBatis = false;

	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("DAO", Locale.getDefault());
			if (bundle.getString("CustomerDAO").equals("com.trivera.jdbc.CustomerMyBatisDAO")) {
				System.out.println("Loading MyBatis");
				myBatis = true;
			} else {
				System.out.println("Defaulting to JDBC DAO");
			}
		} catch (MissingResourceException e) {
			System.out.println("Can't find " + e.getLocalizedMessage() + " (" + e.getKey() + ") defaulting to JDBC DAO");
			e.printStackTrace();
		}
	}

	// Lazy Instantiation = only load on first request
	public static CustomerDAO getDao() {
		if (dao == null) {
			if (myBatis) {
				dao = new CustomerMyBatisDAO();
			} else {
				dao = new CustomerJdbcDAO();
			}
		}
		return dao;
	}

	public static CustomerDAO getInstance() {
		// TODO Auto-generated method stub
		return getDao();
	}

}
