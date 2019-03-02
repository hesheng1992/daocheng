package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ISecKillData;
import com.a1magway.bgg.data.repository.NetSecKillData;
import com.a1magway.bgg.p.seckill.ProductGridAdapter;
import com.a1magway.bgg.v.seckill.ISecKillV;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/7/28.
 */
@Module
public class SecKillModule {

    private ISecKillV mSecKillV;
    private int mSecKillType;

    public SecKillModule(ISecKillV secKillV, int secKillType) {
        mSecKillV = secKillV;
        mSecKillType = secKillType;
    }

    @Provides
    public ISecKillV provideSecKillV() {
        return mSecKillV;
    }

    @Provides
    public ISecKillData provideSecKillData(APIManager apiManager) {
        return new NetSecKillData(apiManager);
    }

    @Provides
    public ProductGridAdapter provideProductGridAdapter() {
        return new ProductGridAdapter(new ArrayList<ProductItem>());
    }

    @Provides
    public int provideSeckillType() {
        return mSecKillType;
    }
}
