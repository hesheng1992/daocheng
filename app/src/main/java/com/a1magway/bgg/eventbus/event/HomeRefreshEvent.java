package com.a1magway.bgg.eventbus.event;

/**
 * Created by enid on 2018/7/26.
 */

public class HomeRefreshEvent {
    private int currentPage;
    public HomeRefreshEvent(int currentPage){
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

