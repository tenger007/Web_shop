package com.atguigu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.util.MyDateUtil;

@Service
public class OrderServiceImp implements OrderServiceInf {

	@Autowired
	OrderMapper orderMapper;

	public void add_order(List<OBJECT_T_MALL_ORDER> list_object_order, T_MALL_ADDRESS address) {

		// 循环订单对象
		for (int i = 0; i < list_object_order.size(); i++) {
			// 逐个插入订单表
			/**
			 * 收货人：进度号：地址id： 地址名称：
			 **/
			list_object_order.get(i).setShhr(address.getShjr());
			list_object_order.get(i).setJdh(1);
			list_object_order.get(i).setDzh_id(address.getId());
			list_object_order.get(i).setDzh_mch(address.getYh_dz());
			orderMapper.insert_order(list_object_order.get(i));

			// 批量插入订单信息表，获得订单对象的主键id
			/**
			 * 订单id：skuid： sku名称： 商品图片： sku价格： sku数量： sku库存地址： 购物车id：
			 * 
			 **/
			for (int j = 0; j < list_object_order.get(i).getList_order_info().size(); j++) {
				list_object_order.get(i).getList_order_info().get(j).setDd_id(list_object_order.get(i).getId());
			}
			orderMapper.insert_order_infos(list_object_order.get(i).getList_order_info());

			// 批量删除购物车表(逻辑或者物理删除)
			orderMapper.delete_shoppingCars(list_object_order.get(i).getList_order_info());

			// 生成物流信息
			/**
			 * psfsh psshj psmsh yh_id dd_id mqdd mdd ywy lxfsh
			 **/
			T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
			// t_MALL_FLOW.setPsfsh("硅谷快递");
			t_MALL_FLOW.setPsshj(MyDateUtil.get_yjsdsj(1));
			t_MALL_FLOW.setPsmsh("等待审核");
			t_MALL_FLOW.setYh_id(list_object_order.get(i).getYh_id());
			t_MALL_FLOW.setDd_id(list_object_order.get(i).getId());
			t_MALL_FLOW.setMqdd("等待出库");
			t_MALL_FLOW.setMdd(list_object_order.get(i).getDzh_mch());
			// t_MALL_FLOW.setYwy("老佟");
			// t_MALL_FLOW.setLxfsh("123123123123");
			orderMapper.insert_flow(t_MALL_FLOW);
		}

	}

	@Override
	public int pay_order(List<OBJECT_T_MALL_ORDER> list_object_order) {
		// 检查库存信息是否符合购买条件，如果符合则加入行锁，如果不符合则返回通知给客户，并且调用退款的支付接口
		boolean b = check_sku_kc(list_object_order);

		if (b) {
			// 检查库存的结果是可购买的
			for (int i = 0; i < list_object_order.size(); i++) {
				HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
				// 修改库存数量
				for (int j = 0; j < list_object_order.get(i).getList_order_info().size(); j++) {
					hashMap.put("sku_shl", list_object_order.get(i).getList_order_info().get(j).getSku_shl());
					hashMap.put("sku_id", list_object_order.get(i).getList_order_info().get(j).getSku_id());
					orderMapper.update_sku_kc(hashMap);
				}

				// 修改订单状态
				hashMap.put("dd_id", list_object_order.get(i).getId());
				hashMap.put("jdh", 2);
				orderMapper.update_order_jdh(hashMap);

				// 修改物流状态
				T_MALL_FLOW t_MALL_FLOW = new T_MALL_FLOW();
				t_MALL_FLOW.setPsfsh("硅谷快递");
				t_MALL_FLOW.setYwy("老佟");
				t_MALL_FLOW.setLxfsh("123123123123");
				t_MALL_FLOW.setPsmsh("商品正在出库");
				t_MALL_FLOW.setMqdd("商品正在出库");
				orderMapper.update_flow(t_MALL_FLOW);

			}

		} else {
			// 检查库存的结果是不可购买的

			// 返回交易失败的通知给客户端
		}

		return 0;
	}

	/***
	 * 判断库存数量
	 * 
	 * @param list_object_order
	 * @return
	 */
	private boolean check_sku_kc(List<OBJECT_T_MALL_ORDER> list_object_order) {

		boolean b = true;

		List<T_MALL_ORDER_INFO> list_order_info = new ArrayList<T_MALL_ORDER_INFO>();

		for (int i = 0; i < list_object_order.size(); i++) {

			list_order_info.addAll(list_object_order.get(i).getList_order_info());

		}

		for (int i = 0; i < list_order_info.size(); i++) {

			int sku_shl = orderMapper.select_sku_for_update(list_order_info.get(i));

			if (sku_shl < list_order_info.get(i).getSku_shl()) {
				b = false;
			}
		}

		return b;
	}

}
