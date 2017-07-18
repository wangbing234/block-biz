package com.qk.core.ibatis.sql.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

	/**
	 * 升序
	 */
	public static final boolean ASC = true;
	/**
	 * 降序
	 */
	public static final boolean DESC = false;

	/**
	 * 排序内容
	 */
	List<OrderBean> list;

	/**
	 * 构造方法
	 * 
	 * @param field
	 *            字段
	 * @param dir
	 *            排序方向，默认升序
	 */
	public Order(String field, boolean dir) {
		this();
		this.add(field, dir);
	}

	public Order(String field) {
		this();
		this.add(field);
	}

	public Order() {
		list = new ArrayList<>();
	}

	/**
	 * 添加排序项
	 * 
	 * @param field
	 *            字段
	 * @param dir
	 *            方向，默认升序
	 * @return
	 */
	public Order add(String field, boolean dir) {
		list.add(new OrderBean(field, dir));
		return this;
	}

	public Order add(String field) {
		return this.add(field, ASC);
	}

	/**
	 * 转换为PreparedSql对象
	 * 
	 * @param colAlias
	 *            字段别名<br>
	 *            { 列名, 别名（拼接进SQL） }
	 * @return
	 */
	public String toSql(Map<String, String> colAlias) {
		StringBuilder sb = new StringBuilder();
		list.forEach(orderBean -> {
			sb.append(", ");
			if (null != colAlias && colAlias.containsKey(orderBean.getField())) {
				sb.append(colAlias.get(orderBean.getField()));
			} else {
				sb.append(orderBean.getField());
			}
			sb.append(orderBean.isAsc() ? " ASC" : " DESC");
		});

		return " order by" + sb.substring(1).toString();
	}

	public String toSql() {
		return toSql(null);
	}

	@Override
	public String toString() {
		return this.toSql();
	}

	public static void main(String[] args) {
		Order order = new Order("eee").add("bbb").add("aaa", Order.DESC);
		System.out.println(order);

		Map<String, String> colAlias = new HashMap<>();
		colAlias.put("eee", "c.E");
		colAlias.put("aaa", "a.AA");
		System.out.println(order.toSql(colAlias));
	}
}
