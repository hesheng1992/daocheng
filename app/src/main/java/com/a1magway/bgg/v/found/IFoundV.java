package com.a1magway.bgg.v.found;

import com.a1magway.bgg.v.IView;

public interface IFoundV extends IView {
    void onPayFailed(String msg);
    void onPaySuccess(int type);
}
