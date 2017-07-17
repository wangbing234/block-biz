package com.qk.core.ibatis.util;

import org.springframework.util.StringUtils;

public class EmptyUtil {

	public static Boolean isEmpty(Object obj){
		return obj==null;
	}
	public static Boolean isEmpty(String obj) {
		return StringUtils.isEmpty(obj);
	}
	public static Boolean isEmpty(Integer obj) {
		return obj!=0;
	}
}
