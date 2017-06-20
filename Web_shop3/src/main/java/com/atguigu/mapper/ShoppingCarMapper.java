package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_SHOPPINGCARMINI;
import com.atguigu.bean.T_MALL_USER;

public interface ShoppingCarMapper {

	List<T_MALL_SHOPPINGCAR> select_shoppingCar_by_user_id(T_MALL_USER user);

	int insert_car(T_MALL_SHOPPINGCAR car);

	int update_car(T_MALL_SHOPPINGCAR car);


	T_MALL_SHOPPINGCARMINI selectShoppingCarTotal(int id);

	void shoppingCar_change_car_tjshl(int id, int sku_id, int tishl);

}
