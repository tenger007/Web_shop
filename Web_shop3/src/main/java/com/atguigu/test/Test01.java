package com.atguigu.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.bean.T_MALL_CLASS_1;
import com.atguigu.mapper.TestMapper1;
import com.google.gson.Gson;


public class Test01 {
   public static void main(String[] args) throws IOException {
	   ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	  SqlSessionFactory sessionFactory = ioc.getBean(SqlSessionFactory.class);
	  ioc.close();
	   SqlSession openSession = sessionFactory.openSession();
	   TestMapper1 mapper = openSession.getMapper(TestMapper1.class);
	   System.out.println(mapper);
	List<T_MALL_CLASS_1> class_1 = mapper.select_class_1();
	Gson gson = new Gson();
	String json = gson.toJson(class_1);
	System.out.println(json);
   FileOutputStream f1 = new FileOutputStream("D:\\class1.js");
   f1.write(json.getBytes("utf-8"));
  f1.close();
}
   
   
}
