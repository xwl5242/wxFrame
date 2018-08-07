package com.ssxxh.frames.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * objectmapper帮助类
 * @author xwl
 *
 */
@SuppressWarnings({"unchecked","rawtypes"})
public class ObjectMapperHelper {

	public static ObjectMapper om = new ObjectMapper();
	
	/**
	 * map转为javaBean
	 * @param source 带转换的map
	 * @param clazz javaBean 的class
	 * @return
	 */
	public static <T> T MapTrans4JavaBean(Map source,Class<?> clazz){
		T t = null;
		try {
			byte[] rByte = om.writeValueAsBytes(source);
			t = (T) om.readValue(rByte, clazz);
		} catch (Exception e) {
		}
		return t;
	}
	
	/**
	 * list<JavaBean> 转换为 list<map<K,V>>
	 * @param list 带转换的list
	 * @return 转换结果
	 */
	public static List<Map<String,Object>> beanListTrans4MapList(List<?> list){
		List<Map<String,Object>> rlist = new ArrayList<Map<String,Object>>();
		byte[] bytes;
		try {
			bytes = om.writeValueAsBytes(list);
			rlist = (List<Map<String,Object>>)om.readValue(bytes, List.class);
		} catch (Exception e) {
		}
		return rlist;
	}
	
	/**
	 * json数组字符串转为list<?>
	 * @param jsonArrayStr json数组字符串
	 * @param clazz 返回list中每一项的数据类型
	 * @return list<?>
	 * @throws Exception
	 */
	public static <T> List<T> jsonArrayStrTrans4List(String jsonArrayStr,Class<?> clazz) throws Exception{
		jsonArrayStr = jsonArrayStr.trim();
		T t = null;
		List<T> list = new ArrayList<T>();
		JSONArray ja = JSONArray.parseArray(jsonArrayStr);
		if(null!=ja&&ja.size()>0){
			for(int i=0;i<ja.size();i++){
				Object o = ja.get(i);
				byte[] rByte = om.writeValueAsBytes(o);
				t = (T) om.readValue(rByte,clazz);
				list.add(t);
			}
		}
		return list;
	}
	
	/**
	 * 对象json字符串转为执行类型的对象
	 * @param jsonObjectStr json对象字符串
	 * @param beanClazz 要转换成的bean的class
	 * @return T object
	 * @throws Exception
	 */
	public static <T> T jsonObjectStrTrans4Bean(String jsonObjectStr,Class<?> beanClazz) throws Exception{
		jsonObjectStr = jsonObjectStr.trim();
		T t=null;
		if(StringUtils.isBlank(jsonObjectStr)){
			return null;
		}
		JSONObject jo = JSONObject.parseObject(jsonObjectStr);
		byte[] rByte = om.writeValueAsBytes(jo);
		t = (T) om.readValue(rByte,beanClazz);
		return t;
	}
	
	/**
	 * map字符串转换为对象
	 * @param jsonMapStr map字符串
	 * @param clazz 要转换成的对象的类型
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonMapStrTrans4Bean(String jsonMapStr,Class<?> clazz) throws Exception{
		jsonMapStr = jsonMapStr.trim();
		jsonMapStr = jsonMapStr.replace("=", ":");
		return jsonObjectStrTrans4Bean(jsonMapStr, clazz);
	}
}
