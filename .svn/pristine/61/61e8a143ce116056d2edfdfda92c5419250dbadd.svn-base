package com.a1magway.bgg.p.search;

import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.SearchRecord;
import com.a1magway.bgg.data.repository.ISearchRecordsData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.v.search.ISearchRecordsV;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 搜索历史界面Presenter
 * Created by jph on 2017/8/1.
 */
public class SearchRecordsP extends BaseLoadP<List<SearchRecord>, ISearchRecordsV> {

    private ISearchRecordsData mSearchRecordsData;

    @Inject
    public SearchRecordsP(ISearchRecordsV searchRecordsV, ISearchRecordsData searchRecordsData) {
        super(searchRecordsV);
        mSearchRecordsData = searchRecordsData;
    }

    @Nullable
    @Override
    public Observable<List<SearchRecord>> getDataObservable() {
        return mSearchRecordsData.getRecords();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadData();
    }

    @Override
    protected void onLoadSuccess(List<SearchRecord> searchRecords) {
        super.onLoadSuccess(searchRecords);

        //显示记录
        Observable.fromIterable(searchRecords)
                .map(new Function<SearchRecord, String>() {
                    @Override
                    public String apply(@NonNull SearchRecord searchRecord) throws Exception {
                        return searchRecord.getKey();
                    }
                })
                .toList()
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        mView.showRecords(strings);
                    }
                });
    }

    /**
     * 清空缓存的搜索记录
     */
    public void clearRecords() {
        mSearchRecordsData.clearRecords();
    }
}
