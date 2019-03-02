package com.a1magway.bgg.v.share;

/**
 * Created by enid on 2018/6/19.
 */

public class ShareFeature {

    public static final int WEIXIN_MINI_TAG = 0x01;

    public static final int QQ_TAG = 0x02;

    public static final int QZONE_TAG = 0x03;

    public static final int WEIXIN_TAG = 0x04;

    public static final int WEIXIN_CIRCLE_TAG = 0x05;

    public static final int SINA_TAG = 0x06;

    // 功能ID
    private int id;
    // 显示图片
    private int drawableId;
    // 显示文字
    private String text;

    public ShareFeature(int id, int drawableId, String text) {
        this.id = id;
        this.drawableId = drawableId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
