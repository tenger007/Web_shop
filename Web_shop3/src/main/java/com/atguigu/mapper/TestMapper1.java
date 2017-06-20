package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_CLASS_2;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_TRADE_MARK;


public interface TestMapper1 {
	public int select_test();

	public List<T_MALL_CLASS_1> select_class_1();

	public List<T_MALL_CLASS_2> select_class_2_by_class_1_id(int id);
	
	public List<T_MALL_TRADE_MARK> select_trade_mark_by_class_1_id(int id);
	public List<T_MALL_PRODUCT> select_product_by_tmid(int id);
}
