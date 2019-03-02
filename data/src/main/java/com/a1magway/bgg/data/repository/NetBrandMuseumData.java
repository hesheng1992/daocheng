package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.Brand;
import com.a1magway.bgg.data.entity.BrandMuseumMap;
import com.a1magway.bgg.data.net.APIManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by jph on 2017/7/29.
 */
public class NetBrandMuseumData implements IBrandMuseumData {
    private APIManager mAPIManager;

    public NetBrandMuseumData(APIManager APIManager) {
        mAPIManager = APIManager;
    }

    @Override
    public Observable<List<Brand>> getBrandMuseum() {
        return mAPIManager.getBrandMuseum()
                .flatMap(new Function<BrandMuseumMap, ObservableSource<List<Brand>>>() {
                    @Override
                    public ObservableSource<List<Brand>> apply(@NonNull final BrandMuseumMap brandMuseumMap) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<List<Brand>>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<List<Brand>> e) throws Exception {
                                List<Brand> list = new ArrayList<>();

                                Collection<List<Brand>> values = brandMuseumMap.values();
                                for (List<Brand> brandList :
                                        values) {
                                    list.addAll(brandList);
                                }

                                e.onNext(list);
                                e.onComplete();
                            }
                        });
                    }
                });
    }
}
