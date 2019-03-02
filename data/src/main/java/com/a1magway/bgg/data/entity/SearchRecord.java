package com.a1magway.bgg.data.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

import java.util.Date;

/**
 * 搜索记录
 * Created by jph on 2017/8/1.
 */
@Entity
public class SearchRecord {
    private String key;
    private Date createTime;

    public SearchRecord(String key) {
        this.key = key;
    }

    @Generated(hash = 2023508607)
    public SearchRecord(String key, Date createTime) {
        this.key = key;
        this.createTime = createTime;
    }

    @Generated(hash = 839789598)
    public SearchRecord() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
