package com.a1magway.bgg.di.module.search;

import com.a1magway.bgg.v.search.IFilterCateV;

import java.io.Serializable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/3.
 */
@Module
public class FilterCateModule {

    private IFilterCateV mFilterCateV;
    private Serializable mCate1Entity;

    public FilterCateModule(IFilterCateV filterCateV, Serializable cate1Entity) {
        mFilterCateV = filterCateV;
        mCate1Entity = cate1Entity;
    }

    @Provides
    public IFilterCateV provideFilterCateV() {
        return mFilterCateV;
    }

    @Provides
    public Serializable provideCate1Entity() {
        return mCate1Entity;
    }

}
