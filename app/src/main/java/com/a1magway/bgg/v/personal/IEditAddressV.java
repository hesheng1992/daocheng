package com.a1magway.bgg.v.personal;

import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.data.entity.AddressData;

import java.util.Map;

/**
 * Created by lyx on 2017/8/16.
 */
public interface IEditAddressV extends ILoadingV {

    /**
     * 返回请求参数
     * @return
     */
    Map<String, Object> getParamsMap();

    /**
     * 设置详细地址
     * @param s
     */
    void setDetailAddress(String s);

    boolean isAddNewAddress();
    AddressData getNewAddressData();
}
