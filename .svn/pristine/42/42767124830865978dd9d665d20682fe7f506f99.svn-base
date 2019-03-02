package com.a1magway.bgg.p.register;

import android.support.annotation.NonNull;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.broadcast.BoundInvitationCodeReceiver;
import com.a1magway.bgg.data.entity.InvitationData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.repository.IInvitationData;
import com.a1magway.bgg.eventbus.event.BindInvitationSuccessEvent;
import com.a1magway.bgg.refactor.BasePresenter;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.invitation.InvitationContract;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2018/5/28.
 */

public class InvitationP extends BasePresenter<InvitationContract.View> implements InvitationContract.Presenter {

    private IInvitationData mIRegisterData;
    private boolean toUpdateUser;

    @Inject
    public InvitationP(@NonNull InvitationContract.View iRegisterV, IInvitationData mIRegisterData) {
        super(iRegisterV);
        this.mIRegisterData = mIRegisterData;
    }

    @Override
    public void bindInvitationCode() {

        String uid = String.valueOf(GlobalData.getInstance().getLoginData().getId());

        final String invitationCode = mView.getInvitationCode();
        mIRegisterData.bindInvitationCode(uid,
                invitationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InvitationData>(getContext()) {
                    @Override
                    public void onNext(InvitationData invitationData) {
                        //绑定成功后,将邀请码保存到用户数据
                        LoginData loginData = GlobalData.getInstance().getLoginData();
                        loginData.setMemberGrade(2);
                        loginData.setInviterName(invitationData.getInviterName());
                        GlobalData.getInstance().setLoginData(loginData);
                        BoundInvitationCodeReceiver.sendBoundInvitationCodeBroadcast(getContext());
                        Toaster.showShort(getContext(), "绑定成功");
                        EventBus.getDefault().post(new BindInvitationSuccessEvent(invitationData.getInviterName()));
                        if (toUpdateUser) {
                            //去升级账号
                            ActivityIntentUtil.toUpgradeGuidePager1Activity(getContext());
                        }else {
                            MainActivity.start(getContext(), MainSubPages.MAIN_HOME);
                        }
                        mView.getActivity().finish();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void toUpdateUser(boolean toUpdateUser) {
        this.toUpdateUser = toUpdateUser;
    }


}
