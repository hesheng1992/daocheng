package com.a1magway.bgg.p.cate;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.GlideApp;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.CateItem;

import butterknife.BindView;

/**
 * 对应第一条数据，横跨两列
 * Created by jph on 2017/7/21.
 */
public class TopCateVH extends BaseRecyclerVH<CateItem> {
    @BindView(R.id.cate_img)
    ImageView mImg;

    public TopCateVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.cate_item_grid_cate_top);
    }

    @Override
    public void showViewContent(CateItem cateItem) {
        super.showViewContent(cateItem);

        GlideApp.with(getContext())
                .load(cateItem.getSmallPath())
                .into(mImg);
    }
}
