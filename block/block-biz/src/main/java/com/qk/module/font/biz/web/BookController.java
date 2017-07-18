package com.qk.module.font.biz.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qk.core.framework.BaseController;
import com.qk.core.framework.dto.ResultBean;
import com.qk.core.ibatis.beans.PagerModel;
import com.qk.core.ibatis.sql.criteria.And;
import com.qk.core.ibatis.sql.criteria.Restrictions;
import com.qk.core.ibatis.sql.order.Order;
import com.qk.module.font.biz.entity.Book;
import com.qk.module.font.biz.service.BookService;
/**前后端/模块**/
@Controller
@RequestMapping("/font/book")

public class BookController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private BookService bookService;

	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean list(@RequestBody Book book) {
		And and =new And(null,null);
		and.add("name", book.getName(),Restrictions.EQ);
		Order order=new Order();
		order.add("number").add("ddd");
		List<Book> list =  bookService.list(and,order);
		return success(list);
	}
	
	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	private ResultBean insert(@RequestBody Book book) {
		int i = bookService.add(book);
		return success(i);
	}
	
	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	private ResultBean update(@RequestBody Book book) {
		return success(bookService.update(book));
	}
	
	
	
	/**
	 * 查询分页列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize,@RequestBody Book book) {
		And and =new And(null,null);
		and.add("name", book.getName(),Restrictions.EQ);
		Order order=new Order();
		order.add("number").add("ddd");
		PagerModel<Book> pageModel = bookService.page(and,order,offset,pageSize);
		return success(pageModel);
	}

	/**
	 * 查询当个数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getById/{bookId}", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean getById(@PathVariable("bookId") Long bookId) {
		Book book = bookService.get(bookId);
		return success(book);
	}
	
	/**
	 * 查询当个数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean deleteById(@PathVariable("bookId") Long bookId) {
		return  success(bookService.del(bookId));
	}

}
