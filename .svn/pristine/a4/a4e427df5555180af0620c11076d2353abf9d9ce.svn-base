package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.SearchRecord;

import java.util.List;

import io.reactivex.Observable;

/**
 * 搜索历史记录的数据交互
 * Created by jph on 2017/8/1.
 */
public interface ISearchRecordsData {

    /**
     * 获取所有搜索记录
     *
     * @return
     */
    Observable<List<SearchRecord>> getRecords();

    /**
     * 保存单个记录
     */
    void storeRecord(SearchRecord searchRecord);

    /**
     * 清空搜索记录
     */
    void clearRecords();
}
