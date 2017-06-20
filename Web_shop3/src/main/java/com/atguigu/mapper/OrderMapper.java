package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(OBJECT_T_MALL_ORDER object_T_MALL_ORDER);

	void insert_order_infos(List<T_MALL_ORDER_INFO> list);

	void delete_shoppingCars(List<T_MALL_ORDER_INFO> list_order_info);

	void insert_flow(T_MALL_FLOW t_MALL_FLOW);

	int select_sku_for_update(T_MALL_ORDER_INFO t_MALL_ORDER_INFO);

	void update_sku_kc(HashMap<Object, Object> hashMap);

	void update_order_jdh(HashMap<Object, Object> hashMap);

	void update_flow(T_MALL_FLOW t_MALL_FLOW);

}
