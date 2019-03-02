package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/9.
 */
public interface ISendVerificationData {
    Observable<String> sendVerification(String phone, int type);

    Observable<APIResponse> checkCode(String phone, String code);
}
