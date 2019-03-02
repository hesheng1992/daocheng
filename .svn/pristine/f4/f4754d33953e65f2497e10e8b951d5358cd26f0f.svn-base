package com.a1magway.bgg.p.product;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.util.ImageLoaderUtil;

import butterknife.BindView;

/**
 * 商品详情下方多图列表Item对应的ViewHolder
 * Created by jph on 2017/8/9.
 */
public class DetailsPicVH extends BaseRecyclerVH<String> {

    @BindView(R.id.pic_img)
    ImageView mImg;

    public DetailsPicVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.product_detail_item_list_pic);
    }

    @Override
    public void showViewContent(String s) {
        super.showViewContent(s);

        ImageLoaderUtil.displayImage(mImg, s);
    }
}
