package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.OBJECT_T_MALL_ATTR_VALUE_NAME;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.OBJECT_T_MALL_SKU_PRODUCT;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_SHOPPINGCARMINI;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.SearchServiceImp;
import com.atguigu.service.ShoppingCarServiceImp;
import com.atguigu.util.MyConfigUtil;
import com.atguigu.util.MyJsonUtil;

@Controller
public class ShoppingCarController {

	@Autowired
	ShoppingCarServiceImp shoppingCarServiceImp;

	 @Autowired
	 SearchServiceImp searchServiceImp;
	 
	 
	
	 @RequestMapping(value="change_car",method=RequestMethod.POST)
	 public String change_car(int car_id,int sku_id,String shfxz,int tjshl,
			 @CookieValue(value="cookie_list_car",required=false) String cookie_list_car,HttpSession session,
			 HttpServletResponse response,ModelMap map){
		 T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		 List<T_MALL_SHOPPINGCAR> list_car = null;
		 if(tjshl >0){
			 //改变数量
			 if(user!=null){
				 list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
				 //改变数据库和session
				 for (int i = 0; i < list_car.size(); i++) {
					if(list_car.get(i).getId() == car_id){
						list_car.get(i).setTjshl(tjshl);
						list_car.get(i).setHj(list_car.get(i).getTjshl()*list_car.get(i).getSku_jg());
						T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
						t_MALL_SHOPPINGCAR.setHj(list_car.get(i).getHj());
						t_MALL_SHOPPINGCAR.setTjshl(tjshl);
						shoppingCarServiceImp.update_car(t_MALL_SHOPPINGCAR);
					}
				}
			 }else{
				 //改变cookie
				 list_car = MyJsonUtil.json_to_list_car(cookie_list_car);
				 for (int i = 0; i < list_car.size(); i++) {
					 if(list_car.get(i).getSku_id()==sku_id){
						 list_car.get(i).setTjshl(tjshl);
						 list_car.get(i).setHj(list_car.get(i).getTjshl()*list_car.get(i).getSku_jg());
					 }
				}
				 cookie_list_car = MyJsonUtil.list_to_json(list_car);
				 Cookie cookie = new Cookie("cookie_list_car",cookie_list_car);
				 cookie.setMaxAge(60*60*24*7);
				 response.addCookie(cookie);
			 }
		 }else{
			 //改变选中
			 if(user!=null){
				 list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
				 //改变数据库和session
				 for (int i = 0; i < list_car.size(); i++) {
					if(list_car.get(i).getId()==car_id){
						list_car.get(i).setShfxz(shfxz);
						T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();
						t_MALL_SHOPPINGCAR.setShfxz(shfxz);
						t_MALL_SHOPPINGCAR.setId(list_car.get(i).getId());
						shoppingCarServiceImp.update_car(t_MALL_SHOPPINGCAR);
						
					}
				}
			 }else{
				 //改变cookie
				 list_car = MyJsonUtil.json_to_list_car(cookie_list_car);
				 for (int i = 0; i < list_car.size(); i++) {
					if(list_car.get(i).getSku_id()==sku_id){
						list_car.get(i).setShfxz(shfxz);
					}
				}
				 cookie_list_car = MyJsonUtil.list_to_json(list_car);
				 Cookie cookie = new Cookie("cookie_list_car",cookie_list_car);
				 cookie.setMaxAge(60*60*24*7);
				 response.addCookie(cookie);
			 }
		 }
		 double sum = 0 ;
		 int count = 0;
		 for (int i = 0; i < list_car.size(); i++) {
			if(list_car.get(i).getShfxz().equals("1")){
				sum = sum+list_car.get(i).getHj();
				count = count + list_car.get(i).getTjshl();
			}
		}
		 map.put("list_car",list_car);
		 map.put("sum",sum);
		 map.put("count",count);
		 return "reception_manager/sale_shoppingCar_inner";
		 
	 }
	/***
	 * 迷你购物车
	 * 
	 * @param session
	 * @param map
	 * @param cookie_list_car
	 * @return
	 */
	@RequestMapping(value = "mini_car", method = RequestMethod.POST)
	public String mini_car(HttpSession session, ModelMap map,
			@CookieValue(value = "cookie_list_car", required = false) String cookie_list_car) {

		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = null;
		if (user != null) {
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
		} else {
			if (cookie_list_car != null && !cookie_list_car.equals("")) {
				list_car = MyJsonUtil.json_to_list_car(cookie_list_car);
			}

		}
		map.put("list_car", list_car);
		return "reception_manager\\sale_mini_shoppingCar_inner";
	}

