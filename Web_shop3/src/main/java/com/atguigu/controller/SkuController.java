package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.SkuServiceImp;

@Controller
public class SkuController {

	@Autowired
	SkuServiceImp skuServiceImp;

	@RequestMapping("goto_sku_publish/{success}")
	public String goto_sku_publish(@PathVariable String success) {

		return "background_manager/manager_sku_publish";

	}

	/***
	 * sku添加操作
	 * 
	 * @param spu
	 * @param list_attr
	 * @param sku
	 * @return
	 */
	@RequestMapping(value = "sku_add", method = RequestMethod.POST)
	public ModelAndView sku_add(T_MALL_PRODUCT spu, MODEL_OBJECT_T_MALL_ATTR list_attr, T_MALL_SKU sku) {

		// 加入商品id到sku对象中，方便业务层的insert执行
		sku.setShp_id(spu.getSpu_id());

		// 参数处理，将提分类属性集合对象转换成t_mall_sku_attr_value关联对象
		ArrayList<T_MALL_SKU_ATTR_VALUE> list_sku_attr_value = new ArrayList<T_MALL_SKU_ATTR_VALUE>();
		for (int i = 0; i < list_attr.getList_attr().size(); i++) {
			if (list_attr.getList_attr().get(i).getId() != 0
					&& list_attr.getList_attr().get(i).getList_value().get(0).getId() != 0) {
				T_MALL_SKU_ATTR_VALUE t_MALL_SKU_ATTR_VALUE = new T_MALL_SKU_ATTR_VALUE();
				t_MALL_SKU_ATTR_VALUE.setShp_id(spu.getSpu_id());
				t_MALL_SKU_ATTR_VALUE.setShxm_id(list_attr.getList_attr().get(i).getId());
				t_MALL_SKU_ATTR_VALUE.setShxzh_id(list_attr.getList_attr().get(i).getList_value().get(0).getId());
				list_sku_attr_value.add(t_MALL_SKU_ATTR_VALUE);
			}
		}

		// 调用业务层，插入sku和sku关联信息对象
		skuServiceImp.sku_add(sku, list_sku_attr_value);

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_sku_publish/{success}.do");

		modelAndView.addObject("success", "成功插入一条sku数据");

		return modelAndView;
	}

	/***
	 * 根据商品和二级分类查询spu
	 * 
	 * @param class_1_id
	 * @param class_2_id
	 * @param tm_id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("get_spu_by_class_id_and_tm_id")
	public List<T_MALL_PRODUCT> get_spu_by_class_id_and_tm_id(int class_1_id, int class_2_id, int tm_id) {
		List<T_MALL_PRODUCT> list_spu = skuServiceImp.get_spu_by_class_id_and_tm_id(class_1_id, class_2_id, tm_id);
		return list_spu;

	}

	/***
	 * 根据二级分类id查询分类属性
	 * 
	 * @param class_2_id
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "get_attr_by_class_2_id_sku", method = RequestMethod.POST)
	public String get_attr_by_class_2_id_sku(int class_2_id, ModelMap map) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = skuServiceImp.get_attr_by_class_2_id(class_2_id);

		map.put("list_object_attr_value", list_object_attr_value);
		return "background_manager/manager_sku_publish_inner";
	}

}
