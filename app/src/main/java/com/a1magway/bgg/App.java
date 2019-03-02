package com.a1magway.bgg;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerAppComponent;
import com.a1magway.bgg.di.module.AppModule;
import com.a1magway.bgg.huanxin.DemoHelper;
import com.a1magway.bgg.okhttp.LogUtil;
import com.almagway.common.log.DebugLog;
import com.almagway.common.log.MLog;
import com.almagway.common.log.ReleaseLog;
import com.almagway.common.utils.ApplicationUtil;
import com.almagway.umeng.UMApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.Alias;
import com.umeng.message.entity.UMessage;


/**
 * Created by jph on 2017/7/19.
 */
public class App extends UMApplication {
    public static volatile App appInstance;
    public static final String ALIAS_TYPE = "MMShop";

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @NonNull
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.primaryColor, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.primaryColor, android.R.color.black);//全局设置主题颜色
                return new ClassicsFooter(context);
            }
        });
    }


    @SuppressLint("StaticFieldLeak")
    private static Context mContext;
    private static AppComponent mAppComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        initUmengPush();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        //如果是debug则内存泄露检测的app
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mContext = this;
        ApplicationUtil.initUtil(this);
        // Log设置
        if (BuildConfig.DEBUG) {
            MLog.plat(new DebugLog());
        } else {
            MLog.plat(new ReleaseLog());
        }
        DemoHelper.getInstance().init(this);
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    public static Context getContext() {
        return mContext;
    }

    public static App getInstance() {
        if (appInstance == null) {
            appInstance = new App();
        }
        return appInstance;
    }

    /**
     * 初始化友盟推送
     */
    private PushAgent mPushAgent;
    private Handler handler;

    private void initUmengPush() {
        mPushAgent = PushAgent.getInstance(this);
        handler = new Handler(getMainLooper());
        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        UmengMessageHandler messageHandler = new UmengMessageHandler() {

            /**
             * 通知的回调方法（通知送达时会回调）
             */
            @Override
            public void dealWithNotificationMessage(Context context, UMessage msg) {
                //调用super，会展示通知，不调用super，则不展示通知。
                super.dealWithNotificationMessage(context, msg);
            }

            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            /**
             * 自定义通知栏样式的回调方法
             */
            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon, getLargeIcon(context, msg));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, msg));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, msg))
                                .setTicker(msg.ticker)
                                .setAutoCancel(true);

                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void launchApp(Context context, UMessage msg) {
                super.launchApp(context, msg);
            }

            @Override
            public void openUrl(Context context, UMessage msg) {
                super.openUrl(context, msg);
            }

            @Override
            public void openActivity(Context context, UMessage msg) {
                super.openActivity(context, msg);
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                LogUtil.i("device_token", s);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });

    }

    //登录成功设置用户别名
    public void addUMengUserAlias(String alias) {
        if (mPushAgent != null) {
            //设置用户别名
            mPushAgent.addAlias(alias, ALIAS_TYPE, new UTrack.ICallBack() {
                @Override
                public void onMessage(boolean b, String s) {
                    LogUtil.e("UMengUserAlias", b + " " + s);
                }
            });
        }
    }


    //退出登录删除用户别名
    public void deleteUMengUserAlias(String alias) {
        if (mPushAgent != null) {
            //退出登录删除用户别名
            mPushAgent.deleteAlias(alias, ALIAS_TYPE, new UTrack.ICallBack() {
                @Override
                public void onMessage(boolean isSuccess, String message) {
                    LogUtil.e("deleteUMengUserAlias", isSuccess + " " + message);
                }
            });
        }
    }
}
