package com.a1magway.bgg.v.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.a1magway.bgg.R;

/**
 * 搜索界面的Fragment子界面管理类
 * Created by jph on 2017/7/31.
 */
public class SearchFragManager {

    private static String FRAG_TAG_RECORDS = "frag_tag_records";
    private static String FRAG_TAG_RESULT = "frag_tag_result";

    private FragmentManager mFragmentManager;

    public SearchFragManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    /**
     * 显示搜索记录界面
     */
    public void showRecordsFragment(int cateId) {
        show(FRAG_TAG_RECORDS, FRAG_TAG_RESULT,cateId);

        //重新加载搜索记录
        RecordsFragment recordsFragment = getRecordsFragment();
        if (recordsFragment != null) {
            recordsFragment.resetRecords();
        }
    }

    /**
     * 显示搜索结果界面
     */
    public void showResultFragment(int cateId) {
        show(FRAG_TAG_RESULT, FRAG_TAG_RECORDS,cateId);
    }

    /**
     * 切换显示Fragment
     *
     * @param showTag
     * @param hideTag
     */
    private void show(String showTag, String hideTag,int cateId) {
        FragmentManager fm = mFragmentManager;
        FragmentTransaction ft = fm.beginTransaction();

        Fragment showFragment = fm.findFragmentByTag(showTag);
        Fragment hideFragment = fm.findFragmentByTag(hideTag);

        if (showFragment == null) {
            if (FRAG_TAG_RECORDS.equals(showTag)) {
                showFragment = RecordsFragment.newInstance();
            } else {
                showFragment = ResultFragment.newInstance(cateId);
            }
        }


        if (showFragment.isAdded()) {
            ft.show(showFragment);
        } else {
            ft.add(R.id.search_layout_frag, showFragment, showTag);
        }

        if (hideFragment != null) {
            ft.hide(hideFragment);
        }

        ft.commit();
    }

    public ResultFragment getResultFragment() {
        return (ResultFragment) mFragmentManager.findFragmentByTag(FRAG_TAG_RESULT);
    }

    public RecordsFragment getRecordsFragment() {
        return (RecordsFragment) mFragmentManager.findFragmentByTag(FRAG_TAG_RECORDS);
    }

    /**
     * 当前显示的是结果页
     *
     * @return
     */
    public boolean isShowResultFragment() {
        Fragment fragResult = mFragmentManager.findFragmentByTag(FRAG_TAG_RESULT);
        if (fragResult != null && !fragResult.isHidden()) {
            return true;
        }

        return false;
    }

    /**
     * 子界面处理onBackPress
     *
     * @return 是否处理
     */
    public boolean onBackPressed4Child() {
        ResultFragment resultFragment = getResultFragment();
        if (resultFragment == null) {
            return false;
        }
        return resultFragment.onBackPressed();
    }
}
