package com.a1magway.bgg.v.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.a1magway.bgg.R;
import com.a1magway.bgg.v.cart.CartFragment;
import com.a1magway.bgg.v.cate.CateFragment;
import com.a1magway.bgg.v.found.FoundListFragment;
import com.a1magway.bgg.v.home.HomeFragment;
import com.a1magway.bgg.v.mainhome.MainHomeFragment;
import com.a1magway.bgg.v.personal.PersonalNewFragment;

import java.util.List;
import javax.inject.Inject;

/**
 * 首页Tab对应的Fragment管理类
 * Created by jph on 2016/7/13.
 */
public class TabFragmentManager {

    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;


    @Inject
    public TabFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }


    /**
     * 显示对应页面
     */
    public void showPage(String fragTag) {
        Log.d("enid","showPage");
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        Fragment frag = mFragmentManager.findFragmentByTag(fragTag);
        if (frag == null) {
            frag = createPageFragment(fragTag);
        }

        //Activity在回收后，重新进入会显示所有hide的Fragment
        hideFragment(ft);
        if (frag.isAdded()) {
            ft.show(frag);
        } else {
            ft.add(R.id.main_layout_frags, frag, fragTag);
        }
        ft.commit();
        mCurrentFragment = frag;
    }


    private void hideFragment(FragmentTransaction fragmentTransaction) {
        String[] names = { MainSubPages.HOME,MainSubPages.MAIN_HOME, MainSubPages.CATE,MainSubPages.FOUND, MainSubPages.CART,
            MainSubPages.PERSONAL };
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        for (Fragment fragment : fragmentList) {
            for (String tag : names) {
                if (fragment.getTag().equals(tag)) {
                    fragmentTransaction.hide(fragment);
                }
            }
        }
    }


    /**
     * 新建Page的Fragment
     */
    private Fragment createPageFragment(String fragTag) {
        switch (fragTag) {
            case MainSubPages.MAIN_HOME:
                return new MainHomeFragment();
            case MainSubPages.HOME:
                return new HomeFragment();
            case MainSubPages.CATE:
                return new CateFragment();
            case MainSubPages.FOUND:
                return new FoundListFragment();
            case MainSubPages.CART:
                return CartFragment.newInstance(true);
            case MainSubPages.PERSONAL:
                return new PersonalNewFragment();
            default:
                return new HomeFragment();
        }
    }


    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }


    public String getCurrentFragmentName() {
        if (getCurrentFragment() == null) {
            return null;
        }
        return getCurrentFragment().getTag();
    }

}