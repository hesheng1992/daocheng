package com.a1magway.bgg.p;

import android.content.Context;
import android.support.annotation.NonNull;

import com.a1magway.bgg.common.broadcast.BaseBroadcastReceiver;
import com.a1magway.bgg.v.IView;

/**
 * Created by jph on 2017/8/7.
 */
public class BasePresenter<V extends IView> extends RxPresenter {

    protected V mView;

    public BasePresenter(@NonNull V view) {
        mView = view;
    }

    public Context getContext() {
        return mView.getContext();
    }

    public void registerBroadCast(BaseBroadcastReceiver... broadcastReceivers){
        if (broadcastReceivers != null && broadcastReceivers.length > 0){
            for (BaseBroadcastReceiver broadcastReceiver : broadcastReceivers){
                getContext().registerReceiver(broadcastReceiver, broadcastReceiver.getIntentFilter());
            }
        }
    }

    public void unRegisterBroadCast(BaseBroadcastReceiver... broadcastReceivers){
        if (broadcastReceivers != null && broadcastReceivers.length > 0){
            for (BaseBroadcastReceiver broadcastReceiver : broadcastReceivers){
                getContext().unregisterReceiver(broadcastReceiver);
            }
        }
    }



    @Override
    public void onDestroy() {
//        mView = null;
        super.onDestroy();
    }
}
