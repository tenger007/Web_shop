package com.atguigu.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MyJsonUtil {

	public static void main(String[] args) {

		String json = "{'id':0,'sku_mch':'购物车0','sku_jg':0.0,'tjshl':0,'hj':0.0,'yh_id':0,'shp_id':0,'sku_id':0}";

		// 1
		JSONObject fromObject = JSONObject.fromObject(json);

		T_MALL_SHOPPINGCAR a = (T_MALL_SHOPPINGCAR) fromObject.toBean(fromObject, T_MALL_SHOPPINGCAR.class);

		System.out.println();

		// 2
		JSONObject fromObject2 = JSONObject.fromObject(a);

		String string = fromObject2.toString();

		System.out.println(string);

		// 3
		List<T_MALL_SHOPPINGCAR> list_car = new ArrayList<T_MALL_SHOPPINGCAR>();

		for (int i = 0; i < 3; i++) {

			T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR = new T_MALL_SHOPPINGCAR();

			t_MALL_SHOPPINGCAR.setSku_mch("购物车" + i);
			t_MALL_SHOPPINGCAR.setSku_jg(1000 * i);

			list_car.add(t_MALL_SHOPPINGCAR);

		}
		JSONArray fromObject3 = JSONArray.fromObject(list_car);
		String string2 = fromObject3.toString();
		System.out.println(string2);
		json_to_list(string2, T_MALL_SHOPPINGCAR.class);

		json_to_list_car(string2);

		// 4
		JSONArray fromObject4 = JSONArray.fromObject(string2);

		List<T_MALL_SHOPPINGCAR> collection = (List<T_MALL_SHOPPINGCAR>) JSONArray.toCollection(fromObject4,
				T_MALL_SHOPPINGCAR.class);

		System.out.println(collection);

	}

	/**
	 * 字符串转集合
	 * 
	 * @param json
	 */
	public static <T> List<T> json_to_list(String json, Class<T> t) {

		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray fromObject4 = JSONArray.fromObject(json);

		List<T> collection = (List<T>) JSONArray.toCollection(fromObject4, t);

		return collection;

	}

	/**
	 * 字符串转购物车集合
	 * 
	 * @param json
	 */
	public static List<T_MALL_SHOPPINGCAR> json_to_list_car(String json) {

		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Gson gson = new Gson();

		// 用TypeToken做一个类型定义的工具
		TypeToken<List<T_MALL_SHOPPINGCAR>> typeToken = new TypeToken<List<T_MALL_SHOPPINGCAR>>(){};

		List<T_MALL_SHOPPINGCAR> fromJson = (List<T_MALL_SHOPPINGCAR>) gson.fromJson(json, typeToken.getType());

		return fromJson;

	}

	/**
	 * 集合转字符串
	 * 
	 * @param list_car
	 * @return
	 */
	public static <T> String list_to_json(List<T> list_car) {
		// 将list_car换为json字符串
		Gson g = new Gson();
		String json = g.toJson(list_car);
		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

}
