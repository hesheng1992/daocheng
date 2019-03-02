package com.a1magway.bgg.v.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.product.ProDetailsPicsModule;
import com.a1magway.bgg.p.product.DetailPicsP;

import butterknife.BindView;

/**
 * 详情下方的多图展示
 * Created by jph on 2017/8/9.
 */
public class DetailPicsFragment extends PFragment<DetailPicsP> implements IDetailPicsV {

    @BindView(R.id.pics_recycler)
    RecyclerView mRecyclerView;

    public static DetailPicsFragment newInstance() {

        Bundle args = new Bundle();

        DetailPicsFragment fragment = new DetailPicsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        ((ProductDetailsActivity) getActivity()).getProDetailsComponent()
                .getPicComponent(new ProDetailsPicsModule(this))
                .inject(this);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.product_fragment_pics;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }
}
