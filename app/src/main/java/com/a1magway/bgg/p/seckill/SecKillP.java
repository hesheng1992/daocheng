package com.a1magway.bgg.p.seckill;

import android.support.annotation.Nullable;
import android.view.View;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.repository.ISecKillData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.a1magway.bgg.v.seckill.ISecKillV;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.CollectionUtil;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/** Created by jph on 2017/7/28. */
public class SecKillP extends BaseLoadP<List<ProductItem>, ISecKillV> {
    private ISecKillData mSecKillData;
    private int mSecKillType;
    private ProductGridAdapter mAdapter;

    private int mLastItemId = -1; // 最后一个元素id，用于分页

    @Inject
    public SecKillP(
            ISecKillV secKillV,
            ISecKillData secKillData,
            int secKillType,
            ProductGridAdapter productGridAdapter) {
        super(secKillV);

        mSecKillData = secKillData;
        mSecKillType = secKillType;
        mAdapter = productGridAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        ProductDetailsActivity.start(
                                getContext(), mAdapter.getItem(position).getId(),
                                mAdapter.getItem(position).getSubject_id());
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {}
                });
        mView.setRecyclerViewAdapter(mAdapter);

        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // 解除Adapter和RecyclerView的绑定才会回调ViewHolder的onDetach,才能停止倒计时
        mView.setRecyclerViewAdapter(null);
        mAdapter.clearCountdownControllerMap();
    }

    public void reload() {
        mLastItemId = -1;
        loadData(false);
    }

    public void loadMore() {
        if (mAdapter.getRealItemCount() == 0) {
            mLastItemId = -1;
        } else {
            mLastItemId = mAdapter.getItem(mAdapter.getRealItemCount() - 1).getId();
        }
        loadData(false);
    }

    @Nullable
    @Override
    public Observable<List<ProductItem>> getDataObservable() {
        return mSecKillData.getSecKills(mLastItemId, AppConfig.PAGE_SIZE_2_ROW, mSecKillType);
    }

    @Override
    protected void onLoadSuccess(List<ProductItem> productItems) {
        super.onLoadSuccess(productItems);

        if (mLastItemId == -1) {
            // 第一页
            mAdapter.setList(productItems);
            checkShowNoneData(productItems);
        } else {
            mAdapter.addList(productItems);
        }

        mView.setLoadable(CollectionUtil.isNotEmpty(productItems));
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        checkShowNoneData(mAdapter.getList());
        mView.stopRefresh();
        mView.stopLoadMore();
    }
}
