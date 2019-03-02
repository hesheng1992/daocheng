package com.a1magway.bgg.p.member.data;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.v.web.WebActivity;
import com.almagway.common.recycler.BaseRecyclerViewBinder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Beaven
 * date: 2017/10/17 13:38
 */

public class MemberRegisterNotesViewBinder extends BaseRecyclerViewBinder<Boolean, MemberRegisterNotesViewBinder.MemberRegisterNotesVH> {

    private CompoundButton.OnCheckedChangeListener checkedChangeListener;

    public MemberRegisterNotesViewBinder(CompoundButton.OnCheckedChangeListener checkedChangeListener) {
        this.checkedChangeListener = checkedChangeListener;
    }

    @NonNull
    @Override
    protected MemberRegisterNotesVH onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new MemberRegisterNotesVH(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull final MemberRegisterNotesVH holder, @NonNull final Boolean item) {
        holder.textView.setText("我已阅读并且同意");
        holder.textGo.setText("注册协议");
        holder.textGo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        holder.textGo.setTextColor(holder.getContext().getResources().getColor(R.color.black));
        holder.checkBox.setChecked(item);
        holder.checkBox.setOnCheckedChangeListener(checkedChangeListener);
    }

    static class MemberRegisterNotesVH extends BaseRecyclerVH<Integer> {

        @BindView(R.id.text_register_notes)
        TextView textView;
        @BindView(R.id.text_register_notes_go)
        TextView textGo;
        @BindView(R.id.checkbox_register_notes)
        CheckBox checkBox;

        MemberRegisterNotesVH(@NonNull ViewGroup parent) {
            super(parent, R.layout.item_register_notes);
        }

        @OnClick(R.id.text_register_notes_go)
        public void clickGo(View view) {
            WebActivity.start(getContext(), "", "注册协议", false);
        }
    }
}
