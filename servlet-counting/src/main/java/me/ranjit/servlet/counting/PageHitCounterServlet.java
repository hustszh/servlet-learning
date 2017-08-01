package me.ranjit.servlet.counting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by suzh on 8/1/2017.
 * 网页点击计数器
 * 以下是实现一个简单的基于 Servlet 生命周期的网页点击计数器需要采取的步骤：
 * 1.在 init() 方法中初始化一个全局变量。
 * 2.每次调用 doGet() 或 doPost() 方法时，都增加全局变量。
 * 3.如果需要，可以在 destroy() 中使用一个数据库表来存储全局变量的值。在下次初始化 Servlet 时，该值可在 init() 方法内被读取。这一步是可选的。
 * 4.如果只想对一个 session 会话计数一次页面点击，那么请使用 isNew() 方法来检查该 session 会话是否已点击过相同页面。这一步是可选的。
 * 5.可以通过显示全局计数器的值，来在网站上展示页面的总点击量。这一步是可选的。
 *
 * 如果需要计算整个网站的总点击量。在 Servlet 中，这也是非常简单的，可以使用“过滤器”做到这一点。
 */
@WebServlet("/phitc")
public class PageHitCounterServlet extends HttpServlet {

    private long hitCount = 0L;

    @Override
    public void init() throws ServletException {
        // 重置点击计数器
        hitCount = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        // 增加 hitCount
        hitCount++;

        PrintWriter out = response.getWriter();
        String title = "总点击量";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">" + hitCount + "</h2>\n" +
                "</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        // 如果需要，可以把 hitCount 的值写入到数据库
    }
}
