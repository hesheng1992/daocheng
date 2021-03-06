package com.a1magway.bgg.v.productReturn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragmentInPager;
import com.a1magway.bgg.data.entity.AfterSaleBean;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.OrderType;
import com.a1magway.bgg.data.entity.SalesReturn;
import com.a1magway.bgg.p.productReturn.AfterSaleAdapter;
import com.a1magway.bgg.p.productReturn.AfterSaleP;
import com.a1magway.bgg.refactor.AppRefreshLayout;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.order.IOrderListV;
import com.a1magway.bgg.v.order.OrderListFragment;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.almagway.common.utils.ToastUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.xxxrecylcerview.XXXRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by enid on 2018/9/8.
 *
 * 申请进行中和申请记录
 */

public class AfterSaleFragment extends PFragmentInPager<AfterSaleP>{
    public static int AFTER_SALE_ING=1;//进行中申请
    public static int AFTER_SALE_RECORD=2;//申请记录，处理完毕的

    private int page=0;
    private int num=5;//请求的页数
    private int status=0;//判断是处理中还是申请记录
    //是否是下拉刷新还是上拉加载
    private boolean isRefresh=false;

    @BindView(R.id.order_list_layout_refresh)
    AppRefreshLayout mRefreshLayout;

    @BindView(R.id.order_list_recycler)
    LoadMoreRecyclerView mRecyclerView;
    //无数据显示
    @BindView(R.id.liner_no_data)
    LinearLayout liner_no_data;

    private AfterSaleAdapter afterSaleAdapter;

    private AfterSaleP afterSaleP;



    public static AfterSaleFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt("type", type);

        AfterSaleFragment fragment = new AfterSaleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.after_slae_fragment;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        int type=getArguments().getInt("type");
        afterSaleP=new AfterSaleP(null);
        afterSaleP.setOnAfterSaleLister(onAfterSaleLister);
        if (type==1){
           status=1;
        }else if (type==2){
            status=2;
        }
        isRefresh=true;
        setRequest(page,num,status);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=0;
                isRefresh=true;
                setRequest(page,num,status);
            }
        });
        mRefreshLayout.setEnableLoadmore(false);
//        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//
//            }
//        });
        mRecyclerView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page+=1;
                isRefresh=false;
                setRequest(page,num,status);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        afterSaleAdapter=new AfterSaleAdapter(new ArrayList<AfterSaleBean>(),getContext());
        afterSaleAdapter.setOnClickItemListener(onClickItemListener);
        mRecyclerView.setAdapter(afterSaleAdapter);


    }
    //点击item回调
    private AfterSaleAdapter.OnClickItemListener onClickItemListener=new AfterSaleAdapter.OnClickItemListener() {
        @Override
        public void onClickItem(AfterSaleBean afterSaleBean) {
            OrderItem orderItem=new OrderItem();
            orderItem.setSkuList(afterSaleBean.getSkuList());
            orderItem.setRefundId(afterSaleBean.getId());
            orderItem.setType(afterSaleBean.getType());
            orderItem.setOrderStatus(afterSaleBean.getType());
            ReturnContentActivity.start(getActivity(),orderItem,new SalesReturn());
        }
    };

    public void setRequest(int page,int num,int status){
        //处理中
        showLoadingDialog();
        afterSaleP.getRefundList(GlobalData.getInstance().getUserId(),page,num,status);
    }

    /**
     * 监听请求售后列表成功与失败回调
     */
    private AfterSaleP.OnAfterSaleLister onAfterSaleLister=new AfterSaleP.OnAfterSaleLister() {
        @Override
        public void onAfterSucess(List<AfterSaleBean> listAPIResponse) {
            hideLoadingDialog();

            liner_no_data.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadmore();
            mRecyclerView.stopLoadMore();
            if (listAPIResponse!=null && listAPIResponse.size()>0){
                if (listAPIResponse.size()<5){
//                    mRefreshLayout.setEnableLoadmore(false);
                    mRecyclerView.setLoadable(false);
                }else {
//                    mRefreshLayout.setEnableLoadmore(true);
                    mRecyclerView.setLoadable(true);
                }
                if (isRefresh){
                    afterSaleAdapter.setList(listAPIResponse);
                }else{
                    afterSaleAdapter.addList(listAPIResponse);
                }
            }else{
                if (isRefresh){
                    liner_no_data.setVisibility(View.VISIBLE);
                    mRefreshLayout.setVisibility(View.GONE);
                }
                mRecyclerView.setLoadable(false);
            }
        }

        @Override
        public void onAfterFaies(Throwable e) {
            hideLoadingDialog();
            mRefreshLayout.finishRefresh();
            mRefreshLayout.finishLoadmore();
            mRecyclerView.stopLoadMore();
            ToastUtil.showShort(e.getMessage());
            //控制如果由于网络问题多加了一页
            if (!isRefresh){
                if (page>0){
                    page-=1;
                }
            }else{
                liner_no_data.setVisibility(View.VISIBLE);
                mRefreshLayout.setVisibility(View.GONE);
            }
        }
    };

}
