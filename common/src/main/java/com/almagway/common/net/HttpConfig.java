package com.almagway.common.net;

import android.support.annotation.Nullable;

import com.almagway.common.log.MLog;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * author: Beaven
 * date: 2017/10/24 18:00
 */

public class HttpConfig {

    public static OkHttpClient buildOkHttpClient(int timeout, Interceptor... interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.MILLISECONDS)
                .connectTimeout(timeout, TimeUnit.MILLISECONDS);
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }


    public static Retrofit buildRetrofit(String baseUrl, OkHttpClient client,
                                         @Nullable Converter.Factory factoryConverter,
                                         @Nullable CallAdapter.Factory factoryCallAdapter) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client);
        if (factoryCallAdapter != null) {
            builder.addCallAdapterFactory(factoryCallAdapter);
        }
        if (factoryConverter != null) {
            builder.addConverterFactory(factoryConverter);
        }
        return builder.build();
    }

    public static NetLogInterceptor buildNetLog(NetLogInterceptor.Level level) {
        NetLogInterceptor.Logger logger = new NetLogInterceptor.Logger() {
            @Override
            public void log(String message) {
                MLog.i("--Net--", message);
            }
        };
        return new NetLogInterceptor(logger).setLevel(level);
    }
}
