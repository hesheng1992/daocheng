package com.a1magway.bgg.refactor;

import android.content.Context;
import android.support.annotation.NonNull;
import com.a1magway.bgg.common.broadcast.BaseBroadcastReceiver;
import com.a1magway.bgg.p.RxPresenter;

/**
 * author: Beaven
 * date: 2017/10/31 14:51
 */

public abstract class BasePresenter<V extends BaseContract.BaseView> extends RxPresenter {

    protected V mView;


    public BasePresenter(@NonNull V view) {
        mView = view;
    }


    public Context getContext() {
        return mView.getActivity();
    }


    public void registerBroadCast(BaseBroadcastReceiver... broadcastReceivers) {
        if (broadcastReceivers != null && broadcastReceivers.length > 0) {
            for (BaseBroadcastReceiver broadcastReceiver : broadcastReceivers) {
                getContext().registerReceiver(broadcastReceiver,
                    broadcastReceiver.getIntentFilter());
            }
        }
    }


    public void unRegisterBroadCast(BaseBroadcastReceiver... broadcastReceivers) {
        if (broadcastReceivers != null && broadcastReceivers.length > 0) {
            for (BaseBroadcastReceiver broadcastReceiver : broadcastReceivers) {
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

