package com.kongzhu.demo.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/FileDownloadServlet")
public class FileDownloadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码和响应的内容类型
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        String dirName = "/Users/caiwenlong/Documents/Eclipse/workspace/digitalweb/WebRoot/images";
        String fileName = "boy副本.jpg";
        
        // 文件以附件形式在客户端浏览器下载
        resp.setHeader("Content-Disposition", "attachment; filename="
                // 如果文件名包含中文，则必须转码
                + new String(fileName.getBytes(), "ISO-8859-1"));
        
        // 读写
        InputStream in = new FileInputStream(dirName + "/" + fileName);
        OutputStream out = resp.getOutputStream();
        int b = -1;
        while ((b = in.read()) > -1) {
            out.write(b);
        }
        in.close();
        out.close();
    }

}
