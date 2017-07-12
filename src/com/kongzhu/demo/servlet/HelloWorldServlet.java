package com.kongzhu.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
// 设置请求地址，"/"必须有
@WebServlet("/HelloWorldServlet")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码和响应的内容类型
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        // 设置响应内容
        PrintWriter out = resp.getWriter();
        out.write("<html>");
        out.write("<head>");
        out.write("<title>HelloWorldServlet</title>");
        out.write("</head>");
        out.write("<body>");
        out.write("<h1>请求方式：GET</h1>");
        out.write("<h1>请求信息：暂无</h1>");
        out.write("<h1>响应信息：你看到的都是！</h1>");
        out.write("</body>");
        out.write("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求体的字符编码和响应的内容类型
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        
        // 设置响应内容
        PrintWriter out = resp.getWriter();
        out.write("<html>");
        out.write("<head>");
        out.write("<title>HelloWorldServlet</title>");
        out.write("</head>");
        out.write("<body>");
        out.write("<h1>请求方式：POST</h1>");
        out.write("<h1>请求信息：暂无</h1>");
        out.write("<h1>响应信息：你看到的都是！</h1>");
        out.write("</body>");
        out.write("</html>");
    }

    /**
     * 接受标准的HTTP请求，并将该HTTP请求分发到具体doMethod方法
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求方式
        String method = req.getMethod();
        
        // 根据请求方式调用相应的请求处理程序
        if (method.equals("GET")) {
            doGet(req, resp);
        } else if (method.equals("POST")) {
            doPost(req, resp);
        } else {
            resp.sendError(501, "不支持的请求方式！");
        }
    }

    /**
     * 将客户端请求分发到service方法
     */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        // 该方法仅将请求参数和响应参数强转为HTTP请求参数和HTTP响应参数，不做其他处理
        HttpServletRequest  request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 调用重载（Overload）的service方法
        service(request, response);
    }

}
