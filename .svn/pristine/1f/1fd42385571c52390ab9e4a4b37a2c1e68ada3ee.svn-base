package com.a1magway.bgg.v.home;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.youth.banner.listener.OnBannerListener;
import java.util.List;

/** Created by jph on 2017/7/24. */
public interface IHomeV extends ILoadingV {

    void setRecyclerViewAdapter(RecyclerView.Adapter adapter);

    /**
     * 显示banner内容
     *
     * @param urls
     */
    void showBannerContent(List<String> urls, OnBannerListener onBannerListener);

    /**
     * 显示秒杀内容
     *
     * @param adapter
     */
    void showSeKillContent(BaseRecyclerAdapter adapter);

    /*
     * 显示秒杀倒计时
     */
    void showSeckillCountdown(String time);

    void showMemberEnterLarge(boolean isShow);
    /** 停止刷新 */
    void stopRefresh();

    TextView getCountView();
}
