package me.ranjit.servlet.started;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by suzh on 7/12/2017.
 */
@Log4j2
public class HelloWorld extends HttpServlet {

    private String message;

    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        // 执行必需的初始化
        message = "您好, Nice To Meet You: " + System.currentTimeMillis();
        log.info("servlet初始化……");
        log.info("charset:{}", config.getInitParameter("character"));
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        log.info("request contentType:{}", request.getContentType());

        // 实际的逻辑是在这里
//        responseByOutputStream(response);
        responseByPrintWriter(request, response);

        //销毁servlet
        destroy();
    }

    private void responseByPrintWriter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");//控制浏览器的行为，即控制浏览器用UTF-8进行解码；
        response.setCharacterEncoding("UTF-8");//用于response.getWriter()输出的字符流的乱码问题，将response对象中的数据以UTF-8解码后发向浏览器
        log.info("response contentType:{}", response.getContentType());
        log.info("response characterEncoding:{}", response.getCharacterEncoding());

        PrintWriter pw = response.getWriter();
        pw.println(message);
        pw.write("<h1>" + message + "</h1>");
    }

    private void responseByOutputStream(HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");//控制浏览器的行为，即控制浏览器用UTF-8进行解码；
        log.info("response contentType:{}", response.getContentType());
        log.info("response characterEncoding:{}", response.getCharacterEncoding());

        OutputStream out = response.getOutputStream();
        out.write(message.getBytes());
    }

    public void destroy()
    {
        log.info("servlet销毁！");
        super.destroy();
    }
}
