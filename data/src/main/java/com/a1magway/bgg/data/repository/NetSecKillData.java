package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;

import io.reactivex.Observable;

/**
 * 从网络获取秒杀数据
 * Created by jph on 2017/7/28.
 */
public class NetSecKillData implements ISecKillData {

    private APIManager mAPIManager;

    public NetSecKillData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<ProductItem>> getSecKills(int lastIdOfProduct, int pageSize, int secKillType) {
        return mAPIManager.getSecKills(lastIdOfProduct, pageSize, secKillType);
    }

    @Override
    public Observable<String> getSeckillCountdown() {
        return mAPIManager.getSeckillCountdown();
    }
}
