package com.a1magway.bgg.eventbus.event;



/**
 * Created by enid on 2018/7/13.
 */

public class ShardImageDownloadCallBack {
    private boolean loadSucceed;

    private String imgUrl;

    public ShardImageDownloadCallBack(boolean loadSucceed,String imgUrl) {
        this.loadSucceed = loadSucceed;
        this.imgUrl = imgUrl;
    }

    public boolean isDownLoadSucceed() {
        return loadSucceed;
    }

    public String getImgUrl() {
        return imgUrl;
    }

}
