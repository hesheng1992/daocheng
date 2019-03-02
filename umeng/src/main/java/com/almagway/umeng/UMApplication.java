package com.almagway.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.QueuedWork;

/**
 * author: Beaven
 * date: 2017/10/23 10:21
 */

public class UMApplication extends Application {

    private static final String UM_PUSH_SECRET = "a947b4beb2c029c2dbb044a94d2bacc7";

    @Override
    public void onCreate() {
        super.onCreate();
//        Config.DEBUG = false;
        QueuedWork.isUseThreadPool = false;
        UMShareAPI.get(this);
        initUmeng();
    }

    {
//         PlatformConfig.setWeixin("wx012236a6e4769040", "8a6cc3df65f3e306c538a7eec9ef2e46");;//预发布测试用的
        PlatformConfig.setWeixin("wxa206377b33012c03", "ebe77f50e4c7a260edee34a871b20526");//正式环境的
        PlatformConfig.setQQZone("1106342893", "PW2009l2hti5diNa");
        PlatformConfig.setSinaWeibo("3248261258", "2187ebfd93c215c50462154ccd3981a8", "http://sns.whalecloud.com");
    }

    private void initUmeng() {
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, UM_PUSH_SECRET);
        UMConfigure.setLogEnabled(true);
        UMConfigure.setEncryptEnabled(false);
    }
}
