package com.atguigu.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;
import com.atguigu.mapper.AttrValueMapper;

@Service
public class AttrValueServiceImp implements AttrValueServiceInf {

	@Autowired
	AttrValueMapper attrValueMapper;

	public List<OBJECT_T_MALL_ATTR> get_attr_by_class_2_id(int class_2_id) {
		List<OBJECT_T_MALL_ATTR> list_object_attr_value = attrValueMapper.select_attr_by_class_2_id(class_2_id);
		return list_object_attr_value;
	}

	public int attr_add(List<OBJECT_T_MALL_ATTR> list_attr, int class_2_id) {

		int num = 0;

		for (int i = 0; i < list_attr.size(); i++) {
			HashMap<Object, Object> hashMap = new HashMap<Object, Object>();

			// 插入一条分类属性
			list_attr.get(i).setFlbh2(class_2_id);
			int a = attrValueMapper.insert_into_t_mall_attr(list_attr.get(i));

			List<T_MALL_VALUE> list_value = list_attr.get(i).getList_value();
			hashMap.put("list_value", list_value);
			hashMap.put("attr_id", list_attr.get(i).getId());
			// 批量插入分类属性值集合
			int b = attrValueMapper.insert_into_t_mall_value(hashMap);

			num++;
		}
		return num;
	}

}
