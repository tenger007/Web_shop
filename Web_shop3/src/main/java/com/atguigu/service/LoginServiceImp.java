package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_USER;
import com.atguigu.mapper.TestMapper;

@Service
public class LoginServiceImp implements LoginServiceInf {

	@Autowired
	TestMapper testMapper;

	public T_MALL_USER login(T_MALL_USER user) {
		T_MALL_USER user_login = testMapper.select_user(user);
		return user_login;
	}

}
