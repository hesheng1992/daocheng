package com.a1magway.bgg.v.mainhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.common.adapter.FragPageAdapter;
import com.a1magway.bgg.data.entity.AppNavigationData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.mainhome.DaggerMainHomeComponent;
import com.a1magway.bgg.di.module.mainhome.MainHomeModule;
import com.a1magway.bgg.eventbus.event.HomeRefreshEvent;
import com.a1magway.bgg.p.mainhome.MainHomeP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.search.SearchActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/6/8.
 */

public class MainHomeFragment extends PFragment<MainHomeP> implements IMainHomeV {

    public static final String TAG = MainHomeFragment.class.getSimpleName();

    @BindView(R.id.main_home_viewpager)
    ViewPager mViewPager;

    @BindView(R.id.main_home_layout_tab)
    TabLayout mTabLayout;

    @BindView(R.id.main_home_refresh_layout)
    AppRefreshLayout mRefreshLayout;

    @BindView(R.id.app_bar_layout)
    AppBarLayout mAppBarLayout;

    @BindView(R.id.home_txt_search)
    TextView mSearchTxt;

    @BindView(R.id.null_status_bar)
    View mNullStatusBar;

    @OnClick(R.id.main_home_top_search)
    public void onClickSearch(View view) {
        SearchActivity.start(getContext());
    }

    private int currentTab = 0;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.home_fragment_main_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        MainActivity.setNullStatusBarHeight(contentView);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.loadData();
                mPresenter.refreshUserInfo();
                EventBus.getDefault().post(new HomeRefreshEvent(mViewPager.getCurrentItem()));
            }
        });
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    mRefreshLayout.setEnabled(true);
                    //TODO top不显示
                } else {
                    mRefreshLayout.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerMainHomeComponent.builder()
                .appComponent(appComponent)
                .mainHomeModule(new MainHomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Log.d(TAG, "initData() execute");

        mPresenter.loadData();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private FragPageAdapter fragPageAdapter;

    @Override
    public void initViewPager(List<AppNavigationData> appNavigationData) {
        List<HomeSpecialFragment> fragmentList = new ArrayList<>();
        /*HomeSpecialFragment frag1 = HomeSpecialFragment.newInstance(appNavigationData.get(0).getType());
        frag1.setTitle(appNavigationData.get(0).getTypeName());
        fragmentList.add(frag1);

        HomeSpecialFragment frag2 = HomeSpecialFragment.newInstance(appNavigationData.get(1).getType());
        frag2.setTitle(appNavigationData.get(1).getTypeName());
        fragmentList.add(frag2);

        HomeSpecialFragment frag3 = HomeSpecialFragment.newInstance(appNavigationData.get(2).getType());
        frag3.setTitle(appNavigationData.get(2).getTypeName());
        fragmentList.add(frag3);

        HomeSpecialFragment frag4 = HomeSpecialFragment.newInstance(appNavigationData.get(3).getType());
        frag4.setTitle(appNavigationData.get(3).getTypeName());
        fragmentList.add(frag4);

        HomeSpecialFragment frag5 = HomeSpecialFragment.newInstance(appNavigationData.get(4).getType());
        frag5.setTitle(appNavigationData.get(4).getTypeName());
        fragmentList.add(frag5);
        */
        for (AppNavigationData data : appNavigationData) {
            HomeSpecialFragment frag = HomeSpecialFragment.newInstance(data.getType());
            frag.setTitle(data.getTypeName());
            fragmentList.add(frag);
        }

        //第一次设置数据，后面刷新数据
        if (fragPageAdapter == null) {
            fragPageAdapter = new FragPageAdapter(getChildFragmentManager(), fragmentList);
            mViewPager.setAdapter(fragPageAdapter);
            mTabLayout.setupWithViewPager(mViewPager);
        } else {
            fragPageAdapter.refreshData(fragmentList);
            fragPageAdapter.notifyDataSetChanged();
        }
        /*mViewPager.setAdapter(fragPageAdapter);
        mViewPager.setAdapter(new FragPageAdapter(getChildFragmentManager(), fragmentList));
        mTabLayout.setupWithViewPager(mViewPager);*/
        TabLayout.Tab tab = mTabLayout.getTabAt(currentTab);
        if (tab != null) {
            tab.select();
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentTab = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRefreshEnd() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void setSearchTxt(String string) {
        mSearchTxt.setText(string);
    }

    @Override
    public void swithTab(int toPage) {
        if (mViewPager != null && fragPageAdapter != null && fragPageAdapter.getCount() > toPage) {
            mViewPager.setCurrentItem(toPage);
        }
    }

}
