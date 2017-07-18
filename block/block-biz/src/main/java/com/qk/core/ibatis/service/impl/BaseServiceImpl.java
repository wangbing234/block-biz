package com.qk.core.ibatis.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qk.core.ibatis.beans.PagerModel;
import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.dao.BaseDao;
import com.qk.core.ibatis.service.BaseService;
import com.qk.core.ibatis.sql.criteria.WherePrams;
import com.qk.core.ibatis.sql.order.Order;

@Repository
public class BaseServiceImpl<T extends Po> implements BaseService<T> {

	@Resource
	private BaseDao<T> baseDao; 
	
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int add(T po) {
		// TODO Auto-generated method stub
		return baseDao.add(po);
	}

	@Override
	public Serializable getField(Serializable id, String fileName) {
		// TODO Auto-generated method stub
		return baseDao.getField(id, fileName);
	}

	@Override
	public int update(T po, Boolean hasNull) {
		return baseDao.update(po,hasNull);
	}

	@Override
	public int del(Serializable id) {
		return baseDao.del(id);
	}
	
	@Override
	public T get(Serializable id) {
		return baseDao.get(id);
	}


	@Override
	public List<T> list(WherePrams where,Order order) {
		// TODO Auto-generated method stub
		return baseDao.list(where,order);
	}

	@Override
	public int update(T po) {
		return baseDao.update(po,false);
	}

	@Override
	public T get(Class<T> t, Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(t, id);
	}
	
	/**--以下接口没有实现--**/
	
	@Override
	public int update(T po, WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.update(po, where);
	}
	
	@Override
	public int del(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.del(where);
	}

	
	@Override
	public T get(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.get(where);
	}

	@Override
	public PagerModel<T> page(WherePrams where, Order order, int offset, int pageSize) {
		// TODO Auto-generated method stub
		return baseDao.page(where, order, offset, pageSize);
	}

	@Override
	public Integer selectCountByParm(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.selectCountByParm(where); 
	}
}
