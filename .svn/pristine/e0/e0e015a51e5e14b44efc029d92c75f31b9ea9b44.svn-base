package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.p.order.OrderListAdapter;
import com.a1magway.bgg.p.order.OrderVH;
import com.a1magway.bgg.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lm on 2018/8/30.
 * 申请售后 详情页面
 */
public class ReturnProductVH extends BaseRecyclerVH<OrderCommodity> {


    @BindView(R.id.return_product_is_select)
    ImageView returnProductIsSelect;
    @BindView(R.id.return_product_goods_img)
    ImageView returnProductGoodsImg;
    @BindView(R.id.return_product_name)
    TextView returnProductName;
    @BindView(R.id.return_product_dec)
    TextView returnProductDec;
    @BindView(R.id.return_product_color)
    TextView returnProductColor;
    @BindView(R.id.return_product_size)
    TextView returnProductSize;
    @BindView(R.id.return_product_money)
    TextView returnProductMoney;

    @BindView(R.id.text_return_goods)
    TextView text_return_goods;
    /**
     * 显示商品数量
     */
    @BindView(R.id.text_show_number)
    TextView text_show_number;


    private ReturnProductListdapter.ItemOperationListener mItemOperationListener;



    public ReturnProductVH(@NonNull ViewGroup parent, int layoutResId) {
        super(parent, R.layout.item_return_product_layout);
    }

    public ReturnProductVH setItemOperationListener(ReturnProductListdapter.ItemOperationListener itemOperationListener) {
        mItemOperationListener = itemOperationListener;
        return this;
    }

    public void showViewContent(OrderCommodity item, final ReturnProductListdapter returnProductListdapter, final int position) {


        ImageLoaderUtil.displayImage(returnProductGoodsImg, item.getCommodityPicturePath());
        String conetn[]=item.getCommoditySpec().split(" ");
        returnProductName.setText(item.getCommodityBrand());
        returnProductDec.setText(item.getCommodityName());
        returnProductColor.setText(conetn[0]);
        if (conetn[1]==null){
            returnProductSize.setVisibility(View.GONE);
        }else {
            returnProductSize.setText(conetn[1]);
        }
        text_show_number.setText("x"+item.getCommodityNumbers());
        returnProductMoney.setText("成交价： ¥"+item.getCommoditySellPrice());


        if (returnProductListdapter.getList().get(position).isCheck()){
            returnProductIsSelect.setImageResource(R.mipmap.is_select);
        }else {
            returnProductIsSelect.setImageResource(R.mipmap.is_select_normal);
        }
        text_return_goods.setVisibility(View.GONE);
        if (item.getRefundId()!=0){
            returnProductIsSelect.setVisibility(View.INVISIBLE);
            text_return_goods.setVisibility(View.VISIBLE);
            returnProductListdapter.getList().get(position).setCheck(false);
            if (item.getRefundStatus()==1){
                text_return_goods.setText("退款中...");
            }else if (item.getRefundStatus()==2){
                text_return_goods.setText("退款完成");
            }else{
                text_return_goods.setText("退货中...");
            }

            switch (item.getRefundStatus()){
                //申请中
                case 1:
                    //退货退款
//                                if (com.getRefundType()==1){
//                                    returnGoodsBean.setTextReturn("退货中...");
//                                    //退款
//                                }else if (com.getRefundType()==3){
//                                    returnGoodsBean.setTextReturn("退款中...");
//                                }
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
                    if (item.getRefundType()==1){
                        text_return_goods.setText("商品评估中...");
                        //退款
                    }else if (item.getRefundType()==3){
                        text_return_goods.setText("退款中...");
                    }
                    break;
                //已退款
                case 6:
                    if (item.getRefundType()==1){
                        text_return_goods.setText("已退货");
                        //退款
                    }else if (item.getRefundType()==3){
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
                    text_return_goods.setText("退款中...");
                    //退货退款
                    break;
                //寄回中
                case 8:
                    if (item.getRefundType()==1){
                        text_return_goods.setText("商品寄回中...");
                        //退款
                    }else if (item.getRefundType()==3){
                        text_return_goods.setText("退款中...");
                    }
                    //退货退款
                    break;
                //退款失败
                case 9:
                    if (item.getRefundType()==1){
                        text_return_goods.setText("退货中...");
                        //退款
                    }else if (item.getRefundType()==3){
                        text_return_goods.setText("退款中...");
                    }
                    break;
            }




        }else{
            text_return_goods.setVisibility(View.GONE);
        }

        if (returnProductListdapter.isHideSelct()){
            returnProductIsSelect.setVisibility(View.GONE);
        }

        returnProductIsSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (returnProductListdapter.getList().get(position).isCheck()){
//                    returnProductListdapter.getPositions().remove(position);
                    returnProductIsSelect.setImageResource(R.mipmap.is_select_normal);
                    returnProductListdapter.getList().get(position).setCheck(false);

                }else {
//                    returnProductListdapter.getPositions().add(position);
                    returnProductListdapter.getList().get(position).setCheck(true);
                    returnProductIsSelect.setImageResource(R.mipmap.is_select);
                }
                int num=0;
                for (int i = 0; i < returnProductListdapter.getList().size(); i++) {
                    if (returnProductListdapter.getList().get(i).isCheck()){
                        num+=returnProductListdapter.getList().get(i).getCommodityNumbers();
                    }
                }
                mItemOperationListener.onItemClickSelectChange(num);
            }
        });
    }
}
