package com.a1magway.bgg.v.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.IBackPress;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderByTypes;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.search.SearchResultComponent;
import com.a1magway.bgg.di.module.search.SearchResultModule;
import com.a1magway.bgg.p.search.SearchResultP;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.OldRefreshLayout;
import com.a1magway.bgg.widget.SearchTabLayout;
import com.a1magway.bgg.widget.divider.GridItemDecoration;
import com.xxxrecylcerview.XXXRecyclerView;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * 搜索结果页面
 * Created by jph on 2017/7/27.
 */
public class ResultFragment extends PFragment<SearchResultP> implements ISearchResultV, IBackPress {

    private SearchResultComponent mSearchResultComponent;
    private static final String CATE_ID = "cate_id";
    @Inject
    ResultFragManager mFragManager;

    @BindView(R.id.result_layout_refresh)
    OldRefreshLayout mOldRefreshLayout;
    @BindView(R.id.result_recycler)
    LoadMoreRecyclerView mRecyclerView;
    @BindView(R.id.result_layout_tab)
    SearchTabLayout mSearchTabLayout;

    private boolean isFistSearch = true;
    private String searchKey = null;

    public static ResultFragment newInstance(int cateId) {

        Bundle args = new Bundle();
        args.putInt(CATE_ID, cateId);
        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.search_fragment_result;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        mSearchResultComponent = ((SearchActivity) getActivity()).getSearchComponent()
                .getSearchResultComponent(new SearchResultModule(this, getChildFragmentManager()));
        mSearchResultComponent.inject(this);

    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.onCreateV(savedInstanceState, contentView);
        initTabLayout();


        mFragManager.setFilterHideListener(new ResultFragManager.FilterHideListener() {
            @Override
            public void onFilterHide() {
//                mSearchTabLayout.setFilterSelected(false);
            }
        });
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.addItemDecoration(new GridItemDecoration(getContext(), R.color.transparent,
                R.dimen.product_grid_divider_h, R.dimen.product_grid_divider_v));

        mOldRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.reload();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMore();
            }
        });

        resetTabShow();
    }

    @Override
    public boolean onBackPressed() {
        return mFragManager.onBackPressed();
    }

    public SearchResultComponent getSearchResultComponent() {
        return mSearchResultComponent;
    }

    /**
     * 重置tab显示，不会call回调
     */
    private void resetTabShow() {
        mSearchTabLayout.setOrderByType(OrderByTypes.COMPREHENSIVE_ASC, false);
    }

    /**
     * 初始化Tab设置
     */
    private void initTabLayout() {
        mSearchTabLayout.setFilterSelectListener(new SearchTabLayout.FilterSelectListener() {
            @Override
            public void onSelectChange(boolean selected) {
                if (selected) {
                    mFragManager.showFilter();
                } else {
                    mFragManager.hideAllFilter();

                }
            }
        });

        mSearchTabLayout.setOrderByChangeListener(new SearchTabLayout.OrderByChangeListener() {
            @Override
            public void onOrderByChange(int newOrderByType) {
                mPresenter.refreshByNewOrderBy(newOrderByType);
            }
        });
    }

    /**
     * 执行搜索
     *
     * @param key
     */
    public void executeSearch(String key) {
        //每次搜索，取消之前选中的排序方式
        resetTabShow();
        int cateId = getArguments().getInt(CATE_ID);
        if (cateId != 0) {
            if (searchKey == null) {
                mPresenter.setCateId(cateId);
                searchKey = key;
            } else {
                if (!searchKey.equals(key)) {
                    mPresenter.setCateId(0);
                } else {
                    mPresenter.setCateId(cateId);
                }
            }
        }


        //如果选中了筛选需要取消筛选（关闭筛选界面）
//        mSearchTabLayout.cancelSelectFilter();

        mPresenter.executeSearch(key);

//        isFistSearch=false;
//        mPresenter.setCateId(-1);

        //每次执行搜索，重置筛选界面
        FilterFragment filterFragment = mFragManager.getFilterFragment();
        if (filterFragment != null) {
            filterFragment.setNeedResetFlag(true);
        }
    }

    @Override
    public void setRecyclerViewAdapter(BaseRecyclerAdapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startRefresh() {
        mOldRefreshLayout.autoRefresh();
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void stopRefresh() {
        if (mOldRefreshLayout != null) {
            mOldRefreshLayout.refreshComplete();
        }
    }

    @Override
    public void stopLoadMore() {
        mRecyclerView.stopLoadMore();
    }

    @Override
    public void setLoadable(boolean loadable) {
        mRecyclerView.setLoadable(loadable);

    }

    public void setFilterRule(Serializable rule) {
        mPresenter.refreshByNewFilterRule(rule);
    }
}
