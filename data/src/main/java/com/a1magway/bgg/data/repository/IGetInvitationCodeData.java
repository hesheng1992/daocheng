package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.InvitationCodeData;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/20.
 */

public interface IGetInvitationCodeData {
    public Observable<InvitationCodeData> getInviteCode(int user_id);

    public Observable<InvitationCodeData> refreshQrcode(int user_id);
}
