/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.qk.module.font.exitsub.web;

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

import com.qk.module.font.exitsub.service.*;
import com.qk.module.font.exitsub.entity.*;


/**
 * 提取表table1  Controller
 * @author bing.wang
 * @version 1.0
 * @since 1.0
 * */
/**前后端/模块**/
@Controller
@RequestMapping("/font/exitSub")
public class ExitSubController  extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	ExitSubService exitSubService;
	
	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean list(@RequestBody ExitSub exitSub) {
					And and =new And("rid", exitSub.getRid(),Restrictions.EQ);
				 	and.add("exitSubState", exitSub.getExitSubState(),Restrictions.EQ);
				 	and.add("isLocked", exitSub.getIsLocked(),Restrictions.EQ);
				 	and.add("exitType", exitSub.getExitType(),Restrictions.EQ);
		 Order order=new Order();
			order.add("rid");
			order.add("exitMasterId");
			order.add("exitSubAmount");
			order.add("exitSubState");
			order.add("matchId");
			order.add("createTime");
			order.add("subPri");
			order.add("isLocked");
			order.add("exitMember");
			order.add("exitType");
			order.add("matchCount");
		List<ExitSub> list =  exitSubService.list(and,order);
		return success(list);
	}
	
	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	private ResultBean insert(@RequestBody ExitSub exitSub) {
		int i = exitSubService.add(exitSub);
		return success(i);
	}
	
	/**
	 * 查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	private ResultBean update(@RequestBody ExitSub exitSub) {
		return success(exitSubService.update(exitSub));
	}
	
	
	
	/**
	 * 查询分页列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/page/{offset}/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean page(@PathVariable("offset") Integer offset,@PathVariable("pageSize") Integer pageSize ,@RequestBody ExitSub exitSub) {
		
			And and =new And("rid", exitSub.getRid(),Restrictions.EQ);
		 	and.add("exitSubState", exitSub.getExitSubState(),Restrictions.EQ);
		 	and.add("isLocked", exitSub.getIsLocked(),Restrictions.EQ);
		 	and.add("exitType", exitSub.getExitType(),Restrictions.EQ);
		 Order order=new Order();
			order.add("rid");
			order.add("exitMasterId");
			order.add("exitSubAmount");
			order.add("exitSubState");
			order.add("matchId");
			order.add("createTime");
			order.add("subPri");
			order.add("isLocked");
			order.add("exitMember");
			order.add("exitType");
			order.add("matchCount");
		PagerModel<ExitSub> pageModel = exitSubService.page(and,order,offset,pageSize);
		return success(pageModel);
	}

	/**
	 * 查询当个数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getById/{exitSubId}", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean getById(@PathVariable("exitSubId") Long exitSubId) {
		ExitSub exitSub = exitSubService.get(exitSubId);
		return success(exitSub);
	}
	
	/**
	 * 查询当个数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete/{exitSubId}", method = RequestMethod.GET)
	@ResponseBody
	private ResultBean deleteById(@PathVariable("exitSubId") Long exitSubId) {
		return  success(exitSubService.del(exitSubId));
	}
	
}
