package com.a1magway.bgg.v.productReturn.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.ReturnGoodsDetailBean;
import com.a1magway.bgg.data.entity.ReturnLogInfoBean;

import java.util.List;

import butterknife.BindView;

/**
 * 获取退货物流信息 适配器
 */
public class ReturnLogisInfoAdapter extends BaseRecyclerAdapter<ReturnLogisInfoAdapter.ViewHold,ReturnGoodsDetailBean.LogsBean> {


    public ReturnLogisInfoAdapter(List<ReturnGoodsDetailBean.LogsBean> list) {
        super(list);
    }

    @Override
    public ViewHold onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHold(parent, R.layout.item_log_info);
    }

    @Override
    public void onRealBindViewHolder(ViewHold holder, int position) {
        super.onRealBindViewHolder(holder, position);
        ReturnGoodsDetailBean.LogsBean item = getItem(position);
        holder.imageView.setImageResource(R.drawable.icon_faest_loginfo_image);
       if (position==getItemCount()-1){
           holder.image_yuandian_2.setVisibility(View.GONE);
           holder.view.setVisibility(View.INVISIBLE);
           holder.imageView.setImageResource(R.drawable.icon_last_loginfo_image);
       }else{
           holder.view.setVisibility(View.VISIBLE);
           holder.image_yuandian_2.setVisibility(View.GONE);
           holder.imageView.setImageResource(R.drawable.icon_faest_loginfo_image);
       }
       holder.text_time.setText(item.getCreateDate());
       holder.text_title.setText(item.getDescription());
    }

    class ViewHold extends BaseRecyclerVH<ReturnLogInfoBean.LogisticsBean.DataBean>{

        @BindView(R.id.image_yuandian)
        ImageView imageView;
        @BindView(R.id.text_title)
        TextView text_title;
        @BindView(R.id.text_time)
        TextView text_time;

        @BindView(R.id.image_yuandian_2)
        ImageView image_yuandian_2;

        @BindView(R.id.view_line)
        View view;

        public ViewHold(@NonNull ViewGroup parent, int layoutResId) {
            super(parent, layoutResId);
        }
    }
}
