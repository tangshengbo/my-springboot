package com.tangshengbo.dao;

import com.tangshengbo.model.User;

import java.util.List;

public interface UserMapper {
    /**
     *
     * @mbggenerated 2018-09-30
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int insert(User record);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int insertSelective(User record);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    User selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int updateByPrimaryKeySelective(User record);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int updateByPrimaryKey(User record);

    List<User> findAll();

    User findByName(String name);
}