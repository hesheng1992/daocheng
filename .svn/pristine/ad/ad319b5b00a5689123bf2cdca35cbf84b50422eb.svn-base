package com.a1magway.bgg.data.repository.personalcenterdata;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.entity.AddressDData;
import com.a1magway.bgg.data.entity.AddressSelectedDataBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by lyx on 2017/8/12.
 */
public interface IAddressData {
    /**
     * 地址列表
     * @param userId
     * @param addressId
     * @return
     */
    Observable<List<AddressData>> getAddressList(int userId, int addressId);

    /**
     * 删除地址
     * @param userId
     * @param addressId
     * @return
     */
    Observable<APIResponse<AddressDData>> deleteAddress(int userId, int addressId);

    /**
     * 设置默认地址
     * @param userId
     * @param addressId
     * @return
     */
    Observable<APIResponse<AddressDData>> setDefaultAddress(int userId, int addressId);

    /**
     * 编辑地址
     * @param map 地址信息
     * @param type 0 添加新地址 1 修改地址
     * @return
     */
    Observable<APIResponse> editAddress(Map<String, Object> map, int type);


    /**
     *得到城市列表
     */
    Observable<AddressSelectedDataBean> getCityList();

}
