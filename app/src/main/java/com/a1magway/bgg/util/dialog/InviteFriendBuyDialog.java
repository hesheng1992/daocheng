package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.util.StringFormatUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 邀请好友团购的dialog
 * Created by enid on 2018/8/14.
 */

public class InviteFriendBuyDialog extends DialogFragment implements View.OnClickListener {


    @BindView(R.id.invite_friend_buy_text3)
    TextView inviteFriendBuyText3;
    @BindView(R.id.invite_friend_buy_time)
    TextView inviteFriendBuyTime;
    @BindView(R.id.invite_friend_buy_text2)
    View invite_friend_buy_text2;
    @BindView(R.id.line1)
    View line1;
    Unbinder unbinder;
    private long downTime;//倒计时
    private Disposable disposable;
    private String remainNum;
    private BtnClickListener listener;

    public static void show(Activity activity, long downTime, String remainNum, BtnClickListener listener) {
        InviteFriendBuyDialog dialog = new InviteFriendBuyDialog();
        dialog.downTime = downTime;
        dialog.remainNum = remainNum;
        dialog.listener = listener;
        dialog.setCancelable(false);
        dialog.show(activity.getFragmentManager(), activity.getLocalClassName());
    }

    public static void dimiss(Activity activity) {
        Fragment fragment = activity.getFragmentManager().findFragmentByTag(activity.getLocalClassName());
        if (fragment != null && fragment instanceof InviteFriendBuyDialog) {
            InviteFriendBuyDialog dialog = (InviteFriendBuyDialog) fragment;
            dialog.dismiss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_invite_friend_buy, container, false);
        unbinder = ButterKnife.bind(this, layout);
        startDownTime();
        StringBuilder builder = new StringBuilder();
        builder.append("邀请好友拼单(差")
                .append(remainNum)
                .append("人)");
        inviteFriendBuyText3.setText(String.valueOf(builder));
        inviteFriendBuyText3.setOnClickListener(this);
        if (remainNum.equals("0")) {
            invite_friend_buy_text2.setVisibility(View.GONE);
            line1.setVisibility(View.GONE);
            inviteFriendBuyText3.setVisibility(View.GONE);
            inviteFriendBuyTime.setVisibility(View.GONE);
            inviteFriendBuyText3.setVisibility(View.GONE);
        }
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

    }


    @OnClick(R.id.invite_friend_buy_close)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        super.onDismiss(dialog);
    }


    /**
     * 显示倒计时
     */
    private void startDownTime() {
        inviteFriendBuyTime.setText(String.valueOf("剩余时间: " + StringFormatUtil.getCountDownTimeStr(downTime / 1000)));
        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (downTime == 0) {
                            disposable.dispose();
                        } else {
                            downTime -= 1000;
                            inviteFriendBuyTime.setText(String.valueOf("剩余时间: " + StringFormatUtil.getCountDownTimeStr(downTime / 1000)));
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.btnClick();
        }
    }

    public interface BtnClickListener {
        void btnClick();
    }
}
