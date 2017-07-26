package me.ranjit.servlet.form;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by suzh on 7/13/2017.
 */

/**
 * @WebServlet，使用注解方式来实现servlet和url的映射
 *
 * web项目里，web.xml中需要配置url和servlet之间的映射，这是使用xml方式实现。
 *
 * Servlet 3.0 的部署描述文件 web.xml 的顶层标签 <web-app> 有一个 metadata-complete 属性，
 * 该属性指定当前的部署描述文件是否是完全的。
 * 如果设置为 true，则容器在部署时将只依赖部署描述文件，
 * 忽略所有的注解（同时也会跳过 web-fragment.xml 的扫描，亦即禁用可插性支持，）；
 * 如果不配置该属性，或者将其设置为 false，则表示启用注解支持（和可插性支持）。
 */
@WebServlet("/doo")//需要在web.xml中将“metadata-complete”设置为false来启用注解支持
@Log4j2
public class HelloFormServlet extends HttpServlet{

    public HelloFormServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter pw = response.getWriter();
        String title = "使用 GET 方法读取表单数据";
        // 处理中文
        //get方式提交的参数编码，只支持iso8859-1编码。
        //页面编码是utf-8，后面就是utf-8，如果是gbk，那后面就改成gbk。
        String name = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");

        String content = "<!DOCTYPE html> \n" +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>站点名</b>："
                + name + "\n" +
                "  <li><b>网址</b>："
                + request.getParameter("url") + "\n" +
                "</ul>\n" +
                "</body></html>";
        pw.println(content);
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
