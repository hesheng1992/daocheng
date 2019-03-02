package com.a1magway.bgg.p.saleorder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.repository.ISaleOrderListData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.p.order.OrderListAdapter;
import com.a1magway.bgg.v.saleorder.ISaleOrderListV;
import com.almagway.common.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;

/** Created by jph on 2017/8/17. */
public class SaleOrderListP extends BaseLoadP<List<OrderItem>, ISaleOrderListV> {

    private int mLastItemId = -1;

    @Inject
    ISaleOrderListData mSaleOrderListData;
    @Inject OrderListAdapter mAdapter;

    @Named(value = "OrderType")
    @Inject
    int mOrderType;

    @Inject
    public SaleOrderListP(@NonNull ISaleOrderListV view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mView.setRecyclerViewAdapter(mAdapter);
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mAdapter.clearControllerMap();
        mView.setRecyclerViewAdapter(null);
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
    public Observable<List<OrderItem>> getDataObservable() {
        return mSaleOrderListData
                .getSaleOrderList(mOrderType, mLastItemId)
                .defaultIfEmpty(new ArrayList<OrderItem>());
    }

    @Override
    protected void onLoadSuccess(List<OrderItem> orderItems) {
        super.onLoadSuccess(orderItems);

        if (mLastItemId == -1) {
            mAdapter.setList(orderItems);

            checkShowNoneData(orderItems);
        } else {
            mAdapter.addList(orderItems);
        }

        mView.setLoadable(CollectionUtil.isNotEmpty(orderItems));
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        mView.stopLoadMore();
    }
//
//    private void cancelOrder(final OrderItem orderItem) {
//        mView.showLoadingDialog();
//        mSaleOrderListData
//                .cancelOrder(GlobalData.getInstance().getUserId(), orderItem.getId())
//                .compose(this.<String>bindToDestroyEvent())
//                .subscribe(
//                        new BaseObserver<String>(getContext()) {
//                            @Override
//                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//                                super.onError(e);
//                                mView.hideLoadingDialog();
//                            }
//
//                            @Override
//                            public void onNext(@io.reactivex.annotations.NonNull String s) {
//                                Toaster.showShort(getContext(), s);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                mView.hideLoadingDialog();
//                                mAdapter.removeItem(orderItem);
//                                mAdapter.notifyDataSetChanged();
//                                checkShowNoneData(mAdapter.getList());
//                            }
//                        });
//    }
//
//    private void deleteOrder(final OrderItem orderItem) {
//        mView.showLoadingDialog();
//        mOrderListData
//                .deleteOrder(GlobalData.getInstance().getUserId(), orderItem.getId())
//                .compose(this.<String>bindToDestroyEvent())
//                .subscribe(
//                        new BaseObserver<String>(getContext()) {
//                            @Override
//                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//                                super.onError(e);
//                                mView.hideLoadingDialog();
//                            }
//
//                            @Override
//                            public void onNext(@io.reactivex.annotations.NonNull String s) {
//                                Toaster.showShort(getContext(), s);
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                mView.hideLoadingDialog();
//
//                                mAdapter.removeItem(orderItem);
//
//                                checkShowNoneData(mAdapter.getList());
//                            }
//                        });
//    }
}
