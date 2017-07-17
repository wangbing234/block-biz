package com.qk.core.ibatis.dao;

import java.io.Serializable;
import java.util.List;

import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.beans.WherePrams;

/**
 * 公共数据库操作层
 * @author bing.wang
 * @time 2016年5月3日下午2:55:13
 * @email test.qq.com
 * @param <T> 实体PO类型
 * @param <PK> PO主键类型
 */
public interface BaseDao<T extends Po, PK extends Serializable> {

    /**
     * 记录添加
     * @param po
     * @return
     */
    public int add(T po);

    /**
     * 通过主键获取某个记录
     * @param id 主键
     * @return PO
     */
    public T get(PK id);
    
    /**
     * 通过主键获取某个记录
     * @param id 主键
     * @return PO
     */
    public T get(Class<T> t,PK id);

    /**
     * 通过主键获取某个字段的值
     * @param id
     * @param fileName
     * @return
     */
    public Serializable getField(PK id, String fileName);

    /**
     * 条件获取一条记录
     * @param t
     * @param 条件表达式
     * @return PO
     */
    public T get(WherePrams where);


    /**
     * 条件查询列表
     * @param where 条件表达式
     * @return PO列表
     */
    public List<T> list(WherePrams where);



    /**
     * 更新PO的所有字段
     * @param po
     * hasNull 是否包含空，默认为false
     * @return 受影响的行数
     */
    public int update(T po,Boolean  hasNull);
    
    /**
     * 更新PO的所有字段
     * @param po
     * hasNull 是否包含空，默认为false
     * @return 受影响的行数
     */
    public int update(T po);


    /**
     * 条件更新所有字段
     * @param po
     * @param 条件表达式
     * @return 受影响的行数
     */
    public int update(T po, WherePrams where);

    /**
     * 删除某个记录
     * @param id 主键
     * @return 受影响的行数
     */
    public int del(PK id);

    /**
     * 条件删除某个记录
     * @param where 条件表达式
     * @return 受影响的行数
     */
    public int del(WherePrams where);


}