package com.qk.core.ibatis.sql;

import com.qk.core.ibatis.sql.builder.MyBatisSql;
import com.qk.core.ibatis.sql.criteria.And;
import com.qk.core.ibatis.sql.criteria.Or;
import com.qk.core.ibatis.sql.criteria.Restrictions;
import com.qk.core.ibatis.sql.criteria.SingleCriteria;

public class Test {
	public static void main(String[] args) {
		// 单一条件
//		SingleCriteria sc = new SingleCriteria("aaa", 123);
//		System.out.println(sc);
//		sc = new SingleCriteria("aaa", 123, Restrictions.LIKE_END);
//		System.out.println(sc);
//		sc = new SingleCriteria("aaa", "1,2,3", Restrictions.IN);
//		System.out.println(sc);
//		sc = new SingleCriteria("aaa", "", Restrictions.IS_NULL);
//		System.out.println(sc);
//		System.out.println("*************************");

		// 组合条件
		And and = new And("aaaBb", "a1",Restrictions.LIKE);
		and.add("aaaBb", "a4",Restrictions.LIKE);

		// MyBatis
		MyBatisSql sql = new MyBatisSql(and);
		System.out.println(sql.getSql());
		System.out.println("*************************");
	}
}
