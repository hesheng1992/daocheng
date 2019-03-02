package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.InvitationData;
import com.a1magway.bgg.data.net.APIManager;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/5/29.
 */

public class NetInvitationData implements IInvitationData {
    private APIManager mApiManager;

    public NetInvitationData(APIManager mApiManager) {
        this.mApiManager = mApiManager;
    }

    @Override
    public Observable<InvitationData> bindInvitationCode(String uid, String invitationCode) {
        return mApiManager.setInvitationCode(uid,invitationCode);
    }
}
