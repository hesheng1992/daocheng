package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.entity.CateItem;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ICateData;
import com.a1magway.bgg.data.repository.NetCateData;
import com.a1magway.bgg.p.cate.CatesAdapter;
import com.a1magway.bgg.v.cate.ICateV;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/7/19.
 */
@Module
public class CateModule {

    ICateV mCateV;

    public CateModule(ICateV cateV) {
        mCateV = cateV;
    }

    @Provides
    public ICateV provideCateV() {
        return mCateV;
    }

    @Provides
    public ICateData provideCateData(APIManager apiManager) {
        return new NetCateData(apiManager);
    }

    @Provides
    public CatesAdapter provideCatesAdapter() {
        return new CatesAdapter(new ArrayList<CateItem>());
    }
}
