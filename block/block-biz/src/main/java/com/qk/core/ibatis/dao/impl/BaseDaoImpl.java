package com.qk.core.ibatis.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.qk.core.ibatis.annotation.po.FieldName;
import com.qk.core.ibatis.beans.PagerModel;
import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.beans.Pram;
import com.qk.core.ibatis.dao.BaseDao;
import com.qk.core.ibatis.sql.builder.MyBatisSql;
import com.qk.core.ibatis.sql.criteria.WherePrams;
import com.qk.core.ibatis.sql.order.Order;
import com.qk.core.ibatis.sql.where.SqlUtil;
import com.qk.core.ibatis.util.EmptyUtil;
import com.qk.core.ibatis.util.GenericsUtils;

@SuppressWarnings("unused")
@Repository
public class BaseDaoImpl<T extends Po> implements BaseDao<T> {

    private static final String SQL_NAME = "__SQL";

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionTemplate;

    private Class<T> entityClass;


    private String idName;                  //对应id名称


    private String tableName;

    private List<Pram> sqlParms;


    private List<Pram> selectSqlParms;

    private SqlUtil<T> sqlUtil;

    @SuppressWarnings("unchecked")
    public BaseDaoImpl(){
        super();

        this.sqlUtil = new SqlUtil<T>();

        this.entityClass = (Class<T>) GenericsUtils.getSuperClassGenricType(this.getClass());

        this.sqlParms = this.sqlUtil.getPramList(this.entityClass);

        this.selectSqlParms = this.sqlUtil.getPramListOfSelect(this.entityClass);

        this.tableName = this.sqlUtil.getTableName(this.entityClass);

        this.idName = "id";

    }
    
    @Override
    public int add(T po) {
        // TODO Auto-generated method stub
        String sql = "insert into " + tableName + " (";
        String prams = "";
        String values = "";
        Map<String, Object> parmMap=new HashMap<String, Object>();
        List<Pram> pramList = sqlUtil.getPramList(po);
        for (int i = 0; i < pramList.size(); i++) {
        	Object value=pramList.get(i).getValue();
        	String filed=pramList.get(i).getFiled();
            prams += pramList.get(i).getFile();
            if (pramList.get(i).getValue() == null) {
                values += "null";
            }else{
                values += "#{"+pramList.get(i).getFiled()+"}";
            }

            if(i < pramList.size() -1){
                prams += ",";
                values += ",";
            }
            parmMap.put(filed, value);
        }
        sql += prams + ") value (" + values +")";
        parmMap.put(SQL_NAME, sql);
        return sqlSessionTemplate.insert("Dao.add", parmMap);
    }
    
    @Override
    public int del(Serializable id) {
        // TODO Auto-generated method stub
    	Map<String, Object> parmMap=new HashMap<String, Object>();
        String idDbName=idName;
        try {
        	idDbName=sqlUtil.getDbFiledName(this.entityClass.newInstance(),idName);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			idDbName=idName;
			e.printStackTrace();
		}
        String sql = "delete from " + tableName + " where "+idDbName+"=#{"+idName+"}";
        parmMap.put(idName, id);
        parmMap.put(SQL_NAME, sql);
        return sqlSessionTemplate.delete("Dao.deleteById", parmMap);
    }


    @Override
    public int update(T po,Boolean hasNull){
    	Map<String, Object> parmMap=new HashMap<String, Object>();
        Serializable id = sqlUtil.getFileValue(po, idName);
        if(null == id){
            return 0;
        }
        StringBuffer sql = new StringBuffer("update ").append(tableName).append(" set ");
        List<Pram> prams = sqlUtil.getPramList(po);
        for (int i = 0; i < prams.size(); i++) {
        	Object value=prams.get(i).getValue();
        	if(!EmptyUtil.isEmpty(value) || (hasNull && EmptyUtil.isEmpty(value))){
        		 String file=prams.get(i).getFile();
            	 String filed=prams.get(i).getFiled();
        		 sql.append(file).append("=");
                 sql.append("#{").append(filed).append("}") ;
                  if (i < prams.size() -1) {
                  	 sql.append(",");
                  }
                  parmMap.put(filed, value);
        	}
        }
        sql.append(" where ").append(sqlUtil.getDbFiledName(po,idName)).append("=#{").append(idName).append("}");
        parmMap.put(SQL_NAME, sql);
        return sqlSessionTemplate.update("Dao.update", parmMap);
    }
    
