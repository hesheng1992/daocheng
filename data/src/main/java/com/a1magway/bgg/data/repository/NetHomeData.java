package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.ActivityData;
import com.a1magway.bgg.data.entity.HomeData;
import com.a1magway.bgg.data.net.APIManager;
import com.almagway.common.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;

/**
 * 从网络获取首页的数据
 * Created by jph on 2017/7/24.
 */
public class NetHomeData implements IHomeData {

    private APIManager mAPIManager;

    public NetHomeData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<HomeData> getHomeData() {
        //将三个接口组合成一个Observable
        return Observable.zip(
                mAPIManager.getHomeBanners().onErrorReturnItem(new ArrayList<ActivityData>()),
                mAPIManager.getHomeFeeds().onErrorReturnItem(new ArrayList<ActivityData>()),
                mAPIManager.getSeckillCountdown()
                        .defaultIfEmpty(TimeUtils.DEFAULT_DATE)
                        .onErrorReturnItem(TimeUtils.DEFAULT_DATE),
                new Function3<List<ActivityData>, List<ActivityData>, String, HomeData>() {
                    @Override
                    public HomeData apply(@NonNull List<ActivityData> bannerList,
                                          @NonNull List<ActivityData> feedList,
                                          @NonNull String s) throws Exception {
                        HomeData homeData = new HomeData();
                        homeData.setBannerList(bannerList);
                        homeData.setFeedList(feedList);
                        homeData.setSeckillCountdown(s);
                        return homeData;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
