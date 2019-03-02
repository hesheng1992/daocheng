package com.a1magway.bgg.v.mainhome;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.p.mainhome.ProductVH;

import java.util.List;

/**
 * Created by enid on 2018/6/11.
 */

public class ProductGridAdapter extends BaseRecyclerAdapter<ProductVH,ProductBean> {

    public ProductGridAdapter(List<ProductBean> list) {
        super(list);
    }

    @Override
    public ProductVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProductVH(parent);
    }

    @Override
    public void onRealBindViewHolder(ProductVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }
}
