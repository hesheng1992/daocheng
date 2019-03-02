package com.a1magway.bgg.p.collection;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.CollectionData;
import com.a1magway.bgg.data.repository.ICollectionListData;
import com.a1magway.bgg.p.order.OrderListAdapter;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.almagway.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

public class CollectionVH extends BaseRecyclerVH<CollectionData> {

    private OrderListAdapter.ItemOperationListener mItemOperationListener;

    private ICollectionListData mICollectionListData;

    @BindView(R.id.item_product_brand)
    TextView mProductBrand;

    @BindView(R.id.item_product_title)
    TextView mProductTitle;

    @BindView(R.id.item_product_chb)
    CheckBox mProCollectionChb;

    @BindView(R.id.item_product_price_original)
    TextView mProductPriceOriginal;

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

    @BindView(R.id.tv_product_down_sale)
    TextView tv_product_down_sale;

    List<Integer> proIds = new ArrayList<>();

    public CollectionVH(@NonNull ViewGroup parent, ICollectionListData iCollectionData) {
        super(parent, R.layout.item_grid_product);
        mICollectionListData = iCollectionData;
        mProductPriceOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
        Log.d("enid", "CollectionVH execute");

    }

    public void showViewContent(final CollectionData collectionItem, ProductCollectionListAdapter adapter, final int position, boolean isShowCheckBox
            , final ProductCollectionListAdapter.ItemClickListener itemClickListener) {
        super.showViewContent(collectionItem);

        // 公共显示
        mProductBrand.setText(collectionItem.getBrandName());
        mProductTitle.setText(collectionItem.getName());
        ImageLoaderUtil.displayImage(mProductImg, collectionItem.getCover());
        mProductPriceOriginal.setText(String.format(getContext().getResources().getString(R.string.format_price), collectionItem.getListPrice()));
        if (StringUtil.isEmpty(collectionItem.getActivityPrice())) {
            mProductPriceDiscount.setText(String.format(getContext().getResources().getString(R.string.format_price), "0.00"));
        } else {
            mProductPriceDiscount.setText(String.format(getContext().getResources().getString(R.string.format_price), collectionItem.getActivityPrice()));
        }
        mProfitTv.setText(String.format(getContext().getResources().getString(R.string.format_price), collectionItem.getProfit()));
        if (collectionItem.getSellOut() == 0) {//无货
            mSaleOutTv.setVisibility(View.VISIBLE);
            mProCollectionChb.setVisibility(View.INVISIBLE);
        } else {
            mSaleOutTv.setVisibility(View.INVISIBLE);
            if (isShowCheckBox) {
                mProCollectionChb.setVisibility(View.VISIBLE);
            } else {
                mProCollectionChb.setVisibility(View.INVISIBLE);
            }
            mProCollectionChb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    List<CollectionData> collectionSelectData = GlobalData.getInstance().getCollectionSelectData();
                    if (collectionSelectData.contains(collectionItem)) {
                        collectionSelectData.remove(collectionItem);
                    } else {
                        collectionSelectData.add(collectionItem);
                    }
                    GlobalData.getInstance().setCollectionSelectData(collectionSelectData);
                    if (itemClickListener != null) {
                        itemClickListener.onItemClickCheckedBox(position);
                    }
                }
            });
            //会员等级3以下不显示产品利润
            if (GlobalData.getInstance().getLoginData().getMemberGrade() >= 3) {
                mProfitLayout.setVisibility(View.VISIBLE);
            } else {
                mProfitLayout.setVisibility(View.INVISIBLE);
            }
        }

        //显示收藏的产品是否下架或者售罄
        if (collectionItem.getDownSale() == -1 || collectionItem.getSellOut() < 1) {
            tv_product_down_sale.setVisibility(View.VISIBLE);
            if (collectionItem.getDownSale() == -1) {
                tv_product_down_sale.setText("商品已下架");
            } else {
                tv_product_down_sale.setText("商品已售罄");
            }
        } else {
            tv_product_down_sale.setVisibility(View.GONE);
        }

    }

    public List<Integer> getSelectedProductIds() {
        return proIds;
    }

    public CollectionVH setItemOperationListener(
            OrderListAdapter.ItemOperationListener itemOperationListener) {
        mItemOperationListener = itemOperationListener;
        return this;
    }


    private TimeCountDownController createCountdownController(
            Date date, TextView textView, TimeCountDownController.CountDownListener listener) {
        return new TimeCountDownController(date, textView, null, listener);
    }

}
