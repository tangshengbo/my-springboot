package com.tangshengbo.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tangshengbo.dao.GoodsMapper;
import com.tangshengbo.model.Goods;
import com.tangshengbo.model.PageBean;
import com.tangshengbo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Tangshengbo on 2018/9/30
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> findAll() {
        return null;
    }

    @Override
    public Goods findById(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    @Override
    public void create(Goods goods) {
        goodsMapper.insertSelective(goods);
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public void delete(Long... ids) {
        for (Long id : ids) {
            goodsMapper.deleteByPrimaryKey(id);
        }
    }

    /**
     * 分页查询-条件查询方法
     *
     * @param goods    查询条件
     * @param pageNum 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    public PageBean<Goods> findByPage(Goods goods, int pageNum, int pageSize) {
        //使用Mybatis分页插件
        PageHelper.startPage(pageNum, pageSize, "id desc");
        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Goods> page = goodsMapper.findByPage(goods);
        return new PageBean<>(page.getTotal(), page.getResult(), page.getPageNum(), page.getPageSize());
    }
}
