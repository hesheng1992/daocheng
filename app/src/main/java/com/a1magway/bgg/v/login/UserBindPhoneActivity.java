package com.a1magway.bgg.v.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerUerBindPhoneComponent;
import com.a1magway.bgg.di.module.UserBindPhoneModel;
import com.a1magway.bgg.p.login.UserBindPhoneP;
import com.a1magway.bgg.refactor.PresenterActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/8/3.
 */

public class UserBindPhoneActivity extends PresenterActivity<UserBindPhoneP> implements
        UserBindPhoneContract.View {


    @BindView(R.id.input_phone_number)
    EditText inputPhoneNumber;
    @BindView(R.id.input_auth_code)
    EditText inputAuthCode;
    @BindView(R.id.send_auth_code)
    TextView sendAuthCode;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_bind_phone;
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerUerBindPhoneComponent.builder()
                .appComponent(appComponent)
                .userBindPhoneModel(new UserBindPhoneModel(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setImageBack(false,0);
        setTextTitle("手机绑定");
    }

    @Override
    public void showVerificationCodeTime(CharSequence time, boolean isEnable) {
        sendAuthCode.setText(time);
        sendAuthCode.setEnabled(isEnable);
    }

    @Override
    public String getPhoneString() {
        return inputPhoneNumber.getText().toString();
    }

    @Override
    public String getVerificationCodeString() {
        return inputAuthCode.getText().toString();
    }

    @Override
    public void setBtnEnable(boolean enable) {
        btnConfirm.setEnabled(enable);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }


    @OnClick({R.id.send_auth_code, R.id.btn_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send_auth_code:
                presenter.sendVerificationCodeToUserPhone();
                break;
            case R.id.btn_confirm:
                presenter.clickBtn();
                break;
        }
    }
}
