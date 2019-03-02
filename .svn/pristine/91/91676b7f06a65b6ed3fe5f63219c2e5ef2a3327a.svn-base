package com.a1magway.bgg.data.net;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加公共参数的拦截器
 * Created by jph on 2017/8/23.
 */
public class CommonParamInterceptor implements Interceptor {

    @Inject
    @Named(value = "CommonParams")
    Provider<Map<String, Object>> mCommonParamsProvider;

    @Inject
    public CommonParamInterceptor() {
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request oldRequest = chain.request();

        HttpUrl.Builder oldBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        Set<Map.Entry<String, Object>> entrySet = mCommonParamsProvider.get().entrySet();
        for (Map.Entry<String, Object> entry :
                entrySet) {
            // 添加新的参数
            oldBuilder.addQueryParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }

        // 新的请求
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(oldBuilder.build())
                .build();

        return chain.proceed(newRequest);
    }

}
