package com.a1magway.bgg.util;

import android.content.Context;

/**
 * Created on 2018/5/21 0021.
 */

public class DensityUtils {
    /**
     * dipからpxへ
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * pxからdipへ
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
