package me.ranjit.servlet.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by suzh on 8/1/2017.
 */
@WebServlet("/predirect")
public class PageRedirectServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        // 要重定向的新位置
        String site = new String("http://www.baidu.com");

        // 重定向：方法一
//        method1(response, site);

        // 重定向：方法二
        method2(response, site);
    }

    private void method2(HttpServletResponse response, String site) throws IOException {
        response.sendRedirect(site);
    }

    private void method1(HttpServletResponse response, String site) {
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
