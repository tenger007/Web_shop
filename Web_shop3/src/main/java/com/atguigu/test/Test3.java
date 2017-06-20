package com.atguigu.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_TRADE_MARK;
import com.atguigu.mapper.TestMapper1;
import com.google.gson.Gson;

public class Test3 {
   public static void main(String[] args) throws IOException {
	SqlSessionFactory instance = MySqlSessionFactory.getInstance();
	SqlSession openSession = instance.openSession();
	TestMapper1 mapper = openSession.getMapper(TestMapper1.class);
	List<T_MALL_CLASS_1> class_1 = mapper.select_class_1();
	for (int i = 0; i < class_1.size(); i++) {
		int n = class_1.get(i).getId();
	 List<T_MALL_TRADE_MARK> trade_mark_by_class_1_id = mapper.select_trade_mark_by_class_1_id(n);
	 for (int j = 0; j < trade_mark_by_class_1_id.size(); j++) {
		 int id = trade_mark_by_class_1_id.get(j).getId();
		 System.out.println(id);
		 List<T_MALL_PRODUCT> product_by_tmid = mapper.select_product_by_tmid(id);
		 if(product_by_tmid.size()!=0){
		   Gson gson = new Gson();
			String json = gson.toJson(product_by_tmid);
			System.out.println(json);
		   FileOutputStream f1 = new FileOutputStream("d:/js/product"+id+".js");
		   f1.write(json.getBytes("utf-8"));
		 }
	}
		
		
	}
	
}
}
