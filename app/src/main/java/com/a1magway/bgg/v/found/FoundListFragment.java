package com.a1magway.bgg.v.found;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.FragmentInPager;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.common.adapter.FragPageAdapter;
import com.a1magway.bgg.data.entity.FoundTitleData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.p.found.FoundListP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.AppConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FoundListFragment  extends PFragment implements IFoundListV {
    @BindView(R.id.found_list_layout_tab)
    TabLayout mTabLayout;
    @BindView(R.id.found_list_pager)
    ViewPager mViewPager;

    private FoundListP foundListP;
    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_found_list_layout;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        MainActivity.setNullStatusBarHeight(contentView);

        foundListP=new FoundListP(this);
        foundListP.getFoundTitle();
        MProgressDialog.showProgress(getContext());
    }

    @Override
    public void getFounTitle(List<FoundTitleData> list) {

        MProgressDialog.dismissProgress();
        LoginData loginData=GlobalData.getInstance().getLoginData();
        if (loginData==null){
            loginData=new LoginData();
            loginData.setId(0);
        }


        List<Fragment> fragList = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            FragmentInPager frag1 = FoundFragment.newInstance(AppConfig.FOUND_WEB_URL+list.get(i).getValue()+"?type="+AppConfig.BASE_URL+"&user_id="+loginData.getId());
            frag1.setTitle(list.get(i).getTypeName());
            fragList.add(frag1);

        }
        mViewPager.setAdapter(new FragPageAdapter(getChildFragmentManager(), fragList));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
