package com.ssxxh.frames.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 下载
 * @author xwl
 *
 */
public class CommonDownload {
	
	public static void download(HttpServletResponse response,String path){
		try {
			Resource resource = new ClassPathResource(path);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(resource.getInputStream());
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(path, "UTF-8"));
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
