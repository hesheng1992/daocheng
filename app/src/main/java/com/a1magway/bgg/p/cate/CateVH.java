package com.a1magway.bgg.p.cate;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.CateItem;
import com.a1magway.bgg.util.ImageLoaderUtil;

import butterknife.BindView;

/**
 * 分类列表的Item的ViewHolder
 * Created by jph on 2017/7/21.
 */
public class CateVH extends BaseRecyclerVH<CateItem> {
    @BindView(R.id.cate_img)
    ImageView mImg;

    public CateVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.cate_item_grid_cate);
    }

    @Override
    public void showViewContent(CateItem cateItem) {
        super.showViewContent(cateItem);

        ImageLoaderUtil.displayImage(mImg, cateItem.getSmallPath(),R.drawable.cate_placeholder);
    }
}
