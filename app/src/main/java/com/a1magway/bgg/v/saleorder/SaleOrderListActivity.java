package com.a1magway.bgg.v.saleorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.common.FragmentInPager;
import com.a1magway.bgg.common.adapter.FragPageAdapter;
import com.a1magway.bgg.data.entity.OrderType;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 销售订单列表界面
 * Created by enid on 2018/06/20.
 */
public class SaleOrderListActivity extends BaseActivity {
    @BindView(R.id.order_list_title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.order_list_layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.order_list_pager)
    ViewPager mViewPager;

    public static void start(Context context) {
        Intent starter = new Intent(context, SaleOrderListActivity.class);
        JumpUtil.startActivity(context, starter);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.order_activity_list;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        List<Fragment> fragList = new ArrayList<>();

        FragmentInPager frag1 = SaleOrderListFragment.newInstance(OrderType.ALL);
        frag1.setTitle(getString(R.string.order_list_title_all));
        fragList.add(frag1);

        FragmentInPager frag2 = SaleOrderListFragment.newInstance(OrderType.WAIT_PAY);
        frag2.setTitle(getString(R.string.order_list_title_wait_pay));
        fragList.add(frag2);

        FragmentInPager frag3 = SaleOrderListFragment.newInstance(OrderType.WAIT_DELIVER);
        frag3.setTitle(getString(R.string.order_list_title_payed));
        fragList.add(frag3);

        FragmentInPager frag4 = SaleOrderListFragment.newInstance(OrderType.DELIVERED);
        frag4.setTitle(getString(R.string.order_list_title_delivered));
        fragList.add(frag4);

        FragmentInPager frag5 = SaleOrderListFragment.newInstance(OrderType.COMPLETED);
        frag5.setTitle(getString(R.string.order_list_title_finish));
        fragList.add(frag5);

        mViewPager.setAdapter(new FragPageAdapter(getSupportFragmentManager(), fragList));
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
