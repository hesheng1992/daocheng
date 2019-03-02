package com.a1magway.bgg.di.module.search;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.data.entity.PresetFilterRule;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ISearchData;
import com.a1magway.bgg.data.repository.ISearchRecordsData;
import com.a1magway.bgg.data.repository.NetSearchData;
import com.a1magway.bgg.data.repository.cache.CacheSearchRecordsData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.p.brand.BrandItem;
import com.a1magway.bgg.v.search.SearchActivity;
import com.a1magway.bgg.v.search.SearchFragManager;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/7/31.
 */
@Module
public class SearchModule {

    private FragmentManager mFragmentManager;
    private Intent mIntent;

    public SearchModule(FragmentManager fragmentManager, Intent intent) {
        mFragmentManager = fragmentManager;
        mIntent = intent;
    }

    @Provides
    public SearchFragManager provideSearchFragManager() {
        return new SearchFragManager(mFragmentManager);
    }

    @PerActivity
    @Provides
    public ISearchRecordsData provideSearchRecordsData(Context context) {
        return new CacheSearchRecordsData(context);
    }

    @PerActivity
    @Provides
    public ISearchData provideISearchData(APIManager apiManager) {
        return new NetSearchData(apiManager);
    }

    @PerActivity
    @Provides
    public List<FilterCate1> provideFilterCatesData() {
        //用一个list来存放即将从服务器获取的数据，好让subComponent的也能直接获取到该数据
        return new ArrayList<>();
    }

    @PerActivity
    @Provides
    public PresetFilterRule providePresetFilterRule() {
        //保存一个全局的预设规则，通过改变他的值来控制关键字是否有效
        PresetFilterRule presetFilterRule = new PresetFilterRule();
        int cate1Id = mIntent.getIntExtra(SearchActivity.EXTRA_PRESET_CATE1_ID, -3);
        BrandItem brandItem = (BrandItem) mIntent.getSerializableExtra(SearchActivity.EXTRA_PRESET_BRAND);
        //男士-2 女士-1 相当于自行搜索一次 id用来区分男女的显示
        presetFilterRule.setCate1Id(cate1Id);
        if (cate1Id > 0) {
            presetFilterRule.setValid(true);
        }
        if (brandItem != null) {
            presetFilterRule.setValid(true);
            presetFilterRule.setBrandId(brandItem.getId());
            presetFilterRule.setBrandName(brandItem.getName());
        }

        return presetFilterRule;
    }
}
