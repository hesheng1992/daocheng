package com.a1magway.bgg.v.mainhome;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.data.entity.PingtuanBannerData;
import com.a1magway.bgg.widget.CycleGalleryViewPager;
import com.a1magway.bgg.widget.PingtuanBannerIndicator;
import com.a1magway.bgg.widget.PingtuanCountDownTextView;

/**
 * Created by enid on 2018/6/11.
 */

public interface IHomeSpecialV extends ILoadingV {
    void setSpecialRecyclerAdapter(RecyclerView.Adapter adapter);

    void setRecommendRecyclerAdapter(RecyclerView.Adapter adapter);

    HomeRecommendHeader getHeader();

    void loadComplete();

    void setLoadable(boolean loadable);
    void stopLoadMore();

    CycleGalleryViewPager getPingtuanViewPager();

    void setPingtuanTitle(String title);

    PingtuanBannerIndicator getpingtuanBannerIndicator();

    void setPingtuanDownTime(long engTime);

    void showBannerPingtuanMore();

}
