package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/5/29.
 */

public class NetThirdpartRegisterData implements IThirdpartRegisterData {
    private APIManager mApiManager;

    public NetThirdpartRegisterData(APIManager mApiManager) {
        this.mApiManager = mApiManager;
    }

    @Override
    public Observable<LoginData> thirdPartRegister(String cover, String nick_name, String open_id, String platform_type, String sex) {
        return mApiManager.thirdpartiesRegister(cover,nick_name,open_id,platform_type,sex);
    }
}
