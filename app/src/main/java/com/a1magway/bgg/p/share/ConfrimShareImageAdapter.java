package com.a1magway.bgg.p.share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.ShareImageSelectedData;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.util.DensityUtils;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.v.share.ShareImageConfirmActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by enid on 2018/8/7.
 */

public class ConfrimShareImageAdapter extends RecyclerView.Adapter<ConfrimShareImageAdapter.ViewHold>
        implements View.OnClickListener {
    private List<String> data;
    private Context context;
    private OnItemClickListener listener;
    private int height;

    public ConfrimShareImageAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        height = (AndroidUtil.getScreenWidth(context) - DensityUtils.dip2px(context, 40)) / 2;
    }

    public List<String> getShareData() {
        List<String> list = new ArrayList<>();
        for (String s : data) {
            if (!s.equals(ShareImageConfirmActivity.ADD_IMAGE)) {
                list.add(s);
            }
        }
        return list;
    }


    public void addOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_select_share_img, parent, false);
        layout.setOnClickListener(this);
        return new ViewHold(layout);
    }

    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.itemView.setTag(position);
        if (data.get(position).equals(ShareImageConfirmActivity.ADD_IMAGE)) {
            ImageLoaderUtil.displayImage(holder.img, R.drawable.add_share_img);
        } else {
            ImageLoaderUtil.displayImage(holder.img, data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick((Integer) v.getTag());
        }
    }


    class ViewHold extends RecyclerView.ViewHolder {
        ImageView img;
        CheckableImageButton checkableImageButton;

        ViewHold(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            checkableImageButton = itemView.findViewById(R.id.checkableImageButton);
            checkableImageButton.setVisibility(View.GONE);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.height = height;
            itemView.setLayoutParams(params);
            RelativeLayout.LayoutParams img_params = (RelativeLayout.LayoutParams) img.getLayoutParams();
            img_params.height = height;
            img.setLayoutParams(img_params);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
