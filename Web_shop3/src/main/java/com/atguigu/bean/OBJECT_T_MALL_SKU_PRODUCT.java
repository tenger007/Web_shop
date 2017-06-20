package com.atguigu.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OBJECT_T_MALL_SKU_PRODUCT {
    private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int sku_id;
	private String shfxz;
	public String getShfxz() {
		return shfxz;
	}
	public void setShfxz(String shfxz) {
		this.shfxz = shfxz;
	}
	private Double sku_jg;
	public Double getSku_jg() {
		return sku_jg;
	}
	public void setSku_jg(Double sku_jg) {
		this.sku_jg = sku_jg;
	}
	private String sku_mch;
	private List<OBJECT_T_MALL_ATTR_VALUE_NAME> list_attr_value_name;
	private String shp_tp;
	private int tjshl;
	private Double hj;
	private int yh_id;
	
	public int getSku_id() {
		return sku_id;
	}
	public void setSku_id(int sku_id) {
		this.sku_id = sku_id;
	}
	public int getYh_id() {
		return yh_id;
	}
	public void setYh_id(int yh_id) {
		this.yh_id = yh_id;
	}
	public Double getHj() {
		return hj;
	}
	public void setHj(Double hj) {
		this.hj = hj;
	}
	public String getSku_mch() {
		return sku_mch;
	}
	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}
	public List<OBJECT_T_MALL_ATTR_VALUE_NAME> getList_attr_value_name() {
		return list_attr_value_name;
	}
	public void setList_attr_value_name(List<OBJECT_T_MALL_ATTR_VALUE_NAME> list_attr_value_name) {
		this.list_attr_value_name = list_attr_value_name;
	}
	public String getShp_tp() {
		return shp_tp;
	}
	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}
	public int getTjshl() {
		return tjshl;
	}
	public void setTjshl(int tjshl) {
		this.tjshl = tjshl;
	}
	
	
}
