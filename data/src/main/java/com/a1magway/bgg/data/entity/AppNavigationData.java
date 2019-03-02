package com.a1magway.bgg.data.entity;

/**
 * Created by enid on 2018/6/13.
 */

public class AppNavigationData {
    private int type;// – 主题类型，int，1.今日推荐 2.新品 3.特价 4.预告
    private String typeName;// – 类型名称

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
