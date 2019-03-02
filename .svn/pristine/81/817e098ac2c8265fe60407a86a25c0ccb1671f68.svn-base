package com.a1magway.bgg.v.mainhome;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.refactor.BaseContract;

import java.util.List;

/**
 * Created by enid on 2018/6/6.
 */

public interface SpecialProductContract {
    interface View extends ILoadingV {
        int getId();

        void setAdapter(RecyclerView.Adapter adapter);

        void setTitle(String title,long time);

        void stopRefresh();

        void stopLoadMore();

        void startRefresh();

        void setLoadable(boolean loadable);

        String getComeFrom();

        void setBannerData(List<String> banners);
    }

    interface Presenter extends BaseContract.BasePresenter {
    }
}
