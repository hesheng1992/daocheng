package com.a1magway.bgg.p.member.data;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import butterknife.BindView;

/**
 * author: Beaven
 * date: 2017/10/17 13:38
 */

public class MemberRegisterGiftViewBinder extends BaseRecyclerViewBinder<Integer, MemberRegisterGiftViewBinder.MemberUpgradeGiftVH> {

    @NonNull
    @Override
    protected MemberUpgradeGiftVH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberUpgradeGiftVH(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull MemberUpgradeGiftVH holder, @NonNull Integer item) {
        holder.layout.setBackgroundColor(holder.getContext().getResources().getColor(R.color.member_upgrade_info_gift_bg));
        holder.imageView.setImageResource(item);
    }

    static class MemberUpgradeGiftVH extends BaseRecyclerVH<Integer> {

        @BindView(R.id.layout_image_default)
        LinearLayout layout;
        @BindView(R.id.image_layout_default)
        ImageView imageView;

        MemberUpgradeGiftVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_layout_image);
        }

    }
}
