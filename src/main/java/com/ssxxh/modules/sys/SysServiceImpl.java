package com.ssxxh.modules.sys;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssxxh.frames.utils.CommonExcelImport;
import com.ssxxh.frames.utils.UUIDGenerator;

@Service
public class SysServiceImpl implements SysService {

	@Autowired
	private SysDao sysDao;

	@Override
	public void save(String meetingId) {
		int r1=0,r2=0,r3=0;
		long s = System.currentTimeMillis();
		Date nowDate = new Date();int totalcount = 0;int errcount=0;int matchcount=0;
		List<RR> rrlist1 = new ArrayList<RR>();
		List<RR> rrlist2 = new ArrayList<RR>();
		List<RRShip> rrlist3 = new ArrayList<RRShip>();
		//不存在的邀约关系
		List<String> nohasDesc = new ArrayList<String>();
		//数据错误的邀约关系
		List<String> errDesc = new ArrayList<String>();
		try {
			Map<String,User> userMap = queryAllList();
			//没有完全匹配的需要做处理
			Map<String,RR> nohasRRList = sysDao.queryAllRRListByMeeting(meetingId,"0");
			Map<String,RR> hasRRList = sysDao.queryAllRRListByMeeting(meetingId,"1");
			//读取excel信息
			File file = new File("c:\\3.xlsx");
			FileInputStream fin = new FileInputStream(file);
			List<List<Object>> list = CommonExcelImport.getBankListByExcel(fin,file.getName());
			for(List<Object> li:list){
				totalcount++;
				String u = li.get(0).toString().toUpperCase();//会员身份证
	    		String iu = li.get(1).toString().toUpperCase();//邀请人身份证
	    		String p = li.get(2).toString().toUpperCase();//运营商身份证
	    		u = u.replaceAll(" ","");
	    		iu = iu.replaceAll(" ","");
	    		p = p.replaceAll(" ","");
	    		if(userMap.get(u)==null){
	    			String err = "身份证号为："+u+"的会员在系统中不存在,excel中记录信息为:"+u+", "+iu+", "+p;
	    			errDesc.add(err);continue;
	    		}
	    		if(userMap.get(iu)==null){
	    			String err = "身份证号为："+iu+"的会员在系统中不存在,excel中记录信息为:"+u+", "+iu+", "+p;
	    			errDesc.add(err);continue;
	    		}
	    		if(userMap.get(p)==null){
	    			String err = "身份证号为："+p+"的会员在系统中不存在,excel中记录信息为:"+u+", "+iu+", "+p;
	    			errDesc.add(err);continue;
	    		}
	    		String uId = userMap.get(u).getId();
	    		String iuId = userMap.get(iu).getId();
	    		String pId = userMap.get(p).getId();
	    		if(nohasRRList.get(uId)==null&&hasRRList.get(uId)==null){
	    			String nohas = "身份证号为："+u+"的会员的邀约关系不存在，会员id为："+uId+", 程序结束后会自动添加邀约关系";
	    			//插入双方填报和ship
	    			RR rr00 = new RR(UUIDGenerator.getUUID(),meetingId,uId,iuId,pId,"01","1","",nowDate,"",nowDate,"程序导入数据","0");
	    			RR rr01 = new RR(UUIDGenerator.getUUID(),meetingId,uId,iuId,pId,"03","1","",nowDate,"",nowDate,"程序导入数据","0");
	    			rrlist2.add(rr00);
	    			rrlist2.add(rr01);
	    			RRShip rr03 = new RRShip(UUIDGenerator.getUUID(),meetingId,uId,iuId,pId,"",nowDate,"",nowDate,"程序导入数据","0");
	    			rrlist3.add(rr03);
	    			nohasDesc.add(nohas);continue;
	    		}
	    		//已经完全匹配的直接跳过
	    		if(hasRRList.get(uId)!=null){
	    			matchcount++;
	    			continue;
	    		}
	    		/**
	    		 * 报名中填报方和会员支持中心关系一致的，新增另一方的邀约关系记录。并维护到邀约关系表中
	    		 */
	    		//没有完全匹配的，报名中自动填报的，或者积极的会员自行填报的数据
	    		RR noHasRR = nohasRRList.get(uId);
	    		
    			RR rr1 = new RR();
	    		rr1.setId(noHasRR.getId());
	    		rr1.setUser_id(uId);
	    		rr1.setInviter_id(iuId);
	    		rr1.setOperation_partner_id(pId);
	    		rr1.setMatch_success_flag("1");
	    		rr1.setRemarks("确认后修改");
	    		rrlist1.add(rr1);
	    		
    			String fp = noHasRR.getFill_party()=="03"?"01":"03";
    			RR rr2 = new RR(UUIDGenerator.getUUID(),meetingId,uId,iuId,pId,fp,"1","",nowDate,"",nowDate,"程序导入数据","0");
	    		rrlist2.add(rr2);
	    		
	    		/**
	    		 * 邀约关系表
	    		 */
	    		RRShip rr3 = new RRShip(UUIDGenerator.getUUID(),meetingId,uId,iuId,pId,"",nowDate,"",nowDate,"程序导入数据","0");
	    		rrlist3.add(rr3);
			}
			r1 = sysDao.updateRR(rrlist1);
			r2 = sysDao.insertRR(rrlist2);
			r3 = sysDao.insertRRShip(rrlist3);
			System.out.println("r1:"+r1+", r2:"+r2+", r3:"+r3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long e = System.currentTimeMillis();
		System.out.println("=========不存在的邀约关系条数:"+nohasDesc.size()+"和详情:=========");
		for(String hasMsg:nohasDesc){
			System.out.println(hasMsg);
		}
		System.out.println("=========数据错误的邀约关系条数:"+errDesc.size()+"和详情:=========");
		for(String errMsg:errDesc){
			System.out.println(errMsg);
		}
		System.out.println("需要更新的记录："+errcount+"条, 实际更新的记录："+r1+", 完全匹配过的记录："+matchcount+"条");
		System.out.println("总共耗时:"+(e-s)/1000+"s,处理记录："+totalcount+"条,需要更新的记录："+errcount+"条");
	}

	@Override
	public Map<String, User> queryAllList() {
		return sysDao.selectAllList();
	}
}
