package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.T_MALL_TRADE_MARK;
import com.atguigu.service.SearchServiceImp;

@Controller
public class SearchController {

	@Autowired
	SearchServiceImp searchServiceImp;
	
	@RequestMapping("goto_sku_detail/{sku_id}/{shp_id}")
	public String goto_sku_detail(@PathVariable int sku_id, @PathVariable int shp_id, ModelMap map) {

		OBJECT_T_MALL_SKU object_sku = searchServiceImp.get_object_sku_by_sku_id(sku_id);

		List<T_MALL_SKU> list_sku = searchServiceImp.get_sku_list_by_shp_id(shp_id);

		map.put("object_sku", object_sku);
		map.put("list_sku", list_sku);

		return "reception_manager/sale_search_sku_detail";
	}

	/***
	 * 商品分类检索
	 * 
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	@RequestMapping("goto_search_index/{class_2_id}/{class_2_name}")
	public String search_index(@PathVariable int class_2_id, @PathVariable String class_2_name, ModelMap map) {


		List<OBJECT_T_MALL_SKU> list_object_sku = searchServiceImp.get_sku_by_class_2_id_and_attr_value(class_2_id,
				null, "", 0);

		List<OBJECT_T_MALL_ATTR> list_object_attr = searchServiceImp.get_attr_by_class_2_id(class_2_id);

		List<T_MALL_TRADE_MARK> list_tm = searchServiceImp.get_tm_by_class_2_id(class_2_id);

		map.put("list_object_sku", list_object_sku);
		map.put("list_object_attr", list_object_attr);
		map.put("list_tm", list_tm);
		return "reception_manager/sale_search_index";
	}

	/***
	 * 商品分类属性检索
	 * 
	 * @param class_2_id
	 * @param map
	 * @return  
	 */
	@RequestMapping("search_index_attr_value")
	public String search_index_attr_value(int class_2_id, MODEL_T_MALL_SKU_ATTR_VALUE list_attr_value, String order,
			ModelMap map) {

		List<OBJECT_T_MALL_SKU> list_object_sku = searchServiceImp.get_sku_by_class_2_id_and_attr_value(class_2_id,
				list_attr_value.getList_attr_value(), order, 0);

		map.put("list_object_sku", list_object_sku);

		return "reception_manager/sale_search_index_inner";
	}

}
