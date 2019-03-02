package com.a1magway.bgg.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.a1magway.bgg.common.FragmentInPager;

import java.util.List;

/**
 * Fragment的VeiwPager使用的适配器
 * Created by jph on 2015/8/25.
 */
public class FragPageAdapter extends FragmentPagerAdapter {
    private List<? extends Fragment> mListFrag;

    public FragPageAdapter(FragmentManager fm, List<? extends Fragment> list) {
        super(fm);
        this.mListFrag = list;
    }

    //刷新数据
    public void refreshData(List<? extends Fragment> list){
        this.mListFrag = list;
    }

    @Override
    public int getCount() {
        return mListFrag.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mListFrag.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (getItem(position) instanceof FragmentInPager) {
            return ((FragmentInPager) getItem(position)).getTitle();
        }
        return null;
    }

}
