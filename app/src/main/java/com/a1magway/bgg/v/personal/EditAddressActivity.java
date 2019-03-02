package com.a1magway.bgg.v.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.di.component.DaggerEditAddressComponent;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.util.Toaster;
import com.almagway.common.utils.StringUtil;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.module.EditAddressModule;
import com.a1magway.bgg.p.personal.EditAddressP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.widget.TitleBar;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 地址添加 编辑
 */
public class EditAddressActivity extends PActivity<EditAddressP> implements IEditAddressV {

    private static final String EXTRA_ADDRESS_DATA = "newAddress";

    private AddressData mAddressData;
    //新地址
    private AddressData newAddress;

    @BindView(R.id.new_address_user_name_tv)
    EditText mUserNameEdit;

    @BindView(R.id.new_address_phone_tv)
    EditText mPhoneEdit;

    @BindView(R.id.new_address_address_tv)
    TextView mAddressTv;

    @BindView(R.id.new_address_address_detail_tv)
    EditText mAddressDetailEdit;

    @BindView(R.id.new_address_post_code_tv)
    EditText mPostCodeTv;

    @BindView(R.id.detail_clear_iv)
    ImageView clearIv;

    @BindView(R.id.edit_address_title)
    TitleBar mTitleBar;

    public static void startForAdd(Context context) {
        Intent starter = new Intent(context, EditAddressActivity.class);
        JumpUtil.startActivityForResult(context, starter, RequestCode.REQUEST_CODE_ADDRESS_ADD);
    }

    public static void startForEdit(Context context, AddressData newAddress) {
        Intent starter = new Intent(context, EditAddressActivity.class);
        starter.putExtra(EXTRA_ADDRESS_DATA, newAddress);
        JumpUtil.startActivityForResult(context, starter, RequestCode.REQUEST_CODE_ADDRESS_EDIT);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_edit_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mAddressData = (AddressData) getIntent().getSerializableExtra(EXTRA_ADDRESS_DATA);
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerEditAddressComponent.builder().appComponent(appComponent)
                .editAddressModule(new EditAddressModule(this))
                .build().inject(this);
    }

    @OnClick({R.id.new_address_address_tv, R.id.detail_clear_iv, R.id.new_address_btn})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_address_address_tv:
                /*SoftInputUtil.hideSoftInput(this);
                mPresenter.showSelector();*/
                if (mPresenter.getCityListData() == null) {
                    Toaster.showShort(this,"获取城市信息中...");
                    break;
                }
                Intent intent = new Intent(this, SelectedAddressActivity.class);
                intent.putExtra(SelectedAddressActivity.DATA, mPresenter.getCityListData());
                intent.putExtra(SelectedAddressActivity.GRADE, 0);
                startActivity(intent);
                break;
            case R.id.detail_clear_iv:
                mAddressDetailEdit.setText("");
                clearIv.setVisibility(View.INVISIBLE);
                break;
            case R.id.new_address_btn:
                mPresenter.doRequest();
                break;
        }

    }

    @OnTextChanged(value = R.id.new_address_address_detail_tv, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onTextChanged(CharSequence text) {
        if (text.length() > 0) {
            clearIv.setVisibility(View.VISIBLE);
        } else {
            clearIv.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mTitleBar.setDefLeftImgClickListener(this);
        //新建地址
        if (mAddressData == null) {
            mTitleBar.setTitleTxt(R.string.address_edit_new_title);
            //编辑地址
        } else {
            mTitleBar.setTitleTxt(R.string.address_edit_title);
            mUserNameEdit.setText(mAddressData.getOrderuserName());
            mPhoneEdit.setText(mAddressData.getOrderuserPhone());
            mAddressTv.setText(mAddressData.getOrderuserAddress());
            mAddressDetailEdit.setText(mAddressData.getOrderuserDetailAddress());
            mPostCodeTv.setText(mAddressData.getPostCode());
        }
    }

    @Override
    public Map<String, Object> getParamsMap() {
        String name = mUserNameEdit.getText().toString();
        String phone = mPhoneEdit.getText().toString();
        String address = mAddressTv.getText().toString();
        String addressDetail = mAddressDetailEdit.getText().toString();
        String postCode = mPostCodeTv.getText().toString();
        if (mAddressData != null) {
            newAddress = (AddressData) mAddressData.clone();
            newAddress.setOrderuserName(name);
            newAddress.setOrderuserPhone(phone);
            newAddress.setOrderuserAddress(address);
            newAddress.setOrderuserDetailAddress(addressDetail);
            newAddress.setPostCode(postCode);
        }

        if (StringUtil.isEmpty(name, phone, address, addressDetail, postCode)) {
            return null;
        } else if (!StringUtil.isMobileNumber(phone)) {
            Toaster.showShort(getContext(), "请填写正确的手机号");
            return null;
        } else if (!StringUtil.isZipNO(postCode)) {
            Toaster.showShort(getContext(), "请填写正确的邮编");
            return null;
        } else if (mAddressData != null && mAddressData == newAddress) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        if (mAddressData != null) {
            map.put("addressId", mAddressData.getId());
            map.put("isDefault", mAddressData.getIsDefaultInt());
        }
        map.put("userId", GlobalData.getInstance().getLoginData().getId());
        map.put("orderuserName", name);
        map.put("orderuserPhone", phone);
        map.put("orderuserAddress", address);
        map.put("orderuserDetailAddress", addressDetail);
        map.put("postCode", postCode);
        if (mPresenter.getCityId() == 0){
            map.put("area_id",mAddressData.getAreaId());
        }else {
            map.put("area_id",mPresenter.getCityId());
        }

        return map;
    }

    @Override
    public void setDetailAddress(String s) {
        mAddressTv.setText(s);
    }

    @Override
    public boolean isAddNewAddress() {
        return mAddressData == null ? true : false;
    }

    @Override
    public AddressData getNewAddressData() {
        return newAddress;
    }
}
