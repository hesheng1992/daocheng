package com.a1magway.bgg.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.OrderByTypes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 搜索结果上方的Tab布局
 * Created by jph on 2017/7/31.
 */
public class SearchTabLayout extends LinearLayout {
    @OrderByTypes.OrderByType
    private int mOrderByType;//选中的排序方式
    private FilterSelectListener mFilterSelectListener;
    private OrderByChangeListener mOrderByChangeListener;

    @BindView(R.id.result_tab_comprehensive_count)
    View mComprehensiveTab;
    @BindView(R.id.result_tab_sale_count)
    View mSaleCountTab;
    @BindView(R.id.result_tab_price)
    View mSalePriceTab;
    @BindView(R.id.result_tab_img_price_sort)
    ImageView mPriceSortImg;

    @BindView(R.id.result_tab_discount)
    View mDiscountTab;
    @BindView(R.id.result_tab_img_discount_sort)
    ImageView mDiscountSortImg;
    @BindView(R.id.result_tab_img_sale_sort)
    ImageView mSaleSortImg;

    public SearchTabLayout(Context context) {
        super(context);
        init();
    }

    public SearchTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.search_records_layout_tab, this, true);

        setOrientation(HORIZONTAL);

        ButterKnife.bind(this);
    }


    private static boolean isExit = false;

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


//    @OnClick(R.id.result_tab_filter)
//    public void onClickFilterTab(View v) {
//        //点击筛选
//        boolean newSelect = !mFilterTab.isSelected();
//        mFilterTab.setSelected(newSelect);
//
//        if (mFilterSelectListener != null) {
//            mFilterSelectListener.onSelectChange(newSelect);
//        }
//    }

    /**
     * 设置筛选按钮是否选中,不会call回调
     *
     * @param
     */
//    public void setFilterSelected(boolean selected) {
//        mFilterTab.setSelected(selected);
//    }

    @OnClick(R.id.result_tab_sale_count)
    public void onClickSaleCountTab(View v) {
        if (mOrderByType == OrderByTypes.SALE_COUNT_DESC) {
            setOrderByType(OrderByTypes.SALE_COUNT_ASC);
        } else {
            setOrderByType(OrderByTypes.SALE_COUNT_DESC);
        }
    }


    @OnClick(R.id.result_tab_price)
    public void onClickPriceTab(View v) {
        if (mOrderByType == OrderByTypes.PRICE_ASC) {
            setOrderByType(OrderByTypes.PRICE_DESC);
        } else {
            setOrderByType(OrderByTypes.PRICE_ASC);
        }
    }

    @OnClick(R.id.result_tab_discount)
    public void onClickDiscountTab(View v) {
        if (mOrderByType == OrderByTypes.DISCOUNT_ASC) {
            setOrderByType(OrderByTypes.DISCOUNT_DESC);
        } else {
            setOrderByType(OrderByTypes.DISCOUNT_ASC);
        }
    }
    @OnClick(R.id.result_tab_comprehensive_count)
    public void onComprehensive(View v){
        setOrderByType(OrderByTypes.COMPREHENSIVE_ASC);
    }


    /**
     * 设置新的排序方式,会call回调
     *
     * @param orderByType 对应{@link OrderByTypes}
     */
    public void setOrderByType(@OrderByTypes.OrderByType int orderByType) {
        setOrderByType(orderByType, true);
    }

    /**
     * 设置新的排序方式
     *
     * @param orderByType  对应{@link OrderByTypes}
     * @param needCallback 是否需要回调
     */
    public void setOrderByType(@OrderByTypes.OrderByType int orderByType, boolean needCallback) {
        if (orderByType == mOrderByType) {
            return;
        }
//        cancelSelectFilter();

        if (!isExit) {
            isExit = true;
            mHandler.sendEmptyMessageDelayed(0, 2000);
        }else {
//            Toast.makeText(getContext(), "请稍等！",Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            return;
        }

        switch (orderByType) {
            case OrderByTypes.COMPREHENSIVE_ASC:
                mSaleCountTab.setSelected(false);
                mSalePriceTab.setSelected(false);
                mDiscountTab.setSelected(false);
                mComprehensiveTab.setSelected(true);
                mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                mSaleSortImg.setImageResource(R.drawable.ic_search_tab);
                break;
            case OrderByTypes.SALE_COUNT_ASC:
                if (mOrderByType== OrderByTypes.SALE_COUNT_DESC){
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                }else {
                    mSaleCountTab.setSelected(true);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(false);
                    mComprehensiveTab.setSelected(false);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                }

                break;
            case OrderByTypes.SALE_COUNT_DESC:
                if (mOrderByType== OrderByTypes.SALE_COUNT_ASC){
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                }else {
                    mSaleCountTab.setSelected(true);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(false);
                    mComprehensiveTab.setSelected(false);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                }

                break;
            case OrderByTypes.PRICE_ASC:
                if (mOrderByType == OrderByTypes.PRICE_DESC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(true);
                    mDiscountTab.setSelected(false);
                    mSaleCountTab.setSelected(false);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case OrderByTypes.PRICE_DESC:
                if (mOrderByType == OrderByTypes.PRICE_ASC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(true);
                    mDiscountTab.setSelected(false);
                    mSaleCountTab.setSelected(false);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab);
                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case OrderByTypes.DISCOUNT_ASC:
                if (mOrderByType == OrderByTypes.DISCOUNT_DESC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(true);
                    mSaleCountTab.setSelected(false);

                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_asc);
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
            case OrderByTypes.DISCOUNT_DESC:
                if (mOrderByType == OrderByTypes.DISCOUNT_ASC) {
                    //之前已经选中当前tab的降序
                    //改变成升序
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                } else {
                    //之前未选中当前tab
                    //取消选中的其他排序方式
                    mSaleCountTab.setSelected(false);
                    mSalePriceTab.setSelected(false);
                    mDiscountTab.setSelected(true);
                    mSaleCountTab.setSelected(false);

                    mPriceSortImg.setImageResource(R.drawable.ic_search_tab);
                    mDiscountSortImg.setImageResource(R.drawable.ic_search_tab_desc);
                    mSaleSortImg.setImageResource(R.drawable.ic_search_tab);
                }
                break;
        }

        if (needCallback) {
            changeOrderByType(orderByType);
        } else {
            mOrderByType = orderByType;
        }
    }

    /**
     * 取消选中筛选
     */
//    public void cancelSelectFilter() {
//        if (!mFilterTab.isSelected()) {
//            return;
//        }
//
//        mFilterTab.setSelected(false);
//        if (mFilterSelectListener != null) {
//            mFilterSelectListener.onSelectChange(false);
//        }
//    }

    public int getOrderByType() {
        return mOrderByType;
    }

    /**
     * 改变保存的排序方式
     *
     * @param orderByType
     */
    public void changeOrderByType(int orderByType) {
        mOrderByType = orderByType;

        if (mOrderByChangeListener != null) {
            mOrderByChangeListener.onOrderByChange(orderByType);
        }
    }

    public void setFilterSelectListener(FilterSelectListener filterSelectListener) {
        mFilterSelectListener = filterSelectListener;
    }

    public void setOrderByChangeListener(OrderByChangeListener orderByChangeListener) {
        mOrderByChangeListener = orderByChangeListener;
    }

    /**
     * 筛选tab选中监听
     */
    public interface FilterSelectListener {
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
        void onOrderByChange(int newOrderByType);
    }
}
