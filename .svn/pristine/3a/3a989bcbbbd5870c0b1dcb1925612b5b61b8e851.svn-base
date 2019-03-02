package com.a1magway.bgg.p.logistics;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.util.ImageLoaderUtil;

import butterknife.BindView;

public class LogisticsOrderInfoVH extends BaseRecyclerVH<OrderCommodity> {


    @BindView(R.id.logistice_order_info_img)
    ImageView mOrderImage;

    public LogisticsOrderInfoVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_logistics_order_info);
    }


    public void showViewContent(OrderCommodity data) {
        super.showViewContent(data);
        ImageLoaderUtil.displayImage(mOrderImage, data.getCommodityPicturePath());
    }

}
