package com.a1magway.bgg.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import com.a1magway.bgg.R;

/**
 * author: Beaven
 * date: 2017/10/17 16:27
 */

public class CenterDialogFragment extends DialogFragment {

    public static CenterDialogFragment newInstance(String title, String message) {
        CenterDialogFragment fragment = new CenterDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("msg", message);
        fragment.setArguments(bundle);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString("title");
        String msg = getArguments().getString("msg");
        return new AlertDialog.Builder(getContext(), R.style.MyAlertDialogStyle)
            .setTitle(title)
            .setMessage(msg)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .create();
    }
}
