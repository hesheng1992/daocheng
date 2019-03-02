package com.a1magway.bgg.p.member;

import android.support.annotation.NonNull;
import android.view.Gravity;
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
 * date: 2017/10/20 11:15
 */

public class MemberCardsAddViewBinder
    extends BaseRecyclerViewBinder<String, MemberCardsAddViewBinder.MemberCardsAddVH> {

    @NonNull
    @Override
    protected MemberCardsAddVH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberCardsAddVH(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull MemberCardsAddVH holder, @NonNull String item) {
        holder.showViewContent(item);
    }

    static class MemberCardsAddVH extends BaseRecyclerVH<String> {

        @BindView(R.id.image_layout_default)
        ImageView imageView;

        public MemberCardsAddVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_layout_image);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 150, 0, 150);
            params.gravity = Gravity.CENTER;
            imageView.setLayoutParams(params);
        }

        @Override
        public void showViewContent(String s) {
            super.showViewContent(s);
            imageView.setImageResource(R.drawable.ic_member_card_add);
        }
    }
}
