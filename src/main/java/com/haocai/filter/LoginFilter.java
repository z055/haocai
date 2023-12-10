package com.haocai.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 拦截器
 * @return:
 * @author: 18380
 * @time: 2023/12/10 19:03
 */

public class LoginFilter implements HandlerInterceptor {

    // 在请求处理之前进行拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 检查用户是否已经登录（通过判断 Session 中是否存在 user 属性）
        if (request.getSession().getAttribute("user") != null) {
            return true;
        }
        // 用户未登录，记录拦截的地址并重定向到登录页
        System.err.println("拦截的地址" + request.getRequestURI());
        response.sendRedirect("/");
        return false;
    }

    // 请求处理之后，视图渲染之前调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 这个方法在目标方法执行成功并且没有抛出异常时调用，可以在这里进行一些额外的处理
    }

    // 在整个请求完成后调用，主要用于清理资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 这个方法在整个请求完成后调用，可以用于一些资源清理工作
    }

}
