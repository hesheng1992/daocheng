package com.a1magway.bgg.v.search;

import com.a1magway.bgg.common.ILoadingV;

import java.util.List;

/**
 * Created by jph on 2017/8/1.
 */
public interface ISearchRecordsV extends ILoadingV {

    void showRecords(List<String> recordList);
}
