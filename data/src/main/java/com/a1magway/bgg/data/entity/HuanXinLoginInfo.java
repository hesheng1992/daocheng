package com.a1magway.bgg.data.entity;

/**
 * Created by enid on 2018/6/4.
 */

public class HuanXinLoginInfo {
   private int id;// – 环信用户id
   private String username;// – 登录名称
   private String password;// – 登录密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
