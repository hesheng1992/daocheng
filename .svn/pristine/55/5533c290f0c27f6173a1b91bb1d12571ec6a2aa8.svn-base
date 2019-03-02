package com.a1magway.bgg.p.search;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.FilterCate1;
import com.a1magway.bgg.data.entity.FilterCate2;
import com.a1magway.bgg.data.entity.SelectCateInfo;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.v.search.IFilterCateV;
import com.a1magway.bgg.v.search.ResultFragManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * 筛选选中二级分类界面的presenter
 * Created by jph on 2017/8/3.
 */
public class FilterCateP extends BasePresenter<IFilterCateV> {

    private List<FilterCate2> mManCate2List = new ArrayList<>();
    private List<FilterCate2> mWomanCate2List = new ArrayList<>();

    @Inject
    Serializable mCate1;
    @Inject
    ResultFragManager mResultFragManager;

    @Inject
    public FilterCateP(IFilterCateV view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FilterCate1 filterCate1 = (FilterCate1) mCate1;
        showCate1Content(filterCate1);
        if (filterCate1.getName().equals("男士")){
            mManCate2List.addAll(filterCate1.getList());
        }else if(filterCate1.getName().equals("女士")){
            mWomanCate2List.addAll(filterCate1.getList());
        }else {
            mWomanCate2List.addAll(filterCate1.getList());
            mManCate2List.addAll(filterCate1.getList());
        }

        showWomanCate2Content();
        showManCate2Content();
//        Observable.just(mCate1)
//                .map(new Function<Serializable, FilterCate1>() {
//                    @Override
//                    public FilterCate1 apply(@NonNull Serializable serializable) throws Exception {
//                        FilterCate1 filterCate1 = (FilterCate1) serializable;
//                        showCate1Content(filterCate1);
//                        return filterCate1;
//                    }
//                })
//                .flatMap(new Function<FilterCate1, ObservableSource<FilterCate2>>() {
//                    @Override
//                    public ObservableSource<FilterCate2> apply(@NonNull FilterCate1 filterCate1) throws Exception {
//                        return Observable.fromIterable(filterCate1.getList());
//                    }
//                })
//                .subscribe(new DisposableObserver<FilterCate2>() {
//
//                    @Override
//                    public void onNext(@NonNull FilterCate2 filterCate2) {
//                        if (filterCate2.getFlag() == 1) {
//                            //男
//                            mManCate2List.add(filterCate2);
//                        } else if (filterCate2.getFlag() == 0) {
//                            //女
//                            mWomanCate2List.add(filterCate2);
//                        } else {
//                            //通用
//                            mManCate2List.add(filterCate2);
//                            mWomanCate2List.add(filterCate2);
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        showWomanCate2Content();
//                        showManCate2Content();
//                    }
//                });

    }

    /**
     * 显示女士的二级分类
     */
    private void showWomanCate2Content() {
        Observable.fromIterable(mWomanCate2List)
                .map(new Function<FilterCate2, String>() {
                    @Override
                    public String apply(@NonNull FilterCate2 filterCate2) throws Exception {
                        return filterCate2.getName();
                    }
                })
                .subscribe(new DisposableObserver<String>() {
                    List<String> strList = new ArrayList<>();

                    @Override
                    public void onNext(@NonNull String s) {
                        strList.add(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.showWomanCate2Content(strList);
                    }
                });
    }


    /**
     * 显示男士的二级分类
     */
    private void showManCate2Content() {
        Observable.fromIterable(mManCate2List)
                .map(new Function<FilterCate2, String>() {
                    @Override
                    public String apply(@NonNull FilterCate2 filterCate2) throws Exception {
                        return filterCate2.getName();
                    }
                })
                .subscribe(new DisposableObserver<String>() {
                    List<String> strList = new ArrayList<>();

                    @Override
                    public void onNext(@NonNull String s) {
                        strList.add(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        mView.showManCate2Content(strList);
                    }
                });
    }

    /**
     * 显示跟一级分类相关的信息
     *
     * @param filterCate1
     */
    private void showCate1Content(FilterCate1 filterCate1) {

        mView.setCate1Text(filterCate1.getName());

        if (mView.getContext().getString(R.string.search_filter_child)
                .equals(filterCate1.getName())) {
            //显示儿童的男女分类title
            mView.setManText(R.string.search_filter_boy);
            mView.setWomanText(R.string.search_filter_girl);
        } else {
            mView.setManText(R.string.search_filter_man);
            mView.setWomanText(R.string.search_filter_woman);
        }
    }

    /**
     * 选中女士二级分类
     *
     * @param position
     */
    public void selectWomanCate2(int position) {
        selectCate2(0, mWomanCate2List.get(position));
    }

    /**
     * 选中男士二级分类
     *
     * @param position
     */
    public void selectManCate2(int position) {
        selectCate2(1, mManCate2List.get(position));
    }

    private void selectCate2(int gender, FilterCate2 filterCate2) {
        SelectCateInfo selectCateInfo = new SelectCateInfo();
        selectCateInfo.setCate1((FilterCate1) mCate1);
        selectCateInfo.setCate2(filterCate2);
        selectCateInfo.setGender(gender);
        mView.backFilterFragment(selectCateInfo);
    }
}
