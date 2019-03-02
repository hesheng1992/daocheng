package com.a1magway.bgg.p.collection;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.CollectionData;
import com.a1magway.bgg.data.repository.ICollectionListData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.collection.CollectionContract;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.almagway.common.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/6/4.
 */

public class CollectionP extends BaseLoadP<List<CollectionData>, CollectionContract.View> {

    ICollectionListData mICollectionListData;

    ProductCollectionListAdapter mCollectionListAdapter;

    int index = -1;

    @Inject
    public CollectionP(@NonNull CollectionContract.View view, ICollectionListData collectionListData, ProductCollectionListAdapter collectionListAdapter) {
        super(view);
        this.mICollectionListData = collectionListData;
        this.mCollectionListAdapter = collectionListAdapter;
    }

    @Nullable
    @Override
    public Observable<List<CollectionData>> getDataObservable() {
        int id = GlobalData.getInstance().getLoginData().getId();
        return mICollectionListData.getCollection(id);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.setRecyclerViewAdapter(mCollectionListAdapter);
        mCollectionListAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //收藏商品未下架并且未售罄才能跳转到详情页面
                if (mCollectionListAdapter.getItem(position).getDownSale() == 0 &&
                        mCollectionListAdapter.getItem(position).getSellOut() > 0) {
                    ProductDetailsActivity.start(v.getContext(), mCollectionListAdapter.getItem(position).getId(),
                            mCollectionListAdapter.getItem(position).getSubject_id());
                }
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        mCollectionListAdapter.addItemBoxListener(new ProductCollectionListAdapter.ItemClickListener() {
            @Override
            public void onItemClickCheckedBox(int position) {
                if (getCollectionSelectSize() > 0) {
                    isShowRadio = true;
                    mView.setDeleteLayoutShow(isShowRadio);
                } else {
                    clickCancel();
                }
            }
        });
        loadData();
    }

    public void reload() {
        index = -1;
        loadData(false);
    }

    @Override
    protected void onLoadSuccess(List<CollectionData> collectionData) {
        super.onLoadSuccess(collectionData);
        mCollectionListAdapter.setList(collectionData);
        checkShowNoneData(collectionData);
//        if (index == -1) {
//            mCollectionListAdapter.setList(collectionData);
//            checkShowNoneData(collectionData);
//        }else {
//            mCollectionListAdapter.addList(collectionData);
//        }
//        if (collectionData != null && collectionData.size() > 0) {
//            index = collectionData.get(collectionData.size() - 1).getId();
//        }
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        if (index > 0) {
            mView.stopLoadMore();
        }
    }

    boolean isShowRadio = false;

    public void showRadioButton() {
        isShowRadio = !isShowRadio;
        mCollectionListAdapter.setShowRadioButton(isShowRadio);
        mCollectionListAdapter.notifyDataSetChanged();
//        mView.setDeleteLayoutShow(isShowRadio);
        if (isShowRadio && getCollectionSelectSize() > 0) {
            mView.setDeleteLayoutShow(true);
            isShowRadio = true;
        } else {
            mView.setDeleteLayoutShow(false);
            isShowRadio = false;
        }
    }

    public void clickDelete() {
        isShowRadio = false;
        mCollectionListAdapter.setShowRadioButton(isShowRadio);
        mCollectionListAdapter.notifyDataSetChanged();
        mView.setDeleteLayoutShow(isShowRadio);
        List<CollectionData> collectionSelectData = GlobalData.getInstance().getCollectionSelectData();
        if (collectionSelectData.size() == 0) {
            Toaster.showShort(getContext(), "请勾选商品");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (CollectionData c : collectionSelectData) {
            Log.d("enid", "select has " + c.getAttentionId());
            builder.append(c.getAttentionId());
            builder.append(",");
        }
        String ids = builder.toString();
        if (!TextUtils.isEmpty(ids)) {
            ids = ids.substring(0, ids.lastIndexOf(","));
        }
        Log.d("enid", "select is" + ids);
        GlobalData.getInstance().setCollectionSelectData(new ArrayList<CollectionData>());
        mICollectionListData.cancelCollection(ids)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<APIResponse>(getContext()) {
                    @Override
                    public void onNext(APIResponse response) {
                        ToastUtil.showShort(response.getMsg());
                        if (response.isSuccess()) {
                            loadData(false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void clickCancel() {
        isShowRadio = false;
        mCollectionListAdapter.setShowRadioButton(isShowRadio);
        mCollectionListAdapter.notifyDataSetChanged();
        mView.setDeleteLayoutShow(isShowRadio);
    }


    private int getCollectionSelectSize() {
        List<CollectionData> collectionSelectData = GlobalData.getInstance().getCollectionSelectData();
        return collectionSelectData == null ? 0 : collectionSelectData.size();
    }

}
