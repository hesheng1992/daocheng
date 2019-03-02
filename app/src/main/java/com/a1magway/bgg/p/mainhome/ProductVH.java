package com.a1magway.bgg.p.mainhome;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;

import butterknife.BindView;

public class ProductVH extends BaseRecyclerVH<ProductBean> {

    @BindView(R.id.item_product_brand)
    TextView mProductBrand;

    @BindView(R.id.item_product_title)
    TextView mProductTitle;

    @BindView(R.id.item_product_price_original)
    TextView mProductPrice;

    @BindView(R.id.item_product_price_discount)
    TextView mProductPriceDiscount;

    @BindView(R.id.item_product_img)
    ImageView mProductImg;

    @BindView(R.id.item_product_profit_tv)
    TextView mProfitTv;

    @BindView(R.id.item_product_sale_out_tv)
    TextView mSaleOutTv;

    @BindView(R.id.product_profit_layout)
    LinearLayout mProfitLayout;

    public ProductVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.item_grid_product);
        mProductPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
    }

    @Override
    public void showViewContent(ProductBean productBean) {
        super.showViewContent(productBean);
        mProductBrand.setText(productBean.getBrandName());
        mProductTitle.setText(productBean.getName());
        ImageLoaderUtil.displayImage(mProductImg,productBean.getCover());
        mProductPrice.setText(String.format(getContext().getResources().getString(R.string.format_price),productBean.getListPrice()));
        mProductPriceDiscount.setText(String.format(getContext().getResources().getString(R.string.format_price),productBean.getActivityPrice()));
        mProfitTv.setText(String.format(getContext().getResources().getString(R.string.format_price),productBean.getProfit()));
        //未登录和会员等级3以下不显示产品利润
        if (GlobalData.getInstance().isLogin() && GlobalData.getInstance().getLoginData().getMemberGrade() >= 3){
            mProfitLayout.setVisibility(View.VISIBLE);
        }else {
            mProfitLayout.setVisibility(View.INVISIBLE);
        }
    }
}
