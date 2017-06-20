package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_PRODUCT;


public interface SpuServiceInf {


	int spu_publish(T_MALL_PRODUCT spu, List<String> image_list);

}