	/***
	 * 添加购物车成功
	 * 
	 * @param sku_mch
	 * @param sku_id
	 * @return
	 */
	@RequestMapping(value = "goto_shoppingCar_success/{sku_mch}/{sku_id}", method = RequestMethod.GET)
	public String goto_shoppingCar_success(@PathVariable String sku_mch, @PathVariable int sku_id) {

		return "reception_manager\\sale_shoppingCar_success";

	}

	/***
	 * 添加购物车
	 * 
	 * @param car
	 * @param session
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "add_car", method = RequestMethod.POST)
	public ModelAndView add_car(OBJECT_T_MALL_SKU_PRODUCT product_car, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
        T_MALL_SHOPPINGCAR car = new T_MALL_SHOPPINGCAR();
        car.setShp_tp(product_car.getShp_tp());
        car.setSku_id(product_car.getSku_id());
        car.setSku_jg(product_car.getSku_jg());
        car.setTjshl(product_car.getTjshl());
        car.setSku_mch(product_car.getSku_mch());
        car.setHj(product_car.getHj());
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> list_car = null;
        
		if (user != null) {
			// 对db进行操作

			// 从session中取出购物车集合
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");// null,[]

			if (list_car == null) {
				list_car = shoppingCarServiceImp.get_shoppingCar_by_user_id(user);// null,[]
			}

			// 判断db中是否有购物数据
			if (list_car == null || list_car.size() == 0) {
				// 直接插入db
				car.setYh_id(user.getId());
				
				shoppingCarServiceImp.add_car(car);

				// 同步session
				list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
				list_car.add(car);

				session.setAttribute("session_list_car", list_car);
			} else {
				car.setYh_id(user.getId());
				// 判断是否是新的购物数据
				boolean b = MyConfigUtil.if_new_car(car, list_car);
				if (b) {
					// 插入db
					shoppingCarServiceImp.add_car(car);
					list_car.add(car);
				} else {
					// 更新db
					for (int i = 0; i < list_car.size(); i++) {
						if (list_car.get(i).getSku_id() == car.getSku_id()) {
							list_car.get(i).setTjshl(list_car.get(i).getTjshl() + car.getTjshl());
							list_car.get(i).setHj(list_car.get(i).getTjshl() * list_car.get(i).getSku_jg());

							car.setHj(list_car.get(i).getHj());
							car.setTjshl(list_car.get(i).getTjshl());
							car.setId(list_car.get(i).getId());
						}
					}
					shoppingCarServiceImp.update_car(car);
				}
			}

		} else {
			Cookie[] cookies = request.getCookies();

			String cookie_list_car = "";
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("cookie_list_car")) {
						cookie_list_car = cookies[i].getValue();
					}
				}
			}

			if (!cookie_list_car.equals("")) {
				list_car = MyJsonUtil.json_to_list_car(cookie_list_car);// 将字符串转化为购物车集合
			}

			// 对cookie进行操作
			if (list_car == null) {
				list_car = new ArrayList<T_MALL_SHOPPINGCAR>();
				// 直接插入cookie
				list_car.add(car);

				// 将购物车集合转json字符串，放入浏览器cookie

			} else {

				// 判断是否是新的购物数据
				boolean b = MyConfigUtil.if_new_car(car, list_car);
				if (b) {
					// 插入cookie
					list_car.add(car);

					// 将购物车集合转json字符串，放入浏览器cookie

				} else {
					// 更新cookie
					// 更新添加数量 和合计
					for (int i = 0; i < list_car.size(); i++) {
						if (list_car.get(i).getSku_id() == car.getSku_id()) {
							list_car.get(i).setTjshl(list_car.get(i).getTjshl() + car.getTjshl());
							list_car.get(i).setHj(list_car.get(i).getTjshl() * list_car.get(i).getSku_jg());
						}
					}
				}
			}

			// 更新客户端cookie
			// 将购物车集合转json字符串，放入浏览器cookie
			cookie_list_car = MyJsonUtil.list_to_json(list_car);

			Cookie cookie = new Cookie("cookie_list_car", cookie_list_car);
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/goto_shoppingCar_success/{sku_mch}/{sku_id}.do");
		modelAndView.addObject("sku_mch", car.getSku_mch());
		modelAndView.addObject("sku_id", car.getSku_id());

		return modelAndView;
	}

	@RequestMapping
	public String goto_shoppingcar(HttpSession session, ModelMap map,
			@CookieValue(value = "cookie_list_car", required = false) String cookie_list_car) {

		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_car = null;
		if (user != null) {
			list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");
			
		} else {
			if (cookie_list_car != null && !cookie_list_car.equals("")) {
				list_car = MyJsonUtil.json_to_list_car(cookie_list_car);
			}

		}
		map.put("list_car", list_car);
		return "reception_manager/sale_shoppingCar";
	}
}
