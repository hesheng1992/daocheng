package com.a1magway.bgg.p.login;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.a1magway.bgg.App;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.broadcast.LoginReceiver;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.repository.ILoginData;
import com.a1magway.bgg.data.repository.IThirdpartLoginData;
import com.a1magway.bgg.data.repository.IThirdpartRegisterData;
import com.a1magway.bgg.eventbus.event.LoginSuccessEvent;
import com.a1magway.bgg.refactor.BaseLoadPresenter;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.invitation.InvitationActivity;
import com.a1magway.bgg.v.login.LoginContract;
import com.almagway.common.utils.AesUtil;
import com.almagway.umeng.PlatformAuthListener;
import com.almagway.umeng.PlatformType;
import com.almagway.umeng.UmShare;
import com.almagway.umeng.bean.AuthBean;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lyx on 2017/7/31.
 */
public class LoginP extends BaseLoadPresenter<LoginContract.View, LoginData> {

    private ILoginData mILoginData;

    private IThirdpartRegisterData mThirdpartRegisterData;

    private IThirdpartLoginData mThirdpartLoginData;
    private static final String FOUND_CANCLE_FC = "found_cancle_fc";


    @Inject
    public LoginP(LoginContract.View iLoginV, ILoginData iLoginData, IThirdpartRegisterData thirdpartRegisterData, IThirdpartLoginData thirdpartLoginData) {
        super(iLoginV);
        mILoginData = iLoginData;
        this.mThirdpartRegisterData = thirdpartRegisterData;
        this.mThirdpartLoginData = thirdpartLoginData;
    }

    @Nullable
    @Override
    public Observable<LoginData> getDataObservable() {
        String aa = AesUtil.encryptAES(mView.getUserPwd());
        return mILoginData.login(mView.getUserPhone(), aa);
    }


    @Override
    protected void onLoadSuccess(LoginData loginData) {
        super.onLoadSuccess(loginData);
        loginSuccess(loginData);
    }

    public void thirdPartAuth(final int platform) {
        //TODO 微信微博登录测试数据
//        AuthBean authBean = new AuthBean();
//        authBean.setIconurl("http://img1.imgtn.bdimg.com/it/u=4172370510,1812558053&fm=27&gp=0.jpg");
//        authBean.setGender("女");
//        authBean.setName("Enid");
//        authBean.setUid("2018888888");
//        GlobalData.getInstance().setThirdUid(authBean.getUid());
//        thirdPartRegister(platform, authBean);
        new UmShare(mView.getActivity()).login(platform, new PlatformAuthListener() {
            @Override
            public void onSuccess(AuthBean authBean) {
                GlobalData.getInstance().setThirdUid(authBean.getUid());
                thirdPartRegister(platform, authBean);
            }
        });
    }

    public void thirdPartRegister(int platform, final AuthBean authBean) {
        mThirdpartRegisterData.thirdPartRegister(authBean.getIconurl(), authBean.getName(),
                authBean.getUid(), getPlatform(platform), getSexValue(authBean.getGender()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LoginData>(getContext()) {
                    @Override
                    public void onNext(LoginData loginData) {
                        loginData.setWeixinLogin(true);
                        loginData.setIconurl(authBean.getIconurl());
                        loginSuccess(loginData);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void loginSuccess(LoginData loginData) {
        Toaster.showShort(mView.getActivity(), "登录成功");
        App.getInstance().addUMengUserAlias(String.valueOf(loginData.getId()));//设置用户指定推送的别名
        GlobalData.getInstance().setLoginData(loginData);
        LoginReceiver.sendLoginStatusBroadcast(getContext(), LoginReceiver.LOG_IN);
        mView.getActivity().setResult(Activity.RESULT_OK);
        mView.getActivity().onBackPressed();

        //登录成功后如果没有绑定邀请码弹出绑定邀请码的界面
//        if (loginData.getMemberGrade() == 1) {
//            InvitationActivity.start(mView.getActivity());
//        }
        //发送登录成功事件到 ProDetailsInfoP
        EventBus.getDefault().post(new LoginSuccessEvent(true));
        EventBus.getDefault().post(FOUND_CANCLE_FC);

        //没有绑定手机就去强制绑定手机
        if (loginData.getTelephone() == null || loginData.getTelephone().isEmpty()) {
            ActivityIntentUtil.toBindPhoneActivity(mView.getActivity());
        } else if (loginData.getInviterName() == null) {
            InvitationActivity.start(mView.getActivity());
        }

    }

    public String getSexValue(String sex) {
        if ("男".equals(sex)) {
            return "0";
        } else if ("女".equals(sex)) {
            return "1";
        } else {
            return "2";
        }
    }

    public String getPlatform(int platform) {
        switch (platform) {
            case PlatformType.QQ:
                return "wx";
            case PlatformType.WEIXIN:
                return "wx";
            case PlatformType.SINA:
                return "wb";
            default:
                return "";
        }
    }
}
