package com.a1magway.bgg.util.pay;

/**
 * Created by jph on 2017/8/22.
 */
public interface PayCallback {

    void onSuccess(int payType);

    void onFailed(String msg);
}
