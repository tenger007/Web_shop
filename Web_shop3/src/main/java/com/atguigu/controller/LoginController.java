package com.atguigu.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_ORDER;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_SHOPPINGCARMINI;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.LoginServiceImp;
import com.atguigu.service.LoginServiceInf;
import com.atguigu.service.SearchServiceImp;
import com.atguigu.service.ShoppingCarServiceImp;
import com.atguigu.service.UserWebServiceInf;
import com.atguigu.util.MyConfigUtil;
import com.atguigu.util.MyJsonUtil;
import com.google.gson.Gson;

@Controller
public class LoginController {

	@Autowired
	LoginServiceImp loginServiceImp;

	@Autowired
	ShoppingCarServiceImp shoppingCarServiceImp;

	@Autowired
	UserWebServiceInf userWebServiceInf;
	
	@Autowired
	SearchServiceImp searchServiceImp;
	
	@RequestMapping("goto_regist")
	public String goto_regist() {
		return "reception_manager/sale_regist";
	}
	
	@RequestMapping("goto_login")
	public String goto_login() {
		return "reception_manager/sale_login";
	}

	@RequestMapping("goto_logout")
	public String goto_logout(HttpSession session, HttpServletResponse response) {
		response.addCookie(new Cookie("sale_cookie_user", ""));
		session.invalidate();
		return "redirect:/indexreception/.do";
	}
	
	@RequestMapping(value = "goto_login_buy", method = RequestMethod.GET)
	public String goto_login_buy() {

		return "reception_manager/sale_login_buy";

	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public String regist(T_MALL_USER user,
			HttpSession session,ModelMap map){
		if(user != null){
		userWebServiceInf.insert_user(user);
		session.setAttribute("user",user);
		return "redirect:indexreception/.do";
		}else{
			map.put("err", "用户名或密码错误");
			return "reception_manager/sale_login";
		}
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(HttpServletResponse response, String auto_login, T_MALL_USER user, HttpSession session,
			@CookieValue(value = "cookie_list_car", required = false) String cookie_list_car, ModelMap map) {

		T_MALL_USER user_login = userWebServiceInf.select_user(user);// 需要调用ws认证接口
		if (user_login != null) {
			if (auto_login != null) {
				auto_login(user_login, response);
			}
			session.setAttribute("user", user_login);
         T_MALL_SHOPPINGCARMINI shoppingCarTotal = shoppingCarServiceImp.getShoppingCarTotal(user_login.getId());
          session.setAttribute("shoppingcar",shoppingCarTotal);
			// 同步cookie和db中的购物车数据
			cookie_car_to_db_session(user_login, cookie_list_car, response, session);

			return "redirect:indexreception/.do";
		} else {
			map.put("err", "用户名或密码错误");
			return "reception_manager/sale_login";
		}

	}

	private void cookie_car_to_db_session(T_MALL_USER user_login, String cookie_list_car, HttpServletResponse response,
			HttpSession session) {

		List<T_MALL_SHOPPINGCAR> list_car = shoppingCarServiceImp.get_shoppingCar_by_user_id(user_login);
		if (cookie_list_car == null || cookie_list_car.equals("")) {
			// cookie中没有购物车数据
		} else {
			// cookie中有购物车数据
			List<T_MALL_SHOPPINGCAR> json_to_list_car = MyJsonUtil.json_to_list_car(cookie_list_car);

			for (int i = 0; i < json_to_list_car.size(); i++) {
				json_to_list_car.get(i).setYh_id(user_login.getId());
				if (list_car != null && list_car.size() != 0) {
					// db中有购物数据，需要根据判断，执行插入或者更新操作
					Map<Object, Object> if_new_car_map = MyConfigUtil.if_new_car_map(json_to_list_car.get(i), list_car);
					boolean b = (boolean) if_new_car_map.get("b");
					T_MALL_SHOPPINGCAR car = (T_MALL_SHOPPINGCAR) if_new_car_map.get("car");
					if (b) {
						shoppingCarServiceImp.add_car(json_to_list_car.get(i));
					} else {
						// 遍历db中的购物车集合，找到需要更新的那一条
						json_to_list_car.get(i).setId(car.getId());
						json_to_list_car.get(i).setTjshl(json_to_list_car.get(i).getTjshl() + car.getTjshl());
						json_to_list_car.get(i)
								.setHj(json_to_list_car.get(i).getTjshl() * json_to_list_car.get(i).getSku_jg());
						shoppingCarServiceImp.update_car(json_to_list_car.get(i));
					}
				} else {
					// db中没有数据，直接插入cookie中的购物车数据
					shoppingCarServiceImp.add_car(json_to_list_car.get(i));
				}
			}

		}
		// 同步sessin,根据用户id查询数据库中的购物车数据，装入sessioin
		list_car = shoppingCarServiceImp.get_shoppingCar_by_user_id(user_login);
		session.setAttribute("session_list_car", list_car);
		for (int i = 0; i < list_car.size(); i++) {
	           OBJECT_T_MALL_SKU object_T_MALL_SKU = searchServiceImp.get_object_sku_by_sku_id(list_car.get(i).getSku_id());
	           session.setAttribute("object_sku"+list_car.get(i).getId(),object_T_MALL_SKU);
			}
		session.setAttribute("index",list_car.size());
		// 清空cookie
		response.addCookie(new Cookie("cookie_list_car", ""));

	}

	/**
	 * 用户自动登录
	 * 
	 * @param user_login
	 * @param response
	 */
	public void auto_login(T_MALL_USER user_login, HttpServletResponse response) {
		Gson gson = new Gson();
		String json = gson.toJson(user_login);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Cookie cookie = new Cookie("sale_cookie_user", json);
		cookie.setMaxAge(60 * 60 * 24 * 7);
		response.addCookie(cookie);
	}
}
