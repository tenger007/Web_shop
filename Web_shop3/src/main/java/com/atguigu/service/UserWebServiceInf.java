package com.atguigu.service;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_ORDER;
import com.atguigu.bean.T_MALL_USER;

@WebService
public interface UserWebServiceInf {
   T_MALL_USER select_user(T_MALL_USER user);
   
   void insert_user(T_MALL_USER user);
   
   T_MALL_ORDER select_yh_dzh(int yh_id);
}
