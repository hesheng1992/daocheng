package com.a1magway.bgg.di.module;


import android.support.v4.app.FragmentManager;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMainData;
import com.a1magway.bgg.data.repository.NetMainData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.main.IMainV;
import com.a1magway.bgg.widget.TabBar;

import dagger.Module;
import dagger.Provides;

/**
 * 对应主体框架页
 * Created by jph on 2017/7/20.
 */
@Module
public class MainModule {

    private IMainV mMainV;
    private FragmentManager mFragmentManager;
    private TabBar mTabBar;

    public MainModule(IMainV mainV, FragmentManager fragmentManager, TabBar tabBar) {
        mMainV = mainV;
        mFragmentManager = fragmentManager;
        mTabBar = tabBar;
    }

    @PerActivity
    @Provides
    public FragmentManager provideFragmentManager() {
        return mFragmentManager;
    }

    @PerActivity
    @Provides
    public TabBar provideTabBar() {
        return mTabBar;
    }

    @Provides
    public IMainV provideMainV() {
        return mMainV;
    }

    @PerActivity
    @Provides
    public IMainData provideMainData(APIManager apiManager) {
        return new NetMainData(apiManager);
    }
}
