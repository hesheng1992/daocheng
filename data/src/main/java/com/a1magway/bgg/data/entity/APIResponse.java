package com.a1magway.bgg.data.entity;

import com.a1magway.bgg.data.net.APICodes;

/**
 * 服务器返回的最基础的结构
 * Created by jph on 2016/11/18.
 */
public class APIResponse<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        if (getCode() == APICodes.SUCCESS) {
            return true;
        }
        if (getCode() == APICodes.SUCCESS_NULL) {
            return true;
        }
        return false;
    }
}
