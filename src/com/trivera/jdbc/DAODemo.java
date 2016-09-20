package com.trivera.jdbc;

public class DAODemo {
	final static String EMAIL = "harry.potter@auror.mom.gov.uk";
	final static String PHONE = "+44 0206 919-9456";
//	@Inject
	final static CustomerDAO dao;
	
	static {
		dao = CustomerDAOFactory.getInstance();		
	}
	
	public static void main(String[] args) {
		
		Customer harry = dao.findById(1L);
		System.out.println(harry);
		harry.setPhoneNumber(PHONE);
		harry.setEmail(EMAIL);
		dao.update(harry);
		
		Customer verifyHarry = dao.findById(1L);
		// Use junit assert statements in a real unit test!
		if (verifyHarry.getEmail().equals(EMAIL)) {
			System.out.println("It worked!");
		} else {
			System.out.println("It didn't work!");
		}
	}

}
