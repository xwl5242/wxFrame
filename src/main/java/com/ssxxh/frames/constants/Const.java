package com.ssxxh.frames.constants;

/**
 * 系统相关常量
 * @author xwl
 *
 */
public class Const {
	
	/**
	 * session相关
	 */
	public static final String SESSION_USER_ID = "userId";
	public static final String SESSION_USER = "user";
	public static final String SESSION_USER_NAME = "userName";
	
	/**
	 * jdbc配置文件相关
	 */
	public static final String JDBC_PROPERTIES_NAME = "jdbc.properties";//jdbc配置文件名称
	public static final String APPLICATION_PROPERTIES_NAME = "application.properties";//应用配置文件名称
	public static final String WX_CONST_PROPERTY_NAME = "wxConst.property";//微信配置文件名称
	public static final String APPLICATION_IGNORABLE_CONFIG = "ignorableConfig";//十分重要的字符串，参与密码加密
	public static final String JDBC_DATABASE_NAME = "jdbc.databaseName";//数据库名称，很重要，小心修改
	
	/**
	 * 日期时间格式相关
	 */
	public static final String DATE_YYYYMMDDHHMMSS_STR = "yyyy-MM-dd HH:mm:ss";//日期时间格式
	public static final String DATE_YYYYMMDD_STR = "yyyy-MM-dd";//日期格式
	public static final String DATE_HHMMSS_STR = "HH:mm:ss";//时间格式
	
	/**
	 * 返回响应体相关
	 */
	public static final String RESPONSE_CODE="code";//response code
	public static final String RESPONSE_SUCCESS="success";//响应成功
	public static final String RESPONSE_FAIL="fail";//响应失败
	public static final String RESPONSE_EXCEPTION="exception";//响应异常
	public static final String RESPONSE_MSG="msg";//response msg

	/**
	 * 操作日志相关
	 */
	public static final String IS_OPT_LOG="isOptLog";//是否开启日志标志
	public static final String OPLOG_TYPE_INSERT="0";//新增类型
	public static final String OPLOG_TYPE_DELETE="1";//删除类型
	public static final String OPLOG_TYPE_UPDATE="2";//修改类型
	public static final String OPLOG_TYPE_SELECT="3";//查询类型
	
	/**
	 * 反射方法类型相关
	 */
	public static final String REFLECT_METHODTYPE_GET="get";
	public static final String REFLECT_METHODTYPE_SET="set";
	
}
