package com.a1magway.bgg.data.entity;

import java.io.Serializable;

public class FindWebContentData implements Serializable {

    private int id;
    private String title;
    private String url;
    private int type;
    private int praise;
    private int clickNum;
    private int praiseFlag;

    public int getPraiseFlag() {
        return praiseFlag;
    }

    public void setPraiseFlag(int praiseFlag) {
        this.praiseFlag = praiseFlag;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
