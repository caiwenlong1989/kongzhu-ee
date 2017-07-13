package com.kongzhu.demo.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/BatchFileDownloadServlet")
public class BatchFileDownloadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码和响应的内容类型
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        // 文件以附件形式在客户端浏览器下载
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String zipName = sdf.format(new Date()) + ".zip";
		resp.setHeader("Content-Disposition", "attachment; filename="
                // 如果文件名包含中文，则必须转码
                + new String(zipName .getBytes(), "ISO-8859-1"));
        
        // 读写
		String dirName = "C:/upload/20170712";
		String[] fileNames = req.getParameterValues("fileName");
        ZipOutputStream out = new ZipOutputStream(resp.getOutputStream());
        for (String fileName : fileNames) {
        	ZipEntry e = new ZipEntry(fileName);
			out.putNextEntry(e);
			InputStream in = new FileInputStream(dirName + "/" + fileName);
			int b = -1;
			while ((b = in.read()) > -1) {
				out.write(b);
			}
			in.close();
			
			out.closeEntry();
        }
        out.close();
    }

}
