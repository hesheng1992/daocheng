package com.a1magway.bgg.v.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.data.entity.PresetFilterRule;
import com.almagway.common.utils.CollectionUtil;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.search.FilterCateModule;
import com.a1magway.bgg.p.search.FilterCateP;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 筛选选中2级分类界面
 * Created by jph on 2017/8/2.
 */
public class FilterCateFragment extends PFragment<FilterCateP> implements IFilterCateV {

    private Serializable mCate1;//外层传进来的数据,可以保持每次显示界面更新
    @Inject
    ResultFragManager mResultFragManager;

    @Inject
    PresetFilterRule mPresetFilterRule;

    @BindView(R.id.cate_txt_cate1)
    TextView mCate1NameTxt;
    @BindView(R.id.cate_txt_man)
    TextView mManTxt;
    @BindView(R.id.cate_txt_woman)
    TextView mWomanTxt;
    @BindView(R.id.cate_layout_cate2_man)
    ViewGroup mManLayout;
    @BindView(R.id.cate_layout_cate2_woman)
    ViewGroup mWomanLayout;
    @BindView(R.id.cate2_woman_layout)
    LinearLayout mWomenLinearLayout;
    @BindView(R.id.cate2_man_layout)
    LinearLayout mManLinearLayout;

    public static FilterCateFragment newInstance() {
        FilterCateFragment fragment = new FilterCateFragment();
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.search_fragment_filter_cate;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        ((ResultFragment) getParentFragment()).getSearchResultComponent()
                .getFilterCateComponent(new FilterCateModule(this, mCate1))
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

    }

    @OnClick(R.id.cate_txt_cate_tag)
    public void onClickCateTag(View v) {
        switch (v.getId()) {
            case R.id.cate_txt_cate_tag:
                //点击返回筛选界面
                mResultFragManager.hideFilterCate();
                break;
        }
    }

    @Override
    public void setCate1Text(String cate1Name) {
        mCate1NameTxt.setText(cate1Name);
    }

    @Override
    public void setManText(@StringRes int resId) {
        mManTxt.setText(resId);
    }

    @Override
    public void setWomanText(@StringRes int resId) {
        mWomanTxt.setText(resId);
    }

    @Override
    public void showWomanCate2Content(List<String> strList) {
        showCate2Content(mWomanLayout, strList, new ICate2ItemClickListener() {
            @Override
            public void onCate2ItemClick(int position) {
                mPresenter.selectWomanCate2(position);
            }
        });
    }

    @Override
    public void showManCate2Content(List<String> strList) {
        showCate2Content(mManLayout, strList, new ICate2ItemClickListener() {
            @Override
            public void onCate2ItemClick(int position) {
                mPresenter.selectManCate2(position);
            }
        });
    }

    @Override
    public void backFilterFragment(Serializable selectedCate) {
        mResultFragManager.hideFilterCate();
        FilterFragment filterFragment = mResultFragManager.getFilterFragment();
        if (filterFragment != null && selectedCate != null) {
            filterFragment.setSelectedCate(selectedCate);
        }
    }

    public void setCate1(Serializable cate1) {
        mCate1 = cate1;
    }

    /**
     * 显示2级分类内容到布局上
     *
     * @param layout
     * @param strList
     * @param cate2ItemClickListener
     */
    private void showCate2Content(ViewGroup layout, List<String> strList,
                                  final ICate2ItemClickListener cate2ItemClickListener) {
        layout.removeAllViews();
        if (mPresetFilterRule.getCategoryId() == -2) {
            mManLinearLayout.setVisibility(View.VISIBLE);
            mWomenLinearLayout.setVisibility(View.GONE);
        } else if (mPresetFilterRule.getCategoryId() == -1) {
            mManLinearLayout.setVisibility(View.GONE);
            mWomenLinearLayout.setVisibility(View.VISIBLE);
        } else {
            mManLinearLayout.setVisibility(View.VISIBLE);
            mWomenLinearLayout.setVisibility(View.VISIBLE);
        }

        if (CollectionUtil.isEmpty(strList)) {
            return;
        }

        for (int i = 0, c = CollectionUtil.getSize(strList); i < c; i++) {
            String name = strList.get(i);

            final View cate2Item = LayoutInflater.from(getContext()).inflate(
                    R.layout.search_filter_item_list_cate2, layout, false);
            TextView nameTxt = ButterKnife.findById(cate2Item, R.id.cate2_txt_name);
            nameTxt.setText(name);
            layout.addView(cate2Item);

            final int position = i;
            cate2Item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (cate2ItemClickListener != null) {
                        cate2ItemClickListener.onCate2ItemClick(position);
                    }
                }
            });
        }
    }

    /**
     * 二级分类item点击事件
     */
    public interface ICate2ItemClickListener {
        void onCate2ItemClick(int position);
    }
}
