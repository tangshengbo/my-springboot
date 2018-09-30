package com.tangshengbo.dao;

import com.github.pagehelper.Page;
import com.tangshengbo.model.Goods;

import java.util.List;

public interface GoodsMapper {
    /**
     *
     * @mbggenerated 2018-09-30
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int insert(Goods record);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int insertSelective(Goods record);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    Goods selectByPrimaryKey(Long id);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     *
     * @mbggenerated 2018-09-30
     */
    int updateByPrimaryKey(Goods record);

    List<Goods> findAll();

    Page<Goods> findByPage(Goods goods);
}