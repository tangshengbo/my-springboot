package com.tangshengbo.controller;

import com.tangshengbo.model.ApiResult;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by Tangshengbo on 2018/9/30
 */
@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ApiResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username:" + username + ", password:" + password);
        User user = userService.findByName(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                attributes.getRequest().getSession().setAttribute("user", user); //将登陆用户信息存入到session域对象中
                return new ApiResult(true, user.getUsername());
            }
        }
        return new ApiResult(false, "登录失败");
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        attributes.getRequest().getSession().removeAttribute("user");
        return "home/login";
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public ApiResult register(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            userService.create(new User(username, password));
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            attributes.getRequest().getSession().setAttribute("user", new User(username, password)); //将登陆用户信息存入到session域对象中
            return new ApiResult(true, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResult(false, "发生未知错误");
    }

//    /**
//     * 登录页
//     *
//     * @return
//     */
//    @GetMapping("/login")
//    public String login() {
//        return "home/login";
//    }

//    /**
//     * 注册页面
//     *
//     * @return
//     */
//    @GetMapping("/register")
//    public String register() {
//        return "home/register";
//    }

    /**
     * 注册页面
     *
     * @return
     */
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

//    /**
//     * 注册页面
//     *
//     * @return
//     */
//    @GetMapping("/goods/list")
//    public String list() {
//        return "site/goods";
//    }
}
