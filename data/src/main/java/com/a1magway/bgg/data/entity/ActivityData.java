package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 专题数据
 * Created by jph on 2017/7/24.
 */
public class ActivityData implements Serializable {

    private static final long serialVersionUID = -1280051742281894906L;

    private int id;
    private String bannerPathBig;
    private String bannerPathSmall;
    private String title;
    private String htmlPath;
    private List<ActivityProduct> productList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public List<ActivityProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<ActivityProduct> productList) {
        this.productList = productList;
    }

    public String getBannerPathBig() {
        return bannerPathBig;
    }

    public void setBannerPathBig(String bannerPathBig) {
        this.bannerPathBig = bannerPathBig;
    }

    public String getBannerPathSmall() {
        return bannerPathSmall;
    }

    public void setBannerPathSmall(String bannerPathSmall) {
        this.bannerPathSmall = bannerPathSmall;
    }
}
