package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_SHOPPINGCARMINI;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.mapper.ShoppingCarMapper;

@Service
public class ShoppingCarServiceImp implements ShoppingCarServiceInf {

	@Autowired
	ShoppingCarMapper shoppingCarMapper;

	public List<T_MALL_SHOPPINGCAR> get_shoppingCar_by_user_id(T_MALL_USER user) {
		List<T_MALL_SHOPPINGCAR> list_car = shoppingCarMapper.select_shoppingCar_by_user_id(user);
		return list_car;
	}

	public void add_car(T_MALL_SHOPPINGCAR car) {
		int i = shoppingCarMapper.insert_car(car);

	}

	public void update_car(T_MALL_SHOPPINGCAR car) {
		int i = shoppingCarMapper.update_car(car);

	}

	public T_MALL_SHOPPINGCARMINI getShoppingCarTotal(int id) {
		T_MALL_SHOPPINGCARMINI i = shoppingCarMapper.selectShoppingCarTotal(id);
		return i;
		
	}

	public void shoppingCar_change_car_tjshl(int id, int sku_id, int tishl) {
		shoppingCarMapper.shoppingCar_change_car_tjshl( id, sku_id,  tishl);
	}

}
