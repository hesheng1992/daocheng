package com.a1magway.bgg.v.search;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.IBackPress;

import java.io.Serializable;

/**
 * 搜索结果的子Fragment管理类
 * Created by jph on 2017/8/2.
 */
public class ResultFragManager implements IResultFragManage, IBackPress {

    private FragmentManager mFragmentManager;

    private FilterHideListener mFilterHideListener;

    public ResultFragManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    private static final String FRAG_TAG_FILTER = "frag_tag_filter";
    private static final String FRAG_TAG_FILTER_CATE = "frag_tag_filter_cate";

    @Override
    public void showFilter() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();

        Fragment filterFrag = mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER);
        if (filterFrag == null) {
            filterFrag = FilterFragment.newInstance();
        }

        if (filterFrag.isAdded()) {
            ft.show(filterFrag);
        } else {
            ft.add(R.id.result_layout_frag, filterFrag, FRAG_TAG_FILTER);
        }
        ft.commit();
    }

    @Override
    public void hideFilter() {
        Fragment filterFrag = mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER);

        if (filterFrag == null) {
            return;
        }

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (!filterFrag.isHidden()) {
            ft.hide(filterFrag);
            ft.commit();
        }

        if (mFilterHideListener != null) {
            mFilterHideListener.onFilterHide();
        }
    }

    @Override
    public void showFilterCate() {
        showFilterCate(null);
    }

    @Override
    public void showFilterCate(@Nullable Serializable cate1) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_right_in, 0);

        FilterCateFragment filterCateFrag = (FilterCateFragment) mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER_CATE);
        if (filterCateFrag == null) {
            filterCateFrag = FilterCateFragment.newInstance();
        }

        if (cate1 != null) {
            filterCateFrag.setCate1(cate1);
        }

        ft.add(R.id.result_layout_frag, filterCateFrag, FRAG_TAG_FILTER_CATE);
        ft.commit();
    }

    @Override
    public void hideFilterCate() {
        hideFilterCate(true);
    }

    /**
     * 隐藏筛选分类界面
     *
     * @param anim 是否动画
     */
    private void hideFilterCate(boolean anim) {
        Fragment filterCateFrag = mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER_CATE);

        if (filterCateFrag == null) {
            return;
        }

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (anim) {
            ft.setCustomAnimations(0, R.anim.slide_right_out);
        }
        if (filterCateFrag.isAdded()) {
            ft.remove(filterCateFrag);
            ft.commit();
        }
    }

    @Override
    public void hideAllFilter() {
        Fragment filterFrag = mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER);
        Fragment filterCateFrag = mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER_CATE);

        if (filterFrag == null && filterCateFrag == null) {
            return;
        }

        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (filterCateFrag != null && filterCateFrag.isAdded()) {
            ft.remove(filterCateFrag);
        }

        if (filterFrag != null && !filterFrag.isHidden()) {
            ft.hide(filterFrag);
        }

        ft.commit();

        if (mFilterHideListener != null) {
            mFilterHideListener.onFilterHide();
        }
    }

    public FilterFragment getFilterFragment() {
        return (FilterFragment) mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER);
    }

    public FilterCateFragment getFilterCateFragment() {
        return (FilterCateFragment) mFragmentManager.findFragmentByTag(FRAG_TAG_FILTER_CATE);
    }

    @Override
    public boolean onBackPressed() {
        FilterFragment filterFragment = getFilterFragment();
        FilterCateFragment filterCateFragment = getFilterCateFragment();

        if (filterFragment != null && !filterFragment.isHidden()) {
            if (filterCateFragment != null && filterFragment.isAdded()) {
                //返回筛选界面
                hideFilterCate(true);
            } else {
                //关闭筛选界面
                hideFilter();
            }
            return true;
        }

        return false;
    }

    public void setFilterHideListener(FilterHideListener filterHideListener) {
        mFilterHideListener = filterHideListener;
    }

    public interface FilterHideListener {
        void onFilterHide();
    }
}
