package com.a1magway.bgg.v.member;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.member.DaggerMemberUpgradeInfoComponent;
import com.a1magway.bgg.di.module.member.MemberUpgradeInfoModule;
import com.a1magway.bgg.p.member.MemberUpgradeInfoP;
import com.a1magway.bgg.refactor.LoadingView;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.StringFormatUtil;

public class MemberUpgradeInfoActivity extends PresenterActivity<MemberUpgradeInfoP>
    implements MemberUpgradeInfoContract.View {

    public static void start(Context context) {
        Intent intent = new Intent(context, MemberUpgradeInfoActivity.class);
        context.startActivity(intent);
    }


    @BindView(R.id.view_load)
    LoadingView loadingView;
    @BindView(R.id.recycler_member_upgrade_info)
    RecyclerView recyclerInfo;
    @BindView(R.id.layout_member_list_price)
    LinearLayout layoutListPrice;
    @BindView(R.id.text_member_list_price)
    TextView textListPrice;
    @BindView(R.id.text_member_sell_price)
    TextView textSellPrice;


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_member_upgrade_info;
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerMemberUpgradeInfoComponent.builder()
            .appComponent(appComponent)
            .memberUpgradeInfoModule(new MemberUpgradeInfoModule(this))
            .build().inject(this);
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle(R.string.member_upgrade_info_title);
        recyclerInfo.setLayoutManager(new LinearLayoutManager(this));
        recyclerInfo.setHasFixedSize(true);
    }


    @OnClick(R.id.bt_member_upgrade)
    public void clickMemberUpgrade(View view) {
        MemberRegisterActivity.start(this, presenter.getMemberUpgradeInfo());
    }


    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        recyclerInfo.setAdapter(adapter);
    }


    /**
     * 设置正常价格显示，没有折扣价
     */
    @Override
    public void setPriceNormal(MemberUpgradeInfo info) {
        layoutListPrice.setVisibility(View.GONE);
        String text = StringFormatUtil.getPrice(this, String.valueOf(info.getSellPrice())) +
            getResources().getString(R.string.per_year);
        textSellPrice.setText(text);
    }


    /**
     * 设置折扣价格显示
     */
    @Override
    public void setPriceSell(MemberUpgradeInfo info) {
        layoutListPrice.setVisibility(View.VISIBLE);
        String sellPrice =
            StringFormatUtil.getPrice(this, String.valueOf(info.getSellPrice())) +
                getResources().getString(R.string.per_year);
        String listPrice =
            StringFormatUtil.getPrice(this, String.valueOf(info.getListPrice())) +
                getResources().getString(R.string.per_year);
        textSellPrice.setText(sellPrice);
        textListPrice.setText(listPrice);
        textListPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }


    @Override public void showLoading(@Nullable String text) {
        loadingView.showLoading();
    }


    @Override public void hideLoading() {
        loadingView.showContent();
    }


    @Override public void showError() {
        loadingView.showError();
    }


    @Override public void showEmpty() {
        loadingView.showEmpty();
    }
}
