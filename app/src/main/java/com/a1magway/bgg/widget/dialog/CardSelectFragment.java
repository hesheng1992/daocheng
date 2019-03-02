package com.a1magway.bgg.widget.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.shre.ShareData;
import com.a1magway.bgg.data.entity.AccountBankCardData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/6/12.
 */

public class CardSelectFragment extends BottomDialogFragment {

    @BindView(R.id.dialog_share_recycler)
    RecyclerView mRecyclerView;

    private static final String CARDS_EXTRA_NAME = "card_extra_name";
    private ShareData mShareData;

    private List<AccountBankCardData> mData;

    @OnClick(R.id.dialog_share_cancel_tv)
    public void onClickEvent(View view) {
        dismiss();
    }

    private MyAdapter mAdapter;

    private CardSelectListener mCardSelectListener;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.dialog_card_select;
    }

    public static CardSelectFragment newInstance(ArrayList<AccountBankCardData> data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(CARDS_EXTRA_NAME, data);
        CardSelectFragment fragment = new CardSelectFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mShareData = (ShareData) getArguments().getSerializable(CARDS_EXTRA_NAME);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


    class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog_card_select, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mCardTxt.setText(mData.get(position).getAccountNumber());

        }

        @Override
        public int getItemCount() {
            return mData == null ? 0 : mData.size();
        }
    }

    class ShareItemOnClickListener implements View.OnClickListener {
        private ViewHolder holder;
        private int position;

        public ShareItemOnClickListener(ViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            if (mCardSelectListener != null) {
                mCardSelectListener.onSelect(mData.get(position));
            }
            dismiss();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mCardTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardTxt = itemView.findViewById(R.id.item_dialog_card_select_txt);
        }
    }

    public interface CardSelectListener {
        void onSelect(AccountBankCardData cardData);
    }

    public void setCardSelectListener(CardSelectListener listener) {
        this.mCardSelectListener = listener;
    }
}
