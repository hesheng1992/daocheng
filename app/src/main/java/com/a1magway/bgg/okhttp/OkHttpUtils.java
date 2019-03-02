package com.a1magway.bgg.okhttp;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * Class are created, On 2017/07/04.
 */

public class OkHttpUtils {
    private List<Param> params = new ArrayList<>();
    private List<Param> files = new ArrayList<>();
    private Map<String, String> headers = new Hashtable<>();
    private RequestMode requestWay;
    private String url;
    private ProgressListener progressListener;
    private String destFileDir;

    private OkHttpUtils() {
    }

    public static OkHttpUtils getInstance() {
        return new OkHttpUtils();
    }


    public OkHttpUtils get() {
        requestWay = RequestMode.GET;
        return this;
    }

    public OkHttpUtils post() {
        requestWay = RequestMode.POST;
        return this;
    }

    public OkHttpUtils postJson() {
        requestWay = RequestMode.POST_JSON;
        return this;
    }

    public OkHttpUtils put() {
        requestWay = RequestMode.PUT;
        return this;
    }

    public OkHttpUtils postUploadImage(ProgressListener progressListener) {
        requestWay = RequestMode.POST_UPLOAD_IMAGE;
        this.progressListener = progressListener;
        return this;
    }

    public OkHttpUtils putUploadImage(ProgressListener progressListener) {
        requestWay = RequestMode.PUT_UPLOAD_IMAGE;
        this.progressListener = progressListener;
        return this;
    }

    public OkHttpUtils postUploadFile(ProgressListener progressListener) {
        requestWay = RequestMode.POST_UPLOAD_FILE;
        this.progressListener = progressListener;
        return this;
    }

    public OkHttpUtils putUploadFile(ProgressListener progressListener) {
        requestWay = RequestMode.PUT_UPLOAD_FILE;
        this.progressListener = progressListener;
        return this;
    }

    public OkHttpUtils downloadFile(String destFileDir, ProgressListener progressListener) {
        requestWay = RequestMode.DOWNLOAD_FILE;
        this.destFileDir = destFileDir;
        this.progressListener = progressListener;
        return this;
    }

    public OkHttpUtils delete() {
        requestWay = RequestMode.DELETE;
        return this;
    }

    public OkHttpUtils url(String url) {
        this.url = url;
        return this;
    }

    public OkHttpUtils addparams(String key, String value) {
        params.add(new Param(key, value));
        return this;
    }

    public OkHttpUtils addparamsCheckNoNull(String key, String value) {
        if (value != null) {
            params.add(new Param(key, value));
        }
        return this;
    }

    public OkHttpUtils addparamsCheckNoNullEmpty(String key, String value) {
        if (value != null && !value.trim().isEmpty()) {
            params.add(new Param(key, value));
        }
        return this;
    }

    public OkHttpUtils addJson(String json) {
        params.clear();
        params.add(new Param("json", json));
        return this;
    }

    public OkHttpUtils addFiles(String key, String value) {
        files.add(new Param(key, value));
        return this;
    }

    public OkHttpUtils addHeads(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public OkHttpExecute build() {
        return new OkHttpExecute(url, requestWay, files, params, headers, progressListener, destFileDir);
    }

    public RxOkHttpExecute buildRx() {
        return new RxOkHttpExecute(url, requestWay, files, params, headers, progressListener, destFileDir);
    }

    public class Param {
        String key;
        String value;

        Param(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public String getKey() {
            return key;
        }

    }

}
