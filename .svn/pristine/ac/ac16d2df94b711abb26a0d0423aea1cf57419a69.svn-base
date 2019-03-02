package com.a1magway.bgg.p.order;

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
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.StringFormatUtil;
import com.almagway.common.utils.StringUtil;

/**
 * 订单详情的货品
 * Created by jph on 2017/8/18.
 */
public class DetailsProVH extends BaseRecyclerVH<OrderDetailsCommodity> {
    @BindView(R.id.pro_img_pic)
    ImageView mPicImg;
    @BindView(R.id.pro_txt_num)
    TextView mNumTxt;
    @BindView(R.id.pro_txt_name)
    TextView mNameTxt;
    @BindView(R.id.pro_txt_brand)
    TextView mBrandTxt;
    @BindView(R.id.pro_txt_sku)
    TextView mSkuTxt;
    @BindView(R.id.pro_txt_count)
    TextView mCountTxt;
    @BindView(R.id.pro_txt_discount)
    TextView mDiscountTxt;
    @BindView(R.id.pro_txt_origin_price)
    TextView mOriginPriceTxt;
    @BindView(R.id.pro_txt_price)
    TextView mPriceTxt;
    //显示退货退款表示
    @BindView(R.id.text_return_goods)
    TextView text_return_goods;

    private boolean isShowText;



    public DetailsProVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.order_details_item_list_pro);
        mOriginPriceTxt.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }

    public boolean isShowText() {
        return isShowText;
    }

    public void setShowText(boolean showText) {
        isShowText = showText;
    }

    @Override
    public void showViewContent(OrderDetailsCommodity orderDetailsCommodity) {
        super.showViewContent(orderDetailsCommodity);
        if (orderDetailsCommodity.getRefundId()!=0 && isShowText()){
            text_return_goods.setVisibility(View.VISIBLE);
            switch (orderDetailsCommodity.getRefundStatus()){
                //申请中
                case 1:
                    //退货退款
                    text_return_goods.setText("待审核...");

                    break;
                //审核成功
                case 2:
                    //退货退款
//                                if (com.getRefundType()==1){
//                                    returnGoodsBean.setTextReturn("审核通过...");
//                                    //退款
//                                }else if (com.getRefundType()==3){
//                                    returnGoodsBean.setTextReturn("审核通过...");
//                                }
                    text_return_goods.setText("审核通过");

                    break;
                //审核失败
                case 3:
                    text_return_goods.setText("审核未通过");
                    break;
                //已撤销
                case 4:
                    //退货退款
//                                if (com.getRefundType()==1){
//                                    returnGoodsBean.setTextReturn("审核通过...");
//                                    //退款
//                                }else if (com.getRefundType()==3){
//                                    returnGoodsBean.setTextReturn("审核通过...");
//                                }
                    break;
                //评估中
                case 5:
                    if (orderDetailsCommodity.getRefundType()==1){
                        text_return_goods.setText("商品评估中...");
                        //退款
                    }else if (orderDetailsCommodity.getRefundType()==3){
                        text_return_goods.setText("退款中...");
                    }
                    break;
                //已退款
                case 6:
                    if (orderDetailsCommodity.getRefundType()==1){
                        text_return_goods.setText("已退货");
                        //退款
                    }else if (orderDetailsCommodity.getRefundType()==3){
                        text_return_goods.setText("已退款");
                    }
                    break;
                //退款中
                case 7:
//                                if (com.getRefundType()==1){
//                                    returnGoodsBean.setTextReturn("商品评估中...");
//                                    //退款
//                                }else if (com.getRefundType()==3){
//                                    returnGoodsBean.setTextReturn("退款中...");
//                                }
                    if (orderDetailsCommodity.getRefundType()==1){
                        text_return_goods.setText("退货中...");
                        //退款
                    }else if (orderDetailsCommodity.getRefundType()==3){
                        text_return_goods.setText("退款中...");
                    }
                    //退货退款
                    break;
                //寄回中
                case 8:
                    if (orderDetailsCommodity.getRefundType()==1){
                        text_return_goods.setText("商品寄回中...");
                        //退款
                    }else if (orderDetailsCommodity.getRefundType()==3){
                        text_return_goods.setText("退款中...");
                    }
                    //退货退款
                    break;
                //退款失败
                case 9:
                    if (orderDetailsCommodity.getRefundType()==1){
                        text_return_goods.setText("退货中");
                        //退款
                    }else if (orderDetailsCommodity.getRefundType()==3){
                        text_return_goods.setText("退款中");
                    }
                    break;
            }
        }else{
            text_return_goods.setVisibility(View.GONE);
        }
        ImageLoaderUtil.displayImage(mPicImg, orderDetailsCommodity.getCoverUrl());
        if (StringUtil.isNotEmpty(orderDetailsCommodity.getProductCode())) {
            mNumTxt.setText(
                    StringFormatUtil.format(getContext(), R.string.order_details_format_pro_num,
                            orderDetailsCommodity.getProductCode()));
        } else {
            mNumTxt.setText(R.string.order_details_default_pro_num);
        }
        mNameTxt.setText(orderDetailsCommodity.getProductName());
        mBrandTxt.setText(orderDetailsCommodity.getBrand());
        mSkuTxt.setText(orderDetailsCommodity.getSpecs());
        mCountTxt.setText(
                StringFormatUtil.format(getContext(), R.string.order_details_format_pro_count,
                        orderDetailsCommodity.getCount()));
        if (orderDetailsCommodity.getProductType() == 2) {
            StringBuilder price = new StringBuilder();
            if (OrderCommitP.showPingTuanPrice) {
                price.append("拼成价:");
            }
            price.append(StringFormatUtil.getPrice(getContext(),
                    orderDetailsCommodity.getListPrice()));
            mPriceTxt.setText(String.valueOf(price));
            mOriginPriceTxt.setVisibility(View.GONE);
        } else {
            mOriginPriceTxt.setVisibility(View.VISIBLE);
            StringBuilder price = new StringBuilder();
            if (OrderCommitP.showPingTuanPrice) {
                price.append("拼成价:");
            }
            price.append(StringFormatUtil.getPrice(getContext(),
                    orderDetailsCommodity.getSellPrice()));
            mPriceTxt.setText(String.valueOf(price));
        }
        mOriginPriceTxt.setText(StringFormatUtil.getPrice(getContext(),
                orderDetailsCommodity.getListPrice()));
        String memberPrice = orderDetailsCommodity.getMemberPrice();
        if (TextUtils.isEmpty(memberPrice)) {
            return;
        }
        String price = getContext().getResources().getString(R.string.member_price)
                + StringFormatUtil.getPrice(getContext(), memberPrice);
        mDiscountTxt.setText(price);
    }
}
