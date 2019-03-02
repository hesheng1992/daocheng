package com.a1magway.bgg.v.search;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.data.entity.PresetFilterRule;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.search.FilterModule;
import com.a1magway.bgg.p.search.FilterP;
import com.a1magway.bgg.v.cate.BrandMuseumActivity;
import com.almagway.common.utils.StringUtil;
import com.almagway.common.utils.ToastUtil;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 筛选条件界面
 * Created by jph on 2017/8/2.
 */
public class FilterFragment extends PFragment<FilterP> implements IFilterV {

    public static final int REQUEST_CODE_SELECT_BRAND = 1013;

    private boolean mNeedResetFlag = false;//是否需要重置筛选

    @Inject
    ResultFragManager mResultFragManager;//父级界面的fragment管理器
    @Inject
    PresetFilterRule mPresetFilterRule;

    @BindView(R.id.filter_txt_cate1_tag)
    TextView mCate1TagTxt;
    @BindView(R.id.filter_recycler_cate1)
    RecyclerView mCate1RecyclerView;
    @BindView(R.id.filter_txt_selected_brand)
    TextView mSelectedBrandTxt;
    @BindView(R.id.filter_layout_selected_cate)
    ViewGroup mSelectedCateLayout;
    @BindView(R.id.filter_txt_selected_cate1)
    TextView mSelectedCate1;
    @BindView(R.id.filter_txt_selected_gender)
    TextView mSelectedGender;
    @BindView(R.id.filter_txt_selected_cate2)
    TextView mSelectedCate2;
    @BindView(R.id.filter_edt_min_price)
    EditText mMinPriceEdt;
    @BindView(R.id.filter_edt_max_price)
    EditText mMaxPriceEdt;

    public static FilterFragment newInstance() {

//        Bundle args = new Bundle();

        FilterFragment fragment = new FilterFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.search_fragment_filter;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        ((ResultFragment) getParentFragment())
                .getSearchResultComponent()
                .getFilterComponent(new FilterModule(this))
                .inject(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.handleActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //从新显示当前界面
            mPresenter.checkInvalidPresetFilter();

            //从新显示时，需要判断是否标记了需要重置筛选条件
            if (mNeedResetFlag) {
                mPresenter.reset();

                setNeedResetFlag(false);//一次显示有效
            }
        }
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        mCate1RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick({R.id.filter_txt_selected_cate_tag, R.id.filter_txt_selected_cate1})
    public void onClickShowCate1Layout(View v) {
        switch (v.getId()) {
            case R.id.filter_txt_selected_cate_tag:
                //点击重置分类选择
                mPresenter.resetCate();
                break;
            case R.id.filter_txt_selected_cate1:
                //显示二级分类栏目
                mPresenter.showFilterCateFragment(mSelectedCate1.getText().toString());
                break;
        }
    }

    /**
     * 点击去选择品牌
     *
     * @param v
     */
    @OnClick(R.id.filter_txt_selected_brand)
    public void onClickSelectBrand(View v) {
        BrandMuseumActivity.startForResult(this, REQUEST_CODE_SELECT_BRAND);
    }

    @OnClick(R.id.filter_txt_reset)
    public void onClickRest(View v) {
        mPresenter.reset();
    }

    @OnClick(R.id.filter_txt_confirm)
    public void onClickConfirm(View v) {
        String mMin = mMinPriceEdt.getText().toString().trim();
        String mMax = mMaxPriceEdt.getText().toString().trim();
        if(!StringUtil.isEmpty(mMax)&&!StringUtil.isEmpty(mMin)){
            if (Integer.parseInt(mMax) - Integer.parseInt(mMin) < 0) {
                ToastUtil.showLong("最高价必须高于最低价");
                return;
            }
        }
        mPresenter.confirm(mMinPriceEdt.getText().toString(), mMaxPriceEdt.getText().toString());

    }

    @Override
    public void setCate1RecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mCate1RecyclerView.setAdapter(adapter);
    }

    @Override
    public void showSelectedBrandTxt(String brand, boolean canSelect) {
        if (canSelect) {
            mSelectedBrandTxt.setEnabled(true);
            Drawable nav_up = getResources().getDrawable(R.drawable.ic_arrow_right);
            nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
            mSelectedBrandTxt.setCompoundDrawables(null, null, nav_up, null);
            mSelectedBrandTxt.setText(brand);
        } else {
            mSelectedBrandTxt.setEnabled(false);
            mSelectedBrandTxt.setCompoundDrawables(null, null, null, null);
            mSelectedBrandTxt.setText(brand);
        }
    }

    @Override
    public void showFilterCateFragment(Serializable cate1) {
        mResultFragManager.showFilterCate(cate1);
    }

    @Override
    public void closeFilterFragment(Serializable rule) {
        mResultFragManager.hideFilter();
        ((ResultFragment) getParentFragment()).setFilterRule(rule);
    }

    @Override
    public void showCate1Layout() {
        mSelectedCateLayout.setVisibility(View.GONE);
        mCate1TagTxt.setVisibility(View.VISIBLE);
        mCate1RecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showSelectedCateInfo(String cate1, String gender, String cate2) {
        mSelectedCateLayout.setVisibility(View.VISIBLE);
        mCate1TagTxt.setVisibility(View.GONE);
        mCate1RecyclerView.setVisibility(View.GONE);

        mSelectedCate1.setText(cate1);
        mSelectedGender.setText(gender);
        mSelectedCate2.setText(cate2);
    }

    @Override
    public void resetView() {
        showCate1Layout();
        mMinPriceEdt.setText("");
        mMaxPriceEdt.setText("");
        if (mPresetFilterRule.isValid()) {
            if (mPresetFilterRule.getBrandId() > 0) {
                mSelectedBrandTxt.setText(mPresetFilterRule.getBrandName());
            } else {
                mPresenter.resetBrandItem();
                mSelectedBrandTxt.setText(R.string.search_filter_pick_brand);
            }
        } else {
            mSelectedBrandTxt.setText(R.string.search_filter_pick_brand);
        }
    }

    /**
     * 外部设置选中的分类
     *
     * @param selectedCate
     */
    public void setSelectedCate(Serializable selectedCate) {
        mPresenter.setSelectedCate(selectedCate);
    }


    public void setNeedResetFlag(boolean needResetFlag) {
        mNeedResetFlag = needResetFlag;
    }
}
