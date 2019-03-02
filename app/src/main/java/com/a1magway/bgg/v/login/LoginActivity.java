package com.a1magway.bgg.v.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SoftKeyBroadManager;
import com.a1magway.bgg.common.shre.ShareUtils;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerLoginComponent;
import com.a1magway.bgg.di.module.LoginModule;
import com.a1magway.bgg.p.login.LoginP;
import com.a1magway.bgg.p.login.UserVerificationP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.widget.ClearEditText;
import com.almagway.common.utils.ToastUtil;
import com.almagway.umeng.PlatformType;
import com.almagway.umeng.UmShare;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/** 登录 */
public class LoginActivity extends PresenterActivity<LoginP> implements LoginContract.View {

    private static  final String FOUND_CANCLE_FC="found_cancle_fc";
    @BindViews({R.id.login_phone_edt, R.id.login_pwd_edt})
    List<ClearEditText> mLoginEdits;

    @BindView(R.id.login_btn)
    Button mLoginBtn;

    @BindView(R.id.quick_register_tv)
    TextView mQuickRegisterTv;

    @BindView(R.id.forget_pwd_tv)
    TextView mForgetPwdTv;

    @BindView(R.id.login_show_pwd_checkBox)
    CheckBox mCheckBox;

    @BindView(R.id.layout_login)
    LinearLayout layoutRoot;


    private TextChangeListener mTextChangeListener = new TextChangeListener(this);
    private SoftKeyBroadManager manager;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        JumpUtil.startActivity(context, starter);
    }

    public static void startForResult(Fragment fragment, int requestCode) {
        Intent starter = new Intent(fragment.getContext(), LoginActivity.class);
        fragment.startActivityForResult(starter, requestCode);
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setImageBack(true, R.drawable.ic_title_close);
        setTextTitle(R.string.login_weixin);
        mLoginEdits.get(0).addTextChangedListener(mTextChangeListener);
        mLoginEdits.get(1).addTextChangedListener(mTextChangeListener);
        manager = new SoftKeyBroadManager(layoutRoot);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.login_activity_login;
    }

    @OnClick({R.id.login_btn,R.id.weixin_login, R.id.forget_pwd_tv, R.id.quick_register_tv,R.id.um_qq,R.id.um_wechat,R.id.um_sina})
    public void clickEvent(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                presenter.loadData();
                break;
            case R.id.quick_register_tv:
                UserVerificationActivity.startSendVerificationCodeActivity(
                        LoginActivity.this, UserVerificationP.CODE_TYPE_REGISTER);
                break;
            case R.id.forget_pwd_tv:
                UserVerificationActivity.startSendVerificationCodeActivity(
                        LoginActivity.this, UserVerificationP.CODE_TYPE_FORGET);
                break;
            case R.id.um_qq:
                presenter.thirdPartAuth(PlatformType.QQ);
                break;
            case R.id.um_wechat:
            case R.id.weixin_login:
                if (!ShareUtils.isWeixinAvilible(this)) {
                    ToastUtil.showShort("微信未安装");
                    return;
                }
                presenter.thirdPartAuth(PlatformType.WEIXIN);
                break;
            case R.id.um_sina:
                presenter.thirdPartAuth(PlatformType.SINA);
                break;

        }
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

    @Override
    public void showLoading(@Nullable String text) {}

    @Override
    public void hideLoading() {}

    @Override
    public void showError() {}

    @Override
    public void showEmpty() {}

    private static class TextChangeListener implements ClearEditText.MyTextWatcher {

        private WeakReference<LoginActivity> weakReference;

        TextChangeListener(LoginActivity loginActivity) {
            weakReference = new WeakReference<>(loginActivity);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        @Override
        public void afterTextChanged(Editable s) {
            LoginActivity loginActivity = weakReference.get();
            if (loginActivity == null) return;
            if (loginActivity.mLoginEdits.get(0).getEditable().length()
                            >= loginActivity.getResources().getInteger(R.integer.phoneLength)
                    && loginActivity.mLoginEdits.get(1).getEditable().length()
                            >= loginActivity.getResources().getInteger(R.integer.pwd_min_length)) {
                if (!loginActivity.mLoginBtn.isEnabled()) {
                    loginActivity.mLoginBtn.setEnabled(true);
                }
            } else {
                if (loginActivity.mLoginBtn.isEnabled()) {
                    loginActivity.mLoginBtn.setEnabled(false);
                }
            }
        }
    }

    @OnCheckedChanged(R.id.login_show_pwd_checkBox)
    void onCheckedChanged() {
        if (mCheckBox.isChecked()) {
            // 如果选中，显示密码
            mLoginEdits
                    .get(1)
                    .setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            mLoginEdits.get(1).setSelection(mLoginEdits.get(1).getEditable().length());
        } else {
            // 否则隐藏密码
            mLoginEdits.get(1).setTransformationMethod(PasswordTransformationMethod.getInstance());
            mLoginEdits.get(1).setSelection(mLoginEdits.get(1).getEditable().length());
        }
    }

    @Override
    public String getUserPhone() {
        return mLoginEdits.get(0).getEditable().toString().trim();
    }

    @Override
    public String getUserPwd() {
        return mLoginEdits.get(1).getEditable().toString().trim();
    }

    @Override
    public AnimType getAnimType() {
        return AnimType.ANIM_TYPE_UP_IN;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        new UmShare(this).activityResult(requestCode,resultCode,data);
        if (requestCode == RequestCode.REQUEST_CODE_SEND_CODE) {
            if (GlobalData.getInstance().getLoginData() != null) {
                finishWithAnim();
            }
        }
    }

    private SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener =
            new SoftKeyBroadManager.SoftKeyboardStateListener() {
                @Override
                public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                    editLayout(mLoginEdits.get(0), true);
                }

                @Override
                public void onSoftKeyboardClosed() {
                    editLayout(mLoginEdits.get(0), false);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        EventBus.getDefault().post(FOUND_CANCLE_FC);
        finish();
    }

}
