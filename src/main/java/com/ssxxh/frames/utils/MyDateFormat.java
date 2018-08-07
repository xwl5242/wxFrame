package com.ssxxh.frames.utils;

import java.text.DateFormat;

/**
 * 配合DateUtils工具类使用
 * @author xwl
 *
 */
public class MyDateFormat {

	//日期格式pattern
	private String datePattern;
	//dateFormat
	private DateFormat dateFormat;
	
	public MyDateFormat(String pattern,DateFormat dateFormat){
		this.datePattern = pattern;
		this.dateFormat = dateFormat;
	}
	
	public String getDatePattern() {
		return datePattern;
	}
	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}
	public DateFormat getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}
	
}
