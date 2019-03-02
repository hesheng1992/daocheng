package com.a1magway.bgg.p.mainhome;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.GoodsRecommendData;
import com.a1magway.bgg.data.repository.mainhome.ISpecialProductData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;
import com.a1magway.bgg.v.mainhome.SpecialProductContract;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/14.
 */

public class SpecialProductP extends BaseLoadP<GoodsRecommendData, SpecialProductContract.View> implements SpecialProductContract.Presenter {

    @Inject
    ISpecialProductData mISpecialProductData;

    @Inject
    ProductGridAdapter mAdapter;

    private int pageId = 0;

    private int order_by = 1;

    private int asc_desc = 2;

    private boolean initBannerData = true;//初始化banner数据，保证只初始化一次

    @Inject
    public SpecialProductP(@NonNull SpecialProductContract.View view) {
        super(view);
    }

    @Nullable
    @Override
    public Observable<GoodsRecommendData> getDataObservable() {
        return mISpecialProductData.getSubject(GlobalData.getInstance().getUserId(),
                mView.getId(),
                order_by,
                asc_desc,
                pageId,
                AppConfig.PAGE_SIZE_2_ROW);
    }

    private GoodsRecommendData data;

    @Override
    protected void onLoadSuccess(GoodsRecommendData data) {
        super.onLoadSuccess(data);
        if (pageId == 0) {
            mAdapter.setList(data.getProducts());
        } else {
            mAdapter.addList(data.getProducts());
        }
        mView.setTitle(data.getTitle(), data.getRemainTime());
        this.data = data;
        if (data.getBanners() != null) {
            getBannerList(data.getBanners().getBigPath());
        }
        checkShowNoneData(data);
        mView.hideLoginLoading();
        mView.setLoadable(CollectionUtil.isNotEmpty(data.getProducts()) && data.getProducts().size() > AppConfig.PAGE_SIZE_2_ROW);
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        mView.stopLoadMore();
        checkShowNoneData(data);
        mView.hideLoginLoading();
    }

    @Override
    public void onCreate() {
        mAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        String comeForm = mView.getComeFrom();
                        Log.e("come", comeForm);
                        ProductDetailsActivity.start(
                                v.getContext(), mAdapter.getItem(position).getId(),
                                mAdapter.getItem(position).getSubject_id(), comeForm);
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
        mView.setAdapter(mAdapter);
        loadData(true);
    }

    @Override
    public void onDestroy() {

    }

    public void executeFilter(int order_by, int asc_desc) {
        this.order_by = order_by;
        this.asc_desc = asc_desc;
        reload();
    }

    public void reload() {
        pageId = 0;
        loadData(false);
    }

    public void loadMore() {
        if (mAdapter.getRealItemCount() == 0) {
            pageId = 0;
        } else {
//            pageId = mAdapter.getItem(mAdapter.getRealItemCount() - 1).getId();
            pageId++;
        }
        loadData(false);
    }

    private void getBannerList(String banners) {
        if (banners == null) return;
        if (initBannerData) {
            String[] strings = banners.split(";");
            List<String> result = new ArrayList<>();
            result.addAll(Arrays.asList(strings));
            mView.setBannerData(result);
            initBannerData = false;
        }
    }
}
