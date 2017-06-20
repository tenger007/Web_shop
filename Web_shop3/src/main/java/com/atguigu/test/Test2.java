package com.atguigu.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_CLASS_2;
import com.atguigu.mapper.TestMapper1;
import com.google.gson.Gson;


public class Test2 {
   public static void main(String[] args) throws IOException {
	SqlSessionFactory instance = MySqlSessionFactory.getInstance();
	SqlSession openSession = instance.openSession();
	TestMapper1 mapper = openSession.getMapper(TestMapper1.class);
	List<T_MALL_CLASS_1> class_1 = mapper.select_class_1();
	for (int i = 0; i < class_1.size(); i++) {
		List<T_MALL_CLASS_2> class_2 = mapper.select_class_2_by_class_1_id(class_1.get(i).getId());
		Gson gson = new Gson();
		String json = gson.toJson(class_2);
		System.out.println(json);
		List fromJson = gson.fromJson(json,List.class);
		System.out.println(fromJson);
	   FileOutputStream f1 = new FileOutputStream("d:/class2"+i+".js");
	   f1.write(json.getBytes("utf-8"));
	}
	
}
}
