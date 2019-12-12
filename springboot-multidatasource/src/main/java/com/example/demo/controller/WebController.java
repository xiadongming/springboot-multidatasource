package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.City;
import com.example.demo.entity.Student;
import com.example.demo.test1.IUserMapper1;
import com.example.demo.test2.IUserMapper2;

@RestController
@RequestMapping("/web")
public class WebController {
	@Autowired
	private IUserMapper1 userMapper1;
	@Autowired
	private IUserMapper2 userMapper2;
	@RequestMapping("/datasource1")
	@ResponseBody
	public String testUserMapper1(String id) {
		City queryById = userMapper1.queryById(id);
		System.out.println(JSONObject.toJSON(queryById));
		return "success";
	}
	@RequestMapping("/datasource2")
	@ResponseBody
	public String testUserMapper2(String id) {
		Student queryById = userMapper2.queryById(id);
		System.out.println(JSONObject.toJSONString(queryById));
		return "success";
	}
}
