package com.a1magway.bgg.p.personal;

/**
 * @author Beaven
 * @date 2017/10/12 15:08
 */
public class PersonalFeature {

    // 卡券中心
    public static final int CARD_CENTER_TAG = 0x01;
    // 个人设置
    public static final int PERSONAL_SETTING_TAG = 0x02;
    // 订单管理
    public static final int ORDER_MANAGE_TAG = 0x03;
    // 地址管理
    public static final int ADDRESS_MANAGE_TAG = 0x04;
    // 联系我们
    public static final int CONTACT_TAG = 0x05;
    // 购买须知
    public static final int BUY_NOTE_TAG = 0x06;
    // 销售订单
    public static final int SALE_ORDER_TAG = 0x07;


    // 我的收藏
    public static final int MY_COLLECT_TAG = 0x11;
    // 订单管理
//    public static final int ORDER_MANAGE_TAG = 0x03;
    // 地址管理
//    public static final int ADDRESS_MANAGE_TAG = 0x12;
    // 我的钱包
    public static final int MY_WALLET_TAG = 0x13;
    // 邀请码
    public static final int INVITATION_CODE_TAG = 0x14;
    //授权书
    public static final int AUTHORIZATION_BOOK = 0x15;
    //我的朋友
    public static final int MY_FRIEND = 0x16;
    //文章管理
    public static final int ARTICLE_MANAGER = 0x17;
    //售后
    public static final int After_sale=0x18;

    // 功能ID
    private int id;
    // 显示图片
    private int drawableId;
    // 显示文字
    private String text;
    // 是否可点击
    private boolean isEnable;
    // 是否是会员
    private boolean isMember;

    public PersonalFeature(
            int id, int drawableId, String text, boolean isEnable, boolean isMember) {
        this.id = id;
        this.drawableId = drawableId;
        this.text = text;
        this.isEnable = isEnable;
        this.isMember = isMember;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String getText() {
        return text;
    }

    public int getId() {
        return id;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public boolean isMember() {
        return isMember;
    }
}
