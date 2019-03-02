package com.a1magway.bgg.p.home;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.ActivityProduct;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.StringFormatUtil;

import butterknife.BindView;

/**
 * 首页专题对应的商品
 * Created by jph on 2017/7/25.
 */
public class HomeProductVH extends BaseRecyclerVH<ActivityProduct> {

    @BindView(R.id.product_img)
    ImageView mImg;
    @BindView(R.id.product_txt_name)
    TextView mNameTxt;
    @BindView(R.id.product_txt_price)
    TextView mPriceTxt;

    public HomeProductVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.home_item_list_product);
    }

    public void showViewContent(ActivityProduct activityProduct, HomeProductAdapter adapter) {
        super.showViewContent(activityProduct);
        if (adapter.getItem(getAdapterPosition()).getId() == activityProduct.getId()) {
            ImageLoaderUtil.displayImage(mImg, activityProduct.getPath());
            mNameTxt.setText(activityProduct.getName());
            mPriceTxt.setText(StringFormatUtil.getPrice(getContext(), activityProduct.getPrice()));
        }
    }
}
