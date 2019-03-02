package com.a1magway.bgg.di.module.member;

import com.a1magway.bgg.data.entity.ProductItem;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMemberGoodsData;
import com.a1magway.bgg.data.repository.NetMemberGoodsData;
import com.a1magway.bgg.p.seckill.ProductGridAdapter;
import com.a1magway.bgg.v.member.MemberGoodsContract;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * author: Beaven
 * date: 2017/10/13 10:50
 */
@Module
public class MemberGoodsModule {

    private MemberGoodsContract.View iMemberGoodsV;

    public MemberGoodsModule(MemberGoodsContract.View iMemberGoodsV) {
        this.iMemberGoodsV = iMemberGoodsV;
    }

    @Provides
    public MemberGoodsContract.View provideMemberGoodsV() {
        return iMemberGoodsV;
    }

    @Provides
    public ProductGridAdapter provideProductGridAdapter() {
        return new ProductGridAdapter(new ArrayList<ProductItem>());
    }

    @Provides
    public IMemberGoodsData provideMemberGoodsData(APIManager apiManager) {
        return new NetMemberGoodsData(apiManager);
    }
}
