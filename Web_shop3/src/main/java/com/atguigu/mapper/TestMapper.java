package com.atguigu.mapper;

import com.atguigu.bean.T_MALL_USER;

public interface TestMapper {

	T_MALL_USER select_user(T_MALL_USER user);
	String[] select_yh_dzh(int yh_id);
}
