package com.tangshengbo.interceptor;

import com.alibaba.fastjson.JSON;
import com.tangshengbo.model.ApiResult;
import com.tangshengbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Tangshengbo on 2018/9/30
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            logger.info("-----------用户未登录-----------");
//            response.sendRedirect("/login"); //手动转发到/login映射路径
            // response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            PrintWriter out = response.getWriter();
            out.append(JSON.toJSONString(ApiResult.error("用户未登录")));
            out.flush();
            out.close();

            return false;
        }
        logger.info("-----------用户已登录-----------");
        return true;
    }
}
