package com.atguigu.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER;
import com.atguigu.service.OrderServiceInf;
import com.atguigu.service.ShoppingCarServiceInf;
import com.atguigu.util.MyDateUtil;
import com.atguigu.service.AddressServiceInf;

//@Controller
@SessionAttributes("list_object_order")
public class OrderController {

	@Autowired
	AddressServiceInf addressServiceInf;

	@Autowired
	OrderServiceInf orderServiceImp;

	@Autowired
	ShoppingCarServiceInf shoppingCarServiceImp;

	@RequestMapping(value = "pay_order", method = RequestMethod.POST)
	public String pay_order(HttpSession session,
			@ModelAttribute("list_object_order") List<OBJECT_T_MALL_ORDER> list_object_order) {

		orderServiceImp.pay_order(list_object_order);

		// 清理session中的订单对象
		session.removeAttribute("list_object_order");
		return "redirect:/sale_order_pay_success.do";

	}

	@RequestMapping(value = "sale_order_pay_success", method = RequestMethod.GET)
	public String sale_order_pay_success() {

		return "reception_manager/sale_order_pay_success";

	}

	@RequestMapping(value = "goto_pay", method = RequestMethod.GET)
	public String goto_pay() {

		return "reception_manager/sale_order_pay";

	}

	/***
	 * 提交订单
	 * 
	 * @param address_id
	 * @param list_object_order
	 * @return
	 */
	@RequestMapping(value = "add_order", method = RequestMethod.POST)
	public String add_order(HttpSession session, int address_id,
			@ModelAttribute("list_object_order") List<OBJECT_T_MALL_ORDER> list_object_order) {
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");

		// 根据地址id查询地址对象
		T_MALL_ADDRESS address = addressServiceInf.get_addresses_by_id(address_id);

		// 订单提交业务的调用
		orderServiceImp.add_order(list_object_order, address);

		// 根据用户id重新查询购物车表，再把查询结果重新放入session中
		// 查询购物车集合
		List<T_MALL_SHOPPINGCAR> get_shoppingCar_by_user_id = shoppingCarServiceImp.get_shoppingCar_by_user_id(user);
		session.setAttribute("session_list_car", get_shoppingCar_by_user_id);
		return "redirect:/goto_pay.do";

	}

	/***
	 * 购物车结算(拆单)
	 * 
	 * @param session
	 * @param map
	 * @return
	 */
	@RequestMapping("check_order")
	public String check_order(HttpSession session, ModelMap map) {
		T_MALL_USER user = (T_MALL_USER) session.getAttribute("user");

		if (user != null) {
			List<T_MALL_SHOPPINGCAR> list_car = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("session_list_car");

			// 拆单
			// 第一步，查询有多少种库存地址
			HashSet<String> hashSet = new HashSet<String>();

			for (int i = 0; i < list_car.size(); i++) {
				hashSet.add(list_car.get(i).getKcdz());
			}

			// 订单集合对象
			List<OBJECT_T_MALL_ORDER> list_object_order = new ArrayList<OBJECT_T_MALL_ORDER>();
			Iterator<String> iterator = hashSet.iterator();

			while (iterator.hasNext()) {
				String next = iterator.next();
				// 订单对象
				OBJECT_T_MALL_ORDER object_order = new OBJECT_T_MALL_ORDER();
				object_order.setYh_id(user.getId());
				object_order.setYjsdshj(MyDateUtil.get_yjsdsj(3));
				double sum = 0;

				// 订单信息集合对象
				List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<T_MALL_ORDER_INFO>();

				for (int j = 0; j < list_car.size(); j++) {
					if (list_car.get(j).getKcdz().equals(next)) {
						// 订单信息对象
						T_MALL_ORDER_INFO order_info = new T_MALL_ORDER_INFO();

						order_info.setGwch_id(list_car.get(j).getId());
						order_info.setShp_tp(list_car.get(j).getShp_tp());
						order_info.setSku_id(list_car.get(j).getSku_id());
						order_info.setSku_jg(list_car.get(j).getSku_jg());
						order_info.setSku_kcdz(list_car.get(j).getKcdz());
						order_info.setSku_mch(list_car.get(j).getSku_mch());
						order_info.setSku_shl(list_car.get(j).getTjshl());

						// 对订单信息进行封装
						list_order_info.add(order_info);
						sum += list_car.get(j).getHj();

					}
				}
				object_order.setZje(sum);
				object_order.setList_order_info(list_order_info);
				list_object_order.add(object_order);// 把订单对象放入集合
			}

			List<T_MALL_ADDRESS> list_address = addressServiceInf.get_addresses_by_user_id(user);

			map.put("list_address", list_address);
			map.put("list_object_order", list_object_order);
		} else {
			return "redirect:/goto_login_buy.do";
		}

		return "reception_manager/sale_order";

	}

}
