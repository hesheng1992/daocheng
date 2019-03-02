package com.a1magway.bgg.util.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * 通过DialogFragment包裹Dialog
 * Created by jph on 2017/1/14.
 */
public class WrapDialogFragment extends android.support.v4.app.DialogFragment {
    private Dialog mWrapDialog;

    public static WrapDialogFragment newDialogFragment() {
        return new WrapDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (mWrapDialog != null) {
            return mWrapDialog;
        }
        return super.onCreateDialog(savedInstanceState);
    }

    public WrapDialogFragment setDialog(Dialog dialog) {
        mWrapDialog = dialog;
        return this;
    }
}
