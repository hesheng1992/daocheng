package com.a1magway.bgg.p.brand;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import com.a1magway.bgg.data.entity.Brand;
import com.a1magway.bgg.data.repository.IBrandMuseumData;
import com.a1magway.bgg.refactor.BaseLoadPresenter;
import com.a1magway.bgg.util.EntityTransUtil;
import com.a1magway.bgg.v.cate.BrandMuseumActivity;
import com.a1magway.bgg.v.cate.BrandMuseumContract;
import com.a1magway.bgg.v.search.SearchActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import me.yokeyword.indexablerv.IndexableAdapter;

/**
 * 品牌馆界面的Presenter
 * Created by jph on 2017/7/29.
 */
public class BrandMuseumP extends BaseLoadPresenter<BrandMuseumContract.View, List<BrandItem>> {

    private IBrandMuseumData mBrandMuseumData;
    private BrandAdapter mAdapter;

    @Named(value = "IsSelect")
    @Inject
    boolean mIsSelect;


    @Inject
    public BrandMuseumP(BrandMuseumContract.View brandMuseumV, IBrandMuseumData brandMuseumData, BrandAdapter adapter) {
        super(brandMuseumV);
        mBrandMuseumData = brandMuseumData;
        mAdapter = adapter;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mView.setIndexLayoutAdapter(mAdapter);
        mAdapter.setOnItemContentClickListener(
            new IndexableAdapter.OnItemContentClickListener<BrandItem>() {
                @Override
                public void onItemClick(View v, int originalPosition, int currentPosition, BrandItem entity) {
                    if (mIsSelect) {
                        //是选择模式，需要返回数据
                        Intent result = new Intent();
                        result.putExtra(BrandMuseumActivity.EXTRA_SELECTED_BRAND, entity);
                        mView.getActivity().setResult(Activity.RESULT_OK, result);
                        mView.getActivity().finish();
                    } else {
                        //正常点击，跳转搜索
                        SearchActivity.start(getContext(), entity, entity.getName());
                    }
                }
            });

        loadData();
    }


    @Nullable
    @Override
    public Observable<List<BrandItem>> getDataObservable() {
        return mBrandMuseumData.getBrandMuseum()
            .flatMap(new Function<List<Brand>, ObservableSource<Brand>>() {
                @Override
                public ObservableSource<Brand> apply(@NonNull List<Brand> brandList)
                    throws Exception {
                    return Observable.fromIterable(brandList);
                }
            })
            .map(new Function<Brand, BrandItem>() {
                @Override
                public BrandItem apply(@NonNull Brand brand) throws Exception {
                    return EntityTransUtil.transBrand(brand);
                }
            })
            .toList()
            .toObservable();
    }


    @Override
    protected void onLoadSuccess(List<BrandItem> brandItems) {
        super.onLoadSuccess(brandItems);
        mAdapter.setDatas(brandItems);
    }
}
