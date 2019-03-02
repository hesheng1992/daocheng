package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 分类item的实体类
 * Created by jph on 2017/7/19.
 */
public class CateItem implements Serializable {

    private static final long serialVersionUID = -1927108951052158586L;
    private int id;
    private String smallPath;
    private String bigPath;
    private String keyword;
    private int type;
    private String htmlPath;
    private String categoryId;//对应分类id，若存在代表能对应一种筛选分类的

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSmallPath() {
        return smallPath;
    }

    public void setSmallPath(String smallPath) {
        this.smallPath = smallPath;
    }

    public String getBigPath() {
        return bigPath;
    }

    public void setBigPath(String bigPath) {
        this.bigPath = bigPath;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHtmlPath() {
        return htmlPath;
    }

    public void setHtmlPath(String htmlPath) {
        this.htmlPath = htmlPath;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
