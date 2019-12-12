package com.example.demo.test1;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.City;

public interface IUserMapper1 {

	@Select("SELECT id,name , state  FROM city WHERE id = #{id}")
	City queryById(@Param("id") String id);

}
