package com.a1magway.bgg.p.search;

import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderByTypes;
import com.a1magway.bgg.data.entity.PresetFilterRule;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.data.entity.ProductFilterRule;
import com.a1magway.bgg.data.repository.ISearchData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.a1magway.bgg.v.search.ISearchResultV;
import com.almagway.common.utils.CollectionUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/** 搜索结果对应Presenter Created by jph on 2017/7/31. */
public class SearchResultP extends BaseLoadP<List<ProductBean>, ISearchResultV> {

    private ISearchData mSearchData;
    private ProductGridAdapter mAdapter;

    private String mKey;
    private int mCateID=-1;
//    private int mLastIdOfProduct;
    private int count = 0;
    @Inject PresetFilterRule mPresetFilterRule;
    private ProductFilterRule mProductFilterRule; // 筛选条件

    @Inject
    public SearchResultP(
            ISearchResultV searchResultV, ISearchData searchData, ProductGridAdapter adapter) {
        super(searchResultV);
        mSearchData = searchData;
        mAdapter = adapter;
    }

    @Nullable
    @Override
    public Observable<List<ProductBean>> getDataObservable() {
        mProductFilterRule.setCategoryId(mCateID);
        return mSearchData
                .searchProduct( mKey, count, mProductFilterRule)
                .defaultIfEmpty(new ArrayList<ProductBean>());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        ProductDetailsActivity.start(
                                v.getContext(), mAdapter.getItem(position).getId(),
                                mAdapter.getItem(position).getSubject_id());
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {}
                });
        mView.setRecyclerViewAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mAdapter.clearCountdownControllerMap();
    }

    /** 刷新，不改变筛选参数，只重置了分页参数 */
    public void reload() {
//        mLastIdOfProduct = -1;
        count = 0;
        loadData(false);
    }

    public void setCateId(int cateId){
        mCateID=cateId;
    }

    /** 加载更多 */
    public void loadMore() {
//        if (mAdapter.getRealItemCount() == 0) {
//            mLastIdOfProduct = -1;
//        } else {
//            mLastIdOfProduct = mAdapter.getItem(mAdapter.getRealItemCount() - 1).getId();
//        }
        count = mAdapter.getList().size();
        loadData(false);
    }

    /**
     * 执行搜索，完全重置了筛选条件
     *
     * @param key
     */
    public void executeSearch(String key) {
        mProductFilterRule = new ProductFilterRule();

        if (mPresetFilterRule.isValid()) {
            mKey = null;
            mProductFilterRule.setCategoryId(mPresetFilterRule.getCategoryId());
            mProductFilterRule.setCategoryFlag(mPresetFilterRule.getCategoryFlag());
            mProductFilterRule.setBrandId(mPresetFilterRule.getBrandId());
        } else {
            mKey = key;
        }

        reload();
    }

    /**
     * 改变了排序方式，刷新数据
     *
     * @param orderByType
     */
    public void refreshByNewOrderBy(@OrderByTypes.OrderByType int orderByType) {
        if (mProductFilterRule == null) {
            mProductFilterRule = new ProductFilterRule();
        }

        mProductFilterRule.setOrderByType(orderByType);
        mView.startRefresh();
    }

    /**
     * 选择了筛选条件，刷新数据
     *
     * @param rule
     */
    public void refreshByNewFilterRule(Serializable rule) {
        ProductFilterRule newRule = (ProductFilterRule) rule;
        if (mProductFilterRule != null) {
            // 使用已有的排序方式
            newRule.setBuyOrder(mProductFilterRule.getBuyOrder());
            newRule.setPriceOrder(mProductFilterRule.getPriceOrder());
            newRule.setDiscountOrder(mProductFilterRule.getDiscountOrder());


        }
        mProductFilterRule = newRule;

        mView.startRefresh();
    }

    @Override
    protected void onLoadSuccess(List<ProductBean> productItems) {
        super.onLoadSuccess(productItems);
        if (count == 0) {// if (mLastIdOfProduct == -1) {
            mAdapter.setList(productItems);
            checkShowNoneData(productItems);
            mView.hideLoginLoading();
        } else {
            mAdapter.addList(productItems);
        }

//        ProductBean lastItem = CollectionUtil.getListLastElement(productItems);
//        if (lastItem != null) {
//            mLastIdOfProduct = lastItem.getId();
//        }

        mView.setLoadable(CollectionUtil.isNotEmpty(productItems));
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        mView.stopLoadMore();
    }
}
