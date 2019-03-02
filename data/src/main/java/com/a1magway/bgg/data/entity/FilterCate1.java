package com.a1magway.bgg.data.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 筛选的一级分类
 * Created by jph on 2017/8/3.
 */
public class FilterCate1 implements Serializable {

    private static final long serialVersionUID = 407146682516581442L;
    private int id;
    private String name;
    private List<FilterCate2> list;

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

    public List<FilterCate2> getList() {
        return list;
    }

    public void setList(List<FilterCate2> list) {
        this.list = list;
    }
}
