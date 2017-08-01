package me.ranjit.servlet.locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by suzh on 8/1/2017.
 * 使用 java.text.DateFormat 类及其静态方法 getDateTimeInstance() 来格式化特定于区域设置的日期和时间。
 */
@WebServlet("/datlocale")
public class DateLocaleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获取客户端的区域设置
        Locale locale = request.getLocale( );
        String date = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.SHORT, locale).format(new Date());

        String title = "特定于区域设置的日期";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">date: " + date + "</h1>\n" +
                "</body></html>");
    }
}
