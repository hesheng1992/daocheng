package com.a1magway.bgg.p.home;

import android.support.annotation.Nullable;
import com.a1magway.bgg.data.entity.ActivityData;
import com.a1magway.bgg.data.entity.HomeData;
import com.a1magway.bgg.data.repository.IHomeData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.DateUtils;
import com.a1magway.bgg.v.home.IHomeV;
import com.a1magway.bgg.v.web.WebActivity;
import com.almagway.common.utils.CollectionUtil;
import com.almagway.common.utils.TimeUtils;
import com.youth.banner.listener.OnBannerListener;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

/** 首页对应的Presenter Created by jph on 2017/7/24. */
public class HomeP extends BaseLoadP<HomeData, IHomeV> {

    private IHomeData mHomeData;

    private HomeFeedsAdapter mHomeFeedsAdapter;
    private TimeCountDownController timeCountDownController;

    @Inject
    public HomeP(IHomeV homeV, IHomeData homeData, HomeFeedsAdapter homeFeedsAdapter) {
        super(homeV);
        mHomeData = homeData;
        mHomeFeedsAdapter = homeFeedsAdapter;
    }

    @Nullable
    @Override
    public Observable<HomeData> getDataObservable() {
        return mHomeData.getHomeData();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mView.setRecyclerViewAdapter(mHomeFeedsAdapter);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 避免倒计时内存泄漏
        mView.showSeKillContent(null);
        cancelCountDown();
    }

    /** 重新加载数据 */
    public void reload() {
        loadData(false);
    }

    @Override
    protected void onLoadSuccess(HomeData homeData) {
        super.onLoadSuccess(homeData);

        // 显示banner
        showBannerContent(homeData.getBannerList());

        // 显示feed流
        mHomeFeedsAdapter.setList(homeData.getFeedList());

        // 秒杀倒计时
        String countdownTime = homeData.getSeckillCountdown();
        if (countdownTime.equals(TimeUtils.DEFAULT_DATE)) {
            mView.getCountView().setText(TimeCountDownController.TIME_DEFAULT);
            mView.showMemberEnterLarge(true);
            return;
        }
        mView.showMemberEnterLarge(false);
        Date time = DateUtils.formatDate(countdownTime, DateUtils.TIME_DEFAULT_ONE);
        cancelCountDown();
        timeCountDownController =
                new TimeCountDownController(
                                time,
                                mView.getCountView(),
                                null,
                                new TimeCountDownController.CountDownListener() {
                                    @Override
                                    public void onFinish() {
                                        reload();
                                    }
                                })
                        .start();
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
    }

    /** 显示banner内容，将获取到的数据转换成显示层需要的结构 */
    private void showBannerContent(final List<ActivityData> bannerList) {
        if (CollectionUtil.isEmpty(bannerList)) {
            return;
        }

        final List<String> bannerUrlList = new ArrayList<>();
        for (ActivityData banner : bannerList) {
            bannerUrlList.add(banner.getBannerPathBig());
        }

        mView.showBannerContent(
                bannerUrlList,
                new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        WebActivity.start(
                                getContext(),
                                bannerList.get(position).getHtmlPath(),
                                bannerList.get(position).getTitle(),
                                true);
                    }
                });
    }

    private void cancelCountDown() {
        if (timeCountDownController != null) {
            timeCountDownController.cancel();
            timeCountDownController = null;
        }
    }
}
