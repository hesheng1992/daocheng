package com.a1magway.bgg.v.productReturn;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.v.IView;

/**
 * Created by lm on 2018/8/30.
 */
public interface IApplyReturnV extends IView {

    void initAdapter(BaseRecyclerAdapter adapter);
    void initPhotoAdapter(BaseRecyclerAdapter adapter);
    void onSucess(APIResponse apiResponse);
    void onFaile(Throwable e);
    public void GoodsInfo(BaseRecyclerAdapter mAdapter);
    public void selectNumber(Integer number);
    public void selectGoods(OrderItem orderItem);
    void getPhone(String phone);
}
