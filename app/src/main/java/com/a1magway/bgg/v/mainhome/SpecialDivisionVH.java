package com.a1magway.bgg.v.mainhome;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.HomeSubjectBean;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.widget.ThemeCountDownTextView;

import java.util.Date;

import butterknife.BindView;

/**
 * Created by enid on 2018/6/13.
 */

public class SpecialDivisionVH extends BaseRecyclerVH<HomeSubjectBean> {

    @BindView(R.id.home_special_division_title)
    TextView mSpecialDivisionTitle;

    @BindView(R.id.home_special_division_tag)
    TextView mSpecialDivisionTag;

    @BindView(R.id.home_special_division_img)
    ImageView mSpecialDivisionImag;

    @BindView(R.id.home_special_division_label)
    TextView mSpecialDivisionLabel;

    @BindView(R.id.division_tag)
    TextView division_tag;

    @BindView(R.id.themeCountDownTextView)
    ThemeCountDownTextView themeCountDownTextView;

    public SpecialDivisionVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_home_special_division);
    }

    public void showViewContent(HomeSubjectBean homeSubjectBean) {
        mSpecialDivisionTitle.setText(homeSubjectBean.getTitle());
        mSpecialDivisionTag.setText(homeSubjectBean.getDescription());
        division_tag.setText(homeSubjectBean.getDescription());
        mSpecialDivisionLabel.setText(homeSubjectBean.getLabel());
        Date date = new Date();
        themeCountDownTextView.startTickWork(homeSubjectBean.getEndTime() - date.getTime());
        ImageLoaderUtil.displayImage(mSpecialDivisionImag, homeSubjectBean.getCover());
    }
}
