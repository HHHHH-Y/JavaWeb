package com.huyue;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: HHH.Y
 * Date: 2020-07-08
 */
public class ReqServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printInfo(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printInfo(req, resp);
    }

    private void printInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain; charset = utf-8");
        PrintWriter writer = resp.getWriter();

        // 获得 请求行 信息
        // 方法(Method)
        writer.println("请求的方式是: " + req.getMethod());
        // 路径(URL or Path)
        writer.println("URL 中的完整路径: " + req.getRequestURI());
        writer.println("URL 中的 Context 路径: " + req.getContextPath());
        writer.println("URL 中的 web.xml 中的路径: " + req.getServletPath());
        // 获取所有的 Query String 部分
        writer.println("完整的 Query String 部分: " + req.getQueryString());
        // 根据 Query String 中的 key 获取对应的 value
        writer.println("Query String 中 name 为 key1 的值: " + req.getParameter("key1"));
        writer.println("Query String 中 name 为 key2 的值: " + req.getParameter("key2"));

        writer.println("Query String 中有如下 name: ");
        // Enumeration 是一个用来做遍历的对象, 是一个迭代器
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) { // 如果还有更多的元素, 就一直循环
            String name = parameterNames.nextElement(); // 挨个进行遍历, 取其 name
            writer.println(name);
        }

        // 获取协议版本信息
        writer.println("请求协议版本: " + req.getProtocol());


        // 获取 请求头 信息 (请求头是多组 key - value 键值对组成的)
        // 1. 根据 key 获取对应的 value 值
        // User-Agent 就是告诉你, 这个 http 请求是从哪里发送的
        writer.println("请求头: User-Agent = " + req.getHeader("User-Agent"));
        // req.getIntHeader("key"); 相当于 Integer.parseInt(req.getHeader("key")), 将得到的请求头信息转换成 int 类型
        // 获取所有的 请求头 信息
        writer.println("所有的请求头信息如下: ");
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement(); // 挨个进行遍历, 获取到对应的 name(key)
            String value = req.getHeader(name); // 根据 key, 得到对应的 value 值
            writer.println("name = " + value);
        }


        // 如果有请求体, 获取请求体
        // 重要信息: 请求体的格式, HTTP协议并没有完全规定死
        ServletInputStream inputStream = req.getInputStream(); // 拿到了一个输入流, 通过这个输入流, 就可以读取请求体中的内容
        // 通过 form 表单提交请求, 怎样获取到请求体?
        // 如果是在 form 表单中提交, 并且请求的 Content -Type 是 application/x-www-form-urlencoded, 通过 req.getParameter 获取
        // 也是现阶段经常使用的方法
        String username = req.getParameter("username");
        writer.println("用户输入的 username 是: " + username);
        String password = req.getParameter("password");
        writer.println("用户输入的 password 是: " + password);
    }
}