    @Override
	public int update(T po) {
		return update(po,false);
	}
  


    @Override
    public T get(Serializable id) {
        return get(this.entityClass, id);
    }
    

	@Override
	public T get(Class<T> t, Serializable id) {
		   // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer("select ");
        for (int i = 0; i < selectSqlParms.size(); i++) {
        	sql.append(selectSqlParms.get(i).getFile());
            if(i < selectSqlParms.size() -1){
            	sql.append(",");
            }else{
            	sql.append(" ");
            }
        }
        String idDbName=idName;
        try {
        	idDbName=sqlUtil.getDbFiledName(t.newInstance(),idName);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			idDbName=idName;
			e.printStackTrace();
		}
        sql.append(" from ").append(tableName).append(" where ").append(idDbName).append("=#{").append(idName).append("}");
        Map<String, Object> parmMap=new HashMap<String, Object>();
        parmMap.put(SQL_NAME, sql);
        parmMap.put(idName, id);
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne("Dao.getById", parmMap);
        return handleResult(resultMap, t);
	}

    @Override
    public Serializable getField(Serializable id, String fileName) {
        // TODO Auto-generated method stub
        String field = fileName;
        String tabField = "";
        Field f = sqlUtil.getField(this.entityClass, fileName);
        if (null == f) {
            logger.error("查询字段失败(无法找到" + this.entityClass + "中的" + fileName + "字段)");
        }
        FieldName annotation = f.getAnnotation(FieldName.class);
        if (null == annotation) {
            tabField = sqlUtil.toTableString(fileName) + " as " + fileName;
        }else{
            tabField = annotation.name() + " as " + fileName;
        }

        String idDbName=idName;
        try {
        	idDbName=sqlUtil.getDbFiledName(this.entityClass.newInstance(),idName);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			idDbName=idName;
			e.printStackTrace();
		}
        
        String sql = "select ";
        sql += tabField + " from " + tableName + " where "+idDbName+"=#{" + idName + "}";
        
        Map<String, Object> parmMap=new HashMap<String, Object>();
        parmMap.put(SQL_NAME, sql);
        parmMap.put(idName, id);
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne("Dao.getFieldById", parmMap);
        return (Serializable) resultMap.get(fileName);
    }
    
    /**
     * 获取某表的下一个Id
     */
    public long nextId(){
        String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_NAME='" + tableName + "'";
        Long idVal = sqlSessionTemplate.selectOne("fetchSeqNextval", sql);
        if (null == idVal) {
            logger.error("/********************************");
            logger.error("/获取id失败，" + tableName + "表异常。请检查是否存在或者配为自增主键");
            logger.error("/********************************");
            return 0;
        }
        return idVal;
    }

