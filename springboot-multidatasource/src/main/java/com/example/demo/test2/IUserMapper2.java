package com.example.demo.test2;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Student;

public interface IUserMapper2 {
	@Select("SELECT id,name , age FROM mystudent WHERE id = #{id}")
	Student queryById(@Param("id") String id);

}
