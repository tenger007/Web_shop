package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SpuMapper;


@Service
public class SpuServiceImp implements SpuServiceInf {

	@Autowired
	SpuMapper spuMapper;

	@Override
	public int spu_publish(T_MALL_PRODUCT spu, List<String> image_list) {
		// 插入spu信息，返回spu主键
		spuMapper.insert_into_t_mall_product_by_spu(spu);

		// 批量插入image信息，根据spu主键
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("shp_id", spu.getId());
		hashMap.put("list", image_list);

		int i = spuMapper.insert_into_t_mall_product_image(hashMap);

		return i;
	}

}
