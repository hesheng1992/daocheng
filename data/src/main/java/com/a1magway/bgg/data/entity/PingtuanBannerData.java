package com.a1magway.bgg.data.entity;

import java.util.List;

/**
 * 拼团轮播图数据
 * Created by enid on 2018/8/19.
 */

public class PingtuanBannerData {

    /**
     * id : 21
     * title : 而你
     * endTime : 1534816200000
     * collageProduct : [{"id":75,"productId":2911,"cover":"https://smjdev.1magway.com/pictures/collage/201808/1534643659249_-1472792585.png"}]
     */

    private int id;
    private String title;
    private long endTime;
    private List<CollageProductBean> collageProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public List<CollageProductBean> getCollageProduct() {
        return collageProduct;
    }

    public void setCollageProduct(List<CollageProductBean> collageProduct) {
        this.collageProduct = collageProduct;
    }

    public static class CollageProductBean {
        /**
         * id : 75
         * productId : 2911
         * cover : https://smjdev.1magway.com/pictures/collage/201808/1534643659249_-1472792585.png
         */

        private int id;
        private int productId;
        private String cover;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }
    }
}
