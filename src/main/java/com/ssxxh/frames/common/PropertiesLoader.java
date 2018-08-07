package com.ssxxh.frames.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * 配置文件加载
 * @author xwl
 *
 */
public class PropertiesLoader {
	
	public final Properties properties;
	
	/**
	 * 构造器，加载多个配置文件
	 * @param locations  配置文件路径
	 */
	public PropertiesLoader(String... locations){
		properties = loadProperties(locations);
	}

	/**
	 * 获取配置文件信息
	 * @return
	 */
	public Properties getProperties(){
		return properties;
	}
	
	/**
	 * 加载配置文件信息
	 * @param locations
	 * @return
	 */
	private Properties loadProperties(String[] locations) {
		Properties pros = new Properties();
		try {
			//循环加载每个配置文件
			for (String location : locations) {
				InputStream inStream = new ClassPathResource(location)
						.getInputStream();
				pros.load(new BufferedReader(new InputStreamReader(inStream)));
			}
		} catch (Exception e) {
		}
		return pros;
	}
	
	/**
	 * 获取配置文件中的key，value
	 * @param key
	 * @return
	 */
	public String getProperty(String key){
		String value = null;
		//配置文件中的key不为null时，获取key的值
		if(StringUtils.isNotEmpty(key)){
			value = properties.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 获取配置文件中已某个字符串开头的配置
	 * @param sw
	 * @return
	 */
	public Map<String,String> getProperty4StartWith(String sw){
		Map<String,String> map = new HashMap<String,String>();
		//所有配置文件中的key，value
		Set<Object> keys = properties.keySet();
		for(Object key:keys){
			if(key.toString().contains(sw)){
				map.put(key+"", getProperty(key+""));
			}
		}
		return map;
	}
}
