package com.qk.core.ibatis.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.beans.WherePrams;
import com.qk.core.ibatis.dao.BaseDao;
import com.qk.core.ibatis.service.BaseService;

@Repository
public class BaseServiceImpl<T extends Po, PK extends Serializable> implements BaseService<T, PK> {

	@Resource
	private BaseDao<T, PK> baseDao; 
	
	public BaseDao<T, PK> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T, PK> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int add(T po) {
		// TODO Auto-generated method stub
		return baseDao.add(po);
	}

	@Override
	public Serializable getField(PK id, String fileName) {
		// TODO Auto-generated method stub
		return baseDao.getField(id, fileName);
	}

	@Override
	public int update(T po, Boolean hasNull) {
		return baseDao.update(po,hasNull);
	}

	@Override
	public int del(PK id) {
		return baseDao.del(id);
	}
	
	@Override
	public T get(PK id) {
		return baseDao.get(id);
	}


	@Override
	public List<T> list(WherePrams where) {
		// TODO Auto-generated method stub
		return baseDao.list(where);
	}

	@Override
	public int update(T po) {
		return baseDao.update(po,false);
	}

	@Override
	public T get(Class<T> t, PK id) {
		// TODO Auto-generated method stub
		return baseDao.get(t, id);
	}
	
	/**--以下接口没有实现--**/
	
	@Override
	public int update(Po po, WherePrams where) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int del(WherePrams where) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public T get(WherePrams where) {
		// TODO Auto-generated method stub
		return null;
	}
}
