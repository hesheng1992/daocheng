package com.a1magway.bgg.di.component.search;

import com.a1magway.bgg.di.module.search.FilterCateModule;
import com.a1magway.bgg.di.module.search.FilterModule;
import com.a1magway.bgg.di.module.search.SearchResultModule;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.search.ResultFragment;

import dagger.Subcomponent;

/**
 * Created by jph on 2017/7/31.
 */
@PerFragment
@Subcomponent(modules = SearchResultModule.class)
public interface SearchResultComponent {

    void inject(ResultFragment resultFragment);

    FilterComponent getFilterComponent(FilterModule filterModule);

    FilterCateComponent getFilterCateComponent(FilterCateModule filterCateModule);
}
