package com.a1magway.bgg.p.productReturn;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.AfterSaleBean;
import com.a1magway.bgg.data.entity.Commodity;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.v.order.ImageMoreAdapter;
import com.a1magway.bgg.v.productReturn.bean.ReturnGoodsBean;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by enid on 2018/9/8.
 * 售后列表适配器
 */

public class AfterSaleAdapter extends BaseRecyclerAdapter<AfterSaleAdapter.ViewHold,AfterSaleBean>{

    private Context context;

    public AfterSaleAdapter(List<AfterSaleBean> list,Context context) {
        super(list);
        this.context=context;
    }

    private OnClickItemListener onClickItemListener;

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @Override
    public ViewHold onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHold(parent,R.layout.item_after_sale_list);
    }

    @Override
    public void onRealBindViewHolder(ViewHold holder, int position) {
        super.onRealBindViewHolder(holder, position);
        final AfterSaleBean afterSaleBean=getList().get(position);
        //多个商品
        if (afterSaleBean.getSkuList().size()>1){
            holder.one_liner.setVisibility(View.GONE);
            holder.image_more_relative.setVisibility(View.VISIBLE);
            holder.recycler_image.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
            holder.imageMoreAdapter=new ImageMoreAdapter(context);
            holder.recycler_image.setAdapter(holder.imageMoreAdapter);
            List<ReturnGoodsBean> list=new ArrayList<>();
            for (OrderCommodity skuListBean:afterSaleBean.getSkuList()
                 ) {
                ReturnGoodsBean bean=new ReturnGoodsBean();
                bean.setImageUrl(skuListBean.getCommodityPicturePath());
                list.add(bean);
            }
            holder.imageMoreAdapter.setList(list);
            holder.imageMoreAdapter.setOnClickImageListenr(new ImageMoreAdapter.OnClickImageListenr() {
                @Override
                public void onClickItem() {
                    if (onClickItemListener!=null){
                        onClickItemListener.onClickItem(afterSaleBean);
                    }
                }
            });

            //单个商品
        }else{
            holder.one_liner.setVisibility(View.VISIBLE);
            holder.image_more_relative.setVisibility(View.GONE);
        }


        OrderCommodity item=afterSaleBean.getSkuList().get(0);
        ImageLoaderUtil.displayImage(holder.returnProductGoodsImg, item.getCommodityPicturePath());
        holder.returnProductName.setText(item.getCommodityBrand());
        holder.returnProductDec.setText(item.getCommodityName());
        if (!StringUtil.isEmpty(item.getCommoditySpec())){
            String conetn[]=item.getCommoditySpec().split(" ");
            if (conetn.length>0){
                if (!StringUtil.isEmpty(conetn[0])){
                    holder.returnProductColor.setText(conetn[0]);
                }
                if (conetn[1]==null){
                    holder.returnProductSize.setVisibility(View.GONE);
                }else {
                    holder.returnProductSize.setText(conetn[1]);
                }
            }
        }
        holder.returnProductMoney.setText("成交价： ¥"+item.getCommoditySellPrice());
        holder.returnProductIsSelect.setVisibility(View.INVISIBLE);
//        if (returnProductListdapter.isHideSelct()){
//            holder.returnProductIsSelect.setVisibility(View.GONE);
//        }

        //1.申请中 2.审核成功 3.审核失败 4.已撤销 5.评估中 6.已退款 7.退款中 8.寄回中 9.退款失败
        switch (afterSaleBean.getStatus()){
            case 1:
                //退货
                if (afterSaleBean.getType()==1){
                    holder.showStatusText.setText("申请退货中");
                    //退款
                }else if (afterSaleBean.getType()==3){
                    holder.showStatusText.setText("申请退款中");
                }
                break;
            case 2:
                holder.showStatusText.setText("审核成功");
                break;
            case 3:
                holder.showStatusText.setText("审核失败");
                break;
            case 4:
                if (afterSaleBean.getType()==1){
                    holder.showStatusText.setText("退货申请已撤销");
                    //退款
                }else if (afterSaleBean.getType()==3){
                    holder.showStatusText.setText("退款申请已撤销");
                }
                break;
            case 5:
                holder.showStatusText.setText("商品评估中");
                    //退款
                break;
            case 6:
                if (afterSaleBean.getType()==1){
                    holder.showStatusText.setText("已退货");
                    //退款
                }else if (afterSaleBean.getType()==3){
                    holder.showStatusText.setText("已退款");
                }
                break;
            case 7:
                if (afterSaleBean.getType()==1){
                    holder.showStatusText.setText("退货中");
                    //退款
                }else if (afterSaleBean.getType()==3){
                    holder.showStatusText.setText("退款中");
                }
                break;
            case 8:
                if (afterSaleBean.getType()==1){
                    holder.showStatusText.setText("寄回中");
                    //退款
                }else if (afterSaleBean.getType()==3){
                    holder.showStatusText.setText("退款中");
                }
                break;
            case 9:
                if (afterSaleBean.getType()==1){
                    holder.showStatusText.setText("退货中");
                    //退款
                }else if (afterSaleBean.getType()==3){
                    holder.showStatusText.setText("退款中");
                }
                break;
        }



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItemListener!=null){
                    onClickItemListener.onClickItem(afterSaleBean);
                }
            }
        });

    }

    public static class ViewHold extends BaseRecyclerVH<AfterSaleBean.SkuListBean> {
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

        @BindView(R.id.image_show_status)
        ImageView showStatusImage;
        @BindView(R.id.text_show_status)
        TextView showStatusText;

        //多商品个图片显示
        @BindView(R.id.recycler_image)
        RecyclerView recycler_image;
        private ImageMoreAdapter imageMoreAdapter;

        @BindView(R.id.image_more_relative)
        RelativeLayout image_more_relative;

        //单个商品展示
        @BindView(R.id.one_liner)
        LinearLayout one_liner;

        public ViewHold(@NonNull ViewGroup parent, int layoutResId) {
            super(parent, layoutResId);
        }

    }

    public interface OnClickItemListener{
        void onClickItem(AfterSaleBean afterSaleBean);
    }
}
