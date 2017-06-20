package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;

public interface AttrValueMapper {

	List<OBJECT_T_MALL_ATTR> select_attr_by_class_2_id(int class_2_id);

	int insert_into_t_mall_value(HashMap<Object, Object> hashMap);

	int insert_into_t_mall_attr(OBJECT_T_MALL_ATTR object_T_MALL_ATTR);

}
