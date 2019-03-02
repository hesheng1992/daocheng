package com.a1magway.bgg.v.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.order.DaggerLogisticsComponent;
import com.a1magway.bgg.di.module.order.LogisticsModule;
import com.a1magway.bgg.p.order.LogisticsP;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.TitleBar;

import butterknife.BindView;

public class LogisticsActivity extends PActivity<LogisticsP> implements ILogisticsV {

    private static final String EXTRA_OUTTRADENO= "extra_outtradeno";

    @BindView(R.id.logistics_title_bar)
    TitleBar mTitleBar;

    @BindView(R.id.logistics_deliver_recyclerview)
    LoadMoreRecyclerView mRecyclerView;

    @BindView(R.id.logistics_deliver_company_tv)
    TextView mDeliverCompanyTv;

    @BindView(R.id.logistics_amount_tv)
    TextView mAmountTv;

    @BindView(R.id.logistics_deliver_num_tv)
    TextView mDeliverNumTv;

    @BindView(R.id.logistics_order_info_recyclerview)
    RecyclerView mOderInfoRecyclerView;




    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_logistics;
    }

    public static final void start(Context context,OrderItem orderItem){
        Intent intent = new Intent(context,LogisticsActivity.class);
        intent.putExtra(EXTRA_OUTTRADENO,orderItem);
        JumpUtil.startActivity(context,intent);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        OrderItem item = (OrderItem) getIntent().getSerializableExtra(EXTRA_OUTTRADENO);
        if (item!=null){
            DaggerLogisticsComponent.builder()
                    .appComponent(appComponent)
                    .logisticsModule(new LogisticsModule(this,item.getOrderNum()))
                    .build()
                    .inject(this);
            mPresenter.setOrderInfo(item);
        }

    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setDefLeftImgClickListener(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadable(false);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    public void setDeliverCompany(String deliverCompany) {
        mDeliverCompanyTv.setText(deliverCompany);
    }

    @Override
    public void setDeliverNum(String delivernum) {
        mDeliverNumTv.setText(delivernum);
    }

    @Override
    public void setOrderInfoAdapter(RecyclerView.Adapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mOderInfoRecyclerView.setLayoutManager(linearLayoutManager);
        mOderInfoRecyclerView.setAdapter(adapter);
    }

    @Override
    public void setOrderNum(Integer num) {
        mAmountTv.setText("总计："+num+"件");
    }
}
