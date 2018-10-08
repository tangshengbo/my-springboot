package com.tangshengbo.controller;

import com.tangshengbo.model.ApiResult;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Tangshengbo on 2018/9/30
 */
@RestController
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
    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }


    @GetMapping("/dateType")
    public ApiResult getDateType(String date) {
        logger.info("dateType param:{}", date);
        String url = "http://api.goseek.cn/Tools/holiday?date=" + date;
        String result = restTemplate.getForObject(url, String.class);
        logger.info("dateType result:{}", date);
        return ApiResult.success(result);
    }

}
