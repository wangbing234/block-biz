package com.qk.core.ibatis.util;

import java.util.ArrayList;
import java.util.List;

import com.qk.core.ibatis.beans.FmtParm;
import com.qk.core.ibatis.beans.Po;
import com.qk.core.ibatis.beans.WherePrams;
/**
 * SQL查询格式化工具类
 * @author bing.wang
 * @time 2016年6月14日下午4:33:29
 * @email test.qq.com
 *
 */
public class FormatterSql implements Formatter {

	/**
	 * 格式化条件
	 */
	private List<FmtParm> fmtParms = new ArrayList<>();
	
	
	@Override
	public  void addFmt(String fieldName, String select, Class<? extends Po> po, WherePrams where) {
		// TODO Auto-generated method stub
		fmtParms.add(new FmtParm(fieldName, select, po, where));
	}

	@Override
	public void addFmt(FmtParm parm) {
		// TODO Auto-generated method stub
		fmtParms.add(parm);
	}

	@Override
	public List<FmtParm> listFmtParm() {
		// TODO Auto-generated method stub
		return fmtParms;
	}

}
