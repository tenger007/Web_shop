package com.atguigu.test;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	private static SqlSessionFactory instance = null;
	private static String resource = "mybatis-config.xml";
  private MySqlSessionFactory(){}
  
  public static SqlSessionFactory getInstance(){
	  if(instance==null){
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
		InputStream f1 = MySqlSessionFactory.class.getClassLoader().getResourceAsStream(resource);
		instance= sqlSessionFactoryBuilder.build(f1);
	  }
	  return instance;
  }
}
