package com.qk.core.ibatis.sql.criteria;

import com.qk.core.ibatis.util.NameCaseUtil;

/**
 * 单一条件
 *
 */
public class SingleCriteria implements Criteria {
	/**
	 * 比较字段
	 */
	private String field;
	/**
	 * 比较值
	 */
	private Object value;
	/**
	 * 比较符
	 */
	private Restrictions oper;

	/**
	 * 构造函数
	 * 
	 * @param field
	 * @param value
	 * @param oper
	 */
	public SingleCriteria(String field, Object value, Restrictions oper) {
		this.field = NameCaseUtil.toCamelCase(field);
		this.value = value;
		this.oper = oper;
	}

	public SingleCriteria(String field, Object value) {
		this(field, value, Restrictions.EQ);
	}


	public String getField() {
		return NameCaseUtil.toUnderlineLowerCase(field);
	}

	public Object getValue() {
		return value;
	}

	public Restrictions getOper() {
		return oper;
	}

	public String getSql() {
		return oper.getSql();
	}

	public Object getArg() {
		return oper.getArg(this.value);
	}

}
