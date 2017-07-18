package com.qk.core.ibatis.sql.criteria;

public class And extends WherePrams {
 
	public And(String field, Object value, Restrictions oper) {
		super(field, value, oper);
		this.name = " AND ";
	}

	public And(String field, Object value) {
		this(field, value, Restrictions.EQ);
	}
}
