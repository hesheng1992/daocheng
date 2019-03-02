package com.a1magway.bgg.data.entity;

/**
 * Created by enid on 2018/9/6.
 */

public class ReturnGoodsReasonBean {
    private int id;
    private String name;
    private boolean isClick;
    private String company;

    public ReturnGoodsReasonBean(){

    }
    public ReturnGoodsReasonBean(String name){
        this.name=name;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
