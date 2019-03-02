package com.a1magway.bgg.widget.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.a1magway.bgg.R;


/**
 * 加载中弹窗
 * Created by jph on 2017/8/23.
 */
public class LoadingDialogFragment extends DialogFragment {
    private String mMessage;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        ProgressDialog dialog = new ProgressDialog(getContext());
        Dialog dialog = new Dialog(getContext(), R.style.alert_dialog_style);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        View view = View.inflate(getContext(), R.layout.loading, null);
        dialog.setContentView(view);
        return dialog;
    }


//    public void setMessage(String message) {
//        mMessage = message;
//        if (getDialog() != null) {
//            ((ProgressDialog) getDialog()).setMessage(message);
//        }
//    }

    /**
     * 是否showing
     * @return
     */
    public boolean isShowing(){
        if (getDialog() != null){
            return getDialog().isShowing();
        }
        return false;
    }
}
