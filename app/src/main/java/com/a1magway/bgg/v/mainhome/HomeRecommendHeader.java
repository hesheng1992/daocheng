package com.a1magway.bgg.v.mainhome;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseViewOwner;
import com.a1magway.bgg.data.entity.RecommendOrderByTypes;
import com.a1magway.bgg.widget.CountDownTextView;
import com.a1magway.bgg.widget.RecommendTabLayout;

import butterknife.BindView;

/**
 * 推荐header
 */
public class HomeRecommendHeader extends BaseViewOwner {
    @BindView(R.id.recommend_normal_name_tv)
    TextView mNameTv;
    @BindView(R.id.recommend_normal_count_down_tv)
    CountDownTextView mCountDownTv;
    @BindView(R.id.recommend_tab)
    RecommendTabLayout mRecommendTabLayout;
    @BindView(R.id.layout_title_count_down)
    LinearLayout layoutTitleCountDown;

    private Context mContext;

    private OrderByChangeListener mListener;

    public HomeRecommendHeader(@NonNull Context context, OrderByChangeListener mListener,boolean showCountDown) {
        super(context, R.layout.home_special_recommend_layout);
        this.mContext = context;
        this.mListener = mListener;
        mRecommendTabLayout.setOrderByChangeListener(new TabOrderByChangeListener());
        if (showCountDown) {
            layoutTitleCountDown.setVisibility(View.VISIBLE);
        }else {
            layoutTitleCountDown.setVisibility(View.GONE);
        }
        setOrderByType(RecommendOrderByTypes.COMPREHENSIVE);
    }

    public void setTitle(String title, long countTime) {
        mNameTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); // 下划线
        mNameTv.setText(title);
//        mCountDownTv.startTickWork(countTime, false);
    }

    public long getCountDownTime(){
        return mCountDownTv.getCountDownTime();
    }

    public String getTitle(){
        return mNameTv.getText().toString();
    }

    public void setOrderByType(int orderType){
        mRecommendTabLayout.setOrderByType(orderType,false);
    }

    public void setOrderByTypeCallback(int orderType){
        mRecommendTabLayout.setOrderByType(orderType,true);
    }

    public int getOrderByType(){
        return mRecommendTabLayout.getOrderByType();
    }

    public void stopTimer() {
        mCountDownTv.stopTickWork();
    }

    public class TabOrderByChangeListener implements RecommendTabLayout.OrderByChangeListener {

        @Override
        public void onOrderByChange(int newOrderByType,boolean isSynthesize) {
            if (mListener == null) return;
            switch (newOrderByType) {
                case RecommendOrderByTypes.COMPREHENSIVE:
                    mListener.onOrderBy(1, 2);
                    break;
                case RecommendOrderByTypes.SALE_COUNT_ASC:
                    mListener.onOrderBy(2, 1);
                    break;
                case RecommendOrderByTypes.SALE_COUNT_DESC:
                    mListener.onOrderBy(2, 2);
                    break;
                case RecommendOrderByTypes.PRICE_ASC:
                    mListener.onOrderBy(3, 1);
                    break;
                case RecommendOrderByTypes.PRICE_DESC:
                    mListener.onOrderBy(3, 2);
                    break;
                case RecommendOrderByTypes.DISCOUNT_ASC:
                    mListener.onOrderBy(4, 1);
                    break;
                case RecommendOrderByTypes.DISCOUNT_DESC:
                    mListener.onOrderBy(4, 2);
                    break;

            }
        }
    }

    public interface OrderByChangeListener {
        void onOrderBy(int order_by, int asc_desc);
    }


}
