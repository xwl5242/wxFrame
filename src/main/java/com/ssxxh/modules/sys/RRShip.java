package com.ssxxh.modules.sys;

import java.util.Date;

public class RRShip {
	private String id;
	private String meeting_id;
	private String user_id;
	private String inviter_id;
	private String operation_partner_id;
	private String create_by;
	private Date create_date;
	private String update_by;
	private Date update_date;
	private String remarks;
	private String del_flag;
	
	public RRShip(){}
	
	public RRShip(String id, String meeting_id, String user_id,
			String inviter_id, String operation_partner_id, String create_by,
			Date create_date, String update_by, Date update_date,
			String remarks, String del_flag) {
		super();
		this.id = id;
		this.meeting_id = meeting_id;
		this.user_id = user_id;
		this.inviter_id = inviter_id;
		this.operation_partner_id = operation_partner_id;
		this.create_by = create_by;
		this.create_date = create_date;
		this.update_by = update_by;
		this.update_date = update_date;
		this.remarks = remarks;
		this.del_flag = del_flag;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMeeting_id() {
		return meeting_id;
	}
	public void setMeeting_id(String meeting_id) {
		this.meeting_id = meeting_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getInviter_id() {
		return inviter_id;
	}
	public void setInviter_id(String inviter_id) {
		this.inviter_id = inviter_id;
	}
	public String getOperation_partner_id() {
		return operation_partner_id;
	}
	public void setOperation_partner_id(String operation_partner_id) {
		this.operation_partner_id = operation_partner_id;
	}
	public String getCreate_by() {
		return create_by;
	}
	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	
}
