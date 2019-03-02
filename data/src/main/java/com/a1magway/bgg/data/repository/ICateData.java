package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.CateItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * 分类的数据交互接口
 * Created by jph on 2017/7/19.
 */
public interface ICateData {

    Observable<List<CateItem>> getCates();
}
