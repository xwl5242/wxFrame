package com.ssxxh.frames.utils;

public class B {

	private String key;
	private String is_necessary;
	private String value;
	private String type;
	private String num;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIs_necessary() {
		return is_necessary;
	}
	public void setIs_necessary(String is_necessary) {
		this.is_necessary = is_necessary;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "{key=" + key + ", is_necessary=" + is_necessary + ", value="
				+ value + ", type=" + type + ", num=" + num + "}";
	}
}
