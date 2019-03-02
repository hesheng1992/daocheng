package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.personalcenterdata.IAddressData;
import com.a1magway.bgg.data.repository.personalcenterdata.NetAddressData;
import com.a1magway.bgg.p.personal.AddressAdapter;
import com.a1magway.bgg.v.personal.IAddressV;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;

/**
 * Created by lyx on 2017/8/14.
 */
@Module
public class AddressModule {

    private IAddressV mIAddressV;


    public AddressModule(IAddressV IAddressV) {
        this.mIAddressV = IAddressV;
    }


    @Provides
    public IAddressV provideAddressManagerV() {
        return mIAddressV;
    }


    @Provides
    public IAddressData provideIAddressData(APIManager apiManager) {
        return new NetAddressData(apiManager);
    }


    @Provides
    public AddressAdapter provideAddressAdapter(IAddressData iAddressData) {
        return new AddressAdapter(new ArrayList<AddressData>(), iAddressData);
    }

}
