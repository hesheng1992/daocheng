package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a1magway.bgg.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 该拼单已完成
 * Created by enid on 2018/8/14.
 */

public class CrowdorderingFinishDialog extends DialogFragment {
    Unbinder unbinder;

    public static void show(Activity activity){
        CrowdorderingFinishDialog dialog = new CrowdorderingFinishDialog();
        dialog.show(activity.getFragmentManager(),activity.getLocalClassName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_crowdordering_finish, container, false);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.crowdordering_finish_close, R.id.crowdordering_finish_know})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.crowdordering_finish_close:
                dismiss();
                break;
            case R.id.crowdordering_finish_know:
                dismiss();
                break;
        }
    }
}
