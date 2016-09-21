package com.trivera.jdbc;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.trivera.jdbc.mapper.AddressMapper;

public class MyBatisFactory {

	// This should be static so all instances share the same configuration
	private static SqlSessionFactory factory = null;
	
	static {
		try {
			String resource = "SqlMapConfig.xml";
			InputStream is = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(is);
			factory.getConfiguration().addMapper(AddressMapper.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getInstance() {
		return factory;
	}
}
