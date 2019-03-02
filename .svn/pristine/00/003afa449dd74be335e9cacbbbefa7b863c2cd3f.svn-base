package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.entity.CartProduct;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ICartData;
import com.a1magway.bgg.data.repository.NetCartData;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.p.cart.CartAdapter;
import com.a1magway.bgg.v.cart.ICartV;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jph on 2017/8/14.
 */
@Module
public class CartModule {

    private ICartV mCartV;

    public CartModule(ICartV cartV) {
        mCartV = cartV;
    }

    @PerFragment
    @Provides
    public ICartV provideCartV() {
        return mCartV;
    }

    @Provides
    public ICartData provideCartData(APIManager apiManager) {
        return new NetCartData(apiManager);
    }

    @PerFragment
    @Provides
    public CartAdapter provideCartAdapter() {
        return new CartAdapter(new ArrayList<CartProduct>());
    }
}
