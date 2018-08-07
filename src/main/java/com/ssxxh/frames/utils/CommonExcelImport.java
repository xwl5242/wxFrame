package com.ssxxh.frames.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONArray;

/**
 * 导入excel
 * @author xwl
 *
 */
public class CommonExcelImport {

	private final static String excel2003L =".xls";    //2003- 版本的excel  
    private final static String excel2007U =".xlsx";   //2007+ 版本的excel  
      
    /** 
     * 描述：获取IO流中的数据，组装成List<List<Object>>对象 
     * @param in,fileName 
     * @return 
     * @throws IOException  
     */  
    public static  List<List<Object>> getBankListByExcel(InputStream in,String fileName) throws Exception{  
        List<List<Object>> list = null;  
          
        //创建Excel工作薄  
        Workbook work = getWorkbook(in,fileName);  
        if(null == work){  
            throw new Exception("创建Excel工作薄为空！");  
        }  
        Sheet sheet = null;  
        Row row = null;  
        Cell cell = null;  
          
        list = new ArrayList<List<Object>>();  
        //遍历Excel中所有的sheet  
        for (int i = 0; i < work.getNumberOfSheets(); i++) {  
            sheet = work.getSheetAt(i);  
            if(sheet==null){continue;}  
              
            //遍历当前sheet中的所有行,默认第一行为标题行，跳过
            for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {  
                row = sheet.getRow(j);  
                if(row==null||row.getFirstCellNum()==j){list.add(new ArrayList<Object>());continue;}  
                  
                //遍历所有的列  
                List<Object> li = new ArrayList<Object>();  
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {  
                    cell = row.getCell(y);  
                    li.add(getCellValue(cell));  
                }  
                list.add(li);  
            }  
        }  
        work.close();  
        return list;  
    }  
      
    /** 
     * 描述：根据文件后缀，自适应上传文件的版本  
     * @param inStr,fileName 
     * @return 
     * @throws Exception 
     */  
    public static Workbook getWorkbook(InputStream inStr,String fileName) throws Exception{  
        Workbook wb = null;  
        String fileType = fileName.substring(fileName.lastIndexOf("."));  
        if(excel2003L.equals(fileType)){  
            wb = new HSSFWorkbook(inStr);  //2003-  
        }else if(excel2007U.equals(fileType)){  
            wb = new XSSFWorkbook(inStr);  //2007+  
        }else{  
            throw new Exception("解析的文件格式有误！");  
        }  
        return wb;  
    }  
  
