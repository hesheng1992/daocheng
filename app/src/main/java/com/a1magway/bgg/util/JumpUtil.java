package com.a1magway.bgg.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.a1magway.bgg.v.login.LoginActivity;
import com.a1magway.bgg.v.invitation.InvitationActivity;


/**
 * 界面跳转工具类
 * Created by jph on 2017/8/2.
 */
public class JumpUtil {

    /**
     * 处理了ApplicationContext打开界面的问题
     *
     * @param context
     * @param intent
     */
    public static void startActivity(@NonNull Context context, @NonNull Intent intent) {
        if (!Activity.class.isInstance(context)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 判断是否登录,如果未登录跳转登录界面
     *
     * @param context
     * @return 是否处理了未登录状态，一般处理了就不继续下一步操作
     */
    public static boolean processCheckLogin(Context context) {
        boolean isLogin = GlobalData.getInstance().isLogin();
        if (!isLogin) {
            LoginActivity.start(context);
            return true;
        }
        return false;
    }

    /**
     * 判断是否绑定邀请码，如果未绑定跳转绑定邀请码界面
     * @param context
     * @return
     */
    public static boolean processCheckInvited(Context context){
        boolean isInvited = GlobalData.getInstance().isBoundInvitationCode();
        if (!isInvited){
            InvitationActivity.start(context);
            return true;
        }
        return false;
    }

    /**
     * 处理了ApplicationContext打开界面的问题
     *
     * @param context
     * @param intent
     */
    public static void startActivityForResult(@NonNull Context context, @NonNull Intent intent, int requestCode) {
        if (!Activity.class.isInstance(context)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return;
        }
        ((Activity) context).startActivityForResult(intent, requestCode);
    }
}
