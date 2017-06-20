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
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.service.AttrValueServiceImp;


@Controller
public class AttrValueController {

	@Autowired
	AttrValueServiceImp attrValueServiceImp;

	@RequestMapping(value = "attr_add.do", method = RequestMethod.POST)
	public ModelAndView attr_add(MODEL_OBJECT_T_MALL_ATTR list_attr, int class_2_id, String class_2_name) {

		//获得页面提交的属性集合
		List<OBJECT_T_MALL_ATTR> list_attr2 = list_attr.getList_attr();
		//循环页面提交的属性集合
		for (int i = 0; i < list_attr2.size(); i++) {
			//每次循环得到属性集合中的一个属性，创建一个新的属性值集合
			List<T_MALL_VALUE> list_value2 = new ArrayList<T_MALL_VALUE>();
			//获得页面提交的属性值集合
			List<T_MALL_VALUE> list_value = list_attr2.get(i).getList_value();
			//循环属性值集合，将属性不为空的放入新的属性值集合中
			for (int j = 0; j < list_value.size(); j++) {
				if(list_value.get(j).getShxzh() != null){
					list_value2.add(list_value.get(j));
				}
				
			}
			//将新的属性值集合放入到当前属性中，替换掉原来的属性值集合
			list_attr2.get(i).setList_value(list_value2);
		}
		// 调用业务层的插入方法
		int i = attrValueServiceImp.attr_add(list_attr2, class_2_id);

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_attr_add/{class_2_id}/{class_2_name}.do");

		modelAndView.addObject("class_2_id", class_2_id);
		modelAndView.addObject("class_2_name", class_2_name);

		return modelAndView;
	}

	@RequestMapping("goto_attr_add/{class_2_id}/{class_2_name}")
	public String goto_attr_add(@PathVariable int class_2_id, @PathVariable String class_2_name) {

		return "background_manager/manager_attr_add";
	}

	@RequestMapping("goto_attr_publish/{success}")
	public String goto_attr_publish() {
		return "background_manager/manager_attr_publish";
	}

	@RequestMapping(value = "get_attr_by_class_2_id", method = RequestMethod.POST)
	public String get_attr_by_class_2_id(int class_2_id, ModelMap map) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueServiceImp.get_attr_by_class_2_id(class_2_id);

		map.put("list_object_attr_value", list_object_attr_value);
		return "background_manager/manager_attr_publish_inner";
	}
	
	@ResponseBody
	@RequestMapping(value = "get_attr_by_class_2_id_json", method = RequestMethod.POST)
	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id_json(int class_2_id) {

		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueServiceImp.get_attr_by_class_2_id(class_2_id);
       
		return list_object_attr_value;
	}

}
