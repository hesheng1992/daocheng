package com.a1magway.bgg.data.entity;

import java.io.Serializable;

public class WebDeliveryData  implements Serializable {

    private int type;
    private String title;
    private String shareURL;
    private int productId;
    private int favoriteId;
    private WXPayInfo payDataWX;
    private String payData;
    private String content;
    private String subject_id;

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShareURL() {
        return shareURL;
    }

    public void setShareURL(String shareURL) {
        this.shareURL = shareURL;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(int favoriteId) {
        this.favoriteId = favoriteId;
    }

    public WXPayInfo getPayDataWX() {
        return payDataWX;
    }

    public void setPayDataWX(WXPayInfo payDataWX) {
        this.payDataWX = payDataWX;
    }

    public String getPayData() {
        return payData;
    }

    public void setPayData(String payData) {
        this.payData = payData;
    }
}
