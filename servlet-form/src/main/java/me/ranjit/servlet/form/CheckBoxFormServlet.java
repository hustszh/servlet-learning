package me.ranjit.servlet.form;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by suzh on 7/25/2017.
 */
@WebServlet("/cbox")//需要在web.xml中将“metadata-complete”设置为false来启用注解支持
public class CheckBoxFormServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String title = "读取复选框数据";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>菜鸟按教程标识：</b>: "
                + request.getParameter("runoob") + "\n" +
                "  <li><b>Google 标识：</b>: "
                + request.getParameter("google") + "\n" +
                "  <li><b>淘宝标识：</b>: "
                + request.getParameter("taobao") + "\n" +
                "</ul>\n" +
                "</body></html>");
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
