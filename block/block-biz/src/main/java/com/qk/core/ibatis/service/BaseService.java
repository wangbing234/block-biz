package com.qk.core.ibatis.service;

import java.io.Serializable;

import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.dao.BaseDao;

/**
 * 公共数据库操作层
 * @author bing.wang
 * @time 2016年5月3日下午2:55:13
 * @email test.qq.com
 * @param <T> 实体PO类型
 * @param <PK> PO主键类型
 */
public interface BaseService<T extends Po, PK extends Serializable> extends BaseDao<T,PK>{

	
}