package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by enid on 2018/8/2.
 */

public class CustomDialog extends DialogFragment {

    @BindView(R.id.message)
    TextView message;
    @BindView(R.id.btn_bind)
    TextView btn;
    Unbinder unbinder;
    @BindView(R.id.top_image)
    ImageView topImage;

    private OnBtnClickListener onBtnClick;
    private String mg;
    private String btnText;
    private int topImageSrc;


    public static void show(Activity activity, String message, String btnText, int topImageSrc, OnBtnClickListener onBtnClick) {
        CustomDialog customDialog = new CustomDialog();
        customDialog.mg = message;
        customDialog.btnText = btnText;
        customDialog.topImageSrc = topImageSrc;
        customDialog.onBtnClick = onBtnClick;
        customDialog.setCancelable(false);
        customDialog.show(activity.getFragmentManager(), activity.getLocalClassName());
    }

    public static void dismiss(Activity activity) {
        Fragment fragment = activity.getFragmentManager().findFragmentByTag(activity.getLocalClassName());
        if (fragment != null && fragment instanceof DialogFragment) {
            DialogFragment dialogFragment = (DialogFragment) fragment;
            dialogFragment.dismiss();
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_custom, container);
        unbinder = ButterKnife.bind(this, view);
        message.setText(mg);
        if (topImageSrc == 0) {
            topImage.setVisibility(View.GONE);
        } else {
            topImage.setImageResource(topImageSrc);
            topImage.setVisibility(View.VISIBLE);
        }
        btn.setText(btnText);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_bind)
    public void onViewClicked() {
        if (onBtnClick != null) {
            onBtnClick.btnClick(btn);
        }
    }

    public interface OnBtnClickListener {
        void btnClick(View view);
    }
}
