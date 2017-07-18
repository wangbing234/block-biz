package com.qk.core.ibatis.sql.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 组合条件
 *
 */
public class WherePrams implements Criteria {

	/**
	 * 组合名
	 */
	protected String name;
	/**
	 * 条件数组
	 */
	protected List<Criteria> criteriaList;
	/**
	 * 
	 * @param field
	 * @param value
	 * @param oper
	 */
	protected WherePrams(String field, Object value, Restrictions oper) {
		this.criteriaList = new ArrayList<>();
		this.criteriaList.add(new SingleCriteria(field, value, oper));
	}

	/**
	 * 追加条件
	 * 
	 * @param field
	 * @param value
	 * @param oper
	 * @return
	 */
	public WherePrams add(String field, Object value, Restrictions oper) {
		if(!StringUtils.isEmpty(field))
		this.criteriaList.add(new SingleCriteria(field, value, oper));
		return this;
	}

	public WherePrams add(String field, Object value) {
		return this.add(field, value, Restrictions.EQ);
	}

	public WherePrams add(Criteria criteria) {
		this.criteriaList.add(criteria);
		return this;
	}


	public String getName() {
		return name;
	}

	public List<Criteria> getCriteriaList() {
		return criteriaList;
	}

}
