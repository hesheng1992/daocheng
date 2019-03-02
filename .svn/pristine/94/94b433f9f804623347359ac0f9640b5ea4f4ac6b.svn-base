package com.a1magway.bgg.util;

import android.content.Context;

import com.a1magway.bgg.common.BaseSharedPref;

/**
 * Data save&get by SharedPreferences.
 */
public class SharedPrefUtils extends BaseSharedPref {
    private static final String TAG = SharedPrefUtils.class.getSimpleName();

    public SharedPrefUtils(String name) {
        super(name);
    }

    // 登录信息
    private final static String PREFS_KEY_LOGIN_INFO = "prefs_key_login_info";

    private final static String PREFS_KEY_HUANXIN_LOGIN_INFO = "prefs_key_huanxin_login_info";

    private final static String PREFS_KEY_OPENED_VERSION_NAME = "prefs_key_opened_version_name";

    private final static String USER_LOGIN_THIRD_UID_NAME = "user_login_third_uid_name";

    /**
     * 保存登录信息
     */
    public static void setLoginInfo(Context c, String info) {
        setString(PREFS_KEY_LOGIN_INFO, info, c);
    }

    /**
     * 获取登录信息
     */
    public static String getLoginInfo(Context c) {
        return getString(PREFS_KEY_LOGIN_INFO, c);
    }

    public static String getOpenedVersionName(Context c) {
        return getString(PREFS_KEY_OPENED_VERSION_NAME, c);
    }

    public static void setOpenedVersionName(Context c, String versionName) {
        setString(PREFS_KEY_OPENED_VERSION_NAME, versionName, c);
    }

    /**
     * 保存第三方uid
     *
     * @param context
     * @param uid
     */
    public static void setThirdUid(Context context, String uid) {
        setString(USER_LOGIN_THIRD_UID_NAME, uid, context);
    }

    /**
     * 获取登录类型
     *
     * @param context
     * @return
     */
    public static String getThirdUid(Context context) {
        return getString(USER_LOGIN_THIRD_UID_NAME, context);
    }

    /**
     * 保存环信登录信息
     */
    public static void setHuanXinLoginInfo(Context c, String info) {
        setString(PREFS_KEY_HUANXIN_LOGIN_INFO, info, c);
    }

    /**
     * 获取环信登录信息
     */
    public static String getHuanXinLoginInfo(Context c) {
        return getString(PREFS_KEY_HUANXIN_LOGIN_INFO, c);
    }

    /**
     * 保存收藏选择信息
     */
    public static void setCollectionSelectInfo(Context c, String info) {
        setString("collection_selection_info", info, c);
    }

    /**
     * 获取收藏选择信息
     */
    public static String getCollectionSelectInfo(Context c) {
        return getString("collection_selection_info", c);
    }

    /**
     * 保存apk的大小
     */
    public static void saveUpdateApkSize(Context c, long info) {
        setLong("apk_size", info, c);
    }

    /**
     * 取出apk的大小
     */
    public static long getChckoutUpdateApkSize(Context c) {
        return getLong("apk_size", c);
    }

}
