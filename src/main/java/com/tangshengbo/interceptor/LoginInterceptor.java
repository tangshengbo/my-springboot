package com.tangshengbo.interceptor;

import com.tangshengbo.model.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Tangshengbo on 2018/9/30
 */
@Component
@Aspect
public class LoginInterceptor {

    @Pointcut("within (com.tangshengbo.controller..*) && !within(com.tangshengbo.controller.LoginController)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object trackInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        request.getSession().setAttribute("user", new User()); //测试，手动添加用户登录的session
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("-----------用户未登录-----------");
            attributes.getResponse().sendRedirect("/home/login"); //手动转发到/login映射路径
        }
        System.out.println("-----------用户已登录-----------");
        //一定要指定Object返回值，若AOP拦截的Controller return了一个视图地址，那么本来Controller应该跳转到这个视图地址的，但是被AOP拦截了，那么原来Controller仍会执行return，但是视图地址却找不到404了
        //切记一定要调用proceed()方法
        //proceed()：执行被通知的方法，如不调用将会阻止被通知的方法的调用，也就导致Controller中的return会404
        return joinPoint.proceed();
    }
}
