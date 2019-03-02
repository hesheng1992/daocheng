package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;

import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.v.productReturn.IProductReturnWriteV;

/**
 * Created by lm on 2018/8/30.
 */
public class ProductReturnWriteP extends BasePresenter<IProductReturnWriteV> {


    private IProductReturnWriteV mView;
    private OrderItem orderItem;
    private ReturnProductListdapter mAdapter;

    public ProductReturnWriteP(@NonNull IProductReturnWriteV view, OrderItem orderItem) {
        super(view);
        this.mView=view;
        this.orderItem=orderItem;
    }

    public void initRV(){
        mAdapter=new ReturnProductListdapter(orderItem.getSkuList());
        mAdapter.setHideSelct(true);
        mView.initAdapter(mAdapter);

    }


}
