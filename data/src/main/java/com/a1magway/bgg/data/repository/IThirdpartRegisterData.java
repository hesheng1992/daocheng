package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LoginData;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/5/29.
 */

public interface IThirdpartRegisterData {
    Observable<LoginData> thirdPartRegister(String cover, String nick_name, String open_id, String platform_type, String sex);
}
