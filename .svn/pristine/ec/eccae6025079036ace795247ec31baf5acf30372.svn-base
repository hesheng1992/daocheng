package com.a1magway.bgg.okhttp;

import java.io.IOException;
import java.util.List;
import java.util.Map;




/**
 * Created on 2017/_8/3 0003.
 */

public class LogApiUtils {
    private String url;
    private String requestWay;
    private List<OkHttpUtils.Param> params;
    private Map<String, String> headers;
    private String logLocation;

    public LogApiUtils(String url, String requestWay, List<OkHttpUtils.Param> params, Map<String, String> headers,
                       String logLocation) {
        this.url = url;
        this.requestWay = requestWay;
        this.params = params;
        this.headers = headers;
        this.logLocation = logLocation;
    }

    public void setResponse(String body, IOException e) {
        //joint heads
        StringBuilder headers_string = new StringBuilder();
        if (headers != null && headers.size() != 0) {
            headers_string.append("Header: [");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headers_string.append("\n")
                        .append(entry.getKey())
                        .append(" = ")
                        .append(entry.getValue())
                        .append(",");
            }
            headers_string.append("\n]");
        } else {
            headers_string.append("Header: []");
        }

        //joint parmas
        StringBuilder params_string = new StringBuilder();
        if (params != null && params.size() != 0) {
            params_string.append("Params:")
                    .append("[");
            for (OkHttpUtils.Param param : params) {
                params_string
                        .append("\n")
                        .append(param.getKey())
                        .append(" = ")
                        .append(param.getValue())
                        .append(",");
            }
            params_string.append("\n]");
        } else {
            params_string.append("Params:[]");
        }

        //joint log string
        StringBuilder builder = new StringBuilder();
        builder.append("\n### START REQUEST ###\n")
                .append(requestWay)
                .append(": ")
                .append(url)
                .append("\n")
                .append(headers_string)
                .append("\n")
                .append(params_string)
                .append("\n")
                .append("### RECEIVED RESPONSE ###\n");

        if (body != null) {
            builder.append(body);
        } else {
            builder.append(e.getMessage());
        }

        //output log
        if (builder.length() < 2500) {
            LogUtil.apiLogStart(logLocation, String.valueOf(builder));
        } else if (builder.length() < 5000) {
            LogUtil.apiLogStart(logLocation, String.valueOf(builder.substring(0, 2500)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(2500)));
        } else if (builder.length() < 7500) {
            LogUtil.apiLogStart(logLocation, String.valueOf(builder.substring(0, 2500)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(2500, 5000)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(5000)));
        } else if (builder.length() < 10000) {
            LogUtil.apiLogStart(logLocation, String.valueOf(builder.substring(0, 2500)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(2500, 5000)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(5000, 7500)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(7500)));
        } else {
            LogUtil.apiLogStart(logLocation, String.valueOf(builder.substring(0, 2500)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(2500, 5000)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(5000, 7500)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(7500, 10000)));
            LogUtil.apiLogMore(String.valueOf(builder.substring(10000)));
        }
    }

}
