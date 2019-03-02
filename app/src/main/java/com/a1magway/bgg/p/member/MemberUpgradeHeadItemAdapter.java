package com.a1magway.bgg.p.member;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.util.ImageLoaderUtil;

import java.util.List;

import butterknife.BindView;

/**
 * author: Beaven
 * date: 2017/10/16 10:03
 */

public class MemberUpgradeHeadItemAdapter
    extends BaseRecyclerAdapter<MemberUpgradeHeadItemAdapter.MemberUpgradeVH, String> {

    MemberUpgradeHeadItemAdapter(List<String> list) {
        super(list);
    }


    @Override
    public MemberUpgradeVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new MemberUpgradeVH(parent);
    }


    @Override
    public void onRealBindViewHolder(MemberUpgradeVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position));
    }


    public static class MemberUpgradeVH extends BaseRecyclerVH<String> {

        @BindView(R.id.image_item_member_upgrade_info)
        ImageView imageView;


        MemberUpgradeVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_member_upgrade_head);
        }


        @Override
        public void showViewContent(String s) {
            super.showViewContent(s);
            ImageLoaderUtil.displayImage(imageView, s);
        }
    }
}
