package com.a1magway.bgg.data.entity;

import java.util.List;

/**
 * Created by enid on 2018/6/25.
 */

public class LogisticsInfoData {
    private List<LogisticsSkuData> logisticsSkuList;
    private LogisticsData logistics;

    public List<LogisticsSkuData> getLogisticsSkuList() {
        return logisticsSkuList;
    }

    public void setLogisticsSkuList(List<LogisticsSkuData> logisticsSkuList) {
        this.logisticsSkuList = logisticsSkuList;
    }

    public LogisticsData getLogistics() {
        return logistics;
    }

    public void setLogistics(LogisticsData logistics) {
        this.logistics = logistics;
    }
}
