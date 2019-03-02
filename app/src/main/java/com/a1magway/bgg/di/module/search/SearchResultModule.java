package com.a1magway.bgg.di.module.search;

import android.support.v4.app.FragmentManager;

import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;
import com.a1magway.bgg.v.search.ISearchResultV;
import com.a1magway.bgg.v.search.ResultFragManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/7/31.
 */
@Module
public class SearchResultModule {

    private ISearchResultV mSearchResultV;
    private FragmentManager mFragmentManager;

    public SearchResultModule(ISearchResultV searchResultV, FragmentManager fragmentManager) {
        mSearchResultV = searchResultV;
        mFragmentManager = fragmentManager;
    }

    @Provides
    public ISearchResultV provideISearchResultV() {
        return mSearchResultV;
    }

    @Provides
    public ProductGridAdapter provideProductGridAdapter() {
        return new ProductGridAdapter(new ArrayList<ProductBean>());
    }

    @PerFragment
    @Provides
    public ResultFragManager provideIResultFragManage() {
        return new ResultFragManager(mFragmentManager);
    }
}
