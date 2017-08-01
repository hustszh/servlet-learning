package me.ranjit.servlet.email.attachment;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import static me.ranjit.servlet.email.utils.Constants.*;

/**
 * Created by suzh on 8/1/2017.
 */
@WebServlet("/attachmail")
public class AttachmentEmailServlet extends HttpServlet {

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

            // 创建消息部分
            BodyPart messageBodyPart = new MimeBodyPart();

            // 填写消息
            messageBodyPart.setText("This is message body");

            // 创建一个多部分消息
            Multipart multipart = new MimeMultipart();

            // 设置文本消息部分
            multipart.addBodyPart(messageBodyPart);

            // 第二部分是附件
            messageBodyPart = new MimeBodyPart();
            String filename = ATTACHMENT_FILENAME;
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // 发送完整的消息部分
            message.setContent(multipart );

            // 发送消息
            Transport.send(message);
            String title = "发送电子邮件";
            String res = "成功发送电子邮件...";
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
