package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_TRADE_MARK;

public interface SearchMapper {

	List<OBJECT_T_MALL_SKU> select_sku_by_class_2_id_and_attr_value(HashMap<Object, Object> hashMap);

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(int class_2_id);

	List<T_MALL_TRADE_MARK> select_tm_by_class_2_id(int class_2_id);

	OBJECT_T_MALL_SKU select_object_sku_by_sku_id(int sku_id);

	List<T_MALL_SKU> select_sku_list_by_shp_id(int shp_id);

	List<OBJECT_T_MALL_SKU_PRODUCT> select_sku_product(int yh_id);

}
