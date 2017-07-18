package com.qk.core.ibatis.sql.criteria;

public class Or extends WherePrams {

	public Or(String field, Object value, Restrictions oper) {
		super(field, value, oper);
		this.name = " OR ";
	}

	public Or(String field, Object value) {
		this(field, value, Restrictions.EQ);
	}

}
