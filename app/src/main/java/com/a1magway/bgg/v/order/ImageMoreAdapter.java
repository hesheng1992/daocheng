package com.a1magway.bgg.v.order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.SalesReturn;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.v.productReturn.ReturnContentActivity;
import com.a1magway.bgg.v.productReturn.bean.ReturnGoodsBean;
import com.a1magway.bgg.v.saleorder.SaleOrderListActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单列表中，多个商品显示图片
 */
public class ImageMoreAdapter extends RecyclerView.Adapter<ImageMoreAdapter.ViewHold> {

    private OrderItem orderItem;

    private boolean invalid;

    private OnClickImageListenr onClickImageListenr;

    private List<ReturnGoodsBean> list=new ArrayList<>();

    private Context context;

    public ImageMoreAdapter(Context context){
        this.context=context;

    }

    public void setOnClickImageListenr(OnClickImageListenr onClickImageListenr) {
        this.onClickImageListenr = onClickImageListenr;
    }

    public void setOrderItem(OrderItem orderItem, boolean invalid) {
        this.orderItem = orderItem;
        this.invalid=invalid;
    }

    public void setList(List<ReturnGoodsBean> list){
        if (list!=null && list.size()>0){
            this.list=list;
            notifyDataSetChanged();
        }
    }

    public List<ReturnGoodsBean> getList(){
        return list;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHold(LayoutInflater.from(context).inflate(R.layout.item_return_recycler_image,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHold holder, final int position) {
        ImageLoaderUtil.displayBannerImage(holder.imageView,list.get(position).getImageUrl());
        if (list.get(position).isReturn()){
            holder.textView.setVisibility(View.VISIBLE);
            holder.textView.setText(list.get(position).getTextReturn());
        }else{
            holder.textView.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickImageListenr!=null){
                    onClickImageListenr.onClickItem();
                }
//                if (context instanceof SaleOrderListActivity) {
//                    OrderDetailsActivity.start(
//                            context, orderItem.getOrderNum(), orderItem.getCreatorId(), invalid);
//                } else {
//                        OrderDetailsActivity.start(
//                                context, orderItem.getOrderNum(), -1, invalid);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHold extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        public ViewHold(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.order_img_pro_2);
            textView=itemView.findViewById(R.id.text_return_goods);
        }
    }

    public interface OnClickImageListenr{
        void onClickItem();
    }
}
