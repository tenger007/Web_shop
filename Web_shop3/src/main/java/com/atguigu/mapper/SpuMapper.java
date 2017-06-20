package com.atguigu.mapper;

import java.util.Map;

import com.atguigu.bean.T_MALL_PRODUCT;


public interface SpuMapper {

	public int select_test();

	public int insert_into_t_mall_product_by_spu(T_MALL_PRODUCT spu);

	public int insert_into_t_mall_product_image(Map<Object, Object> map);

}
