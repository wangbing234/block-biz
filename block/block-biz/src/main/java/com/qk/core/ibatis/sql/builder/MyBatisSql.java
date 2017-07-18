package com.qk.core.ibatis.sql.builder;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import com.qk.core.ibatis.sql.criteria.WherePrams;
import com.qk.core.ibatis.sql.criteria.Criteria;
import com.qk.core.ibatis.sql.criteria.Restrictions;
import com.qk.core.ibatis.sql.criteria.SingleCriteria;

/**
 * 
 * 对应MyBatis的SQL对象，包含格式化SQL及相关参数。
 * <ul>
 * <li>CUD：update table set col = #{col}, col2 = col2 + #{col2}
 * <li>R：col = #{col} and (col2 = #{col2} or col2 like %#{col2_2}%) and col3 in (#{col3_0}, #{col3_1})
 * </ul>
 *
 */
public class MyBatisSql extends AiSql {

	public MyBatisSql(String sql, Map<String, Object> argMap) {
		super(sql, argMap);

	}

	public MyBatisSql(SingleCriteria sc) {
		this(sc, 0);
	}

	private MyBatisSql(SingleCriteria sc, int argLen) {
		if (sc.getOper() == Restrictions.IN || sc.getOper() == Restrictions.NOT_IN ) {
			Object[] values = (Object[]) sc.getArg();
			Map<String, Object> argMap = new LinkedHashMap<>();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < values.length; i++) {
				sb.append(", #{").append(argLen + i).append("}");
				argMap.put(String.valueOf(argLen + i), values[i]);
			}

			StringBuilder sb2 = new StringBuilder();
			sb2.append(sc.getField()).append(" ");
			sb2.append(sc.getSql().replaceAll("\\?", ""));
			sb2.append("(").append(sb.substring(2)).append(")");

			this.sql = sb2.toString();
			this.argMap = argMap;
			return;
		} 
		
		else if (sc.getSql().indexOf("#") != -1) {
			this.sql = sc.getSql().replaceAll("#", sc.getField());
			this.sql = this.sql.replaceAll("\\?", "#{" + argLen + "}");
		} else {
			this.sql = sc.getField() + " " + sc.getSql().replaceAll("\\?", "#{" + argLen + "}").replaceAll("\\%", "\"\\%\"");
		}

		Optional<Object> arg = Optional.ofNullable(sc.getArg());
		if (arg.isPresent()) {
			this.argMap.put(String.valueOf(argLen), sc.getArg());
		}
	}

	public MyBatisSql(WherePrams cc) {
		this(cc, 0);
	}

	private MyBatisSql(WherePrams cc, int argLen) {
		MyBatisSql subSql; // 子条件sql
		Criteria criteria; // 循环对象
		if (cc != null) {

			int len = cc.getCriteriaList().size(); // 子条件数组长度
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < len; i++) {
				criteria = cc.getCriteriaList().get(i);
				if (criteria instanceof SingleCriteria) {
					subSql = new MyBatisSql((SingleCriteria) criteria, argLen);
					argLen += subSql.argMap.size();
				} else {
					// 递归触发
					subSql = new MyBatisSql((WherePrams) criteria, argLen);
				}

				// 第二个元素开始加组合名
				if (i > 0) {
					sb.append(cc.getName());
				}

				// 组合sql
				sb.append(subSql.sql);

				// 追加参数
				this.argMap.putAll(subSql.argMap);
			}

			this.sql = "(" + sb.toString() + ")";
		}
	}

	@Override
	public String toString() {
		System.out.println("sql =" + sql);
		System.out.println("sql2=" + MessageFormat.format(sql, argMap.values().toArray()));
		return "";
	}

}
