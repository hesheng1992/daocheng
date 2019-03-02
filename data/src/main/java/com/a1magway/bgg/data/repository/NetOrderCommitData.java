package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CheckOrderProResult;
import com.a1magway.bgg.data.entity.OrderCommitResult;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by jph on 2017/8/23.
 */
public class NetOrderCommitData implements IOrderCommitData {

    private APIManager mAPIManager;

    public NetOrderCommitData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<CheckOrderProResult> checkOrderPro(List<OrderDetailsCommodity> commodityList,String buyStatus) {
        return mAPIManager.checkOrderPro(commodityList,buyStatus);
    }

    @Override
    public Observable<OrderCommitResult> commitOrder(boolean isSeckill, int addressId, String remark, List<OrderDetailsCommodity> commodityList, CheckOrderProResult checkOrderProResult) {
        return mAPIManager.commitOrder(isSeckill, addressId, remark, commodityList, checkOrderProResult);
    }
}
