package com.a1magway.bgg.eventbus.event;

/**
 * Created by enid on 2018/7/10.
 */

public class  LoginSuccessEvent {
    private boolean loginSuccess;

    public LoginSuccessEvent(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }
}
