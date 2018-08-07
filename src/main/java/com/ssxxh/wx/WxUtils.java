package com.ssxxh.wx;

import java.util.List;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssxxh.frames.utils.ObjectMapperHelper;

/**
 * 微信工具类
 * @author xwl
 *
 */
public class WxUtils {
	
	public static WxMpService wxMpService = WxConfig.wxMpService;
	
	public static String[] jsApiList = {"checkJsApi","onMenuShareTimeline","onMenuShareAppMessage",
        "onMenuShareQQ","onMenuShareWeibo","onMenuShareQZone","hideMenuItems","showMenuItems","hideAllNonBaseMenuItem",
        "showAllNonBaseMenuItem","translateVoice","startRecord","stopRecord","onVoiceRecordEnd","playVoice","onVoicePlayEnd",
        "pauseVoice","stopVoice","uploadVoice","downloadVoice","chooseImage","previewImage","uploadImage","downloadImage",
        "getNetworkType","openLocation","getLocation","hideOptionMenu","showOptionMenu","closeWindow","scanQRCode","chooseWXPay",
        "openProductSpecificView","addCard","chooseCard","openCard"};

	/**
	 * 获取jsApiConfig
	 * @param url
	 * @return
	 * @throws WxErrorException
	 * @throws JsonProcessingException
	 */
	public static String getWxJsApiConfig(String url) throws WxErrorException, JsonProcessingException{
		//创建签名
		WxJsapiSignature sn = wxMpService.createJsapiSignature(url);
		//生成微信jsApiConfig对象
		WxJsApiConfig config = new WxJsApiConfig(sn.getAppid(), sn.getTimestamp(), sn.getNoncestr(), sn.getSignature(), jsApiList);
		//转换成字符串
		return ObjectMapperHelper.om.writeValueAsString(config);
	}
	
	/**
	 * 获取jsApiConfig
	 * @param url
	 * @param funcs
	 * @return
	 * @throws WxErrorException
	 * @throws JsonProcessingException
	 */
	public static String getWxJsApiConfig(String url,String[] funcs) throws WxErrorException, JsonProcessingException{
		//创建签名
		WxJsapiSignature sn = wxMpService.createJsapiSignature(url);
		//生成微信jsApiConfig对象
		WxJsApiConfig config = new WxJsApiConfig(sn.getAppid(), sn.getTimestamp(), sn.getNoncestr(), sn.getSignature(), funcs);
		//转换成字符串
		return ObjectMapperHelper.om.writeValueAsString(config);
	}
	
	/**
	 * 获取jsApiConfig
	 * @param url
	 * @param funcs
	 * @return
	 * @throws JsonProcessingException
	 * @throws WxErrorException
	 */
	public static String getWxJsApiConfig(String url,List<String> funcs) throws JsonProcessingException, WxErrorException{
		return getWxJsApiConfig(url, (String[]) funcs.toArray());
	}
	
}
