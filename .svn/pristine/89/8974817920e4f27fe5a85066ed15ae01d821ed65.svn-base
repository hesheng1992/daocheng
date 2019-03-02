package com.a1magway.bgg.v.productReturn;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.ReturnGoodsDetailBean;
import com.a1magway.bgg.v.IView;
/**
 * Created by lm on 2018/8/30.
 */
public interface IReturnContentV extends IView {
    void undoReturnGoodsTrue(APIResponse apiResponse);//撤销退货成功
    void undoReturnGoodsFalse(Throwable e);//撤销退货失败
    void getRefundDetailTrue(ReturnGoodsDetailBean bean);
    void getRefundDetailFaile(Throwable e);
}
