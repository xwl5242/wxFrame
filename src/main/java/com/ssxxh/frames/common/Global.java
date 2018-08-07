package com.ssxxh.frames.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ssxxh.frames.constants.Const;

/**
 * 全局变量
 * @author xwl
 *
 */
public class Global {

	private static Global global;
	
	private static Map<String,String> map = new HashMap<String,String>();
	
	private static PropertiesLoader propsLoader = new PropertiesLoader(Const.APPLICATION_PROPERTIES_NAME,
			Const.JDBC_PROPERTIES_NAME,Const.WX_CONST_PROPERTY_NAME);
	
	private Global(){}
	
	public static Global getInstance(){
		if(global==null){
			synchronized (Global.class) {
				global = new Global();
			}
		}
		return global;
	}
	
	public static String getConfig(String key){
		String value = map.get(key);
		if(null==value){
			value = propsLoader.getProperty(key);
			map.put(key, value!=null?value:"");
		}
		return value;
	}
	
	public static String getProperty(String key){
		return getConfig(key);
	}
	
	public static Map<String,String> getPropertys4StartWith(String sw){
		return propsLoader.getProperty4StartWith(sw);
	}
	
	public static String getIgnorableConfig(){
		return getProperty(Const.APPLICATION_IGNORABLE_CONFIG);
	}
	
	public static String getJdbc2DatabaseName(){
		return getProperty(Const.JDBC_DATABASE_NAME);
	}
	
	public static boolean isOpLog(){
		String s =  getProperty(Const.IS_OPT_LOG);
		return StringUtils.isBlank(s)?false:Boolean.valueOf(s);
	}
}
