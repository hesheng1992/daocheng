package com.a1magway.bgg.p.personal;

import android.view.ViewGroup;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.repository.personalcenterdata.IAddressData;

import java.util.List;

/**
 * 地址管理adapter
 * Created by lyx on 2017/8/14.
 */
public class AddressAdapter extends BaseRecyclerAdapter<AddressVH, AddressData> {
    private IAddressData mIAddressData;

    public AddressAdapter(List<AddressData> list, IAddressData iAddressData) {
        super(list);
        mIAddressData = iAddressData;
    }

    @Override
    public AddressVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new AddressVH(parent, mIAddressData);
    }

    @Override
    public void onRealBindViewHolder(AddressVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position), position, this);
    }
}
