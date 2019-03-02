package com.a1magway.bgg.common;

import android.content.Context;

import io.reactivex.annotations.NonNull;

/**
 * 不需要复写所有回调方法的Observer
 * Created by jph on 2017/8/23.
 */
public abstract class SimpleObserver<T> extends BaseObserver<T> {
    public SimpleObserver(Context context) {
        super(context);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        onFinish();
    }

    @Override
    public void onNext(@NonNull T t) {

    }

    @Override
    public void onComplete() {
        onFinish();

    }

    /**
     * 无论是onError还是onComplete最后都会回调该方法,但是会在复写的onError和onComplete内容之前执行
     */
    public void onFinish() {

    }
}
