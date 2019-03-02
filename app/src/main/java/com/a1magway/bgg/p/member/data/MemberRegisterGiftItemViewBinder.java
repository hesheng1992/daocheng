package com.a1magway.bgg.p.member.data;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import butterknife.BindView;

/**
 * author: Beaven
 * date: 2017/10/16 17:12
 */

public class MemberRegisterGiftItemViewBinder
    extends BaseRecyclerViewBinder<MemberRegisterGiftSelectWrapper, MemberRegisterGiftItemViewBinder.MemberUpgradeGiftVH> {

    private ItemGiftClickListener clickListener;


    public MemberRegisterGiftItemViewBinder(ItemGiftClickListener clickListener) {
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    protected MemberUpgradeGiftVH onCreateViewHolder(
        @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberUpgradeGiftVH(parent);
    }


    @Override
    protected void onBindViewHolder(
        @NonNull final MemberUpgradeGiftVH holder,
        @NonNull final MemberRegisterGiftSelectWrapper item) {
        holder.showViewContent(item.getMemberRegisterGift().getPicturePath());
        String title = holder.getContext().getString(R.string.gift) + item.getId();
        holder.textTitle.setText(title);
        holder.radioGift.setChecked(item.isSelect());
        int colorId = item.isSelect() ? android.R.color.transparent : R.color.white_50;
        holder.imageGift.setColorFilter(holder.getContext().getResources().getColor(colorId));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(getPosition(holder), item);
            }
        });
    }


    static class MemberUpgradeGiftVH extends BaseRecyclerVH<String> {

        @BindView(R.id.text_item_title)
        TextView textTitle;
        @BindView(R.id.radio_gift)
        RadioButton radioGift;
        @BindView(R.id.image_gift)
        ImageView imageGift;
        @BindView(R.id.layout_gift)
        LinearLayout layout;


        MemberUpgradeGiftVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_member_upgrade_gift);
        }


        @Override
        public void showViewContent(String s) {
            super.showViewContent(s);
            ImageLoaderUtil.displayImage(imageGift, s);
        }
    }


    public interface ItemGiftClickListener {
        void onClick(int position, MemberRegisterGiftSelectWrapper item);
    }
}
