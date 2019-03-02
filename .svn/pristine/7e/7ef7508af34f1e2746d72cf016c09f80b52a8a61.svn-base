package com.a1magway.bgg.data.repository.personalcenterdata;

import android.util.Log;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.entity.AddressDData;
import com.a1magway.bgg.data.entity.AddressSelectedDataBean;
import com.a1magway.bgg.data.net.APIManager;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/12.
 */
public class NetAddressData implements IAddressData {
    private static final int MANAGER_DELETE = 0;
    private static final int MANAGER_SET_DEFAULT = 1;

    APIManager mAPIManager;

    public NetAddressData(APIManager apiManager){
        this.mAPIManager = apiManager;
        Log.d("enid","NetAddressData execute");
    }

    @Override
    public Observable<List<AddressData>> getAddressList(int userId, int addressId) {
        return mAPIManager.queryAddressList(userId, addressId);
    }

    @Override
    public Observable<APIResponse<AddressDData>> deleteAddress(int userId, int addressId) {
        return mAPIManager.managerAddress(userId, addressId, MANAGER_DELETE);
    }

    @Override
    public Observable<APIResponse<AddressDData>> setDefaultAddress(int userId, int addressId) {
        return mAPIManager.managerAddress(userId, addressId, MANAGER_SET_DEFAULT);
    }

    @Override
    public Observable<APIResponse> editAddress(Map<String, Object> map, int type) {
        return mAPIManager.editAddress(map, type);
    }

    @Override
    public Observable<AddressSelectedDataBean> getCityList() {
        return mAPIManager.getCityList();
    }
}
