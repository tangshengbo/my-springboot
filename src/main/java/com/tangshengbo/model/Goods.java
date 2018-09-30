package com.tangshengbo.model;

public class Goods {
    /**
     * 编号
     */
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品价格
     */
    private String price;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品品牌
     */
    private String brand;

    /**
     * 编号
     * @return id 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 编号
     * @param id 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品标题
     * @return title 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品标题
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 商品价格
     * @return price 商品价格
     */
    public String getPrice() {
        return price;
    }

    /**
     * 商品价格
     * @param price 商品价格
     */
    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    /**
     * 商品图片
     * @return image 商品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 商品图片
     * @param image 商品图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 商品品牌
     * @return brand 商品品牌
     */
    public String getBrand() {
        return brand;
    }

    /**
     * 商品品牌
     * @param brand 商品品牌
     */
    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }
}