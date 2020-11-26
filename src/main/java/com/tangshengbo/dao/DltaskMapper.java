package com.tangshengbo.dao;


import com.tangshengbo.model.Dltask;

public interface DltaskMapper {
    /**
     *
     * @mbggenerated 2020-03-11
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-03-11
     */
    int insert(Dltask record);

    /**
     *
     * @mbggenerated 2020-03-11
     */
    int insertSelective(Dltask record);

    /**
     *
     * @mbggenerated 2020-03-11
     */
    Dltask selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2020-03-11
     */
    int updateByPrimaryKeySelective(Dltask record);

    /**
     *
     * @mbggenerated 2020-03-11
     */
    int updateByPrimaryKey(Dltask record);

    int deleteBy(Dltask record);
}