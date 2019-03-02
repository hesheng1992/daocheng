package com.a1magway.bgg.di.component.search;

import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.search.RecordsModule;
import com.a1magway.bgg.di.module.search.SearchModule;
import com.a1magway.bgg.di.module.search.SearchResultModule;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.v.search.SearchActivity;

import dagger.Component;

/**
 * Created by jph on 2017/7/31.
 */
@PerActivity
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity searchActivity);

    RecordsComponent getRecordsComponent(RecordsModule recordsModule);


    SearchResultComponent getSearchResultComponent(SearchResultModule searchResultModule);
}
