package com.a1magway.bgg.p.logistics;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.LogisticsDeliverData;

import butterknife.BindView;

/**
 * Created by enid on 2018/6/15.
 */

public class LogisticsVH extends BaseRecyclerVH<LogisticsDeliverData> {

    @BindView(R.id.item_logistics_text)
    TextView mLogisticsTv;

    @BindView(R.id.item_logistics_dot_imv)
    ImageView mDotImage;

    public LogisticsVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_logistics);
    }

    public void showViewContent(LogisticsDeliverData data, int position) {
        super.showViewContent(data);
        if (position == 0) {
            mDotImage.setBackgroundResource(R.drawable.ic_dot_black);
        } else {
            mDotImage.setBackgroundResource(R.drawable.ic_dot_gray);
        }

        mLogisticsTv.setText(data.getTime() + data.getContext());
    }
}
