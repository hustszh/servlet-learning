package me.ranjit.servlet.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by suzh on 7/27/2017.
 * Servlet 过滤器是可用于 Servlet 编程的 Java 类，可以实现以下目的：
   - 在客户端的请求访问后端资源之前，拦截这些请求。
   - 在服务器的响应发送回客户端之前，处理这些响应。
 */
@Log4j2
public class LogServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取初始化参数
        String site = filterConfig.getInitParameter("Site");

        // 输出初始化参数
        log.info("网站名称: " + site);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 输出站点名称
        log.info("站点网址：http://www.ranjit.me");

        // 把请求传回过滤链
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
    }
}
