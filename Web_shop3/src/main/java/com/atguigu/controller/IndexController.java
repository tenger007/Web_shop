package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_USER;
import com.google.gson.Gson;

@Controller
public class IndexController {
	@RequestMapping("indexbackground/{success}")
	public String indexbackground() {
		return "background_manager/manager_index";
	}
    
	@RequestMapping("indexreception/")
	public String indexreception(HttpServletRequest request, HttpSession session){
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			String sale_cookie_user = "";
			for (int i = 0; i < cookies.length; i++) {
				if ((cookies[i].getName()).equals("sale_cookie_user")) {
					sale_cookie_user = cookies[i].getValue();
				}
			}

			try {
				sale_cookie_user = URLDecoder.decode(sale_cookie_user, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (sale_cookie_user != null && !sale_cookie_user.equals("")) {
				Gson gson = new Gson();
				T_MALL_USER fromJson = gson.fromJson(sale_cookie_user, T_MALL_USER.class);
				session.setAttribute("user", fromJson);
			}
		}

		return "reception_manager/sale_index";
	}

		
	}
	 

