package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.Brand;

import java.util.List;

import io.reactivex.Observable;

/**
 * 品牌馆数据
 * Created by jph on 2017/7/29.
 */
public interface IBrandMuseumData {

    Observable<List<Brand>> getBrandMuseum();
}
