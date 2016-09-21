package com.trivera.jdbc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.trivera.jdbc.Address;

public interface AddressMapper {

	public static final String FIND_ALL = "SELECT * FROM address";
	public static final String FIND_BY_ID = 
			"SELECT * FROM address WHERE address_id = #{addressId}";
	public static final String INSERT = "INSERT INTO address "
			+ "(LINE1, LINE2, CITY, STATE, ZIP_CODE) "
			+ "values "
			+ "(#{line1}, #{line2}, #{city}, #{state}, #{zip} )";
	public static final String FIND_BY_CITY_STATE = 
			"SELECT * FROM address WHERE city = #{city} AND state = #{state}";
	public static final String RESULT_MAPPER = "addressResultMapper";
	public static final String UPDATE = "UPDATE address SET "
			+ "line1 = #{line1}, "
			+ "line2 = #{line2}, "
			+ "city = #{city}, "
			+ "state = #{state}, "
			+ "zip = #{zip} "
			+ "WHERE address_id = #{addressId}"
			;
	public static final String DELETE = "DELETE FROM address "
			+ "WHERE address_id = #{addressId}"
			;
	
	@Select(FIND_ALL)
	@Results(id = RESULT_MAPPER, value = {
		@Result(property="addressId", column="ADDRESS_ID"),
		@Result(property="line1", column="LINE1"),
		@Result(property="line2", column="LINE2"),
		@Result(property="city", column="CITY"),
		@Result(property="state", column="STATE"),
		@Result(property="zip", column="ZIP_CODE"),
		
	})
	List<Address> findAll();
	
	@Select(FIND_BY_ID)
	@ResultMap(RESULT_MAPPER)
	Address findById(Long addressId);
	
	@Select(FIND_BY_CITY_STATE)
	List<Address> findByCityState(
			@Param("city") String city, 
			@Param("state") String state);
	
	@Insert(INSERT)
	@Options(useGeneratedKeys=true, keyProperty="addressId")
	void insert(Address address);
	
	@Update(UPDATE)
	public Address update(Address address);
	
	
	@Delete(DELETE)
	public Address delete(Address address);
	
	
}
