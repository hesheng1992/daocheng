package com.a1magway.bgg.p.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.ActivityData;
import com.a1magway.bgg.data.entity.ActivityProduct;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.a1magway.bgg.v.web.WebActivity;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 首页专题列表的item对应的ViewHolder
 * Created by jph on 2017/7/24.
 */
public class HomeActivityVH extends BaseRecyclerVH<ActivityData> {

    private HomeProductAdapter mHomeProductAdapter;

    @BindView(R.id.activity_img_cover)
    ImageView mCoverImg;
    @BindView(R.id.activity_recycler_product)
    RecyclerView mProductRecyclerView;//显示关联的商品列表

    public HomeActivityVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.home_item_list_activity);

        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mProductRecyclerView.addItemDecoration(new LinearItemDecoration(getContext(),
                R.color.transparent, R.dimen.home_product_divider));
    }

    @Override
    public void showViewContent(final ActivityData activityData) {
        mHomeProductAdapter = new HomeProductAdapter(new ArrayList<ActivityProduct>());
        mHomeProductAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                ProductDetailsActivity.start(getContext(),
                        mHomeProductAdapter.getItem(position).getId(),mHomeProductAdapter.getItem(position).getSubject_id());
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        mProductRecyclerView.setAdapter(mHomeProductAdapter);
        ImageLoaderUtil.displayImage(mCoverImg, activityData.getBannerPathSmall());
        mHomeProductAdapter.setList(activityData.getProductList());
        mCoverImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebActivity.start(getContext(), activityData.getHtmlPath(), activityData.getTitle(), true);
            }
        });
    }
}
