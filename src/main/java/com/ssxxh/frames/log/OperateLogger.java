package com.ssxxh.frames.log;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ssxxh.frames.common.Global;
import com.ssxxh.frames.constants.Const;
import com.ssxxh.frames.utils.UUIDGenerator;

@Aspect
public class OperateLogger {
	
//	@Autowired
//	private LogService logService;
	
	@Around("execution(* com.ssxxh.modules.*..*Controller.*(..))")
	public Object OperateLogger(ProceedingJoinPoint jp) throws Throwable{
		if(Global.isOpLog()){
			//获取插入点信息
			Signature sig = jp.getSignature();
			MethodSignature msig = null;
			if(!(sig instanceof MethodSignature)){
				throw new IllegalArgumentException("方法调用异常");
			}
			msig = (MethodSignature)sig;
			OperateLog log = new OperateLog();
			//获取操作的类名称
			String className = jp.getTarget().getClass().getName();
			//获取操作的方法名称
			Method method = msig.getMethod();
			String methodName = method.getName();
			//获取日志注解中的描述信息
			String optKey = method.getAnnotation(OpLog.class)==null?"":method.getAnnotation(OpLog.class).optKey();
			String optType = method.getAnnotation(OpLog.class)==null?"":method.getAnnotation(OpLog.class).optType();
			String optName = method.getAnnotation(OpLog.class)==null?"":method.getAnnotation(OpLog.class).optName();
			//没有加注解，不予以保存日志信息
			if(StringUtils.isBlank(optKey)&&StringUtils.isBlank(optType)&&StringUtils.isBlank(optName)){
				return jp.proceed();
			}
			//获取request中的信息
			Object[] params = jp.getArgs();
			HttpServletRequest request = null;
			for(Object param:params){
				if(param instanceof HttpServletRequest){
					request = (HttpServletRequest)param;break;
				}
			}
			if(request == null){
				request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
			}
			if(request!=null){
				String ip = getIpAddr(request);
				String host = request.getRemoteHost();
				String port = request.getRemotePort()+"";
				String uri = request.getRequestURI();
				String url = request.getRequestURL().toString();
				log.setRemoteIp(ip);
				log.setRemoteName(host);
				log.setRemotePort(port);
				log.setReqUri(uri);
				log.setReqUrl(url);
			}
			log.setId(UUIDGenerator.getUUID());
			log.setOptName(optName);
			log.setOptKey(optKey);
			log.setOptType(optType);
			log.setClassName(className);
			log.setMethodName(methodName);
			log.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			log.setCreator(request.getSession().getAttribute(Const.SESSION_USER_ID)==null
					?"":request.getSession().getAttribute(Const.SESSION_USER_ID).toString());
//			logService.saveOperateLog(log);
		}
		return jp.proceed();
	}
	
	/**
	 * 获取真实ip地址
	 * @param request
	 * @return
	 */
	private String getIpAddr(HttpServletRequest request){  
	    String ipAddress = request.getHeader("x-forwarded-for");  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	            ipAddress = request.getRemoteAddr();  
	            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
	                //根据网卡取本机配置的IP  
	                InetAddress inet=null;  
	                try {  
	                    inet = InetAddress.getLocalHost();  
	                } catch (UnknownHostException e) {  
	                    e.printStackTrace();  
	                }  
	                ipAddress= inet.getHostAddress();  
	            }  
	        }  
	        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
	            if(ipAddress.indexOf(",")>0){  
	                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
	            }  
	        }  
	        return ipAddress;   
	}  
}
