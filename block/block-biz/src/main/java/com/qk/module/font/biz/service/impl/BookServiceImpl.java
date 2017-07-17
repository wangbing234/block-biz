package com.qk.module.font.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qk.core.ibatis.service.impl.BaseServiceImpl;
import com.qk.module.font.biz.dao.BookDao;
import com.qk.module.font.biz.entity.Book;
import com.qk.module.font.biz.service.BookService;

@Service
@Component
public class BookServiceImpl extends BaseServiceImpl<Book, Long> implements BookService {


	// 注入Service依赖
	@Resource
	private BookDao bookDao;

}
