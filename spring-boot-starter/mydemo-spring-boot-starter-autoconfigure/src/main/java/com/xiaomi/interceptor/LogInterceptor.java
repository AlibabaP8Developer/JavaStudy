package com.xiaomi.interceptor;

import com.xiaomi.annotation.Log;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义日志拦截器
 */
public class LogInterceptor extends HandlerInterceptorAdapter {
    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    /**
     * 在controller方法执行前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获得被拦截的方法对象
        Method method = handlerMethod.getMethod();
        // 获得方法上的注解
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 当前拦截的方法加入了log注解
            long startTime = System.currentTimeMillis();
            startTimeThreadLocal.set(startTime);
        }
        return true;
    }

    /**
     * 在controller方法执行后执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 获得被拦截的方法对象
        Method method = handlerMethod.getMethod();
        // 获得方法上的注解
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 当前拦截的方法加入了log注解
            Long startTime = startTimeThreadLocal.get();
            long endTime = System.currentTimeMillis();
            // controller方法执行时间
            long optTime = endTime - startTime;

            String requestURI = request.getRequestURI();
            String methodName = method.getDeclaringClass().getName() + "." + method.getName();
            String value = log.value();
            System.out.println("请求uri：" + requestURI);
            System.out.println("请求方法名：" + methodName);
            System.out.println("方法描述：" + value);
            System.out.println("方法执行时间：" + optTime + "ms");
        }

        super.postHandle(request, response, handler, modelAndView);
    }
}
