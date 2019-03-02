package com.a1magway.bgg.p.member.data;

/**
 * author: Beaven
 * date: 2017/10/16 18:42
 */

public class MemberRegisterEdit {

    public static final String TAG_TELEPHONE = "手机号";
    public static final String TAG_NAME = "* 姓名";
    public static final String TAG_SEX = "* 性别";
    public static final String TAG_BIRTHDAY = "* 生日";
    public static final String TAG_CAREER = "职业";
    public static final String TAG_INCOME = "月收入";
    public static final String TAG_GIFT_ADDRESS = "* 礼品签收地址";

    private String title;
    private boolean isShowRightButton;
    private boolean isLayoutEnable;
    private boolean isEditEnable;
    private String text;
    private String editHint;

    public MemberRegisterEdit(String title, boolean isShowRightButton, String editHint,
                              boolean isEditEnable) {
        this.title = title;
        this.isShowRightButton = isShowRightButton;
        this.editHint = editHint;
        this.isLayoutEnable = true;
        this.isEditEnable = isEditEnable;
        this.text = "";
    }

    public String getEditHint() {
        return editHint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isShowRightButton() {
        return isShowRightButton;
    }

    public void setShowRightButton(boolean showRightButton) {
        isShowRightButton = showRightButton;
    }

    public boolean isLayoutEnable() {
        return isLayoutEnable;
    }

    public void setLayoutEnable(boolean layoutEnable) {
        isLayoutEnable = layoutEnable;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEditEnable() {
        return isEditEnable;
    }

    public void setEditEnable(boolean editEnable) {
        isEditEnable = editEnable;
    }
}
