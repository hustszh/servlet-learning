package me.ranjit.servlet.locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by suzh on 8/1/2017.
 * Servlet 可以输出以西欧语言（如英语、西班牙语、德语、法语、意大利语、荷兰语等）编写的页面。
 * 为了能正确显示所有的字符，设置 Content-Language 头是非常重要的。
 * 第二点是使用 HTML 实体显示所有的特殊字符，例如，"&#241;" 表示 "ñ"，"&#161;" 表示 "¡"，
 */
@WebServlet("/lanlocale")
public class LanguageLocaleServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 设置西班牙语言代码
        response.setHeader("Content-Language", "es");

        String title = "En Espa&ntilde;ol";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1>" + "En Espa&ntilde;ol:" + "</h1>\n" +
                "<h1>" + "&iexcl;Hola Mundo!" + "</h1>\n" +
                "</body></html>");
    }
}
