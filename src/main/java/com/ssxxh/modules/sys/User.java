package com.ssxxh.modules.sys;

public class User {

	private String id;
	private String idNumber;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Override
	public String toString() {
		return "id="+id+",idNumber="+idNumber;
	}
	
}
