package com.atguigu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDateUtil {
    public static void main(String[] args) {
    	Date get_yjsdsj = get_yjsdsj(3);
    	System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(get_yjsdsj));
    	System.out.println(Calendar.DAY_OF_MONTH);
    	Calendar instance = Calendar.getInstance();
    	System.out.println(instance.get(Calendar.DAY_OF_MONTH));
	}

	public static Date get_yjsdsj(int i) {
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH,i);
		return instance.getTime();
	}
}
