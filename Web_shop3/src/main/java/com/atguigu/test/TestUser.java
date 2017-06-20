package com.atguigu.test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.UserWebServiceInf;

public class TestUser {
 public static void main(String[] args) {
	JaxWsProxyFactoryBean proxy = new JaxWsProxyFactoryBean();
      proxy.setAddress("http://localhost:18080/Web_shop_UserWebService/UserWebServiceInf?wsdl");	
      UserWebServiceInf create = proxy.create(UserWebServiceInf.class);
      T_MALL_USER user = new T_MALL_USER();
      user.setYh_mch("xiaoming");
      user.setYh_mm("123");
      T_MALL_USER user3 = new T_MALL_USER();
      user3.setYh_mch("xiaozhou");
      user3.setYh_mm("123");
      T_MALL_USER user2 = create.select_user(user);
      System.out.println(user2.toString());
     create.insert_user(user3);
}
 
 @Test
 public void test1(){
	 
 }
}
