package com.tangshengbo.model;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash("user")
public class User extends Base {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    private Date createDate;

    private RequestBean request;

    public RequestBean getRequest() {
        return request;
    }

    public void setRequest(RequestBean request) {
        this.request = request;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }


    /**
     * 用户名
     * @return username 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    private static class RequestBean {

        private String amount;

        private String computer;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getComputer() {
            return computer;
        }

        public void setComputer(String computer) {
            this.computer = computer;
        }

    }


    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}