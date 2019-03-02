package com.a1magway.bgg.di.module;

import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.personalcenterdata.IAddressData;
import com.a1magway.bgg.data.repository.personalcenterdata.NetAddressData;
import com.a1magway.bgg.v.personal.IEditAddressV;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lyx on 2017/8/16.
 */
@Module
public class EditAddressModule {
    IEditAddressV mIEditAddressV;

    public EditAddressModule(IEditAddressV iEditAddressV) {
        this.mIEditAddressV = iEditAddressV;
    }

    @Provides
    public IEditAddressV provideIEditAddressV() {
        return mIEditAddressV;
    }

    @Provides
    public IAddressData pIAddressData(APIManager apiManager) {
        return new NetAddressData(apiManager);
    }
}
