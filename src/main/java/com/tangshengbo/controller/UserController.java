package com.tangshengbo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.AopLog;
import com.tangshengbo.core.PurgeJsonEscape;
import com.tangshengbo.model.ApiResult;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Tangshengbo on 2018/9/30
 */
@AopLog
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 查询所有
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PurgeJsonEscape
    @PostMapping("/add")
    public ApiResult addUser(@RequestBody User user) {
        logger.info("{}", user);
        return ApiResult.success("成功");
    }


    @GetMapping("/dateType")
    public ApiResult getDateType(String date) {
        logger.info("dateType param:{},{}", date);
        String url = "https://api.goseek.cn/Tools/holiday?date=" + date;
//        String result = restTemplate.getForObject(url, String.class);
        logger.info("dateType result:{}", date);
        return ApiResult.success("");
    }

    @GetMapping("/jsonp")
    public String jsonpCall(@RequestParam(value = "callback", required = false) String callback) throws Exception {
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        requestAttributes.getResponse().setHeader("Access-Control-Allow-Origin", "http://localhost:8085");DispatcherServlet
        logger.info("jsonpCall param:{}", callback);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonp", "成功");
        return callback + "(" + jsonObject.toJSONString() + ")";
    }

    @CrossOrigin(origins = {"http://localhost:8085"}, allowCredentials = "true", allowedHeaders = {"GET", "POST"})
    @RequestMapping("/cors")
    public String corsCall() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cors", "成功");
        return jsonObject.toJSONString();
    }
}
