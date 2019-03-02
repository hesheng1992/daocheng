package com.a1magway.bgg.v.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.common.enums.GenderType;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.util.SoftInputUtil;
import com.almagway.common.utils.StringUtil;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerPersonalSettingComponent;
import com.a1magway.bgg.di.module.PersonalSettingModule;
import com.a1magway.bgg.p.personal.PersonalSettingP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人设置
 */
public class PersonalSettingActivity extends PActivity<PersonalSettingP>
        implements IPersonalSettingV {

    @BindView(R.id.personal_setting_birthday_tv)
    TextView mBirthdayTv;

    @BindView(R.id.personal_setting_gender_tv)
    TextView mGenderTv;

    @BindView(R.id.personal_setting_phone_tv)
    TextView mPhoneTv;

    @BindView(R.id.personal_setting_titleBar)
    TitleBar mTitleBar;

    @BindView(R.id.personal_setting_nickname_edt)
    EditText mNicknameEdt;

    @BindView(R.id.lly_change_pwd_parent)
    LinearLayout lly_change_pwd_parent;


    public static void startPersonalCenterActivity(Context context) {
        Intent intent = new Intent(context, PersonalSettingActivity.class);
        context.startActivity(intent);
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerPersonalSettingComponent.builder()
                .appComponent(appComponent)
                .personalSettingModule(new PersonalSettingModule(this))
                .build().inject(this);
    }


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_personal_center;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData.isWeixinLogin()){
            //如果是从微信登录，不显示修改密码选项
        lly_change_pwd_parent.setVisibility(View.GONE);
        }
        mPhoneTv.setText(loginData.getTelephone());
        mGenderTv.setText(loginData.getGender());
        mBirthdayTv.setText(loginData.getBirthday());
        mNicknameEdt.setText(loginData.getNickName());
        if (loginData.getRoleType() != GlobalData.USER_ROLE_TYPE_DEFAULT) {
            mGenderTv.setEnabled(false);
            mBirthdayTv.setEnabled(false);
        }
//        mTitleBar.setDefLeftImgClickListener(this);
        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringUtil.isEmpty(mNicknameEdt.getText().toString())) {
                    mPresenter.nickNameChanged();
                }
                SoftInputUtil.hideSoftInput(PersonalSettingActivity.this);
                finish();
            }
        });
        mNicknameEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    mPresenter.nickNameChanged();
                    return true;
                }
                return false;
            }
        });


    }


    @OnClick({R.id.personal_setting_birthday_tv, R.id.personal_setting_gender_tv,
            R.id.personal_setting_pwd_tv})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.personal_setting_birthday_tv:
                mPresenter.showDateSelectDialog(
                        GlobalData.getInstance().getLoginData().getBirthday());
                break;
            case R.id.personal_setting_gender_tv:
                Integer a = null;
                LoginData loginData = GlobalData.getInstance().getLoginData();
                if (StringUtil.isNotEmpty(loginData.getGender())) {
                    a = GenderType.getIndex(GlobalData.getInstance().getLoginData().getGender());
                }
                mPresenter.showGenderSelectDialog(a);
                break;
            case R.id.personal_setting_pwd_tv:
                ModifyPwdActivity.startModifyPwdActivity(getContext());
                break;
        }
    }


    @Override
    public void setBirthDay(String string) {
        mBirthdayTv.setText(string);
    }


    @Override
    public void setGender(String string) {
        mGenderTv.setText(string);
    }

    @Override
    public void setNickname(String nickname) {
        mNicknameEdt.setText(nickname);
    }

    @Override
    public String getNickname() {
        return mNicknameEdt.getText().toString().trim();
    }
}
