package com.a1magway.bgg.data.net;

/**
 * 服务器返回的异常
 * Created by jph on 2016/11/18.
 */
public class APIException extends Exception {

    private int code;
    private String message;

    public APIException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
