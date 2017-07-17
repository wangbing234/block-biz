package com.qk.core.ibatis.beans;


/**
 * POJO字段封装类
 * @author bing.wang
 * @time 2015-下午10:54:36
 * @email test.qq.com
 */
public class Pram {

	private String file;
	
	private String filed;
	
	private Object value;

	public Pram(){}
	public Pram(String file, Object value,String filed){
		this.file = file;
		this.value = value;
		this.filed=filed;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getFiled() {
		return filed;
	}
	public void setFiled(String filed) {
		this.filed = filed;
	}
	
	

}
