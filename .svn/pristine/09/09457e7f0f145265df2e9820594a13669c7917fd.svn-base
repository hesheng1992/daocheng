package com.a1magway.bgg.v.main;

import android.util.Log;

import com.a1magway.bgg.eventbus.event.FoundChangeEvent;
import com.a1magway.bgg.widget.TabBar;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Tab相关逻辑管理类
 * Created by jph on 2016/7/21.
 */
public class TabManager implements TabBar.IOnTabCheckListener {

    private TabBar mTabBar;
    private TabFragmentManager mTabFragmentManager;


    @Inject
    public TabManager(TabBar tabBar, TabFragmentManager tabFragmentManager) {
        mTabBar = tabBar;
        mTabFragmentManager = tabFragmentManager;
    }


    public void init() {
        mTabBar.setOnTabCheckListener(this);
    }


    /**
     * 初始化并默认选中某个tab
     *
     * @param selectTabName {@link MainSubPages}
     */
    public void init(@MainSubPages String selectTabName) {
        init();
        mTabBar.selectTab(selectTabName);
//        mTabFragmentManager.showPage(selectTabName);
    }


    @Override
    public void onCheckMainHomeTab() {
        Log.d("enid","onCheckMainHomeTab2");
        mTabFragmentManager.showPage(MainSubPages.MAIN_HOME);
        EventBus.getDefault().post(new FoundChangeEvent(false));
    }

    @Override
    public void onCheckHomeTab() {
        mTabFragmentManager.showPage(MainSubPages.HOME);
        EventBus.getDefault().post(new FoundChangeEvent(false));
    }


    @Override
    public void onCheckCateTab() {
        mTabFragmentManager.showPage(MainSubPages.CATE);
        EventBus.getDefault().post(new FoundChangeEvent(false));
    }


    @Override
    public void onCheckCartTab() {
        mTabFragmentManager.showPage(MainSubPages.CART);
        EventBus.getDefault().post(new FoundChangeEvent(false));
    }


    @Override
    public void onCheckPersonalTab() {
        mTabFragmentManager.showPage(MainSubPages.PERSONAL);
        EventBus.getDefault().post(new FoundChangeEvent(false));
    }

    @Override
    public void onCheckFoundTAB() {
        mTabFragmentManager.showPage(MainSubPages.FOUND);
        EventBus.getDefault().post(new FoundChangeEvent(true));
    }
}
