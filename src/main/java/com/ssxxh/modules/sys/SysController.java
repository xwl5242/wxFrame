package com.ssxxh.modules.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysController {
	
	@Autowired
	private SysService sysService;

	/**
	 * 导入邀请人关系
	 */
	@RequestMapping("/import/{meetingId}")
	public void importRR(@PathVariable String meetingId){
		sysService.save(meetingId);
	}
}
