package com.a1magway.bgg.di.component.search;

import com.a1magway.bgg.di.module.search.FilterModule;
import com.a1magway.bgg.v.search.FilterFragment;

import dagger.Subcomponent;

/**
 * Created by jph on 2017/8/3.
 */
@Subcomponent(modules = FilterModule.class)
public interface FilterComponent {

    void inject(FilterFragment filterFragment);
}
