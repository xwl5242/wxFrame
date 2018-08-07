package com.ssxxh.frames.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 获取随机数
 * @author xwl
 *
 */
public class RandomUtil {

	/**
	 * 获取随机数list，list的每一项是数值
	 * 在minValue~maxValue之间获取number数个整数
	 * @param number 获取的个数
	 * @param minValue 范围，最小值
	 * @param maxValue 范围，最大值
	 */
	public static List<Integer> getRandomNumberList(int number,int minValue,int maxValue) throws Exception{
		if(maxValue-minValue<number){
			throw new Exception(minValue+"~"+maxValue+"此区间内的整数只有"+(maxValue-minValue)+"个，无法生成"+number+"个随机数！");
		}
		List<Integer> randomList = new ArrayList<Integer>();
		while(randomList.size()<number){
			int ri = ThreadLocalRandom.current().nextInt(minValue,maxValue);
			if(!randomList.contains(ri)){
				randomList.add(ri);
			}
		}
		return randomList;
	}
	
	/**
	 * 获取随机数list，list的每一项是数值
	 * 在0~maxValue之间获取number数个整数
	 * @param number 获取的个数
	 * @param maxValue 范围，最大值（最小值默认为0）
	 * @return
	 */
	public static List<Integer> getRandomNumberList(int number,int maxValue)throws Exception{
		return getRandomNumberList(number,0,maxValue);
	}
	
}
