package com.tangshengbo.model;

import java.util.Date;

public class Dltask {
    /**
     * auto id
     */
    private Long id;

    /**
     * uniq.a
     */
    private String a;

    /**
     * uniq.b
     */
    private String b;

    /**
     * uniq.c
     */
    private String c;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * auto id
     * @return id auto id
     */
    public Long getId() {
        return id;
    }

    /**
     * auto id
     * @param id auto id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * uniq.a
     * @return a uniq.a
     */
    public String getA() {
        return a;
    }

    /**
     * uniq.a
     * @param a uniq.a
     */
    public void setA(String a) {
        this.a = a == null ? null : a.trim();
    }

    /**
     * uniq.b
     * @return b uniq.b
     */
    public String getB() {
        return b;
    }

    /**
     * uniq.b
     * @param b uniq.b
     */
    public void setB(String b) {
        this.b = b == null ? null : b.trim();
    }

    /**
     * uniq.c
     * @return c uniq.c
     */
    public String getC() {
        return c;
    }

    /**
     * uniq.c
     * @param c uniq.c
     */
    public void setC(String c) {
        this.c = c == null ? null : c.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}