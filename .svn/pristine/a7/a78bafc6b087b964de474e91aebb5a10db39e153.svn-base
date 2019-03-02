package com.a1magway.bgg.p.seckill;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.entity.SecKillTypes;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.StringFormatUtil;

/** 秒杀专区的秒杀商品Item对应的ViewHolder Created by jph on 2017/7/28. */
public class ProductVH extends BaseRecyclerVH<ProductItem> {
    @BindView(R.id.seckill_img)
    ImageView mImg;

    @BindView(R.id.seckill_txt_brand)
    TextView mBrandTxt;

    @BindView(R.id.seckill_txt_name)
    TextView mNameTxt;

    @BindView(R.id.text_sell_price)
    TextView mPriceTxt;

    @BindView(R.id.text_list_price)
    TextView mOriginPriceTxt;

    @BindView(R.id.text_member_price)
    TextView mDiscountTxt;

    @BindView(R.id.seckill_txt_time)
    TextView mTimeTxt; // 倒计时显示

    public ProductVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.seckill_item_list_seckill);
        mOriginPriceTxt.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
    }

    public void showViewContent(int position, ProductItem productItem) {
        super.showViewContent(productItem);

        ImageLoaderUtil.displayImage(mImg, productItem.getCover());
        mBrandTxt.setText(productItem.getBrand());
        mNameTxt.setText(productItem.getName());
        // 秒杀商品显示原价，和折扣价格，秒杀折上折
        if ("1".equals(productItem.getProductType())) {
            mOriginPriceTxt.setText(
                    StringFormatUtil.getPrice(getContext(), productItem.getListPrice()));
            String productMemberPrice = productItem.getMemberPrice();
            if (TextUtils.isEmpty(productMemberPrice) || Double.valueOf(productMemberPrice) <= 0) {
                mPriceTxt.setVisibility(View.GONE);
                String memberPrice =
                        StringFormatUtil.getPrice(getContext(), productItem.getSellPrice());
                mDiscountTxt.setText(memberPrice);
                mDiscountTxt.setTextColor(getContext().getResources().getColor(R.color.text_black));
            } else {
                mPriceTxt.setVisibility(View.VISIBLE);
                String sellPrice =
                        StringFormatUtil.getPrice(getContext(), productItem.getSellPrice());
                mPriceTxt.setText(sellPrice);
                String memberPrice =
                        getContext().getResources().getString(R.string.member_seckill_price)
                                + StringFormatUtil.getPrice(
                                        getContext(), productItem.getMemberPrice());
                mDiscountTxt.setText(memberPrice);
                mDiscountTxt.setTextColor(getContext().getResources().getColor(R.color.text_red));
            }

        } else if ("2".equals(productItem.getProductType())) {
            // 会员商品显示原价和会员价（会员价值为折扣价值），显示“会员价标签”
            mPriceTxt.setVisibility(View.GONE);
            mOriginPriceTxt.setText(
                    StringFormatUtil.getPrice(getContext(), productItem.getListPrice()));
            String memberPrice =
                    getContext().getResources().getString(R.string.member_price)
                            + StringFormatUtil.getPrice(getContext(), productItem.getSellPrice());
            mDiscountTxt.setText(memberPrice);
        } else {
            // 普通商品三种价格都显示
            mPriceTxt.setVisibility(View.VISIBLE);
            mPriceTxt.setText(StringFormatUtil.getPrice(getContext(), productItem.getSellPrice()));
            mOriginPriceTxt.setText(
                    StringFormatUtil.getPrice(getContext(), productItem.getListPrice()));
            String memberPrice =
                    getContext().getResources().getString(R.string.member_price)
                            + StringFormatUtil.getPrice(getContext(), productItem.getMemberPrice());
            mDiscountTxt.setText(memberPrice);
        }
        itemView.setBackgroundResource(R.drawable.seckill_item_bg);
        mTimeTxt.setBackgroundResource(R.drawable.seckill_time_bg);
        if (productItem.getSecKillType() == SecKillTypes.ING) {
            mTimeTxt.setVisibility(View.VISIBLE);
        } else if (productItem.getSecKillType() == SecKillTypes.WAIT) {
            mTimeTxt.setVisibility(View.VISIBLE);
            mTimeTxt.setActivated(true);
        } else {
            mTimeTxt.setVisibility(View.GONE);
        }
    }

    public TimeCountDownController createCountdownController(
            final int position, final ProductItem productItem, final ProductGridAdapter adapter) {
        if (productItem.getSecKillType() == SecKillTypes.ING) {
            return new TimeCountDownController(
                    productItem.getDownSaleDate(),
                    mTimeTxt,
                    null,
                    new TimeCountDownController.CountDownListener() {
                        @Override
                        public void onFinish() {
                            mTimeTxt.setText(TimeCountDownController.TIME_DEFAULT);
                            adapter.removeCountdownController(position);
                            itemView.setBackgroundResource(R.drawable.seckill_item_bg_invalid);
                            mTimeTxt.setBackgroundResource(
                                    R.drawable.ic_home_seckill_bg_time_invalid);
                        }
                    });
        } else if (productItem.getSecKillType() == SecKillTypes.WAIT) {
            return new TimeCountDownController(
                    productItem.getOnSaleDate(),
                    mTimeTxt,
                    null,
                    new TimeCountDownController.CountDownListener() {
                        @Override
                        public void onFinish() {
                            adapter.removeItem(productItem);
                            adapter.removeCountdownController(position);
                        }
                    });
        }
        return null;
    }
}
