package com.tangshengbo.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by Tangshengbo on 2019/3/25
 */
public class Tang extends Base {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
