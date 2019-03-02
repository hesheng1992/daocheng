package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.entity.ActivityData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IHomeData;
import com.a1magway.bgg.data.repository.NetHomeData;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.p.home.HomeFeedsAdapter;
import com.a1magway.bgg.v.home.HomeHeaderVO;
import com.a1magway.bgg.v.home.IHomeV;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/7/24.
 */
@Module
public class HomeModule {

    private IHomeV mHomeV;

    public HomeModule(IHomeV homeV) {
        mHomeV = homeV;
    }

    @PerFragment
    @Provides
    public IHomeV provideHomeV() {
        return mHomeV;
    }

    @PerFragment
    @Provides
    public IHomeData provideHomeData(APIManager apiManager) {
        return new NetHomeData(apiManager);
    }

    @PerFragment
    @Provides
    public HomeFeedsAdapter provideHomeFeedsAdapter() {
        return new HomeFeedsAdapter(new ArrayList<ActivityData>());
    }

    @PerFragment
    @Provides
    public HomeHeaderVO provideHomeHeaderVO() {
        return new HomeHeaderVO(mHomeV.getContext());
    }
}
