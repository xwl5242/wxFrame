package com.ssxxh.frames.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信拦截器
 * @author xwl
 *
 */
public class WxSimpleOauth2Interceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(WxSimpleOauth2Interceptor.class);

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		logger.info("微信拦截器开始！");
		try{
			//是否在微信浏览器打开
			if (!request.getHeader("user-agent").contains("MicroMessenger")
					&& !request.getHeader("user-agent").contains("MQQBrowser")) {
				logger.info("请在微信客户端打开！");
				//请在微信客户端打开
				response.sendRedirect(request.getContextPath());
				return false;
			}
			//身份验证开始
		}catch(Exception e){
			logger.error("微信拦截器异常："+e.getMessage());
			return false;
		}
		logger.info("微信拦截器结束！");
		return false;
	}

}
