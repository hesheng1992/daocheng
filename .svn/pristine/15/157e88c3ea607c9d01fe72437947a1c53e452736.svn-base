package com.almagway.common.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;

/**
 * author: Beaven
 * date: 2017/10/21 22:37
 */

public class AssetUtil {

    private AssetUtil() {
    }

    /**
     * 从文件中获取String字符串
     */
    public static String getString(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();
        String content = "";
        try {
            content = FileUtil.getStringFromIs(assetManager.open(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
