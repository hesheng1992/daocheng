package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.entity.CollectionData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ICollectionListData;
import com.a1magway.bgg.data.repository.NetCollectionListData;
import com.a1magway.bgg.p.collection.ProductCollectionListAdapter;
import com.a1magway.bgg.v.collection.CollectionContract;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/4.
 */
@Module
public class ProductCollectionModule {
    private CollectionContract.View mICollectionV;

    public ProductCollectionModule(CollectionContract.View mICollectionV) {
        this.mICollectionV = mICollectionV;
    }

    @Provides
    public CollectionContract.View provideProductCollectionV(){
        return mICollectionV;
    }

    @Provides
    public ICollectionListData provideCollectionData(APIManager apiManager) {
        return new NetCollectionListData(apiManager);
    }

    @Provides
    public ProductCollectionListAdapter provideCollectionAdapter(ICollectionListData iCollectionListData) {
        return new ProductCollectionListAdapter(new ArrayList<CollectionData>(),iCollectionListData);
    }

}
