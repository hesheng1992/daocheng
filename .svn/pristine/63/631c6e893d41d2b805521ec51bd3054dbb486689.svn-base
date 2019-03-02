package com.a1magway.bgg.p.mainhome;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.broadcast.BoundInvitationCodeReceiver;
import com.a1magway.bgg.common.broadcast.LoginReceiver;
import com.a1magway.bgg.data.entity.AppNavigationData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IMainHomeData;
import com.a1magway.bgg.eventbus.event.SwitchToUpgradePagerEvent;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.mainhome.IMainHomeV;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by enid on 2018/6/11.
 */

public class MainHomeP extends BaseLoadP<List<AppNavigationData>, IMainHomeV> {
    IMainHomeData mIMainHomeData;

    @Inject
    APIManager apiManager;
    @Inject
    public MainHomeP(@NonNull IMainHomeV view, IMainHomeData iMainHomeData) {
        super(view);
        this.mIMainHomeData = iMainHomeData;
    }

    @Nullable
    @Override
    public Observable<List<AppNavigationData>> getDataObservable() {
        return mIMainHomeData.getAppNavigation();
    }



    @Override
    protected void onLoadSuccess(List<AppNavigationData> appNavigationData) {
        super.onLoadSuccess(appNavigationData);
        mView.initViewPager(appNavigationData);
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.onRefreshEnd();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver();
        setSearchTxtContent();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(SwitchToUpgradePagerEvent event){
        mView.swithTab(4);
    }

    public void registerReceiver() {
        getContext().registerReceiver(mLoginReceiver, mLoginReceiver.getIntentFilter());
        getContext().registerReceiver(mBoundInvitationCodeReceiver, mBoundInvitationCodeReceiver.getIntentFilter());
    }

    public void unregisterReceiver() {
        getContext().unregisterReceiver(mLoginReceiver);
        getContext().unregisterReceiver(mBoundInvitationCodeReceiver);
    }


    private LoginReceiver mLoginReceiver = new LoginReceiver() {
        @Override
        public void onLogin() {
         setSearchTxtContent();
        }

        @Override
        public void onLogout() {
            mView.setSearchTxt("");
        }
    };

    private BoundInvitationCodeReceiver mBoundInvitationCodeReceiver = new BoundInvitationCodeReceiver() {
        @Override
        public void onBoundSuccess() {
           setSearchTxtContent();
        }
    };

    public void refreshUserInfo(){

        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData == null)
            return;
        mIMainHomeData.thirdPartLogin(loginData.getId()+"")  //获取首页搜索框提示
                .compose(this.<LoginData>bindToDestroyEvent())
                .subscribe(new BaseObserver<LoginData>(getContext()){
                    @Override
                    public void onNext(LoginData loginData) {
                        GlobalData.getInstance().setLoginData(loginData);
                        String inviterName;
                        if (loginData.getMemberGrade() > 2) {
                            inviterName = loginData.getNickName();

                        } else {
                            inviterName = loginData.getInviterName();
                        }
                        if (!TextUtils.isEmpty(inviterName)) {
                            mView.setSearchTxt(String.format(getContext().getResources().getString(R.string.search_shop), inviterName));
                        }
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });


    }

    private void setSearchTxtContent() {

        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData == null)
            return;
        String inviterName;
        if (loginData.getMemberGrade() > 2) {
            inviterName = loginData.getNickName();

        } else {
            inviterName = loginData.getInviterName();
        }
        if (!TextUtils.isEmpty(inviterName)) {
            mView.setSearchTxt(String.format(getContext().getResources().getString(R.string.search_shop), inviterName));
        }


    }

}
