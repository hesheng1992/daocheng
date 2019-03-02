package com.a1magway.bgg.di.module;

import android.content.Context;

import com.a1magway.bgg.util.GlobalData;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/7/19.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }

//    @Provides
//    @Singleton
//    public APIManager provideAPIManager() {
//
//        return new APIManager(commonParams);
//    }

    @Named(value = "CommonParams")
    @Provides
    public Map<String, Object> provideCommonParams() {
        Map<String, Object> commonParams = new HashMap<>();
//        commonParams.put("userId", GlobalData.getInstance().getUserId());
        commonParams.put("user_id", GlobalData.getInstance().getUserId());
        return commonParams;
    }

}
