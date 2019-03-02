package com.a1magway.bgg.p.personal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.shre.ShareData;
import com.a1magway.bgg.common.shre.ShareType;
import com.a1magway.bgg.data.entity.InvitationCodeData;
import com.a1magway.bgg.data.repository.IGetInvitationCodeData;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.personal.IInvitationCodeV;
import com.a1magway.bgg.widget.dialog.ShareDialogFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 邀请码
 */
public class InvitationCodeP extends BasePresenter<IInvitationCodeV> {
    private InvitationCodeData mCodeData;

    @Inject
    public InvitationCodeP(@NonNull IInvitationCodeV view) {
        super(view);
    }

    @Inject
    IGetInvitationCodeData mIGetInvitationCodeData;

    public void getInvitationCode(){
        mIGetInvitationCodeData.getInviteCode(GlobalData.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InvitationCodeData>(getContext()) {
                    @Override
                    public void onNext(InvitationCodeData invitationCodeData) {
                        mCodeData = invitationCodeData;
                        mView.setInvitationData(invitationCodeData);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void refreshQrcode(){
        mIGetInvitationCodeData.refreshQrcode(GlobalData.getInstance().getUserId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InvitationCodeData>(getContext()) {
                    @Override
                    public void onNext(InvitationCodeData invitationCodeData) {
                        mCodeData = invitationCodeData;
                        mView.setInvitationData(invitationCodeData);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void shareInvitationCode(FragmentManager fragmentManager){
        if (mCodeData == null) return;
        if (TextUtils.isEmpty(mCodeData.getUrl())){
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(mCodeData.getUrl());
//        list.add("https://gss2.bdstatic.com/-fo3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=0f270fffba19ebc4c078719fba1da8c1/37d12f2eb9389b5039d4d0c88c35e5dde7116e04.jpg");
        ShareData data = new ShareData.Builder()
                .setType(ShareType.SYSTEM)
                .setTitle("title")
                .setDescription("description")
                .setMediaPath(list)
                .build();
        ShareDialogFragment shareDialogFragment = ShareDialogFragment.newInstance(data,"",ShareDialogFragment.MY_INVITATIONCODE_SHARD);
        shareDialogFragment.show(fragmentManager, "share invitation code");
    }

}
