package com.a1magway.bgg.p.member;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.MemberUpgradeInfo;
import com.a1magway.bgg.data.entity.Permission;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: Beaven
 * date: 2017/10/13 16:45
 */

public class MemberUpgradeHeadViewBinder
    extends BaseRecyclerViewBinder<MemberUpgradeInfo, MemberUpgradeHeadViewBinder.MemberUpgradeHeadVH> {

    @NonNull
    @Override
    protected MemberUpgradeHeadVH onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberUpgradeHeadVH(parent);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull MemberUpgradeHeadVH holder, @NonNull MemberUpgradeInfo item) {
        holder.showViewContent(item);
    }


    static class MemberUpgradeHeadVH extends BaseRecyclerVH<MemberUpgradeInfo> {

        @BindView(R.id.recycler_member_upgrade_info)
        RecyclerView recyclerView;


        MemberUpgradeHeadVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.layout_member_upgrade_head);
        }


        @Override
        public void showViewContent(MemberUpgradeInfo memberUpgradeInfo) {
            super.showViewContent(memberUpgradeInfo);
            List<String> iconPathList = new ArrayList<>();
            List<Permission> permissions = memberUpgradeInfo.getPermission();
            if (permissions == null) return;

            for (Permission per : permissions) {
                iconPathList.add(per.getIconPath());
            }
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            recyclerView.setAdapter(new MemberUpgradeHeadItemAdapter(iconPathList));
        }
    }
}
