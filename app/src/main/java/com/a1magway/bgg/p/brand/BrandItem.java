package com.a1magway.bgg.p.brand;

import java.io.Serializable;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * 用于显示的model
 * Created by jph on 2017/7/29.
 */
public class BrandItem implements Serializable, IndexableEntity {

    private static final long serialVersionUID = -5158610458028513172L;

    private int id = -1;
    private String name;
    private int type;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getFieldIndexBy() {
        return getName();
    }

    @Override
    public void setFieldIndexBy(String indexField) {
        setName(indexField);
    }

    @Override
    public void setFieldPinyinIndexBy(String pinyin) {

    }
}
