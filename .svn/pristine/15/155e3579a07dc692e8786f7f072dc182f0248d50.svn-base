package com.almagway.common.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * author: Beaven
 * date: 2017/10/21 15:29
 */

public class ApplicationUtil {

    private ApplicationUtil() {
    }

    @SuppressLint("StaticFieldLeak")
    private static Application application;

    /**
     * 设置初始化，在应用Application类中
     */
    public static void initUtil(@NonNull Application application) {
        ApplicationUtil.application = application;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }
}
