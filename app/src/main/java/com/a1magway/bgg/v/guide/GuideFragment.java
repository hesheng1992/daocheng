package com.a1magway.bgg.v.guide;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseFragment;

import butterknife.BindView;

/**
 * 引导页子界面
 * Created by jph on 2017/8/26.
 */
public class GuideFragment extends BaseFragment {

    private static final String EXTRA_PIC_RES = "extra_pic_res";

    @BindView(R.id.guide_img)
    ImageView mImg;

    public static GuideFragment newInstance(@DrawableRes int picRes) {

        Bundle args = new Bundle();
        args.putInt(EXTRA_PIC_RES, picRes);

        GuideFragment fragment = new GuideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.guide_fragment_guide;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        mImg.setImageResource(getArguments().getInt(EXTRA_PIC_RES));
    }
}
