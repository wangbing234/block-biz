package com.qk.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author bing.wang
 *
 */
public class ContextInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod)handler;
	        Object hmBean = hm.getBean();
	        logger.info("ContextInterceptor: "+hmBean.toString());
		}
		else{
			response.getWriter().println("reject!");
			response.getWriter().close();
			return false;
		}
		return true;
	}


	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
    }

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
        //释放资源 避免内存溢出
        HandlerMethod hm = (HandlerMethod)handler;
        Object hmBean = hm.getBean();
        logger.info("ContextInterceptor: "+hmBean.toString());
//        if(	hmBean instanceof AdminLoginController) {
//            ControllerContext.remove();
//        }

	}
	
}
