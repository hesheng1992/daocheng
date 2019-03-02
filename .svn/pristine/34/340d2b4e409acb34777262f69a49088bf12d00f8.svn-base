package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.a1magway.bgg.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by enid on 2018/8/7.
 */

public class ShareCopyHintDialog extends DialogFragment {

    @BindView(R.id.copy_text)
    TextView tv_copyText;
    Unbinder unbinder;
    private String copyText;
    private BtnClickListener btnClickListener;

    public static void show(Activity activity, String copyText, BtnClickListener btnClickListener) {
        ShareCopyHintDialog dialog = new ShareCopyHintDialog();
        dialog.copyText = copyText;
        dialog.btnClickListener = btnClickListener;
        dialog.setCancelable(false);
        dialog.show(activity.getFragmentManager(), activity.getLocalClassName());
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
        View view = inflater.inflate(R.layout.dialog_share_copy_hint, container);
        unbinder = ButterKnife.bind(this, view);
        tv_copyText.setText(copyText);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked() {
        if (btnClickListener != null) {
            ClipboardManager copy = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            assert copy != null;
            copy.setPrimaryClip(ClipData.newPlainText(null, tv_copyText.getText()));
            btnClickListener.click();
        }
    }

    public interface BtnClickListener {
        void click();
    }
}
