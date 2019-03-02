package com.a1magway.bgg.p.member;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.GlideApp;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.Permission;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import butterknife.BindView;

/**
 * author: Beaven
 * date: 2017/10/13 15:15
 */

public class MemberUpgradeInfoViewBinder
    extends BaseRecyclerViewBinder<Permission, BaseRecyclerVH<Permission>> {

    @NonNull
    @Override
    protected BaseRecyclerVH<Permission> onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberUpgradeDefaultVH(parent);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull BaseRecyclerVH<Permission> holder, @NonNull Permission item) {
        holder.showViewContent(item);
    }


    public static class MemberUpgradeDefaultVH extends BaseRecyclerVH<Permission> {

        @BindView(R.id.image_info)
        ImageView imageView;


        MemberUpgradeDefaultVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_member_upgrade_info_default);
        }


        @Override
        public void showViewContent(Permission permission) {
            super.showViewContent(permission);
            GlideApp.with(getContext())
                .load(permission.getImagePath())
                .into(imageView);
        }
    }
}
