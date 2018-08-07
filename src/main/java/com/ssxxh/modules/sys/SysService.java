package com.ssxxh.modules.sys;

import java.util.Map;

public interface SysService {

	void save(String meetingId);

	Map<String,User> queryAllList();
}