    /** 
     * 描述：对表格中数值进行格式化 
     * @param cell 
     * @return 
     */  
    public static Object getCellValue(Cell cell){  
        Object value = null;  
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符  
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化  
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字  
        if(null==cell) return "";
        switch (cell.getCellType()) {  
        case Cell.CELL_TYPE_STRING:  
            value = cell.getRichStringCellValue().getString();  
            break;  
        case Cell.CELL_TYPE_NUMERIC:  
            if("General".equals(cell.getCellStyle().getDataFormatString())){  
                value = df.format(cell.getNumericCellValue());  
            }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){  
                value = sdf.format(cell.getDateCellValue());  
            }else{  
                value = df2.format(cell.getNumericCellValue());  
            }  
            break;  
        case Cell.CELL_TYPE_BOOLEAN:  
            value = cell.getBooleanCellValue();  
            break;  
        case Cell.CELL_TYPE_BLANK:  
            value = "";  
            break;  
        default:  
            break;  
        }  
        return value;  
    }  
    
    public static void main(String[] args) {
    	try{
//    		ssxxhRR();
//    		bshCourseCID();
    		gbkInitMember();
//    		for(int i=1;i<100;i++){
//    			String no = String.format("%07d", i);
//    			System.out.println(no);
//    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    /**
     * 耕本客初始化会员信息
     * @throws Exception
     */
    public static void gbkInitMember() throws Exception{
    	File file = new File("c:\\1.xlsx");
    	FileInputStream fin = new FileInputStream(file);
    	List<List<Object>> list = getBankListByExcel(fin,file.getName());
    	int i=5;
    	for(List<Object> li:list){
    		String no = li.get(0).toString();//会员编号
    		String name = li.get(1).toString();//姓名
    		String signdate = li.get(3).toString();//登记日期
    		String startdate = li.get(4).toString();//开卡日期
    		String enddate = li.get(5).toString();//有效期
    		String phone = li.get(6).toString();//电话
    		String remark = li.get(7).toString();//备注
    		String userId = UUIDGenerator.getUUID();
    		String userprodId = UUIDGenerator.getUUID();
    		String user_create_date = "2018-04-18 10:00:00";
    		String paymentId = UUIDGenerator.getUUID();
    		String ei_receipt_id = UUIDGenerator.getUUID();
    		String receipt_code = "V"+String.format("%07d", i);
    		//会员表sql
    		String usql = "insert into sys_user(id,no,name,phone,mobile,login_flag,level,user_type,member_del_flag,create_date,remarks,del_flag,low_quota,high_quota,high_init_quota,clc_quota,clc_init_quota) "
    				+ "values('"+userId+"','"+no+"','"+name+"','"+phone+"','"+phone+"','1','03','8','0',str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),'"+remark+"','0','0','0','0','0','0');";
    		//会员产品关系表（激活表）sql
    		String upsql = "insert into ms_user_prod(id,user_id,prod_id,validity_start_date,validity_end_date,create_date,remarks,del_flag) "
    				+ "values('"+userprodId+"','"+userId+"','3',str_to_date('"+startdate+"','%Y-%m-%d %H:%i:%s'),str_to_date('"+enddate+"','%Y-%m-%d %H:%i:%s'),str_to_date('"+signdate+"','%Y-%m-%d %H:%i:%s'),'批量导入的数据,create_date字段为会员卡登记日期','0');";
    		//账款支付
    		String paymentsql = "insert into ei_payment(id,meeting_type,user_id,payment_standard_version,pay_type,should_pay_amount,payment_amount,money_property,sign_date,service_process_date,receipt_flag,end_status,end_date,create_date,remarks,del_flag,payment_amount_total,abolish_flag) "
    				+ "values('"+paymentId+"','03','"+userId+"','44d8500930fb45d6bf40d699c12f2011','01','599','599','03',str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),'1','1',str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),'初始化会员的账款支付信息，为了能够正常签到','0','599','0');";
    		//收据
    		String receiptsql = "insert into ei_receipt(id,user_id,payment_id,code,payment_type,amount_uppercase,amount_lowercase,handling_date,fill_party,open_flag,approval_flag,abolish_flag,sign_in_flag,create_date,remarks,del_flag,meeting_type,finance_open_flag) "
    				+ "values('"+ei_receipt_id+"','"+userId+"','"+paymentId+"','"+receipt_code+"','年卡','伍玖玖零','599.0',str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),'0','1','3','0','0',str_to_date('"+user_create_date+"','%Y-%m-%d %H:%i:%s'),'初始化会员的收据信息，模拟的收据没有真正的pdf文件，为了能够正常签到','0','03','1');";
    		//打印
    		System.out.println(usql);
    		System.out.println(upsql);
    		System.out.println(paymentsql);
    		System.out.println(receiptsql);
    		i++;
    	}
    }
    
    /**
     * 业务一体化系统邀约关系的维护
     * @throws Exception
     */
    public static void ssxxhRR() throws Exception{
    	File file = new File("c:\\35确认.xlsx");
    	FileInputStream fin = new FileInputStream(file);
    	List<List<Object>> list = getBankListByExcel(fin,file.getName());
    	for(List<Object> li:list){
    		String u = li.get(0).toString();
    		String iu = li.get(1).toString();
    		String p = li.get(2).toString();
    		String usql = "INSERT INTO rr_invitation(id,meeting_id,user_id,inviter_id,operation_partner_id,fill_party,match_success_flag,create_by,create_date,update_by,update_date,remarks,del_flag) VALUES "
    				+ "('"+UUIDGenerator.getUUID()+"', '4247983c2db64077bbd90d629846a6f0', '"+u+"', '"+iu+"', '"+p+"', '01', '1', '', str_to_date('2017-12-14 15:48:25','%Y-%m-%d %H:%i:%s'), '', str_to_date('2017-12-14 15:48:25','%Y-%m-%d %H:%i:%s'), '', '0');";
    		String iusql = "INSERT INTO rr_invitation(id,meeting_id,user_id,inviter_id,operation_partner_id,fill_party,match_success_flag,create_by,create_date,update_by,update_date,remarks,del_flag) VALUES "
    				+ "('"+UUIDGenerator.getUUID()+"', '4247983c2db64077bbd90d629846a6f0', '"+u+"', '"+iu+"', '"+p+"', '03', '1', '', str_to_date('2017-12-14 15:48:25','%Y-%m-%d %H:%i:%s'), '', str_to_date('2017-12-14 15:48:25','%Y-%m-%d %H:%i:%s'), '', '0');";
    		String rr = "INSERT INTO rr_invitation_relationship(id,meeting_id,user_id,inviter_id,operation_partner_id,create_by,create_date,update_by,update_date,remarks,del_flag) VALUES "
    				+ "('"+UUIDGenerator.getUUID()+"', '4247983c2db64077bbd90d629846a6f0', '"+u+"', '"+iu+"', '"+p+"', '', str_to_date('2017-12-14 15:48:25','%Y-%m-%d %H:%i:%s'), '', str_to_date('2017-12-14 15:48:25','%Y-%m-%d %H:%i:%s'), '', '0');";
    		System.out.println(usql);
    		System.out.println(iusql);
    		System.out.println("/*------------------------------------------------------------------*/");
    		System.out.println(rr);
    	}
    }
    
    /**
     * 博思会上课人员备注信息中抽取身份证号
     * @throws Exception
     */
    public static void bshCourseCID() throws Exception{
    	File file = new File("c:\\1.xlsx");
    	FileInputStream fin = new FileInputStream(file);
    	List<List<Object>> list = getBankListByExcel(fin,file.getName());
    	for(List<Object> li:list){
    		if(li.size()==0){
    			System.out.println("empty");continue;
    		}
    		JSONArray ja = JSONArray.parseArray(li.get(0).toString());
    		List<String> cidList = new ArrayList<String>();
    		if(ja.size()==0){
    			System.out.println("error");
    		}else{
    			for(int i=0;i<ja.size();i++){
    				Object o = ja.get(i);
    				byte[] rByte = ObjectMapperHelper.om.writeValueAsBytes(o);
    				A a = (A) ObjectMapperHelper.om.readValue(rByte, A.class);
    				List<B> blist = a.getContent();
    				if(null!=blist){
    					for(B b:blist){
    						if("本人身份证".equals(b.getKey())&&b.getValue().length()==18) cidList.add(b.getValue());
    					}
    				}
    			}
    			if(cidList.size()==0){
    				System.out.println("error");
    			}else{
    				String cid = cidList.get(cidList.size()-1);
    				System.out.println(cid);
    			}
    		}
    	}
    }
}