    @Override
    public T get(WherePrams where) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        MyBatisSql sqlObj = new MyBatisSql(where);
        sql += "from " + tableName + sqlObj.getSql();
        Map<String, Object> map = sqlObj.getArgMap();
        map.put(SQL_NAME, sql);
        Map<String, Object> resultMap = sqlSessionTemplate.selectOne("Dao.getByParm", map);
        return handleResult(resultMap, this.entityClass);
    }
    
    


    @Override
    public List<T> list(WherePrams where,Order order) {
        // TODO Auto-generated method stub
        String sql = "select ";
        for (int i = 0; i < selectSqlParms.size(); i++) {
            sql += selectSqlParms.get(i).getFile();
            if(i < selectSqlParms.size() -1){
                sql += ",";
            }else{
                sql += " ";
            }
        }
        
        MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> argMap = sqlObj.getArgMap();
        sql += "from " + tableName + ((where==null)?"":sqlObj.getSql()) +(StringUtils.isEmpty(order)?"":order.toString());
        argMap.put(SQL_NAME, sql);
        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("Dao.selectList", argMap);
        List<T> list = new ArrayList<>();
        for (Map<String, Object> map : selectList) {
            T t = handleResult(map, this.entityClass);
            list.add(t);
        }
        return list;
    }
    
    public Integer selectCountByParm(WherePrams where) {
    	 // TODO Auto-generated method stub
        MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> argMap = sqlObj.getArgMap();
        String sql = "select count(1) from " + tableName + ((where==null)?"":sqlObj.getSql());
        argMap.put(SQL_NAME, sql);
		return sqlSessionTemplate.selectOne("Dao.selectCountByParm", argMap);
	}
    
    
	@Override
	public PagerModel<T> page(WherePrams where, Order order, int offset, int pageSize) {
		MyBatisSql sqlObj = new MyBatisSql(where);
        Map<String, Object> argMap = sqlObj.getArgMap();
        String countSql = "select count(1) from " + tableName + ((where==null)?"":sqlObj.getSql());
        argMap.put(SQL_NAME, countSql);
		int count=sqlSessionTemplate.selectOne("Dao.selectCountByParm", argMap);
		PagerModel pm=null;
		if(count>0l)
		{
			pm=new PagerModel();
			pm.setTotal(count);
			pm.setPageSize(pageSize);
			pm.setOffset(offset);
			
			 // TODO Auto-generated method stub
	        String sql = "select ";
	        for (int i = 0; i < selectSqlParms.size(); i++) {
	            sql += selectSqlParms.get(i).getFile();
	            if(i < selectSqlParms.size() -1){
	                sql += ",";
	            }else{
	                sql += " ";
	            }
	        }
	        sql += "from " + tableName + ((where==null)?"":sqlObj.getSql()) +(StringUtils.isEmpty(order)?"":order.toString())+" limit #{offset},#{pageSize}";
	        argMap.put(SQL_NAME, sql);
	        argMap.put("offset", offset);
	        argMap.put("pageSize", pageSize);
	        List<Map<String, Object>> selectList = sqlSessionTemplate.selectList("Dao.selectList", argMap);
	        List<T> list = new ArrayList<>();
	        for (Map<String, Object> map : selectList) {
	            T t = handleResult(map, this.entityClass);
	            list.add(t);
	        }
	        pm.setList(list);
		}
		return pm;
	}

   

    @Override
    public int update(T po, WherePrams where) {
        // TODO Auto-generated method stub
    	Map<String, Object> parmMap=new HashMap<String, Object>();
    	List<Pram> prams = sqlUtil.getPramList(po);
        String sql = "update " + tableName + " set ";
        Object[] o = new Object[sqlParms.size()];
        for (int i = 0; i < sqlParms.size(); i++) {
            if(null != sqlParms.get(i).getValue()){
                sql += sqlParms.get(i).getFile() + "=" + sqlParms.get(i).getValue();
                if (i < sqlParms.size() -1) {
                    sql += ",";
                }
            }else{
                sql += sqlParms.get(i).getFile() + "=null";
                if (i < sqlParms.size() -1) {
                    sql += ",";
                }
            }
        }
        
        MyBatisSql sqlObj = new MyBatisSql(where);
        sql += sqlObj.getSql();
        Map<String, Object> map = sqlObj.getArgMap();
        map.put(SQL_NAME, sql);
        return sqlSessionTemplate.update("Dao.updateByPram", map);
    }


    @Override
    public int del(WherePrams where) {
        // TODO Auto-generated method stub
        MyBatisSql sqlObj = new MyBatisSql(where);
        String sql = "delete from " + tableName +sqlObj.getSql();
        sql += sqlObj.getSql();
        Map<String, Object> map = sqlObj.getArgMap();
        map.put(SQL_NAME, sql);
        return sqlSessionTemplate.delete("Dao.deleteByparm", map);
    }
  

    private T handleResult(Map<String, Object> resultMap, Class<T> tClazz) {
        if (null == resultMap) {
            return null;
        }
        T t = null;
        try {
            t = tClazz.newInstance();
        } catch (InstantiationException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        } catch (IllegalAccessException e) {
            logger.error("/********************************");
            logger.error("实例化Bean失败(" + this.entityClass + ")!"
                    + e.getMessage());
            logger.error("/********************************");
        }
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            String key = entry.getKey();
            Serializable val = (Serializable) entry.getValue();
            try {
                SqlUtil.setFileValue(t, key, val);
            } catch (Exception e) {
                // TODO: handle exception
                logger.error("/********************************");
                logger.error("/实例化Bean失败(" + this.entityClass + ")不能赋值到字段(" + key + "):"
                        + e.getMessage());
                logger.error("/********************************");
            }
        }
        return t;
    }
    
  
    
	/**
	 * 从List<String>集合中检查是否有存在的元素
	 * @param list
	 * @param tabName
	 * @return
	 */
	private boolean isExcTab (List<String> list, String tabName){
		for (String string : list) {
			if (tabName .equals( string)) {
				return true;
			}
		}
		return false;
	}


}