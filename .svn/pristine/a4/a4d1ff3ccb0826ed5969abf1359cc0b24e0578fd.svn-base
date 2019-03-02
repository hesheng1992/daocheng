package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.ProductItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * 秒杀专区数据接口
 * Created by jph on 2017/7/28.
 */
public interface ISecKillData {
    /**
     * 获取秒杀商品列表
     *
     * @param lastIdOfProduct 上一页数据最后一条数据id
     * @param pageSize
     * @param secKillType     对应{@link com.a1magway.bgg.data.entity.SecKillTypes}  @return
     */
    Observable<List<ProductItem>> getSecKills(int lastIdOfProduct, int pageSize, int secKillType);

    Observable<String> getSeckillCountdown();
}
