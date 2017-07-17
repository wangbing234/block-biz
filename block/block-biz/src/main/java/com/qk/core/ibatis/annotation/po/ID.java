package com.qk.core.ibatis.annotation.po;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义PO类的键类型
 * @author bing.wang
 * @time 2016年2月26日下午3:41:06
 * @email test.qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ID {

	/**
	 * 主键数据类型，默认整数型
	 * @return
	 */
	int type() default PUBVALUE.TABLE_ID_TYPE_INTEGER;
	
}
