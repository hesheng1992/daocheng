package com.a1magway.bgg.v.personal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerAddressComponent;
import com.a1magway.bgg.di.module.AddressModule;
import com.a1magway.bgg.p.personal.AddressManagerP;
import com.a1magway.bgg.p.personal.EditAddressP;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.widget.OldRefreshLayout;
import com.a1magway.bgg.widget.TitleBar;
import com.xxxrecylcerview.XXXRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.a1magway.bgg.util.code.RequestCode.REQUEST_CODE_ADDRESS_EDIT;
import static com.a1magway.bgg.util.code.RequestCode.REQUEST_CODE_ADDRESS_SELECT;

/**
 * 地址管理activity
 */
public class AddressManagerActivity extends PActivity<AddressManagerP> implements IAddressV {

    private static final String EXTRA_NEED_ADDRESS_DATA = "need";

    private static final int EDIT_ADD = 0;
    private static final int EDIT_EDIT = 1;

    private boolean needAddressData;

    @BindView(R.id.address_recycler)
    XXXRecyclerView mRecycleView;

    @BindView(R.id.address_layout_refresh)
    OldRefreshLayout mOldRefreshLayout;

    @BindView(R.id.address_titleBar)
    TitleBar mTitleBar;

    public static void startActivityForResult(Context context, boolean needAddress) {
        Intent starter = new Intent(context, AddressManagerActivity.class);
        starter.putExtra(EXTRA_NEED_ADDRESS_DATA, needAddress);
        JumpUtil.startActivityForResult(context, starter, REQUEST_CODE_ADDRESS_SELECT);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_address_manager;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerAddressComponent.builder().appComponent(appComponent)
                .addressModule(new AddressModule(this))
                .build().inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        needAddressData = getIntent().getBooleanExtra(EXTRA_NEED_ADDRESS_DATA, false);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setNullStatus(R.mipmap.null_address);
        mOldRefreshLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPresenter.reload();
            }
        });
        mRecycleView.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadData(false);
            }
        });
        mTitleBar.setDefLeftImgClickListener(this);

    }

    @Override
    public void setRecyclerViewAdapter(RecyclerView.Adapter adapter) {
        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycleView.setAdapter(adapter);
    }

    @OnClick(R.id.address_add_new_btn)
    void addNewAddress() {
        EditAddressActivity.startForAdd(AddressManagerActivity.this);
    }

    @Override
    public void stopRefresh() {
        if (mOldRefreshLayout != null){
            mOldRefreshLayout.refreshComplete();
        }
    }

    @Override
    public void setLoadable(boolean loadable) {
        mRecycleView.setLoadable(loadable);
    }

    @Override
    public void stopLoadMore() {
        if (mRecycleView != null){
            mRecycleView.stopLoadMore();
        }
    }

    @Override
    public boolean needAddressData() {
        return needAddressData;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RequestCode.REQUEST_CODE_ADDRESS_ADD:
                    mPresenter.loadData(false);
                    break;
                case REQUEST_CODE_ADDRESS_EDIT:
                    AddressData addressData = (AddressData) data.getSerializableExtra(EditAddressP.RESULT_EXTRA_ADDRESS_DATA);
                    if (addressData != null) {
                        mPresenter.changeAddressData(addressData);
                    }
                    break;
            }
        }
    }

    @Override
    public void showLoadingDialog() {
        showLoadingDialog(getString(R.string.dialog_wait_loading));
    }

    @Override
    public void hideLoadingDialog() {
        super.hideLoadingDialog();

    }
}
