package com.a1magway.bgg.v.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SoftKeyBroadManager;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerSetPassWordComponent;
import com.a1magway.bgg.di.module.SetPasswordModule;
import com.a1magway.bgg.p.login.SetPassWordP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.widget.ClearEditText;
import java.util.List;

/** 设置用户密码 */
public class PasswordResetActivity extends PresenterActivity<SetPassWordP>
        implements ClearEditText.MyTextWatcher, PasswordResetContract.View {
    private static final String SET_PWD_TYPE = "set_pwd_type";
    private static final String SET_PWD_PHONE = "set_pwd_phone";

    // 类型 0注册 1忘记密码
    private int type;

    // 手机号
    private String phone;

    @BindView(R.id.set_pwd_tip_tv)
    TextView setBtnEnable;

    @BindViews({R.id.set_pwd_edt, R.id.set_pwd_repeat_edt})
    List<ClearEditText> mClearEditTexts;

    @BindView(R.id.set_pwd_btn)
    Button mNextBtn;

    @BindView(R.id.layout_root)
    LinearLayout layoutRoot;

    private SoftKeyBroadManager manager;

    public static void startSetPassWordActivity(Activity context, String phone, int type) {
        Intent intent = new Intent(context, PasswordResetActivity.class);
        intent.putExtra(SET_PWD_TYPE, type);
        intent.putExtra(SET_PWD_PHONE, phone);
        context.startActivity(intent);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerSetPassWordComponent.builder()
                .appComponent(appComponent)
                .setPasswordModule(new SetPasswordModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        // 注册
        if (type == 0) {
            setBtnEnable.setVisibility(View.INVISIBLE);
            setTextTitle(R.string.common_title_register);
        } else if (type == 1) {
            setTextTitle(R.string.common_title_forget);
        }
        mClearEditTexts.get(0).addTextChangedListener(this);
        mClearEditTexts.get(1).addTextChangedListener(this);
        setBackListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        preExitPage();
                    }
                });
        manager = new SoftKeyBroadManager(layoutRoot);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getIntExtra(SET_PWD_TYPE, 0);
        phone = intent.getStringExtra(SET_PWD_PHONE);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_set_pass_word;
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.addSoftKeyboardStateListener(softKeyboardStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.removeSoftKeyboardStateListener(softKeyboardStateListener);
    }

    @OnClick({R.id.set_pwd_btn})
    void onViewsClick(View v) {
        presenter.onNextBtnClick(phone, type);
    }

    @OnCheckedChanged(R.id.set_pwd_checkBox)
    void onChecked(boolean isChecked) {
        for (ClearEditText clearEditText : mClearEditTexts) {
            if (isChecked) {
                // 如果选中，显示密码
                clearEditText.setTransformationMethod(
                        HideReturnsTransformationMethod.getInstance());
                clearEditText.setSelection(clearEditText.getEditable().length());
            } else {
                // 否则隐藏密码
                clearEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                clearEditText.setSelection(clearEditText.getEditable().length());
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void afterTextChanged(Editable s) {
        presenter.canGoNext();
    }

    @Override
    public Editable getEditable(int index) {
        return mClearEditTexts.get(index).getEditable();
    }

    @Override
    public void setBtnEnable(boolean isEnable) {
        if (mNextBtn.isEnabled() != isEnable) {
            mNextBtn.setEnabled(isEnable);
        }
    }

    @Override
    protected boolean preExitPage() {
        showExitDialog();
        return true;
    }

    private void showExitDialog() {
        int msg = 0;
        if (type == 0) {
            msg = R.string.set_pwd_register_give_up;
        } else if (type == 1) {
            msg = R.string.set_pwd_forget_give_up;
        }
        new AlertDialog.Builder(this)
                .setMessage(msg)
                .setNegativeButton(R.string.common_cancel, null)
                .setPositiveButton(
                        R.string.common_ensure,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                .show();
    }

    @Override
    public void showLoading(@Nullable String text) {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError() {}

    @Override
    public void showEmpty() {}

    private SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener =
            new SoftKeyBroadManager.SoftKeyboardStateListener() {
                @Override
                public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                    editLayout(setBtnEnable, true);
                }

                @Override
                public void onSoftKeyboardClosed() {
                    editLayout(setBtnEnable, false);
                }
            };

    private void editLayout(View view, boolean isOpen) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int topMargin = isOpen ? params.topMargin / 3 : params.topMargin * 3;
        int leftMargin = params.leftMargin;
        int bottomMargin = params.bottomMargin;
        int rightMargin = params.rightMargin;
        params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        view.requestLayout();
    }
}
