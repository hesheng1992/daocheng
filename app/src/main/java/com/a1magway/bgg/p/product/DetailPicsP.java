package com.a1magway.bgg.p.product;

import android.support.annotation.NonNull;

import com.a1magway.bgg.data.entity.Product;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.v.product.IDetailPicsV;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * Created by jph on 2017/8/9.
 */
@PerFragment
public class DetailPicsP extends BasePresenter<IDetailPicsV> {

    @Inject
    Provider<Product> mProductProvider;

    @Inject
    public DetailPicsP(@NonNull IDetailPicsV view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mView.setRecyclerViewAdapter(new DetailsPicsAdapter(mProductProvider.get().getBigPicList()));
    }
}
