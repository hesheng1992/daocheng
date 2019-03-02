package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CheckOrderProResult;
import com.a1magway.bgg.data.entity.OrderCommitResult;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/23.
 */
public interface IOrderCommitData {

    /**
     * 校验订单商品
     *
     * @param commodityList
     * @return
     */
    Observable<CheckOrderProResult> checkOrderPro(List<OrderDetailsCommodity> commodityList,String buyStatus);

    /**
     * 提交订单
     *
     * @param isSeckill
     * @param addressId
     * @param remark
     * @param commodityList
     * @param checkOrderProResult
     * @return
     */
    Observable<OrderCommitResult> commitOrder(boolean isSeckill, int addressId, String remark,
                                              List<OrderDetailsCommodity> commodityList, CheckOrderProResult checkOrderProResult);

}
