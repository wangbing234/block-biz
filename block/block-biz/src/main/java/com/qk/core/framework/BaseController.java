package com.qk.core.framework;

import com.qk.core.framework.dto.ResultEnum;
import com.qk.core.framework.dto.ResultBean;

public class BaseController {
	
	protected ResultBean success(Object data){
		return new ResultBean(ResultEnum.SUCCESS).setData(data);
	}
	protected ResultBean success(){
		return new ResultBean(ResultEnum.SUCCESS);
	}
}
