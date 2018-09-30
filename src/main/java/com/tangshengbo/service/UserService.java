package com.tangshengbo.service;

import com.tangshengbo.model.User;

/**
 * @auther TyCoding
 * @date 2018/9/28
 */
public interface UserService extends BaseService<User> {

    User findByName(String name);
}
