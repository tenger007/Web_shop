package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_TRADE_MARK;

public interface SearchServiceInf {

	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_value(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String string, int i);

	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id);

	public List<T_MALL_TRADE_MARK> get_tm_by_class_2_id(int class_2_id);
	public OBJECT_T_MALL_SKU get_object_sku_by_sku_id(int sku_id);

	public List<T_MALL_SKU> get_sku_list_by_shp_id(int shp_id);
}
