package com.a1magway.bgg.p.member;

import android.support.annotation.Nullable;
import android.view.View;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.repository.IMemberGoodsData;
import com.a1magway.bgg.p.seckill.ProductGridAdapter;
import com.a1magway.bgg.refactor.BaseLoadPresenter;
import com.a1magway.bgg.v.member.MemberGoodsContract;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.CollectionUtil;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

/** author: Beaven date: 2017/10/13 10:38 */
public class MemberGoodsP extends BaseLoadPresenter<MemberGoodsContract.View, List<ProductItem>> {

    private IMemberGoodsData memberGoodsData;
    private ProductGridAdapter productGridAdapter;

    private int mLastItemId = -1; // 最后一个元素id，用于分页

    @Inject
    public MemberGoodsP(
            MemberGoodsContract.View iMemberGoodsV,
            IMemberGoodsData memberGoodsData,
            ProductGridAdapter productGridAdapter) {
        super(iMemberGoodsV);
        this.memberGoodsData = memberGoodsData;
        this.productGridAdapter = productGridAdapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        productGridAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        ProductDetailsActivity.start(
                                getContext(), productGridAdapter.getItem(position).getId(),
                                productGridAdapter.getItem(position).getSubject_id());
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {}
                });
        mView.setRecyclerViewAdapter(productGridAdapter);

        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        productGridAdapter.clearCountdownControllerMap();
    }

    @Nullable
    @Override
    public Observable<List<ProductItem>> getDataObservable() {
        return memberGoodsData.getMemberGoods(mLastItemId, AppConfig.PAGE_SIZE_2_ROW);
    }

    public void reload() {
        mLastItemId = -1;
        loadData(false);
    }

    public void loadMore() {
        if (productGridAdapter.getRealItemCount() == 0) {
            mLastItemId = -1;
        } else {
            mLastItemId =
                    productGridAdapter.getItem(productGridAdapter.getRealItemCount() - 1).getId();
        }
        loadData(false);
    }

    @Override
    protected void onLoadSuccess(List<ProductItem> productItems) {
        super.onLoadSuccess(productItems);
        if (mLastItemId == -1) {
            if (CollectionUtil.isEmpty(productItems)) {
                mView.showEmpty();
            } else {
                productGridAdapter.setList(productItems);
            }
        } else {
            if (CollectionUtil.isEmpty(productItems)) {
                mView.setLoadable(true);
            } else {
                productGridAdapter.addList(productItems);
            }
        }
    }

    @Override
    protected void onLoadError(Throwable e) {
        super.onLoadError(e);
        if (mLastItemId == -1) {
            mView.showError();
        } else {
            mView.stopLoadMore(false);
        }
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        mView.stopLoadMore(true);
    }
}
