package com.a1magway.bgg.p.brand;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;

import butterknife.BindView;
import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * 品牌列表适配器
 * Created by jph on 2017/7/29.
 */
public class BrandAdapter extends IndexableAdapter<BrandItem> {

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        return new IndexVH(parent);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        return new ContentVH(parent);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        ((IndexVH) holder).showViewContent(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, final BrandItem brandItem) {
        ((ContentVH) holder).showViewContent(brandItem.getName());
    }

    static class IndexVH extends BaseRecyclerVH<String> {
        @BindView(R.id.brand_txt_index)
        TextView mIndexTxt;

        public IndexVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.brand_item_list_brand_index);
        }

        @Override
        public void showViewContent(String s) {
            super.showViewContent(s);
            mIndexTxt.setText(s);
        }
    }

    static class ContentVH extends BaseRecyclerVH<String> {

        @BindView(R.id.brand_txt_name)
        TextView mNameTxt;

        public ContentVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.brand_item_list_brand);
        }

        @Override
        public void showViewContent(String s) {
            super.showViewContent(s);
            mNameTxt.setText(s);
        }
    }
}