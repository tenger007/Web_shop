package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_TRADE_MARK;
import com.atguigu.mapper.SearchMapper;

@Service
public class SearchServiceImp implements SearchServiceInf {
	@Autowired
	SearchMapper searchMapper;

	/***
	 * 查询检索sku集合
	 */
	public List<OBJECT_T_MALL_SKU> get_sku_by_class_2_id_and_attr_value(int class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_attr_value, String order, int tm_id) {
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("class_2_id", class_2_id);

		if (list_attr_value != null) {
			// 拼接动态sql语句
			StringBuffer sbf = new StringBuffer();

			sbf.append(" and sku.id in ");
			sbf.append("( select sku_0.sku_id from");

			for (int i = 0; i < list_attr_value.size(); i++) {
				sbf.append(" (select sku_id from t_mall_sku_attr_value in_attr_val where in_attr_val.shxm_id="
						+ list_attr_value.get(i).getShxm_id() + " and in_attr_val.shxzh_id = "
						+ list_attr_value.get(i).getShxzh_id() + ") sku_" + i);
				if (i < (list_attr_value.size() - 1)) {
					sbf.append(" , ");
				}

			}

			if (list_attr_value.size() > 1) {
				sbf.append(" where ");
			}

			for (int j = 0; j < list_attr_value.size(); j++) {
				if (j < (list_attr_value.size() - 1)) {
					sbf.append(" sku_" + j + ".sku_id = sku_" + (j + 1) + ".sku_id ");
				}

				if (j < (list_attr_value.size() - 2)) {
					sbf.append(" and ");
				}
			}
			sbf.append(" ) ");

			hashMap.put("sql", sbf.toString());

		}
		hashMap.put("order", order);
		List<OBJECT_T_MALL_SKU> list_object_sku = searchMapper.select_sku_by_class_2_id_and_attr_value(hashMap);
		return list_object_sku;
	}

	/***
	 * 根据二级分类查找分类属性集合
	 * 
	 * @param class_2_id
	 * @return
	 */
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_object_attr_value = searchMapper.select_attr_by_class_2_id(class_2_id);
		return list_object_attr_value;
	}

	public List<T_MALL_TRADE_MARK> get_tm_by_class_2_id(int class_2_id) {
		List<T_MALL_TRADE_MARK> list_tm = searchMapper.select_tm_by_class_2_id(class_2_id);
		return list_tm;
	}

	/**
	 * 根据skuid查询sku对象
	 */
	public OBJECT_T_MALL_SKU get_object_sku_by_sku_id(int sku_id) {
		OBJECT_T_MALL_SKU object_sku = searchMapper.select_object_sku_by_sku_id(sku_id);
		return object_sku;
	}

	/**
	 * 根据商品id查询sku集合
	 */
	public List<T_MALL_SKU> get_sku_list_by_shp_id(int shp_id) {
		List<T_MALL_SKU> list_sku = searchMapper.select_sku_list_by_shp_id(shp_id);
		return list_sku;
	}

	public List<OBJECT_T_MALL_SKU_PRODUCT> get_sku_product(int yh_id) {
		List<OBJECT_T_MALL_SKU_PRODUCT> select_sku_product = searchMapper.select_sku_product(yh_id);
		return select_sku_product;
		
	}

}
