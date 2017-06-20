package com.atguigu.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.bean.T_MALL_TRADE_MARK;
import com.atguigu.mapper.TestMapper1;
import com.google.gson.Gson;


public class Test4 {
   public static void main(String[] args) throws IOException {
	SqlSessionFactory instance = MySqlSessionFactory.getInstance();
	SqlSession openSession = instance.openSession();
	TestMapper1 mapper = openSession.getMapper(TestMapper1.class);
	List<T_MALL_CLASS_1> select_class_1 = mapper.select_class_1();
	for (int j = 0; j < select_class_1.size(); j++) {
		int id = select_class_1.get(j).getId();
		List<T_MALL_TRADE_MARK> class_tm = mapper.select_trade_mark_by_class_1_id(id);
		Gson gson = new Gson();
		String json = gson.toJson(class_tm);
		FileOutputStream f1 = new FileOutputStream("d:/js/trade_mark_"+id+".js");
		f1.write(json.getBytes("utf-8"));
	}
   }
   
   
   @Test
   public void test1(){
	String s = "e";
	   byte[] s1 = s.getBytes();
	   System.out.println(s1.length);
   }  
   

}
