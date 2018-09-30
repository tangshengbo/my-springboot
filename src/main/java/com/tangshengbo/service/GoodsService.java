package com.tangshengbo.service;

import com.tangshengbo.model.Goods;
import com.tangshengbo.model.PageBean;

/**
 * Created by Tangshengbo on 2018/9/30
 */
public interface GoodsService extends BaseService<Goods> {

    /**
     * 分页查询
     * @param goods 查询条件
     * @param pageNum 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(Goods goods, int pageNum, int pageSize);
}
