package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.InvitationCodeData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/20.
 */

public class NetGetInvitationCodeData implements IGetInvitationCodeData {

    private APIManager mApiManager;

    public NetGetInvitationCodeData(APIManager apiManager) {
        this.mApiManager = apiManager;
    }

    @Override
    public Observable<InvitationCodeData> getInviteCode(int user_id) {
        return mApiManager.getInviteCode(user_id);
    }

    @Override
    public Observable<InvitationCodeData> refreshQrcode(int user_id) {
        return mApiManager.refreshQrcode(user_id);
    }
}
