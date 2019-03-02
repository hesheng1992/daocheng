package com.a1magway.bgg.di.module.search;

import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.search.ISearchRecordsV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/1.
 */
@Module
public class RecordsModule {

    private ISearchRecordsV mSearchRecordsV;

    public RecordsModule(ISearchRecordsV searchRecordsV) {
        mSearchRecordsV = searchRecordsV;
    }

    @PerFragment
    @Provides
    public ISearchRecordsV provideSearchRecordsV() {
        return mSearchRecordsV;
    }
}
