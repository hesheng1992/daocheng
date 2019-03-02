package com.a1magway.bgg.data.repository;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.HuanXinLoginInfo;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/4.
 */

public interface IHuanxinData {
    Observable<HuanXinLoginInfo> hxLoginInfoGet();

    Observable<APIResponse> hxLoginInfoRelease(int id);
}
