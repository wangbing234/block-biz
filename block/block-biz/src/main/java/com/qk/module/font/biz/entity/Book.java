package com.qk.module.font.biz.entity;

import com.qk.core.ibatis.annotation.po.FieldName;
import com.qk.core.ibatis.annotation.po.TableName;
import com.qk.core.ibatis.beans.Po;

/**
 * 图书实体
 */
@TableName(name="book")
public class Book  extends Po{


	@FieldName(name="book_Id")
	private Long id;// 图书ID

	private String name;// 图书名称

	private int number;// 馆藏数量

	public Book() {
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "Book [bookId=" + id + ", name=" + name + ", number=" + number + "]";
	}
	
	


}
