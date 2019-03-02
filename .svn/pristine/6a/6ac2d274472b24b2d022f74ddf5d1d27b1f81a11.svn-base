package com.a1magway.bgg.util;

import android.text.TextUtils;

import com.a1magway.bgg.App;
import com.a1magway.bgg.data.entity.CollectionData;
import com.a1magway.bgg.data.entity.HuanXinLoginInfo;
import com.a1magway.bgg.data.entity.LoginData;
import com.almagway.common.json.Json;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    // 普通用户
    public static final int USER_ROLE_TYPE_DEFAULT = 0;
    // 会员
    public static final int USER_ROLE_TYPE_MEMBER_DEFAULT = 1;
    // 万达会员
    public static final int USER_ROLE_TYPE_MEMBER_WANDA = 2;
    // 是会员，也是万达会员
    public static final int USER_ROLE_TYPE_MEMBER_AND_WANDA = 3;

    private LoginData mLoginData;

    private GlobalData() {
    }

    private volatile static GlobalData instance = null;

    public static GlobalData getInstance() {
        if (null == instance) {
            synchronized (GlobalData.class) {
                if (null == instance) {
                    instance = new GlobalData();
                }
            }
        }
        return instance;
    }

    /**
     * 用户信息设置
     */
    public void setLoginData(LoginData loginData) {
        String gender = loginData.getGender();
        if ("0".equals(gender)) {
            loginData.setGender("男");
        } else if ("1".equals(gender)) {
            loginData.setGender("女");
        } else if ("2".equals(gender)) {
            loginData.setGender("保密");
        }
        this.mLoginData = loginData;
        String json = Json.getJson(loginData);
        SharedPrefUtils.setLoginInfo(App.getContext(), json);
    }

    /**
     * 用户信息清除
     */
    public void clearLoginData() {
        this.mLoginData = null;
        SharedPrefUtils.setLoginInfo(App.getContext(), "");
    }

    /**
     * 环信登录信息清除
     */
    public void clearHuanXinLoginData() {
        this.mLoginData = null;
        SharedPrefUtils.setHuanXinLoginInfo(App.getContext(), "");
    }

    /**
     * 获取用户信息
     */
    public LoginData getLoginData() {
        if (isAccountDataInvalid()) {
            synchronized (GlobalData.class) {
                String loginInfo = SharedPrefUtils.getLoginInfo(App.getContext());
                if (!TextUtils.isEmpty(loginInfo)) {
                    this.mLoginData = Json.fromJson(loginInfo, LoginData.class);
                }
            }
        }
        return mLoginData;
    }

    public boolean isLogin() {
        this.mLoginData = getLoginData();
        return !isAccountDataInvalid();
    }

    public boolean isBoundInvitationCode() {
        if (getLoginData().getMemberGrade() > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取登录用户Id
     */
    public int getUserId() {

        if (isLogin()) {
            return getLoginData().getId();
        }
        return -1;
    }

    /**
     * 保存环信登录信息
     *
     * @param loginData
     */
    public void setHuanXinLoginData(HuanXinLoginInfo loginData) {
        String json = Json.getJson(loginData);
        SharedPrefUtils.setHuanXinLoginInfo(App.getContext(), json);
    }

    /**
     * 获取环信登录信息
     *
     * @return
     */
    public HuanXinLoginInfo getHuanXinLoginData() {
        String xinLoginInfo = SharedPrefUtils.getHuanXinLoginInfo(App.getContext());
        HuanXinLoginInfo huanXinLoginInfo = Json.fromJson(xinLoginInfo, HuanXinLoginInfo.class);
        return huanXinLoginInfo;
    }

    public void setThirdUid(String uid) {
        SharedPrefUtils.setThirdUid(App.getContext(), uid);
    }

    public String getThirdUid() {
        return SharedPrefUtils.getThirdUid(App.getContext());
    }

    /**
     * 账户数据是否失效：被系统回收？
     *
     * @return true：已失效
     */
    private boolean isAccountDataInvalid() {
        return mLoginData == null || mLoginData.isNull();
    }


    List<CollectionData> mCollectionData = new ArrayList<>();

    public List<CollectionData> getCollectionSelectData() {
        if (mCollectionData == null) {
            synchronized (GlobalData.class) {
                String collectionInfo = SharedPrefUtils.getCollectionSelectInfo(App.getContext());
                if (!TextUtils.isEmpty(collectionInfo)) {
                    Type type = new TypeToken<ArrayList<CollectionData>>() {
                    }.getType();
                    this.mCollectionData = Json.fromJson(collectionInfo, type);
                }
            }
        }
        return mCollectionData;
    }

    public void setCollectionSelectData(List<CollectionData> collectionData) {
        this.mCollectionData = collectionData;
        String json = Json.getJson(collectionData);
        SharedPrefUtils.setCollectionSelectInfo(App.getContext(), json);
    }
}
