package com.a1magway.bgg.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.OrderByTypes;
import com.a1magway.bgg.data.entity.RecommendOrderByTypes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 推荐商品结果上方的Tab布局
 * Created by jph on 2017/7/31.
 */
public class RecommendTabLayout extends LinearLayout {
    @RecommendOrderByTypes.RecommendOrderByType
    private int mOrderByType;//选中的排序方式
    private OrderByChangeListener mOrderByChangeListener;

    @BindView(R.id.recommend_tab_comprehensive)
    View mComprehensiveTab;
    @BindView(R.id.recommend_tab_price)
    View mSalePriceTab;
    @BindView(R.id.recommend_tab_img_price_sort)
    ImageView mPriceSortImg;
    @BindView(R.id.recommend_tab_discount)
    View mDiscountTab;
    @BindView(R.id.recommend_tab_img_discount_sort)
    ImageView mDiscountSortImg;
    @BindView(R.id.recommend_tab_sale_count)
    View mSaleCountTab;
    @BindView(R.id.recommend_tab_img_sale_sort)
    ImageView mSaleCountSortImg;

    public RecommendTabLayout(Context context) {
        super(context);
        init();
    }

    public RecommendTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecommendTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.recommend_product_layout_tab, this, true);

        setOrientation(HORIZONTAL);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.recommend_tab_comprehensive)
    public void onClickDefaultAllTab(View v) {      //点击综合
        if (mOrderByType != RecommendOrderByTypes.COMPREHENSIVE){
            setOrderByType(RecommendOrderByTypes.COMPREHENSIVE);
        }
    }

    @OnClick(R.id.recommend_tab_price)
    public void onClickPriceTab(View v) {
        if (mOrderByType == RecommendOrderByTypes.PRICE_ASC) {
            setOrderByType(RecommendOrderByTypes.PRICE_DESC);
        } else {
            setOrderByType(RecommendOrderByTypes.PRICE_ASC);
        }
    }

    @OnClick(R.id.recommend_tab_discount)
    public void onClickDiscountTab(View v) {
        if (mOrderByType == RecommendOrderByTypes.DISCOUNT_ASC) {
            setOrderByType(RecommendOrderByTypes.DISCOUNT_DESC);
        } else {
            setOrderByType(RecommendOrderByTypes.DISCOUNT_ASC);
        }
    }

    @OnClick(R.id.recommend_tab_sale_count)
    public void onClickSaleCountTab(View v) {
        if (mOrderByType == RecommendOrderByTypes.SALE_COUNT_DESC) {

            setOrderByType(RecommendOrderByTypes.SALE_COUNT_ASC);
        } else {
            setOrderByType(RecommendOrderByTypes.SALE_COUNT_DESC);
        }
    }

    /**
     * 设置新的排序方式,会call回调
     *
     * @param orderByType 对应{@link RecommendOrderByTypes}
     */
    public void setOrderByType(@RecommendOrderByTypes.RecommendOrderByType int orderByType) {
        setOrderByType(orderByType, true);
    }

    /**
     * 设置新的排序方式
     *
     * @param orderByType  对应{@link RecommendOrderByTypes}
     * @param needCallback 是否需要回调
     */
    public void setOrderByType(@RecommendOrderByTypes.RecommendOrderByType int orderByType, boolean needCallback) {
        if (orderByType == mOrderByType) {
            return;
        }
        mOrderByType = orderByType;
        Log.e("orderBy","oooo");
//        changeOrderByType(orderByType,needCallback);
        switch (orderByType) {
            case RecommendOrderByTypes.COMPREHENSIVE:
                changeOrderByType(orderByType,true,needCallback);
                mComprehensiveTab.setSelected(true);

                mSalePriceTab.setSelected(false);
                mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                mDiscountTab.setSelected(false);
                mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                mSaleCountTab.setSelected(false);
                mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab);

                break;
            case RecommendOrderByTypes.PRICE_ASC:
                changeOrderByType(orderByType,false,needCallback);
                if (mOrderByType == RecommendOrderByTypes.PRICE_DESC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mComprehensiveTab.setSelected(false);
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(true);
                    mDiscountTab.setSelected(false);

                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case RecommendOrderByTypes.PRICE_DESC:
                changeOrderByType(orderByType,false,needCallback);
                if (mOrderByType == RecommendOrderByTypes.PRICE_ASC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mComprehensiveTab.setSelected(false);
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(true);
                    mDiscountTab.setSelected(false);

                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case RecommendOrderByTypes.DISCOUNT_ASC:
                changeOrderByType(orderByType,false,needCallback);
                if (mOrderByType == RecommendOrderByTypes.DISCOUNT_DESC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mComprehensiveTab.setSelected(false);
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(true);

                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case RecommendOrderByTypes.DISCOUNT_DESC:
                changeOrderByType(orderByType,false,needCallback);
                if (mOrderByType == RecommendOrderByTypes.DISCOUNT_ASC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mComprehensiveTab.setSelected(false);
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(true);

                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case RecommendOrderByTypes.SALE_COUNT_ASC:
                changeOrderByType(orderByType,false,needCallback);
                if (mOrderByType == RecommendOrderByTypes.SALE_COUNT_DESC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                } else {
                    //之前未选中当前Tab
                    //取消选中的其他排序方式
                    mComprehensiveTab.setSelected(false);
                    mSaleCountTab.setSelected(true);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(false);
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                }
                break;
            case RecommendOrderByTypes.SALE_COUNT_DESC:
                changeOrderByType(orderByType,false,needCallback);
                if (mOrderByType == RecommendOrderByTypes.SALE_COUNT_ASC) {
                    //之前已经选中当前Tab的升序
                    //改变为降序
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mComprehensiveTab.setSelected(false);
                    mSaleCountTab.setSelected(true);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(false);
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mSaleCountSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                }
                break;
        }
    }


    public int getOrderByType() {
        return mOrderByType;
    }

    /**
     * 改变保存的排序方式
     *
     * @param orderByType
     */
    public void changeOrderByType(int orderByType,boolean isSynthesize, boolean needCallback) {
        if (mOrderByChangeListener != null && needCallback) {
            mOrderByChangeListener.onOrderByChange(orderByType,isSynthesize);
        }
    }

    public void setOrderByChangeListener(OrderByChangeListener orderByChangeListener) {
        mOrderByChangeListener = orderByChangeListener;
    }

    /**
     * 筛选tab选中综合
     */
    public interface ComprehensiveSelectListener {
        void onSelectChange(boolean selected);
    }

    /**
     * 排序方式选择改变监听
     */
    public interface OrderByChangeListener {
        /**
         * 选择的排序方式发生了改变
         *
         * @param newOrderByType 对应{@link OrderByTypes}
         */
        void onOrderByChange(int newOrderByType,boolean isSynthesize);
    }
}
