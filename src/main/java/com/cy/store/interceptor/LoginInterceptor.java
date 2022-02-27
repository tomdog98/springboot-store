package com.cy.store.interceptor;

import org.omg.PortableInterceptor.Interceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**定义一个拦截器*/
public class LoginInterceptor implements HandlerInterceptor {
    //在调用所有处理请求的方法之前被自动调用执行的方法
    //检测全局session对象中是否有uid数据，如果有则放行，没有则重定向到登录
    //如果返回值为true表示放行当前请求，返回false表示拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj == null){//说明没有等录过系统，重定向到login.html页面
            response.sendRedirect("/web/login.html");
            //结束后续的调用
            return false;
        }
        //请求放行
        return true;
    }

}
