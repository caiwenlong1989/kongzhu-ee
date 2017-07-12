package com.kongzhu.demo.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@WebServlet("/FileUploadServlet")
// 添加该注释，提供对上传文件的支持
// maxFileSize设置上传的文件最大值，5M
@MultipartConfig(maxFileSize=5242880)
public class FileUploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = resp.getWriter();
        
        // 获取form表单中的文件域
        Part part = req.getPart("headImg");
        
        // 获取上传文件的名称
        String disposition = part.getHeader("Content-Disposition");
        String srcFileName = disposition.substring(
                disposition.indexOf("filename") + 10, disposition.lastIndexOf("\""));
        
        // 检查上传文件类型
        String extension = srcFileName.substring(srcFileName.indexOf("."));
        List<String> extensions = new ArrayList<>(Arrays.asList(".jpg", ".jpeg", ".png"));
        if (!extensions.contains(extension)) {
            out.write("上传文件类型错误！目前仅支持JPG、JPEG、PNG。");
            return;
        }
        
        // 设置存放上传文件的文件夹和名称
        SimpleDateFormat dirFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat fileFormat = new SimpleDateFormat("HHmmssS");
        Date now = new Date();
        String destDirName = "/Users/caiwenlong/Documents/" + dirFormat.format(now);
        
        // 如果文件夹不存在，需要手动创建
        File dir = new File(destDirName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String destFileName = destDirName + "/" + fileFormat.format(now) + extension;
        
        // 将上传文件上传到指定位置
        part.write(destFileName);
        
        // 设置响应信息
        out.write("<br>文件上传成功！");
        out.write("<br>上传文件名称：" + srcFileName);
        out.write("<br>上传文件类型：" + part.getContentType());
        out.write("<br>上传文件大小：" + part.getSize() + " 字节");
    }

}
