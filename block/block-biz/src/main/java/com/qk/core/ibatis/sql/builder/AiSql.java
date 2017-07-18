package com.qk.core.ibatis.sql.builder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AiSql {

	/**
	 * 格式化sql
	 */
	protected String sql;
	/**
	 * 参数map
	 */
	protected Map<String, Object> argMap;

	protected AiSql() {
		argMap = new LinkedHashMap<>();
	}

	public AiSql(String sql, Map<String, Object> argMap) {
		this.sql = sql;
		this.argMap = argMap;
	}

	public void join(AiSql newSql) {
		this.sql += newSql.sql;
		this.argMap.putAll(newSql.argMap);
	}

	public AiSql joinNew(AiSql newSql) {
		Map<String, Object> argMap = new HashMap<>();
		argMap.putAll(this.argMap);
		argMap.putAll(newSql.argMap);
		return new AiSql(this.sql + newSql.sql, argMap);
	}

	public String getSql() {
		return StringUtils.isEmpty(sql)?"":" WHERE "+sql;
	}

	public Map<String, Object> getArgMap() {
		return argMap;
	}

	@Override
	public String toString() {
		return sql;
	}
}
