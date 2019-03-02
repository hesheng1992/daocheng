package com.a1magway.bgg.data.entity;

/**
 * Created by enid on 2018/6/25.
 */

public class LogisticsDeliverData {
    private String time;// "2018-06-10 11:56:29",
    private String ftime;//"2018-06-10 11:56:29",
    private String context;// "【成都市】 快件已在 【市区二部春熙路分部】 签收,签收人: 门卫, 感谢使用中通快递,期待再次为您服务!"

    public LogisticsDeliverData(String time, String context) {
        this.time = time;
        this.context = context;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
