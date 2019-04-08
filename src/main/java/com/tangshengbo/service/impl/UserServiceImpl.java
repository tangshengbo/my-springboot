package com.tangshengbo.service.impl;


import com.tangshengbo.dao.UserMapper;
import com.tangshengbo.model.User;
import com.tangshengbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/9/28
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(value = "userList-key")
    @Override
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        System.out.println("数据库查询");
        return all;
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void create(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id : ids) {
            userMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }
}
