package com.a1magway.bgg.okhttp;

import android.os.Handler;

import com.google.gson.Gson;

import io.reactivex.ObservableEmitter;
import okhttp3.Response;

/**
 * Created on 2018/5/28 0028.
 */

class RxResponse {
    static <T> void onResponse(String body, final ObservableEmitter<T> observableEmitter, Class<T> c, Handler mDelivery) {
        if (observableEmitter != null && !observableEmitter.isDisposed()) {
            T t = null;
            try {
                t = new Gson().fromJson(body, c);
            } catch (Exception e) {
                LogUtil.e("rxResponse", "jsonDataResolveError");
                e.printStackTrace();
            }
            final T finalT = t;
            mDelivery.post(new Runnable() {
                @Override
                public void run() {
                    observableEmitter.onNext(finalT);
                    observableEmitter.onComplete();
                }
            });
        }
    }


    static <T> void onResponse(final ObservableEmitter<T> observableEmitter, final Response response, Handler mDelivery) {
        if (observableEmitter != null && !observableEmitter.isDisposed()) {
            mDelivery.post(new Runnable() {
                @Override
                public void run() {
                    observableEmitter.onNext((T) response);
                    observableEmitter.onComplete();
                }
            });
        }
    }


    /**
     * ObservableEmitter must be implemented onError() else throw OnErrorNotImplementedException
     */
    static <T> void onFailure(Exception e, ObservableEmitter<T> observableEmitter) {
        observableEmitter.onError(e);
    }
}
