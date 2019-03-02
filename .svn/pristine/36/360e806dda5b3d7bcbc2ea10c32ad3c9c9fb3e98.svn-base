package com.a1magway.bgg.v.productReturn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ReturnGoodsReasonBean;
import com.a1magway.bgg.v.productReturn.bean.ReturnResason;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by enid on 2018/9/4.
 *
 * 退款退货原因适配器
 */

public class ApplyReturnReasonAdapter extends RecyclerView.Adapter<ApplyReturnReasonAdapter.ApplyReturnReasonViewHold> {
    private Context context;
    private List<ReturnGoodsReasonBean> list=new ArrayList<>();
    private OnClickImageChange onClickImageChange;
    public ApplyReturnReasonAdapter(Context context) {
        this.context=context;
    }

    public void setList(List<ReturnGoodsReasonBean> list){
        if (list!=null && list.size()>0){
            this.list=list;
            notifyDataSetChanged();
        }
    }
    public List<ReturnGoodsReasonBean> getList(){
        return list;
    }

    public void setOnClickImageChange(OnClickImageChange onClickImageChange){
        if (onClickImageChange!=null){
            this.onClickImageChange=onClickImageChange;
        }
    }

    @Override
    public ApplyReturnReasonViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ApplyReturnReasonViewHold(LayoutInflater.from(context).inflate(R.layout.item_recyler_pop_tuikuan_reason,parent,false));
    }

    @Override
    public void onBindViewHolder(final ApplyReturnReasonViewHold holder, final int position) {
        holder.textView.setText(list.get(position).getName());
        if (list.get(position).isClick()){
            holder.imageView.setImageResource(R.mipmap.is_select);
        }else{
            holder.imageView.setImageResource(R.mipmap.is_select_normal);
        }
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).isClick()){
                    holder.imageView.setImageResource(R.mipmap.is_select_normal);
                    list.get(position).setClick(false);
                }else{
                    holder.imageView.setImageResource(R.mipmap.is_select);
                    list.get(position).setClick(true);
                    onClickImageChange.onClickImage(list.get(position).getName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

    class ApplyReturnReasonViewHold extends RecyclerView.ViewHolder{
        @BindView(R.id.text_reason)
        TextView textView;
        @BindView(R.id.return_product_is_select)
        ImageView imageView;
        public ApplyReturnReasonViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            textView=itemView.findViewById(R.id.text_reason);
            imageView=itemView.findViewById(R.id.return_product_is_select);
        }
    }

    public interface OnClickImageChange{
        void onClickImage(String reason);
    }
}
