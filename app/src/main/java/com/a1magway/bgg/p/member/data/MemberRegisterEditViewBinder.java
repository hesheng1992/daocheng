package com.a1magway.bgg.p.member.data;

import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.common.listener.BaseEditTextWatchListener;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import butterknife.BindView;

/**
 * author: Beaven
 * date: 2017/10/16 18:45
 */

public class MemberRegisterEditViewBinder extends BaseRecyclerViewBinder<MemberRegisterEdit, MemberRegisterEditViewBinder.ViewHolder> {

    private ItemEditClickListener clickListener;

    public MemberRegisterEditViewBinder(ItemEditClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(parent, new MyCustomEditTextListener(clickListener));
    }

    @Override
    protected void onBindViewHolder(@NonNull final ViewHolder holder, @NonNull final MemberRegisterEdit item) {
        boolean isLayoutEnable = item.isLayoutEnable();
        int colorId = isLayoutEnable ? R.color.white : R.color.bg_page;
        holder.layoutRoot.setBackgroundColor(holder.getContext().getResources().getColor(colorId));
        holder.editText.setEnabled(isLayoutEnable);
        holder.textLeftTitle.setText(item.getTitle());
        holder.editText.setHint(item.getEditHint());
        holder.editText.setFocusable(item.isEditEnable());
        holder.editText.setFocusableInTouchMode(item.isEditEnable());
        holder.myCustomEditTextListener.updatePosition(getPosition(holder));
        holder.editText.setText(item.getText());
        holder.editText.setTag(getPosition(holder));
        holder.textRightTitle.setVisibility(item.isShowRightButton() ? View.VISIBLE : View.GONE);
        if (!item.isEditEnable()) {
            holder.editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.editClick(getPosition(holder), item);
                }
            });
        } else {
            holder.editText.setOnClickListener(null);
        }
        holder.textRightTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.saveClick(getPosition(holder), item);
            }
        });
    }

    static class ViewHolder extends BaseRecyclerVH {

        @BindView(R.id.layout_member_title)
        LinearLayout layoutRoot;
        @BindView(R.id.text_item_title)
        TextView textLeftTitle;
        @BindView(R.id.text_item_save)
        TextView textRightTitle;
        @BindView(R.id.edit_member_item)
        EditText editText;
        MyCustomEditTextListener myCustomEditTextListener;

        ViewHolder(@NonNull ViewGroup parent, MyCustomEditTextListener listener) {
            super(parent, R.layout.member_register_item_normal);
            editText.addTextChangedListener(listener);
            this.myCustomEditTextListener = listener;
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    return true;
                }
            });
        }
    }

    private static class MyCustomEditTextListener extends BaseEditTextWatchListener {

        private ItemEditClickListener clickListener;

        MyCustomEditTextListener(ItemEditClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            clickListener.editWatch(getPosition(), charSequence.toString());
        }
    }

    public interface ItemEditClickListener {

        void editClick(int position, MemberRegisterEdit item);

        void saveClick(int position, MemberRegisterEdit item);

        void editWatch(int position, String value);
    }
}
