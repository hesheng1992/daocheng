package com.a1magway.bgg.common;

import android.content.Context;

import com.a1magway.bgg.util.ErrorUtil;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 处理了服务器错误的单独的error consumer
 * Created by jph on 2017/8/16.
 */
public class APIOnError implements Consumer<Throwable> {
    private Context mContext;

    public APIOnError(Context context) {
        mContext = context;
    }

    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        ErrorUtil.processAPIError(mContext, throwable);
    }
}
