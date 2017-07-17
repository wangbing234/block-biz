package com.qk.core.framework.dto;

public enum ResultEnum {
	//客户端
	CLIENT_ERROR("404","参数错误"), 
	NO_LOGIN_ERROR("403","用户未登录"), 
	//服务器
	SERVER_ERROR( "500","服务器异常"), 
	//成功
	SUCCESS("000","success");  
	
	private String code;
	private String msg;
	 // 构造方法  
  private ResultEnum(String code, String msg) {
      this.code = code;  
      this.msg = msg;  
  }  
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
	
}
