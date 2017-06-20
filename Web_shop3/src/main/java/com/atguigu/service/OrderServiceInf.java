package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;

public interface OrderServiceInf {

	public void add_order(List<OBJECT_T_MALL_ORDER> list_object_order, T_MALL_ADDRESS address);

	public int pay_order(List<OBJECT_T_MALL_ORDER> list_object_order);

}
