package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 邀请好友超限(每天只能邀请每个好友一次)
 * Created by enid on 2018/8/15.
 */

public class GeneralImageTextDialog extends DialogFragment {
    @BindView(R.id.dialog_invite_friend_limiting_img)
    ImageView img;
    @BindView(R.id.dialog_invite_friend_limiting_message)
    TextView message;
    Unbinder unbinder;
    private int imgId;
    private int text;

    public static void show(Activity activity, @DrawableRes int res, @StringRes int text) {
        GeneralImageTextDialog dialog = new GeneralImageTextDialog();
        dialog.imgId = res;
        dialog.text = text;
        dialog.show(activity.getFragmentManager(), activity.getLocalClassName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_invite_friend_limiting, container, false);
        unbinder = ButterKnife.bind(this, view);
        img.setImageResource(imgId);
        message.setText(text);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
