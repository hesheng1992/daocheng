package com.a1magway.bgg.eventbus.event;

import com.a1magway.bgg.data.entity.Commodity;

/**
 * 拼团购买事件
 * Created by enid on 2018/8/20.
 */

public class PingtuanBuyEvent {

    public PingtuanBuyEvent(int collageOrderId) {
        this.collageOrderId = collageOrderId;
    }

    public PingtuanBuyEvent(int collageOrderId, Commodity selectedCommodity) {
        this.collageOrderId = collageOrderId;
        this.selectedCommodity = selectedCommodity;
    }

    private int collageOrderId;
    private Commodity selectedCommodity;

    public Commodity getSelectedCommodity() {
        return selectedCommodity;
    }

    public void setSelectedCommodity(Commodity selectedCommodity) {
        this.selectedCommodity = selectedCommodity;
    }

    public int getCollageOrderId() {
        return collageOrderId;
    }

    public void setCollageOrderId(int collageOrderId) {
        this.collageOrderId = collageOrderId;
    }
}
