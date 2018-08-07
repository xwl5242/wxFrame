package com.ssxxh.modules.sys;

import java.util.List;
import java.util.Map;

public interface SysDao {

	int updateRR(List<RR> rrlist);
	
	int insertRR(List<RR> rrlist);
	
	int insertRRShip(List<RRShip> rrlist);

	Map<String, User> selectAllList();

	Object selectByUserIdAndMId(String uId,String meetingId);

	Map<String,RR> queryAllRRListByMeeting(String meetingId,String successFlag);

}
