package com.trivera.jdbc.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.trivera.jdbc.Address;
import com.trivera.jdbc.MyBatisFactory;

public class TestAddressMapper {
	
	private AddressMapper dao;
	private SqlSession session;
	private AddressMapper mapper;
	
	@Before
	public void setup() {
		session = MyBatisFactory.getInstance().openSession(ExecutorType.REUSE, true);
		mapper = session.getMapper(AddressMapper.class);
	}
	
	@After
	public void tearDown() {
		session.close();
	}

	@Test
	public void TestFindAll() {
		List<Address> addresses = mapper.findAll();
		assertNotNull(addresses);
		assertTrue("No addresses found!", addresses.size() > 0);
	}

	@Test
	public void TestFindById() {
		Address harryHome = mapper.findById(1L);
		assertNotNull(harryHome);
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
