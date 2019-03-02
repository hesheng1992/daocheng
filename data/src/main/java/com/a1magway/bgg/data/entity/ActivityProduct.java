package com.a1magway.bgg.data.entity;

import java.io.Serializable;

/**
 * 专题对应的商品
 * Created by jph on 2017/7/24.
 */
public class ActivityProduct implements Serializable {

    private static final long serialVersionUID = -1783432584184351786L;

    private int id;
    private String price;
    private String name;
    private String path;
    private String brand;
    private String subject_id;

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
