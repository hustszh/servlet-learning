package me.ranjit.servlet.email.httpformat;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import static me.ranjit.servlet.email.utils.Constants.*;
import static me.ranjit.servlet.email.utils.Constants.EMAIL_PASSWORD;

/**
 * Created by suzh on 8/1/2017.
 * response.setContent() 方法来设置第二个参数为 "text/html" 的内容，该参数用来指定 HTML 内容是包含在消息中的。
 */
@WebServlet("/httpmail")
public class HttpFormatEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 收件人的电子邮件 ID
        String to = EMAIL_TO;

        // 发件人的电子邮件 ID
        String from = EMAIL_FROM;

        // 发送主机
        String host = EMAIL_HOST;

        // 获取系统的属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth","true");

        // 获取默认的 Session 对象
        Session session = Session.getDefaultInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USER, EMAIL_PASSWORD);
            }});

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try{
            // 创建一个默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);
            // 设置 From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // 设置 To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            // 设置 Subject: header field
            message.setSubject("This is the Subject Line!");

            // 设置实际的 HTML 消息，内容大小不限
            message.setContent("<h1>This is actual message</h1>",
                    "text/html" );
            // 发送消息
            Transport.send(message);
            String title = "发送电子邮件";
            String res = "成功发送消息...";
            String docType = "<!DOCTYPE html> \n";
            out.println(docType +
                    "<html>\n" +
                    "<head><title>" + title + "</title></head>\n" +
                    "<body bgcolor=\"#f0f0f0\">\n" +
                    "<h1 align=\"center\">" + title + "</h1>\n" +
                    "<p align=\"center\">" + res + "</p>\n" +
                    "</body></html>");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
