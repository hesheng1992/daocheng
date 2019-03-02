package com.a1magway.bgg.common.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 登录状态发生改变的广播接收器
 * Created by lyx on 2017/8/30.
 */
public abstract class LoginReceiver extends BaseBroadcastReceiver {
    private static final String MM_LOGIN_ACTION = "mm_login_broadcast_action";
    public static final String EXTRA_LOGIN_STATUS = "extra_login_status";
    public static final int LOG_IN = 1;
    public static final int LOG_OUT = 2;

    /**
     * 登录状态变化
     *
     * @param context
     */
    public static void sendLoginStatusBroadcast(Context context, int status) {
        Intent intent = new Intent(MM_LOGIN_ACTION);
        intent.putExtra(EXTRA_LOGIN_STATUS, status);
        context.sendBroadcast(intent);
    }

    @Override
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MM_LOGIN_ACTION);
        return intentFilter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int loginStatus = intent.getIntExtra(EXTRA_LOGIN_STATUS, 0);
        if (loginStatus == LOG_IN) {
            onLogin();
        } else {
            onLogout();
        }
    }

    /**
     * 接受到登录了
     */
    public abstract void onLogin();

    /**
     * 接受到退出登录了
     */
    public abstract void onLogout();
}
