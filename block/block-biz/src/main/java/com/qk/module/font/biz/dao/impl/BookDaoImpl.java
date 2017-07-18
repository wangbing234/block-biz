package com.qk.module.font.biz.dao.impl;

import org.springframework.stereotype.Component;

import com.qk.core.ibatis.dao.impl.BaseDaoImpl;
import com.qk.module.font.biz.dao.BookDao;
import com.qk.module.font.biz.entity.Book;

@Component
public class BookDaoImpl extends BaseDaoImpl<Book> implements BookDao{

}
