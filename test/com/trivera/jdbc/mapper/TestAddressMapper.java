package com.trivera.jdbc.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import com.trivera.jdbc.Address;
import com.trivera.jdbc.MyBatisFactory;

public class TestAddressMapper {
	
	private AddressMapper dao;
	private SqlSession session;
	
	@Before
	public void setup() {
		session = MyBatisFactory.getInstance().openSession(ExecutorType.REUSE, true);
	}

	@Test
	public void TestFindAll() {
//		 dao.findAll();
		List<Address> addresses = session.getMapper(AddressMapper.class).findAll();
		assertNotNull(addresses);
		assertTrue("No addresses found!", addresses.size() > 0);
	}

	@Test
	public void TestFindById() {
//		return dao.findById(addressId);
	}

//	public List<Address> findByCityState(String city, String state) {
//		return dao.findByCityState(city, state);
//	}
//
//	public void insert(Address address) {
//		dao.insert(address);
//	}
//
//	public Address update(Address address) {
//		return dao.update(address);
//	}
//
//	public Address delete(Address address) {
//		return dao.delete(address);
//	}
//
//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}
