package com.ssxxh.wx;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;

/**
 * 微信配置类
 * @author xwl
 *
 */
public final class WxConfig {

	/**
	 * 微信相关属性的配置文件
	 */
	public final static String WX_CONST_PROPERTY = "wxConst.property";

	/**
	 * 微信内存存储配置
	 */
	public static WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();

	/**
	 * 微信service
	 */
	public static WxMpService wxMpService = new WxMpServiceImpl();

	public static String APPID;//公众号appid

	public static String SECRET;//公众号secret
	
	public static String OAUTH2_REDIRECT_URI;

	static {
		PropertiesConfiguration config = null;
		try {
			config = new PropertiesConfiguration(WX_CONST_PROPERTY);
			// Automatic Reloading 自动重新加载
			config.setReloadingStrategy(new FileChangedReloadingStrategy());

			APPID = config.getString("appid");
			if (StringUtils.isEmpty(APPID)) {
				throw new RuntimeException("APPID不能为空");
			}
			wxMpConfigStorage.setAppId(APPID);
			SECRET = config.getString("secret");
			if (StringUtils.isEmpty(SECRET)) {
				throw new RuntimeException("SECRET不能为空");
			}
			wxMpConfigStorage.setSecret(SECRET);
			
			OAUTH2_REDIRECT_URI = config.getString("oauth2_redirect_uri");
			if(!StringUtils.isEmpty(OAUTH2_REDIRECT_URI)){
				wxMpConfigStorage.setOauth2redirectUri(OAUTH2_REDIRECT_URI);
			}
			wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
		} catch (ConfigurationException e) {
		}
	}
}
