package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;

public class MyConfigUtil {

	public static void main(String[] args) {
		String properties = getProperties("linuxPath");
		System.out.println(properties);
	}

	/***
	 * 判断是否是新的购物车2
	 * 
	 * @param car
	 * @param if_new_car_map
	 * @return
	 */
	public static Map<Object, Object> if_new_car_map(T_MALL_SHOPPINGCAR car, List<T_MALL_SHOPPINGCAR> list_car) {
		// TODO Auto-generated method stub
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		hashMap.put("b", true);

		for (int i = 0; i < list_car.size(); i++) {

			if (list_car.get(i).getSku_id() == car.getSku_id()) {
				hashMap.put("b", false);
				hashMap.put("car", list_car.get(i));
			}

		}
		return hashMap;
	}

	/***
	 * 判断是否是新的购物车
	 * 
	 * @param car
	 * @param list_car
	 * @return
	 */
	public static boolean if_new_car(T_MALL_SHOPPINGCAR car, List<T_MALL_SHOPPINGCAR> list_car) {
		// TODO Auto-generated method stub
		boolean b = true;

		for (int i = 0; i < list_car.size(); i++) {

			if (list_car.get(i).getSku_id() == car.getSku_id()) {
				b = false;
			}

		}

		return b;
	}

	public static String getProperties(String key) {

		Properties properties = new Properties();

		InputStream resourceAsStream = MyConfigUtil.class.getClassLoader()
				.getResourceAsStream("myConfigUtil.properties");

		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String property = properties.getProperty(key);
		return property;
	}

}
