package com.qk.core.ibatis.sql.criteria;

import java.util.Date;
import java.util.List;

import com.qk.core.ibatis.util.date.DateUtil;

/**
 * 比较符枚举
 *
 */
public enum Restrictions {
	// null
	IS_NULL("# is null"), IS_NOT_NULL("# is not null"),

	// 空串
	IS_EMPTY("# = ''"), IS_NOT_EMPTY("# <> ''"),

	// 精确比较
	EQ("= ?"), GT("> ?"), LT("< ?"), GE(">= ?"), LE("<= ?"), NE("<> ?"),

	// 日期用比较符（主要用于前端页面的条件查询）
	GE_DATE(">= ?"), LE_DATE("< ?"),

	// 日期时间用比较符（主要用于后台时间比较）
	GE_DATETIME(">= ?"), LE_DATETIME("< ?"),

	// 模糊比较
	LIKE("like %?%"), LIKE_START("like ?%"), LIKE_END("like %?"), NOT_LIKE("not like %?%"), NOT_LIKE_START("like ?%"), NOT_LIKE_END("like %?"),

	// in
	IN("in ?"), NOT_IN("not in ?");

	/**
	 * 比较符对应SQL
	 */
	private String operSql;

	/**
	 * 构造函数
	 * 
	 * @param operSql
	 *            比较符
	 */
	private Restrictions(String operSql) {
		this.operSql = operSql;
	}

	/*
	 * 返回比较符SQL
	 */
	public String getSql() {
		return this.operSql;
	}

	/**
	 * 返回预处理后的比较值
	 * 
	 * @param value
	 * @return
	 */
	public Object getArg(Object value) {
		switch (this) {
		case IS_NULL:
		case IS_NOT_NULL:
		case IS_EMPTY:
		case IS_NOT_EMPTY:
			// 无需参数
			return null;

		case EQ:
		case GT:
		case LT:
		case GE:
		case LE:
		case NE:

		case GE_DATETIME:
		case LE_DATETIME:

		case LIKE:
		case NOT_LIKE:
		case LIKE_START:
		case NOT_LIKE_START:
		case LIKE_END:
		case NOT_LIKE_END:
			// 直接比较
			return value;

		case GE_DATE:
			if (value instanceof Date) {
				return value;
			} else {
				return DateUtil.parseDate(String.valueOf(value));
			}
		case LE_DATE:
			// 数据库对应字段为datetime类型时，结束日期自动+1
			if (value instanceof Date) {
				return DateUtil.offsetDate((Date) value, 1);
			} else {
				return DateUtil.offsetDate(DateUtil.parseDate(String.valueOf(value)), 1);
			}

		case IN:
		case NOT_IN:
			// 统一值类型为Object[]
			if (value instanceof Object[]) { // 数组
				return (Object[]) value;
			} else if (value instanceof List) { // 列表
				return ((List<?>) value).toArray();
			} else { // 字符串
				return (Object[]) String.valueOf(value).split("[,\\|]"); // 可扩展分隔符
			}

		default:
			return value;
		}
	}

}
