package me.ranjit.servlet.updown;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static me.ranjit.servlet.util.Constants.UPDOWN_FILE_DIRECTORY;

/**
 * Created by suzh on 7/31/2017.
 */
@Log4j2
@WebServlet("/downfile")
public class DownloadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取文件名
        String filename = request.getParameter("name");

        //防止读取name名乱码
        filename = new String(filename.getBytes("iso-8859-1"), "utf-8");
        log.info("文件名：" + filename);

        //获取要下载的文件绝对路径
        String fullFileName = request.getServletContext().getRealPath("./") +
                File.separator + UPDOWN_FILE_DIRECTORY + File.separator +filename;
        if (!(new File(fullFileName).exists())) {
            // 设置响应内容类型
            response.setContentType("text/html;charset=UTF-8");//控制浏览器的行为，即控制浏览器用UTF-8进行解码；
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write("文件不存在");
            pw.close();
            return;
        }

        //设置文件MIME类型
        String contentType = getServletContext().getMimeType(filename);
        log.info("content type:" + contentType);
        response.setContentType(contentType);

        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);

        //输入流为项目文件，输出流指向浏览器
        InputStream is = new FileInputStream(fullFileName);
        ServletOutputStream os = response.getOutputStream();

        /*
         * 设置缓冲区
         * is.read(b)当文件读完时返回-1
         */
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = is.read(b)) != -1) {
            os.write(b, 0, len);
        }
        //关闭流
        is.close();
        os.close();
    }
}
