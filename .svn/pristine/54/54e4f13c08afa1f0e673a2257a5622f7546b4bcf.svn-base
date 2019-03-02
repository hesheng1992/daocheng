package com.a1magway.bgg.p;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.common.broadcast.LoginReceiver;
import com.a1magway.bgg.data.entity.CartNum;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMainData;
import com.a1magway.bgg.di.scope.PerActivity;

import version.AppUpdateDialog;
import version.AppVersionUpdateService;
import version.VersionUpdateShowDialog;


import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.dialog.CustomDialog;
import com.a1magway.bgg.v.main.IMainV;
import com.a1magway.bgg.v.main.MainActivity;
import com.almagway.common.utils.ToastUtil;



import javax.inject.Inject;


/**
 * Created by jph on 2017/8/25.
 */
@PerActivity
public class MainP extends BasePresenter<IMainV> implements VersionUpdateShowDialog {
    private Handler handler = new Handler();

    @Inject
    IMainData mMainData;

    @Inject
    APIManager apiManager;

    @Inject
    public MainP(@NonNull IMainV view) {
        super(view);
    }

    public void refreshCartNum() {
        mMainData.getCartNum()
                .subscribe(new SimpleObserver<CartNum>(getContext()) {
                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull CartNum cartNum) {
                        super.onNext(cartNum);

                        mView.showCartNum(cartNum.getShopCartCount());
                    }
                });
    }

    public void registerLoginReceiver() {
        getContext().registerReceiver(mLoginReceiver, mLoginReceiver.getIntentFilter());
    }

    public void unregisterLoginReceiver() {
        getContext().unregisterReceiver(mLoginReceiver);
    }


    private LoginReceiver mLoginReceiver = new LoginReceiver() {
        @Override
        public void onLogin() {
            refreshCartNum();
        }

        @Override
        public void onLogout() {
            refreshCartNum();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        getUserInfo();
        startVersionUpdateService();
    }

    //每次进入更新用户信息
    private void getUserInfo(){
        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData == null) return;
        apiManager.getUserInfo(String.valueOf(loginData.getId()))
                .subscribe(new SimpleObserver<LoginData>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(LoginData loginData) {
                        super.onNext(loginData);
                        GlobalData.getInstance().setLoginData(loginData);
                        checkToBindPhone();
                    }
                });

    }

    //没有绑定手机号强制绑定
    private void checkToBindPhone() {
        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData != null && (loginData.getTelephone() == null || loginData.getTelephone().isEmpty())) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showBindPhoneDialog();
                }
            });
        }
    }

    public void showBindPhoneDialog() {
        String message = "为了你的账号安全\n请立即绑定手机号码";
        String btnText = "立即绑定";
        CustomDialog.show(mView.getActivity(), message, btnText, 0, new CustomDialog.OnBtnClickListener() {
            @Override
            public void btnClick(View view) {
                ActivityIntentUtil.toBindPhoneActivity(getContext());
                CustomDialog.dismiss(mView.getActivity());
            }
        });
    }



    private AppVersionUpdateService appVersionUpdateService;
    private AppUpdateDialog appUpdateDialog = new AppUpdateDialog();
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppVersionUpdateService.MyBinder myBinder = (AppVersionUpdateService.MyBinder) service;
            appVersionUpdateService = myBinder.getAppVersionUpdateService();
            appVersionUpdateService.addApKDownloadProgressListener(appUpdateDialog);
            appVersionUpdateService.addUpdateShowDialog(MainP.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void startVersionUpdateService() {
        Intent intent = new Intent(getContext(), AppVersionUpdateService.class);
        getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void stopVersionUpdateService() {
        getContext().unbindService(serviceConnection);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopVersionUpdateService();
    }


    @Override
    public void showCompelVersionUpdateDialog(String updateInfo) {
        appUpdateDialog.addAppVersionUpdateService(appVersionUpdateService);
        appUpdateDialog.setMsg(true, false, updateInfo, "立即更新", new AppUpdateDialog.GeneralRoundDialogCallBack() {
            @Override
            public void clickRoundDialogButton(TextView textView) {
                if (textView.getText().equals("开始安装")) {
                    appVersionUpdateService.installApk();
                } else {
                    textView.setText("下载中...");
                    appVersionUpdateService.startDownloadApk();
                }
            }
        });
        Activity activity = (Activity) getContext();
        appUpdateDialog.show(activity.getFragmentManager(), "");
    }


    @Override
    public void showCompelInstallApkDialog(String updateInfo) {
        appUpdateDialog.addAppVersionUpdateService(appVersionUpdateService);
        appUpdateDialog.setMsg(false, false, updateInfo, "开始安装", new AppUpdateDialog.GeneralRoundDialogCallBack() {
            @Override
            public void clickRoundDialogButton(TextView textView) {
                appVersionUpdateService.installApk();
            }
        });
        Activity activity = (Activity) getContext();
        appUpdateDialog.show(activity.getFragmentManager(), "");
    }


    @Override
    public void installApkDialog(String updateInfo) {
        if (MainActivity.activityStatus.equals(MainActivity.RESUME)) {
            appUpdateDialog.addAppVersionUpdateService(appVersionUpdateService);
            appUpdateDialog.setMsg(false, true, updateInfo, "开始安装", new AppUpdateDialog.GeneralRoundDialogCallBack() {
                @Override
                public void clickRoundDialogButton(TextView textView) {
                    appVersionUpdateService.installApk();
                }
            });
            Activity activity = (Activity) getContext();
            appUpdateDialog.show(activity.getFragmentManager(), "");
        }
    }

    @Override
    public void downloadApkError() {
        if (appUpdateDialog.getShowsDialog()) {
            appUpdateDialog.dismiss();
            ToastUtil.showShort("下载升级包错误");
        }
    }
}
