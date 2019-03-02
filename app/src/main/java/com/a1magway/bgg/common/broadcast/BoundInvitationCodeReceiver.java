package com.a1magway.bgg.common.broadcast;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * 绑定邀请码成功的广播接收器
 * Created by lyx on 2017/8/30.
 */
public abstract class BoundInvitationCodeReceiver extends BaseBroadcastReceiver {
    private static final String MM_BOUND_INVITATION_CODE_SUCCESS_ACTION = "mm_bound_invitation_code_success";

    /**
     * 绑定邀请码成功
     *
     * @param context
     */
    public static void sendBoundInvitationCodeBroadcast(Context context) {
        Intent intent = new Intent(MM_BOUND_INVITATION_CODE_SUCCESS_ACTION);
        context.sendBroadcast(intent);
    }

    @Override
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MM_BOUND_INVITATION_CODE_SUCCESS_ACTION);
        return intentFilter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        onBoundSuccess();
    }

    /**
     * 绑定邀请码成功
     */
    public abstract void onBoundSuccess();

}
