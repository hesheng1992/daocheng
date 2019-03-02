package com.a1magway.bgg.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enid on 2018/6/14.
 */

public class GoodsRecommendData {
    private String title;// – 好货推荐
    private long remainTime;// – 剩余时间，单位：毫秒
    private List<ProductBean> products = new ArrayList<>();
    private Banner banners;//banner列表

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

    public Banner getBanners() {
        return banners;
    }

    public void setBanners(Banner banners) {
        this.banners = banners;
    }

    public class Banner{
        private String id; //banner id
        private String subject_id;//主题ID
        private String bigPath;//轮播图地址，以“；”隔开
        private String jumpId;//跳转的主题ID，暂时没用

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSubject_id() {
            return subject_id;
        }

        public void setSubject_id(String subject_id) {
            this.subject_id = subject_id;
        }

        public String getBigPath() {
            return bigPath;
        }

        public void setBigPath(String bigPath) {
            this.bigPath = bigPath;
        }

        public String getJumpId() {
            return jumpId;
        }

        public void setJumpId(String jumpId) {
            this.jumpId = jumpId;
        }
    }
}
