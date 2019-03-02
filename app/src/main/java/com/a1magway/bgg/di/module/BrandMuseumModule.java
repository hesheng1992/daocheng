package com.a1magway.bgg.di.module;

import android.content.Intent;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IBrandMuseumData;
import com.a1magway.bgg.data.repository.NetBrandMuseumData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.brand.BrandAdapter;
import com.a1magway.bgg.v.cate.BrandMuseumActivity;
import com.a1magway.bgg.v.cate.BrandMuseumContract;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by jph on 2017/7/29.
 */
@Module
public class BrandMuseumModule {

    private BrandMuseumContract.View mBrandMuseumV;
    private Intent mIntent;


    public BrandMuseumModule(BrandMuseumContract.View brandMuseumV, Intent intent) {
        mBrandMuseumV = brandMuseumV;
        mIntent = intent;
    }


    @PerActivity
    @Provides
    public IBrandMuseumData provideBrandMuseumData(APIManager apiManager) {
        return new NetBrandMuseumData(apiManager);
    }


    @PerActivity
    @Provides
    public BrandMuseumContract.View provideBrandMuseumV() {
        return mBrandMuseumV;
    }


    @PerActivity
    @Provides
    public BrandAdapter provideBrandAdapter() {
        return new BrandAdapter();
    }


    @PerActivity
    @Named(value = "IsSelect")
    @Provides
    public boolean provideIsSelect() {
        return mIntent.getBooleanExtra(BrandMuseumActivity.EXTRA_IS_SELECT, false);
    }
}
