package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.LoginData;

import io.reactivex.Observable;

/**
 * author: Beaven
 * date: 2017/10/18 16:15
 */

public interface IUserData {

    Observable<LoginData> getUserInfo(String userId);
}
