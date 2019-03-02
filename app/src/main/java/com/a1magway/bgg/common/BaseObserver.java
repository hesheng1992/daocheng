package com.a1magway.bgg.common;

import android.content.Context;

import com.a1magway.bgg.util.ErrorUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * 统一处理api请求的基类
 * Created by jph on 2017/7/20.
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> {

    private Context mContext;

    public BaseObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onError(@NonNull Throwable e) {
        ErrorUtil.processAPIError(mContext, e);
    }
}
