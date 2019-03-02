package com.a1magway.bgg.di.module.search;

import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.p.search.FilterCate1Adapter;
import com.a1magway.bgg.v.search.IFilterV;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/3.
 */
@Module
public class FilterModule {

    private IFilterV mFilterV;

    public FilterModule(IFilterV filterV) {
        mFilterV = filterV;
    }

    @Provides
    public IFilterV provideFilterV() {
        return mFilterV;
    }

    @Provides
    public FilterCate1Adapter provideFilterCate1Adapter() {
        return new FilterCate1Adapter(new ArrayList<FilterCate1>());
    }
}
