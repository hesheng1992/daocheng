package com.a1magway.bgg.data.entity;

/**
 * Created by enid on 2018/7/12.
 */

public class ShardXcxQRCode {

    /**
     * code : 0
     * msg : 获取小程序二维码成功
     * data : https://mmdev.1magway.com/pictures/qrcode/bgg_12345.jpg
     */

    private int code;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ShardXcxQRCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
