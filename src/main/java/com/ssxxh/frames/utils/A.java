package com.ssxxh.frames.utils;

import java.util.List;

public class A {

	private String course_id;
	private List<B> content;
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public List<B> getContent() {
		return content;
	}
	public void setContent(List<B> content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "{course_id=" + course_id + ", content=" + content + "}";
	}
	
}
