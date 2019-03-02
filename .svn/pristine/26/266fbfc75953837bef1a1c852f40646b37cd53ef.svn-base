package com.a1magway.bgg.v.upgrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerUpgradePayComponent;
import com.a1magway.bgg.di.module.UpgradePayModel;
import com.a1magway.bgg.eventbus.event.ChangeUserSettingEvent;
import com.a1magway.bgg.p.login.UpgradePayP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.AppManager;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.dialog.CustomDialog;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

/**
 * Created by enid on 2018/8/3.
 */

public class UpgradePayActivity extends PresenterActivity<UpgradePayP> implements UpgradePayContract.View {

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_upgrade_member;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerUpgradePayComponent.builder()
                .appComponent(appComponent)
                .upgradePayModel(new UpgradePayModel(this, getIntent().getIntExtra("pay_money", 0)))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("支付");
    }


    @OnClick({R.id.weixin_pay, R.id.ali_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin_pay:
                presenter.toWeixinPay();
                break;
            case R.id.ali_pay:
                presenter.toAliPay();
                break;
        }
    }


    @Override
    public void showBindPhoneDialog() {
        String message = "为了你的账号安全\n请立即绑定手机号码";
        String btnText = "立即绑定";
        CustomDialog.show(this, message, btnText, 0, new CustomDialog.OnBtnClickListener() {
            @Override
            public void btnClick(View view) {
                CustomDialog.dismiss(UpgradePayActivity.this);
            }
        });
    }

    @Override
    public void showUpgradeSucceedDialog() {
        EventBus.getDefault().post(new ChangeUserSettingEvent());
        String chenghao = GlobalData.getInstance().getLoginData().getMemberGradeName();
        String message = "账号已成功升级为时尚买手\n获得“" + chenghao + "”称号";
        String btnText = "查看";
        CustomDialog.show(this, message, btnText, R.mipmap.icon_pay_succeed, new CustomDialog.OnBtnClickListener() {
            @Override
            public void btnClick(View view) {
                CustomDialog.dismiss(UpgradePayActivity.this);
                MainActivity.start(UpgradePayActivity.this, MainSubPages.PERSONAL);
            }
        });
    }


}
