package com.atguigu.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuMapper {

	List<T_MALL_PRODUCT> select_spu_by_class_id_and_tm_id(HashMap<Object, Object> hashMap);

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(int class_2_id);

	void insert_into_t_mall_sku(T_MALL_SKU sku);

	void insert_into_t_mall_sku_attr_value(HashMap<Object, Object> hashMap);

}
