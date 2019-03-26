package com.tangshengbo.model;

import org.springframework.data.annotation.Id;

/**
 * Created by Tangshengbo on 2019/3/25
 */
public class Base {

    /**
     * 编号
     */
    @Id
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
