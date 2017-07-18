/*
 * Powered By [rapid-framework]
 *  copyright © 趋快科技(武汉)有限公司
 * Since 2017 - 2017
 */

package com.qk.module.font.exitsub.service.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.qk.core.ibatis.service.impl.BaseServiceImpl;
import com.qk.module.font.exitsub.dao.ExitSubDao;
import com.qk.module.font.exitsub.service.ExitSubService;
import com.qk.module.font.exitsub.entity.*;


/**
 * 提取表table1  Service实现类
 * @author bing.wang
 * @version 1.0
 * @since 1.0
 * */
@Service
@Component
public class ExitSubServiceImpl extends BaseServiceImpl<ExitSub> implements ExitSubService {

	@Resource
	ExitSubDao exitSubDao;
	
}
