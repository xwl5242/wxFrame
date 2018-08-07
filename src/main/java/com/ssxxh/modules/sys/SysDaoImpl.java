package com.ssxxh.modules.sys;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SysDaoImpl extends JdbcTemplate implements SysDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public int updateRR(final List<RR> rrlist) {
		String sql = "update rr_invitation set user_id=?,inviter_id=?,operation_partner_id=?,match_success_flag=?,remarks=? where id=?";
		int[] count = batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				RR rr = rrlist.get(i);
				ps.setString(1, rr.getUser_id());
				ps.setString(2, rr.getInviter_id());
				ps.setString(3, rr.getOperation_partner_id());
				ps.setString(4, rr.getMatch_success_flag());
				ps.setString(5, rr.getRemarks());
				ps.setString(6, rr.getId());
			}
			
			@Override
			public int getBatchSize() {
				return rrlist.size();
			}
		});
		return count.length;
	}
	
	@Override
	public int insertRR(final List<RR> rrlist) {
		String sql = "INSERT INTO rr_invitation(id,meeting_id,user_id,inviter_id,operation_partner_id,fill_party,match_success_flag,create_by,create_date,update_by,update_date,remarks,del_flag) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int[] count = batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				RR rr = rrlist.get(i);
				ps.setString(1, rr.getId());
				ps.setString(2, rr.getMeeting_id());
				ps.setString(3, rr.getUser_id());
				ps.setString(4, rr.getInviter_id());
				ps.setString(5, rr.getOperation_partner_id());
				ps.setString(6, rr.getFill_party());
				ps.setString(7, rr.getMatch_success_flag());
				ps.setString(8, rr.getCreate_by());
				ps.setDate(9, new Date(rr.getCreate_date().getTime()));
				ps.setString(10, rr.getUpdate_by());
				ps.setDate(11, new Date(rr.getUpdate_date().getTime()));
				ps.setString(12, rr.getRemarks());
				ps.setString(13, rr.getDel_flag());
			}
			
			@Override
			public int getBatchSize() {
				return rrlist.size();
			}
		});
		return count.length;
	}

	@Override
	public int insertRRShip(final List<RRShip> rrlist) {
		String sql = "INSERT INTO rr_invitation_relationship(id,meeting_id,user_id,inviter_id,operation_partner_id,create_by,create_date,update_by,update_date,remarks,del_flag) VALUES "
				+ "(?,?,?,?,?,?,?,?,?,?,?)";
		int[] count = batchUpdate(sql, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				RRShip rr = rrlist.get(i);
				ps.setString(1, rr.getId());
				ps.setString(2, rr.getMeeting_id());
				ps.setString(3, rr.getUser_id());
				ps.setString(4, rr.getInviter_id());
				ps.setString(5, rr.getOperation_partner_id());
				ps.setString(6, rr.getCreate_by());
				ps.setDate(7, new Date(rr.getCreate_date().getTime()));
				ps.setString(8, rr.getUpdate_by());
				ps.setDate(9, new Date(rr.getUpdate_date().getTime()));
				ps.setString(10, rr.getRemarks());
				ps.setString(11, rr.getDel_flag());
			}
			
			@Override
			public int getBatchSize() {
				return rrlist.size();
			}
		});
		return count.length;
	}
	
	@Override
	public Map<String, User> selectAllList() {
		Map<String, User> result = new HashMap<String, User>();
		String sql = "select id,id_number from sys_user where id_number!='' and del_flag='0' and user_type in('8','9')";
		List<Map<String,Object>> list = queryForList(sql);
		if(null!=list&&list.size()>0){
			for(Map<String,Object> uMap:list){
				Object id = uMap.get("id");
				Object idNumber = uMap.get("id_number");
				if(null!=id&&null!=idNumber){
					User user = new User();
					user.setId(id.toString());
					user.setIdNumber(idNumber.toString().toUpperCase());
					result.put(idNumber.toString().toUpperCase(), user);
				}
			}
		}
		return result;
	}
	
	@Override
	public Object selectByUserIdAndMId(String uId,String meetingId) {
		String sql = "select * from rr_invitation where user_id='"+uId+"' and meeting_id='"+meetingId+"' and del_flag='0'";
		List<Map<String,Object>> list = queryForList(sql);
		if(null!=list&&list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	
	@Override
	public Map<String,RR> queryAllRRListByMeeting(String meetingId,String successFlag) {
		Map<String,RR> result = new HashMap<String, RR>();
		String sql = "select id,user_id,inviter_id,operation_partner_id,fill_party from rr_invitation where meeting_id='"+meetingId+"' and del_flag='0' and match_success_flag='"+successFlag+"'";
		List<Map<String,Object>> list = queryForList(sql);
		if(null!=list&&list.size()>0){
			for(Map<String,Object> map:list){
				Object id = map.get("id");
				Object userId = map.get("user_id");
				Object inviterId = map.get("inviter_id");
				Object opId = map.get("operation_partner_id");
				Object fp = map.get("fill_party");
				if(null!=userId&&null!=inviterId&&null!=opId){
					RR rr = new RR();
					rr.setUser_id(userId.toString());
					rr.setInviter_id(inviterId.toString());
					rr.setOperation_partner_id(opId.toString());
					rr.setFill_party(fp.toString());
					rr.setId(id.toString());
					result.put(userId.toString(), rr);
				}
			}
		}
		return result;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
