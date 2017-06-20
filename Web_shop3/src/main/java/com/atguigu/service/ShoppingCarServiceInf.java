package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER;

public interface ShoppingCarServiceInf {
	public List<T_MALL_SHOPPINGCAR> get_shoppingCar_by_user_id(T_MALL_USER user);
}
