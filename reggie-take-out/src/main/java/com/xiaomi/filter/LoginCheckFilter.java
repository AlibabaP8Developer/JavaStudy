package com.xiaomi.filter;

import com.alibaba.fastjson.JSON;
import com.xiaomi.common.BaseContext;
import com.xiaomi.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 检查用户是否已经登陆
 *
 * @author xiaomi
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1.获取本次请求的uri
        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}", requestURI);

        // 不需要处理的请求路径
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login"
        };

        // 2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        // 3.如果不需要处理，则直接放行
        if (check) {
            // true 不需要处理，直接放行
            log.info("本次请求不需要处理：{}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        // 4.1判断登陆状态，如果已登陆，则直接放行
        Long employee = (Long) request.getSession().getAttribute("employee");
        if (employee != null) {
            // 已经登陆直接放行
            log.info("用户已登陆，用户ID为：{}", employee);
            long id = Thread.currentThread().getId();
            log.info("线程ID为：{}", id);

            // 设置用户ID
            BaseContext.setCurrentId(employee);

            filterChain.doFilter(request, response);
            return;
        }

        // 4.2判断登陆状态，如果已登陆，则直接放行
        Long user = (Long) request.getSession().getAttribute("user");
        if (user != null) {
            // 已经登陆直接放行
            log.info("用户已登陆，用户ID为：{}", user);
            long id = Thread.currentThread().getId();
            log.info("线程ID为：{}", id);

            // 设置用户ID
            BaseContext.setCurrentId(user);

            filterChain.doFilter(request, response);
            return;
        }

        log.info("用户未登陆！");

        // 5.如果未登陆则返回未登陆结果，通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配：检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                // true
                return true;
            }
        }
        return false;
    }
}
