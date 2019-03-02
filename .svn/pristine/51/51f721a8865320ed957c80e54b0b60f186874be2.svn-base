package com.a1magway.bgg.v.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerModifyPwdComponent;
import com.a1magway.bgg.di.module.ModifyPwdModule;
import com.a1magway.bgg.p.personal.ModifyPwdP;
import com.a1magway.bgg.widget.ClearEditText;
import com.a1magway.bgg.widget.TitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * 修改密码
 */
public class ModifyPwdActivity extends PActivity<ModifyPwdP> implements IModifyPwdV {

    @BindViews({R.id.modify_pwd_old_edt, R.id.modify_pwd_new_edt, R.id.modify_pwd_new_repeat_edt})
    List<ClearEditText> mClearEditTexts;

    @BindView(R.id.modify_pwd_btn)
    Button mNextBtn;

    @BindView(R.id.modify_title_bar)
    TitleBar mTitleBar;

    public static void startModifyPwdActivity(Context context) {
        Intent intent = new Intent(context, ModifyPwdActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setDefLeftImgClickListener(this);
        for (ClearEditText clearEditText : mClearEditTexts) {
            clearEditText.addTextChangedListener(mOnTextChangeListener);
        }
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerModifyPwdComponent.builder()
                .appComponent(appComponent)
                .modifyPwdModule(new ModifyPwdModule(this))
                .build().inject(this);
    }

    @Override
    public void setNextEnable(boolean enable) {
        mNextBtn.setEnabled(enable);
    }

    @OnCheckedChanged(R.id.modify_pwd_checkBox)
    void onCheckChanged(boolean checked) {
        for (ClearEditText clearEditText : mClearEditTexts) {
            if (checked) {
                //如果选中，显示密码
                clearEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                clearEditText.setSelection(clearEditText.getEditable().length());
            } else {
                //否则隐藏密码
                clearEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                clearEditText.setSelection(clearEditText.getEditable().length());
            }
        }
    }

    @OnClick(R.id.modify_pwd_btn)
    void doChangePwd() {
        mPresenter.doChange(mClearEditTexts);
    }

    private OnTextChangeListener mOnTextChangeListener = new OnTextChangeListener();

    class OnTextChangeListener implements ClearEditText.MyTextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            mPresenter.canGoNext(mClearEditTexts);
        }
    }
}
