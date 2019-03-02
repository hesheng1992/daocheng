package com.a1magway.bgg.p.pintuan;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.PintuanGoodsBeanData;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.refactor.BaseContract;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;
import com.a1magway.bgg.v.pintuan.PingtuanProductContract;
import com.a1magway.bgg.v.product.ProductDetailsActivity;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/8/22.
 */

public class PingtuanProductP extends BaseLoadP<List<ProductBean>, PingtuanProductContract.View> implements
        PingtuanProductContract.Presenter {

    @Inject
    APIManager apiManager;
    private int pageId = 0;
    private int order_by = 1;
    private int asc_desc = 2;

    private ProductGridAdapter mAdapter;
    private List<ProductBean> data;

    @Inject
    public PingtuanProductP(@NonNull PingtuanProductContract.View view) {
        super(view);
    }

    @Nullable
    @Override
    public Observable<List<ProductBean>> getDataObservable() {
        return apiManager.getMorePintuanHomeData(GlobalData.getInstance().getUserId(),
                pageId, 20, order_by, asc_desc);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAdapter = new ProductGridAdapter(new ArrayList<ProductBean>());
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
    protected void onLoadSuccess(List<ProductBean> productBeans) {
        super.onLoadSuccess(productBeans);
        data = productBeans;
        if (pageId == 0) {
            mAdapter.setList(productBeans);
        } else {
            mAdapter.addList(productBeans);
        }
        checkShowNoneData(data);
    }


    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        mView.stopLoadMore();
        checkShowNoneData(data);
        mView.hideLoginLoading();
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
            pageId++;
        }
        loadData(false);
    }

}
