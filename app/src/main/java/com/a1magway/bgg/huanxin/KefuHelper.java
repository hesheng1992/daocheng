package com.a1magway.bgg.huanxin;

import android.app.Activity;
import android.content.Intent;

import com.a1magway.bgg.huanxin.constant.HuanxinConstant;
import com.hyphenate.helpdesk.easeui.util.IntentBuilder;

/**
 * Created by enid on 2018/6/4.
 */

public class KefuHelper {
    public static KefuHelper instance = new KefuHelper();
    private KefuHelper(){}
    public synchronized static KefuHelper getInstance() {
        return instance;
    }
    public void chatWithHefu(Activity activity){
        Intent intent = new IntentBuilder(activity)
                .setServiceIMNumber(HuanxinConstant.KEFU_IM_SERVICE_NUMBER)
                .setTitleName("客服中心")
                .setShowUserNick(true)
                .build();
        activity.startActivity(intent);
    }
}
