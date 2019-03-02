package com.a1magway.bgg.di.module.mainhome;

import com.a1magway.bgg.data.entity.HomeSubjectBean;
import com.a1magway.bgg.data.entity.ProductBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.mainhome.IHomeRecommendData;
import com.a1magway.bgg.data.repository.mainhome.IHomeSpecialData;
import com.a1magway.bgg.data.repository.mainhome.NetHomeRecommendData;
import com.a1magway.bgg.data.repository.mainhome.NetHomeSpecialData;
import com.a1magway.bgg.v.mainhome.IHomeSpecialV;
import com.a1magway.bgg.v.mainhome.SpecialDivisionAdapter;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by enid on 2018/6/13.
 */
@Module
public class HomeSpecialModule {
    IHomeSpecialV homeSpecialV;
    int mType;
    String comeFrom;

    public HomeSpecialModule(IHomeSpecialV homeSpecialV,int type,String comeFrom) {
        this.homeSpecialV = homeSpecialV;
        this.mType = type;
        this.comeFrom = comeFrom;
    }

    @Provides
    public IHomeSpecialV provideHomeSpecialView(){
        return this.homeSpecialV;
    }

    @Provides
    public IHomeSpecialData provideHomeSpecialData(APIManager apiManager){
        return new NetHomeSpecialData(apiManager);
    }

    @Provides
    public IHomeRecommendData provideHomeRecommendData(APIManager apiManager){
        return new NetHomeRecommendData(apiManager);
    }

    @Provides
    public int provideType(){
        return mType;
    }

    @Provides
    public String provideTitle(){return comeFrom;}


    @Provides
    public SpecialDivisionAdapter provideSpecialDivisionAdapter(){
        return new SpecialDivisionAdapter(new ArrayList<HomeSubjectBean>());
    }

    @Provides
    public ProductGridAdapter provideSpecialRecommendAdapter(){
        return new ProductGridAdapter(new ArrayList<ProductBean>());
    }
}
