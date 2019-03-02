package com.a1magway.bgg.v.productReturn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.FragmentInPager;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.common.adapter.FragPageAdapter;
import com.a1magway.bgg.data.entity.OrderType;
import com.a1magway.bgg.v.order.OrderListFragment;
import com.a1magway.bgg.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by enid on 2018/9/8.
 * 申请售后列表页面
 */

public class AfterSaleListActivity extends PActivity {
    @BindView(R.id.order_list_title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.order_list_layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.order_list_pager)
    ViewPager mViewPager;

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setTitleTxt("售后");
        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        List<Fragment> fragList = new ArrayList<>();

        FragmentInPager frag1 = AfterSaleFragment.newInstance(AfterSaleFragment.AFTER_SALE_ING);
        frag1.setTitle(getString(R.string.after_sale_doing));
        fragList.add(frag1);

        FragmentInPager frag2 = AfterSaleFragment.newInstance(AfterSaleFragment.AFTER_SALE_RECORD);
        frag2.setTitle(getString(R.string.after_sale_record));
        fragList.add(frag2);

        mViewPager.setAdapter(new FragPageAdapter(getSupportFragmentManager(), fragList));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_after_sale;
    }
}
