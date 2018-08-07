package com.ssxxh.wx;

import me.chanjar.weixin.common.exception.WxErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 微信jsapi
 * @author xwl
 *
 */
@Controller
@RequestMapping("/wxapi")
public class WxController {
	
	/**
	 * 获取jsApiConfig
	 * @param url 调用jsApi的请求地址
	 * @param funcs 需要使用的功能
	 * @return JsApiConfig对象
	 * @throws JsonProcessingException
	 * @throws WxErrorException
	 */
	@RequestMapping(value="/createWxJsApiConfig",method=RequestMethod.POST)
	@ResponseBody
	public String createWxJsApiConfig(@RequestParam String url,@RequestParam String funcs) throws JsonProcessingException, WxErrorException{
		if(!StringUtils.isEmpty(funcs)){//自定义功能
			return WxUtils.getWxJsApiConfig(url, funcs.split(","));
		}else{//所有功能
			return WxUtils.getWxJsApiConfig(url);
		}
	}

}
