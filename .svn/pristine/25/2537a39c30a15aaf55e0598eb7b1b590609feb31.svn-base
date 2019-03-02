package com.a1magway.bgg.common.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Created by lyx on 2017/9/15.
 */
public abstract class RefreshCartReceiver extends BaseBroadcastReceiver{
    private static String MM_REFRESH_CART_ACTION = "mm_refresh_cart_action";

    /**
     * 购物车数量变化
     *
     * @param context
     */
    public static void sendRefreshCartBroadcast(Context context) {
        Intent intent = new Intent(MM_REFRESH_CART_ACTION);
        context.sendBroadcast(intent);
    }

    @Override
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MM_REFRESH_CART_ACTION);
        return intentFilter;
    }
}
