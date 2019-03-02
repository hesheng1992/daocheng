package com.a1magway.bgg.p.search;

import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.data.entity.SearchRecord;
import com.a1magway.bgg.data.repository.ISearchData;
import com.a1magway.bgg.data.repository.ISearchRecordsData;
import com.a1magway.bgg.p.RxPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * 搜索主界面对应的presenter
 * Created by jph on 2017/8/3.
 */
public class SearchP extends RxPresenter {

    @Inject
    ISearchData mSearchData;
    @Inject
    ISearchRecordsData mSearchRecordsData;
    @Inject
    List<FilterCate1> mFilterCate1List;

    @Inject
    public SearchP() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        loadFilterCates();
    }

    public void storeRecord(String key) {
        //保存搜索历史到本地
        SearchRecord searchRecord = new SearchRecord(key);
        mSearchRecordsData.storeRecord(searchRecord);
    }

    /**
     * 加载过滤的分类数据
     */
    private void loadFilterCates() {
        mSearchData.getFilterCates()
                .compose(this.<List<FilterCate1>>bindToDestroyEvent())
                .defaultIfEmpty(new ArrayList<FilterCate1>())
                .subscribe(new DisposableObserver<List<FilterCate1>>() {
                    @Override
                    public void onNext(@NonNull List<FilterCate1> filterCate1s) {
                        //将获取到的分类数据放入全局的list中
                        mFilterCate1List.addAll(filterCate1s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




}
