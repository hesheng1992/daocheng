package com.a1magway.bgg.p.cate;

import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.CateItem;
import com.a1magway.bgg.data.repository.ICateData;
import com.almagway.common.utils.StringUtil;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.v.cate.BrandMuseumActivity;
import com.a1magway.bgg.v.cate.ICateV;
import com.a1magway.bgg.v.search.SearchActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 分类对应的P
 * Created by jph on 2017/7/20.
 */
public class CateP extends BaseLoadP<List<CateItem>, ICateV> {

    ICateData mCateData;
    CatesAdapter mCatesAdapter;

    @Inject
    public CateP(ICateV cateV, ICateData cateData, CatesAdapter catesAdapter) {
        super(cateV);
        mCateData = cateData;
        mCatesAdapter = catesAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mCatesAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {
                    BrandMuseumActivity.start(v.getContext());
                    return;
                }

                CateItem cateItem = mCatesAdapter.getItem(position);
                SearchActivity.start(v.getContext(), StringUtil.parseInt(cateItem.getCategoryId()),
                        cateItem.getKeyword());
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        mView.setRecyclerViewAdapter(mCatesAdapter);

        loadData();
    }

    public void reload() {
        loadData(false);
    }

    @Nullable
    @Override
    public Observable<List<CateItem>> getDataObservable() {
        return mCateData.getCates();
    }

    @Override
    protected void onLoadSuccess(List<CateItem> cateItems) {
        super.onLoadSuccess(cateItems);

        mCatesAdapter.setList(cateItems);
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
    }
}
