package com.a1magway.bgg.util.pay;

import android.app.Activity;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jph on 2017/8/21.
 */
public class AliPay {

    private Activity mActivity;

    public AliPay(Activity activity) {
        mActivity = activity;
    }

    public Observable<Map<String, String>> pay(final String orderInfo) {
        return Observable
                .create(new ObservableOnSubscribe<Map<String, String>>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Map<String, String>> e) throws Exception {
                        PayTask payTask = new PayTask(mActivity);
                        Map<String, String> resultMap = payTask.payV2(orderInfo, true);
                        e.onNext(resultMap);
                        e.onComplete();
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
