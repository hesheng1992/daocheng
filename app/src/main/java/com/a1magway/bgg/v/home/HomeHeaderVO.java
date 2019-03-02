package com.a1magway.bgg.v.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseViewOwner;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.v.member.MemberGoodsActivity;
import com.a1magway.bgg.v.seckill.SecKillActivity;
import com.a1magway.bgg.widget.banner.BannerView;
import com.youth.banner.listener.OnBannerListener;
import java.util.List;

/** 首页顶部header的逻辑控制 Created by jph on 2017/7/24. */
public class HomeHeaderVO extends BaseViewOwner {

    @BindView(R.id.home_banner)
    BannerView mBannerView;

    @BindView(R.id.text_seckill_countdown_time)
    TextView textCountdown;

    @BindView(R.id.image_member_entrance_large)
    ImageView imageMemberLarge;

    @BindView(R.id.image_member_entrance)
    ImageView imageMember;

    @BindView(R.id.layout_spike)
    LinearLayout layoutSeckill;

    public HomeHeaderVO(@NonNull Context context) {
        super(context, R.layout.home_header);
    }

    @OnClick(R.id.layout_spike)
    public void onClickMoreSecKill(View v) {
        SecKillActivity.start(v.getContext());
    }

    @OnClick({R.id.image_member_entrance, R.id.image_member_entrance_large})
    public void onClickMemberEntrance(View v) {
        MemberGoodsActivity.start(v.getContext());
    }

    public void showBannerContent(List<String> urls, OnBannerListener onBannerListener) {
        mBannerView
                .setDelayTime(3000)
                .setImages(urls)
                .setOnBannerListener(onBannerListener)
                .start();
    }

    public void startAutoPlayBanner() {
        mBannerView.startAutoPlay();
    }

    public void stopAutoPlayBanner() {
        mBannerView.stopAutoPlay();
    }

    public void setSeckillCountdown(String time) {
        textCountdown.setText(time);
    }

    public TextView getTextCountdown() {
        return textCountdown;
    }

    public void showMemberEnterLarge(boolean isShow) {
        if (isShow) {
            layoutSeckill.setVisibility(View.GONE);
            imageMember.setVisibility(View.GONE);
            imageMemberLarge.setVisibility(View.VISIBLE);
        } else {
            layoutSeckill.setVisibility(View.VISIBLE);
            imageMember.setVisibility(View.VISIBLE);
            imageMemberLarge.setVisibility(View.GONE);
        }
    }
    /**
     * 显示秒杀内容
     *
     * @param adapter
     */
    public void showSeKillContent(BaseRecyclerAdapter adapter) {}
}
