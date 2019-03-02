package com.a1magway.bgg.v.productReturn.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.util.ImageLoaderUtil;

import java.util.List;

import butterknife.BindView;

/**
 * 物流详情页面适配器
 */
public class RefundGoodsLogInfoAdapter extends BaseRecyclerAdapter<RefundGoodsLogInfoAdapter.ViewHold,String> {


    public RefundGoodsLogInfoAdapter(List<String> list) {
        super(list);
    }

    @Override
    public ViewHold onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHold(parent,0);
    }

    @Override
    public void onRealBindViewHolder(ViewHold holder, int position) {
        super.onRealBindViewHolder(holder, position);
        ImageLoaderUtil.displayBannerImage(holder.imageView,getList().get(position));
    }

    class ViewHold extends BaseRecyclerVH{
        @BindView(R.id.image)
        ImageView imageView;
        public ViewHold(@NonNull ViewGroup parent, int layoutResId) {
            super(parent, R.layout.item_image_refund_loginfo);
        }

    }
}
