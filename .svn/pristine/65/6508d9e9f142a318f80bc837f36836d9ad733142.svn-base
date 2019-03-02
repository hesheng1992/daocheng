package com.a1magway.bgg.p.personal;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.repository.personalcenterdata.IAddressData;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.personal.IAddressV;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * 地址p
 * Created by lyx on 2017/8/12.
 */
public class AddressManagerP extends BaseLoadP<List<AddressData>, IAddressV> {

    public static final String EXTRA_ADDRESS_DATA = "addressData";

    IAddressData mIAddressData;

    AddressAdapter mAddressAdapter;

    int index = -1;

    @Inject
    public AddressManagerP(IAddressV view, IAddressData iAddressData, final AddressAdapter adapter) {
        super(view);
        this.mIAddressData = iAddressData;
        mAddressAdapter = adapter;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mView.setRecyclerViewAdapter(mAddressAdapter);
        mAddressAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (mView.needAddressData()) {
                    AddressData addressData = mAddressAdapter.getList().get(position);
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_ADDRESS_DATA, addressData);
                    mView.getActivity().setResult(Activity.RESULT_OK, intent);
                    mView.getActivity().finish();
                }
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        loadData();
    }

    public void reload() {
        index = -1;
        loadData(false);
    }

    @Nullable
    @Override
    public Observable<List<AddressData>> getDataObservable() {
        return mIAddressData.getAddressList(GlobalData.getInstance().getLoginData().getId(), index).defaultIfEmpty(new ArrayList<AddressData>());
    }

    @Override
    protected void onLoadSuccess(List<AddressData> addressDatas) {
        super.onLoadSuccess(addressDatas);
        if (index == -1) {
            mAddressAdapter.setList(addressDatas);
            checkShowNoneData(addressDatas);
        } else {
            mAddressAdapter.addList(addressDatas);
        }
        if (addressDatas != null && addressDatas.size() > 0) {
            index = addressDatas.get(addressDatas.size() - 1).getId();
        }
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        if (index > 0) {
            mView.stopLoadMore();
        }
    }

    public void changeAddressData(AddressData addressData) {
        List<AddressData> addressDataList = mAddressAdapter.getList();
        for (int i = 0; i < addressDataList.size(); i++) {
            if (addressDataList.get(i).getId() == addressData.getId()) {
                addressDataList.set(i, addressData);
                break;
            }
        }
        mAddressAdapter.notifyDataSetChanged();
    }
}
