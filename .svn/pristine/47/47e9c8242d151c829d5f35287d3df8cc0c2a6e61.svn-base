package com.a1magway.bgg.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * 公共的Toast工具类
 * Created by jph on 2017/7/20.
 */
public class Toaster {
    public static void showShort(Context context, String text) {
        Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, @StringRes int textResId) {
        Toast.makeText(context.getApplicationContext(), textResId, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, String text) {
        Toast.makeText(context.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, @StringRes int textResId) {
        Toast.makeText(context.getApplicationContext(), textResId, Toast.LENGTH_LONG).show();
    }
}
