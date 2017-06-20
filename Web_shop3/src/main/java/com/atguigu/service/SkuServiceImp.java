package com.atguigu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SkuMapper;

@Service
public class SkuServiceImp implements SkuServiceInf {

	@Autowired
	SkuMapper skuMapper;

	/***
	 * 根据二级分类获得分类属性集合
	 */
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_object_attr_value = skuMapper.select_attr_by_class_2_id(class_2_id);
		return list_object_attr_value;
	}

	/***
	 * 根据商品和二级分类查询spu
	 * 
	 * @param class_1_id
	 * @param class_2_id
	 * @param tm_id
	 * @return
	 */
	public List<T_MALL_PRODUCT> get_spu_by_class_id_and_tm_id(int class_1_id, int class_2_id, int tm_id) {

		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("class_1_id", class_1_id);
		hashMap.put("class_2_id", class_2_id);
		hashMap.put("tm_id", tm_id);

		List<T_MALL_PRODUCT> list_spu = skuMapper.select_spu_by_class_id_and_tm_id(hashMap);
		return list_spu;
	}

	/***
	 * sku添加操作
	 * 
	 * @param spu
	 * @param list_attr
	 * @param sku
	 * @return
	 */
	public void sku_add(T_MALL_SKU sku, ArrayList<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value) {

		// 插入sku表数据，返回主键
		skuMapper.insert_into_t_mall_sku(sku);

		// 将sku返回主键放入sku关联对象集合
		for (int i = 0; i < list_sku_attr_value.size(); i++) {
			list_sku_attr_value.get(i).setSku_id(sku.getId());
		}

		// 批量插入sku关联对象表数据
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("list_attr_value", list_sku_attr_value);
		skuMapper.insert_into_t_mall_sku_attr_value(hashMap);

	}

}
