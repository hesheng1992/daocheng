package com.a1magway.bgg.v.guide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CheckableImageButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.common.adapter.FragPageAdapter;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 引导页
 * Created by jph on 2017/8/26.
 */
public class GuideActivity extends BaseActivity {


    @BindView(R.id.guide_pager)
    ViewPager mViewPager;
    @BindView(R.id.guide_txt_go)
    TextView mGoTxt;
    @BindView(R.id.guide_point1)
    CheckableImageButton guidePoint1;
    @BindView(R.id.guide_point2)
    CheckableImageButton guidePoint2;
    @BindView(R.id.guide_point3)
    CheckableImageButton guidePoint3;
    @BindView(R.id.guild_lly_jump)
    LinearLayout guildLlyJump;


    public static void start(Context context) {
        Intent starter = new Intent(context, GuideActivity.class);
//        starter.putExtra();
        JumpUtil.startActivity(context, starter);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.guide_activity_guide;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        List<Fragment> fragList = new ArrayList<>();
        fragList.add(GuideFragment.newInstance(R.mipmap.ic_guide_1));
        fragList.add(GuideFragment.newInstance(R.mipmap.ic_guide_2));
        fragList.add(GuideFragment.newInstance(R.mipmap.ic_guide_3));

        setPointChecked(0);
        setJumpShowOrHide(0);
        mViewPager.setAdapter(new FragPageAdapter(getSupportFragmentManager(), fragList));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPointChecked(position);
                setJumpShowOrHide(position);
                if (position == mViewPager.getAdapter().getCount() - 1) {
                    mGoTxt.setVisibility(View.VISIBLE);
                } else {
                    mGoTxt.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.guide_txt_go,R.id.guild_lly_jump})
    public void onClickGo(View v) {
        MainActivity.start(getContext());
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @SuppressLint("RestrictedApi")
    private void setPointChecked(int currentPage) {
        guidePoint1.setChecked(false);
        guidePoint2.setChecked(false);
        guidePoint3.setChecked(false);
        switch (currentPage) {
            case 0:
                guidePoint1.setChecked(true);
                break;
            case 1:
                guidePoint2.setChecked(true);
                break;
            case 2:
                guidePoint3.setChecked(true);
                break;
        }
    }

    private void setJumpShowOrHide(int currentPage) {
        if (currentPage == 0) {
            guildLlyJump.setVisibility(View.VISIBLE);
        } else {
            guildLlyJump.setVisibility(View.GONE);
        }
    }

}
