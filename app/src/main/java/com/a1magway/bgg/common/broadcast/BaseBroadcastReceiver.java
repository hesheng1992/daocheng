package com.a1magway.bgg.common.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;

/**
 * Created by lyx on 2017/8/30.
 */
public abstract class BaseBroadcastReceiver extends BroadcastReceiver{

    /**
     * 获取过滤器
     * @return
     */
    public abstract IntentFilter getIntentFilter();
}
