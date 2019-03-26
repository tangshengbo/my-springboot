package com.tangshengbo.controller;

import com.google.common.collect.Lists;
import com.tangshengbo.model.Tang;
import com.tangshengbo.model.User;
import com.tangshengbo.repository.TangMongoRepository;
import com.tangshengbo.repository.UserMongoRepository;
import com.tangshengbo.repository.UserRedisRepository;
import com.tangshengbo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by Tangshengbo on 2019/3/26
 */
@RestController
public class DataFetchController {

    private static final Logger logger = LoggerFactory.getLogger(DataFetchController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Autowired
    private TangMongoRepository tangMongoRepository;

    @Autowired
    private UserRedisRepository userRedisRepository;

    @Value("#{10 * 60}")
    private int cacheSeconds;

    /**
     * 查询所有
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("redisTemplate/findAll")
    public List<Object> redisTemplateFindAll() {
        Map redisTemplateFindAll = redisTemplate.opsForHash().entries("redisTemplateFindAll");
        if (!redisTemplateFindAll.isEmpty()) {
            return Lists.newArrayList(redisTemplateFindAll);
        }
        Map<String, User> userMap = userService.findAll().stream()
                .collect(Collectors.toMap(User::getUsername, user -> user));
        BoundHashOperations hashOps = redisTemplate.boundHashOps("redisTemplateFindAll");
        hashOps.putAll(userMap);
        hashOps.expire(cacheSeconds, TimeUnit.SECONDS);
        return Lists.newArrayList(userMap);
    }

    /**
     * 查询所有
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("redis/findAll")
    public List<User> redisFindAll() {
        List<User> findAll = Lists.newArrayList(userRedisRepository.findAll());
        if (!findAll.isEmpty()) {
            return findAll;
        }
        List<User> userList = userService.findAll();
        userRedisRepository.saveAll(userList);
        return userList;
    }

    @RequestMapping("/mongodb/findAll")
    public List<User> mongodbFindAll() {
        List<User> findAll = userMongoRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll;
        }
        List<User> userList = userService.findAll();
        userMongoRepository.saveAll(userList);
        return userList;
    }

    @RequestMapping("/mongodb/findTang")
    public List<Tang> mongodbFindTang() {
        List<Tang> findAll = tangMongoRepository.findAll();
        if (!findAll.isEmpty()) {
            return findAll;
        }
        List<Tang> tangList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Tang tang = new Tang();
            tang.setId((long) i);
            tang.setAge(100 + i);
            tang.setName("唐唐");
            tangList.add(tang);
        }
        tangMongoRepository.saveAll(tangList);
        return tangList;
    }
}
