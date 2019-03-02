package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.util.KeyBoardUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by enid on 2018/8/13.
 */

public class BuyerCountDialog extends DialogFragment {

    @BindView(R.id.pro_txt_count)
    EditText proTxtCount;
    Unbinder unbinder;
    @BindView(R.id.pro_layout_reduce)
    FrameLayout proLayoutReduce;
    private int count;
    private Context context;
    private CommitCallBack commitCallBack;

    public static void show(Activity activity, int count, CommitCallBack commitCallBack) {
        BuyerCountDialog buyerCountDialog = new BuyerCountDialog();
        buyerCountDialog.context = activity;
        buyerCountDialog.count = count;
        buyerCountDialog.commitCallBack = commitCallBack;
        buyerCountDialog.show(activity.getFragmentManager(), activity.getLocalClassName());
    }

    public static void dismiss(Activity activity) {
        DialogFragment fragment = (DialogFragment) activity.getFragmentManager().findFragmentByTag(activity.getLocalClassName());
        fragment.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View layout = inflater.inflate(R.layout.dialog_buyer_count, container, false);
        unbinder = ButterKnife.bind(this, layout);
        proTxtCount.setText(String.valueOf(count));
        Selection.selectAll(proTxtCount.getText());
        setTextChangedListener();
        return layout;
    }

    private void setTextChangedListener() {
        proTxtCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!proTxtCount.getText().toString().isEmpty()) {
                    count = Integer.valueOf(proTxtCount.getText().toString());
                } else {
                    count = 1;
                }
                if (count < 1) {
                    Observable.timer(1, TimeUnit.MILLISECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    proTxtCount.setText("1");
                                    proTxtCount.setSelection(1);
                                    count = 1;
                                    setReduceEnabled();
                                }
                            });
                } else {
                    setReduceEnabled();
                }
            }
        });
        setReduceEnabled();
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyBoardUtils.openKeybord(proTxtCount, context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.pro_layout_reduce, R.id.pro_layout_plus, R.id.cancel, R.id.commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pro_layout_reduce:
                count--;
                proTxtCount.setText(String.valueOf(count));
                proTxtCount.setSelection(proTxtCount.getText().toString().length());
                break;
            case R.id.pro_layout_plus:
                count++;
                proTxtCount.setText(String.valueOf(count));
                proTxtCount.setSelection(proTxtCount.getText().toString().length());
                break;
            case R.id.cancel:
                KeyBoardUtils.closeKeybord(proTxtCount, context);
                this.dismiss();
                break;
            case R.id.commit:
                KeyBoardUtils.closeKeybord(proTxtCount, context);
                this.dismiss();
                if (count > 0) {
                    commitCallBack.commit(count);
                }
                break;
        }
        setReduceEnabled();
    }

    private void setReduceEnabled() {
        if (count < 2) {
            proLayoutReduce.setEnabled(false);
        } else {
            proLayoutReduce.setEnabled(true);
        }
    }

    public interface CommitCallBack {
        void commit(int count);
    }

}
