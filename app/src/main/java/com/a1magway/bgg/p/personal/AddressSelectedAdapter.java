package com.a1magway.bgg.p.personal;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.AddressSelected;

import java.util.List;

/**
 * Created by enid on 2018/7/21.
 */

public class AddressSelectedAdapter extends RecyclerView.Adapter<AddressSelectedAdapter.ViewHold> implements
        View.OnClickListener {
    private List<AddressSelected> listData;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public AddressSelectedAdapter(Context context, List<AddressSelected> listData) {
        this.context = context;
        this.listData = listData;
    }

    public void addItemListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<AddressSelected> getData(){
        return listData;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_address_checked, parent, false);
        layout.setOnClickListener(this);
        return new ViewHold(layout);
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.itemView.setTag(position);
        holder.tv.setText(listData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick((Integer) v.getTag());
        }
    }

    class ViewHold extends RecyclerView.ViewHolder {
        private TextView tv;

        ViewHold(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_address);
        }
    }

   public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
