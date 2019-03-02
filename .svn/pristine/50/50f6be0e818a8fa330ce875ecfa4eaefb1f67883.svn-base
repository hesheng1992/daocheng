package com.a1magway.bgg.p.personal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.repository.personalcenterdata.IAddressData;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.personal.AddressManagerActivity;
import com.a1magway.bgg.v.personal.EditAddressActivity;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lyx on 2017/8/12.
 */
public class AddressVH extends BaseRecyclerVH<AddressData> {
    private IAddressData mIAddressData;
    @BindView(R.id.address_item_name_tv)
    TextView mNameTv;

    @BindView(R.id.address_item_address_tv)
    TextView mAddressTv;

    @BindView(R.id.address_item_address_detail_tv)
    TextView mAddressDetailTv;

    @BindView(R.id.address_item_post_code_tv)
    TextView mPostCodeTv;

    @BindView(R.id.address_item_edit_tv)
    TextView mEditTv;

    @BindView(R.id.address_item_delete_tv)
    TextView mDeleteTv;

    @BindView(R.id.address_item_default_box)
    CheckBox mCheckBox;

    public AddressVH(ViewGroup parent, IAddressData iAddressData) {
        super(parent, R.layout.address_item_list_address);
        mIAddressData = iAddressData;
        Log.d("enid", "AddressVH execute");
    }

    public void showViewContent(final AddressData addressData, final int position, final AddressAdapter adapter) {
        super.showViewContent(addressData);
        mNameTv.setText(getContext().getString(R.string.address_name, addressData.getOrderuserName(), addressData.getOrderuserPhone()));
        mAddressTv.setText(addressData.getOrderuserAddress());
        mAddressDetailTv.setText(addressData.getOrderuserDetailAddress());
        mPostCodeTv.setText(addressData.getPostCode());
        mCheckBox.setChecked(addressData.getIsDefault());
        //默认地址更新
        if (addressData.getIsDefault()) {
            LoginData loginData = GlobalData.getInstance().getLoginData();
            loginData.setDefaultAddress(addressData);
            GlobalData.getInstance().setLoginData(loginData);
        }
        if (addressData.getIsDefault()) {
            mCheckBox.setClickable(false);
        } else {
            mCheckBox.setClickable(true);
        }

        mDeleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setMessage(getContext().getString(R.string.address_delete_tip))
                        .setPositiveButton(getContext().getString(R.string.common_ensure), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ((AddressManagerActivity) getContext()).showLoadingDialog();
                                mIAddressData.deleteAddress(GlobalData.getInstance().getLoginData().getId(), addressData.getId())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribeOn(Schedulers.io())
                                        .subscribe(new BaseObserver<APIResponse>(getContext()) {
                                            @Override
                                            public void onNext(@NonNull APIResponse apiResponse) {
                                                adapter.removeItem(position);
                                                adapter.notifyDataSetChanged();
                                                if (addressData.getIsDefault()) {
                                                    LoginData loginData = GlobalData.getInstance().getLoginData();
                                                    if (adapter.getList() != null && adapter.getList().size() > 0) {
                                                        adapter.getList().get(0).setIsDefault(true);
                                                        loginData.setDefaultAddress(adapter.getList().get(0));
                                                    } else {
                                                        loginData.setDefaultAddress(null);
                                                    }
                                                    GlobalData.getInstance().setLoginData(loginData);
                                                }
                                                ((AddressManagerActivity) getContext()).hideLoadingDialog();
                                                Toaster.showShort(getContext(), apiResponse.getMsg());
                                            }

                                            @Override
                                            public void onComplete() {
//                                                adapter.removeItem(position);
                                                adapter.notifyDataSetChanged();
                                                ((AddressManagerActivity) getContext()).hideLoadingDialog();
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                super.onError(e);
                                                ((AddressManagerActivity) getContext()).hideLoadingDialog();
                                                Toaster.showShort(getContext(), "删除失败");
                                            }
                                        });
                            }
                        }).setNegativeButton(getContext().getString(R.string.common_cancel), null).show();

            }
        });
        mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBox.isChecked()) {
                    ((AddressManagerActivity) getContext()).showLoadingDialog();
                    mIAddressData.setDefaultAddress(GlobalData.getInstance().getLoginData().getId(), addressData.getId())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(new BaseObserver<APIResponse>(getContext()) {
                                @Override
                                public void onNext(@NonNull APIResponse apiResponse) {
                                    ((AddressManagerActivity) getContext()).hideLoadingDialog();
                                    adapter.getList().get(position).setIsDefault(true);
                                    //更新默认地址
                                    LoginData loginData = GlobalData.getInstance().getLoginData();
                                    loginData.setDefaultAddress(adapter.getList().get(position));
                                    GlobalData.getInstance().setLoginData(loginData);
                                    for (AddressData addressData1 : adapter.getList()) {
                                        if (addressData1.getIsDefault() && addressData1.getId() != adapter.getList().get(position).getId()) {
                                            addressData1.setIsDefault(false);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    Toaster.showShort(getContext(), apiResponse.getMsg());
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                } else {
                    mCheckBox.setChecked(true);
                    return;
                }
            }
        });

        mEditTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAddressActivity.startForEdit(getContext(), addressData);
            }
        });

    }
}
