package com.atguigu.service;

import java.util.ArrayList;
import java.util.List;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuServiceInf {

	public List<T_MALL_PRODUCT> get_spu_by_class_id_and_tm_id(int class_1_id, int class_2_id, int tm_id);

	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id);

	public void sku_add(T_MALL_SKU sku, ArrayList<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value);
}
