package com.ssxxh.frames.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 通用唯一识别码生成器
 * @author xwl
 *
 */
public class UUIDGenerator {

	public UUIDGenerator(){}
	
	/**
	 * 获取默认的uuid，带"-"
	 * @return
	 */
	public static String getDefaultUUID(){
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 获取不带"-"的uuid
	 * @return
	 */
	public static String getUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * 获取指定个数不带"-"的uuid
	 * @param number
	 * @return
	 */
	public static String[] getUUIDArrays(int number){
		String[] uuids = new String[number];
		if(number<1){
			return null;
		}else{
			for(int i=0;i<number;i++){
				uuids[i] = UUID.randomUUID().toString();
			}
			return uuids;
		}
	}
	
	/**
	 * 获取指定个数不带"-"的uuid
	 * @param number
	 * @return
	 */
	public static List<String> getUUIDLists(int number){
		if(number<1){
			return null;
		}else{
			return Arrays.asList(getUUIDArrays(number));
		}
	}
}
