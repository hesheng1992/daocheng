package com.a1magway.bgg.v.seckill;

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
import com.a1magway.bgg.data.entity.SecKillTypes;
import com.a1magway.bgg.widget.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 秒杀专区界面
 * Created by jph on 2017/7/27.
 */
public class SecKillActivity extends BaseActivity {

    @BindView(R.id.seckill_title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.seckill_layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.seckill_pager)
    ViewPager mViewPager;

    public static void start(Context context) {
        Intent starter = new Intent(context, SecKillActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.seckill_activity_seckill;
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
        FragmentInPager ingFrag = SecKillFragment.newInstance(SecKillTypes.ING);
        ingFrag.setTitle(getString(R.string.seckill_title_ing));
        fragList.add(ingFrag);

        FragmentInPager waitFrag = SecKillFragment.newInstance(SecKillTypes.WAIT);
        waitFrag.setTitle(getString(R.string.seckill_title_wait));
        fragList.add(waitFrag);

        mViewPager.setAdapter(new FragPageAdapter(getSupportFragmentManager(), fragList));
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
