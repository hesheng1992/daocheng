package com.a1magway.bgg.p.search;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.data.entity.PresetFilterRule;
import com.a1magway.bgg.data.entity.ProductFilterRule;
import com.a1magway.bgg.data.entity.SelectCateInfo;
import com.almagway.common.utils.CollectionUtil;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.p.brand.BrandItem;
import com.a1magway.bgg.v.cate.BrandMuseumActivity;
import com.a1magway.bgg.v.search.FilterFragment;
import com.a1magway.bgg.v.search.IFilterV;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

/**
 * 筛选界面对应的presenter
 * Created by jph on 2017/8/3.
 */
public class FilterP extends BasePresenter<IFilterV> {

    private FilterCate1Adapter mFilterCate1Adapter;
    private SelectCateInfo mSelectCateInfo;//选择的分类信息
    private BrandItem mSelectedBrand;//选择的品牌
    @Inject
    PresetFilterRule mPresetFilterRule;

    @Inject
    List<FilterCate1> mFilterCate1List;

    @Inject
    public FilterP(IFilterV filterV, FilterCate1Adapter filterCate1Adapter) {
        super(filterV);
        mFilterCate1Adapter = filterCate1Adapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mFilterCate1Adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                mView.showFilterCateFragment(mFilterCate1Adapter.getItem(position));
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        if (mPresetFilterRule.isValid() && mPresetFilterRule.getBrandId() > 0) {
            mSelectedBrand = new BrandItem();
            mSelectedBrand.setId(mPresetFilterRule.getBrandId());
            mSelectedBrand.setName(mPresetFilterRule.getBrandName());
        }
        mView.setCate1RecyclerViewAdapter(mFilterCate1Adapter);
        mFilterCate1Adapter.setList(mFilterCate1List);//显示过滤界面时直接拿在activity层获取到的数据显示

        checkInvalidPresetFilter();
    }

    /**
     * 处理跳转其他界面返回的数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void handleActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == FilterFragment.REQUEST_CODE_SELECT_BRAND &&
                resultCode == Activity.RESULT_OK && data != null) {
            mSelectedBrand = (BrandItem)
                    data.getSerializableExtra(BrandMuseumActivity.EXTRA_SELECTED_BRAND);
            mView.showSelectedBrandTxt(mSelectedBrand.getName(), true);
        }
    }

    /**
     * 设置选中的分类
     *
     * @param selectedCate
     */
    public void setSelectedCate(Serializable selectedCate) {
        mSelectCateInfo = (SelectCateInfo) selectedCate;
        //cate2可能为空
        String cate2 = mSelectCateInfo.getCate2() != null ?
                mSelectCateInfo.getCate2().getName() : null;
        mView.showSelectedCateInfo(mSelectCateInfo.getCate1().getName(),
                mSelectCateInfo.getGender() == 1 ? "男" : "女",
                cate2);
    }

    public void resetCate() {
        mSelectCateInfo = null;

        mView.showCate1Layout();
    }

    /**
     * 重置筛选
     */
    public void reset() {
        mSelectCateInfo = null;
        if (!mPresetFilterRule.isValid()) {
            mSelectedBrand = null;
        }
        mView.resetView();
    }

    public void confirm(String minPrice, String maxPrice) {
        ProductFilterRule rule = new ProductFilterRule();
        if (mSelectedBrand != null && mSelectedBrand.getId() > 0) {
            rule.setBrandId(mSelectedBrand.getId());
        }
        rule.setMinPrice(minPrice);
        rule.setMaxPrice(maxPrice);
        if (mSelectCateInfo != null) {
            if (mSelectCateInfo.getCate2() != null) {
                rule.setFilterSex(String.valueOf(mSelectCateInfo.getGender()));
                rule.setCategoryId(mSelectCateInfo.getCate2().getId());
                rule.setCategoryFlag("0");
            } else {
                //未选择二级分类
                rule.setCategoryId(mSelectCateInfo.getCate1().getId());
                rule.setCategoryFlag("1");
            }
        } else {
            if (mPresetFilterRule.isValid()) {
                rule.setCategoryId(mPresetFilterRule.getCategoryId());
                rule.setCategoryFlag("1");
            }
        }
        mView.closeFilterFragment(rule);
    }

    /**
     * 判断是否需要限制可选的分类,和是否需要显示预设的品牌
     */
    public void checkInvalidPresetFilter() {

        int validCateId = -1;
        if (mPresetFilterRule.isValid() && mPresetFilterRule.getCategoryId() > 0) {
            validCateId = mPresetFilterRule.getCategoryId();
        }

        mFilterCate1Adapter.setValidCateId(validCateId);
        mFilterCate1Adapter.notifyDataSetChanged();

        if (mPresetFilterRule.isValid() && mPresetFilterRule.getBrandId() > 0) {
            //有预设的品牌
            mView.showSelectedBrandTxt(mPresetFilterRule.getBrandName(), false);
        } else {
            if (mSelectedBrand != null) {
                mView.showSelectedBrandTxt(mSelectedBrand.getName(), true);
            } else {
                mView.showSelectedBrandTxt(getContext().getString(R.string.search_filter_pick_brand), true);
            }
        }
    }

    public void showFilterCateFragment(String str) {
        for (int i = 0, j = CollectionUtil.getSize(mFilterCate1List); i < j; i++) {
            if (mFilterCate1List.get(i).getName() == str) {
                mView.showFilterCateFragment(mFilterCate1List.get(i));
            }
        }
    }

    public void resetBrandItem(){
        mSelectedBrand = null;
    }
}
