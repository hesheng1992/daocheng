package com.a1magway.bgg.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enid on 2018/6/25.
 */

public class LogisticsData {
    private String message;//ok
    private String nu;// "499355859540",
    private String ischeck;// "1",
    private String condition;// "D01",
    private String com;// "中通",
    private String status;// "200",
    private String state;
    private List<LogisticsDeliverData> data = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<LogisticsDeliverData> getData() {
        return data;
    }

    public void setData(List<LogisticsDeliverData> data) {
        this.data = data;
    }
}
