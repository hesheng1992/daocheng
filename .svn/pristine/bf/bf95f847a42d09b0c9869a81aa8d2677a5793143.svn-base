package com.a1magway.bgg.p.share;

import android.annotation.SuppressLint;
import android.content.Context;
import android.icu.util.ValueIterator;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.ShareImageSelectedData;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.util.DensityUtils;
import com.a1magway.bgg.util.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by enid on 2018/8/7.
 */

public class SelectShareImageAdapter extends RecyclerView.Adapter<SelectShareImageAdapter.ViewHold>
        implements View.OnClickListener {
    private List<ShareImageSelectedData> data;
    private Context context;
    private OnItemClickListener listener;
    private int height;

    public SelectShareImageAdapter(Context context, List<ShareImageSelectedData> data) {
        this.context = context;
        this.data = data;
        height = (AndroidUtil.getScreenWidth(context) - DensityUtils.dip2px(context, 40)) / 3;
    }

    public List<ShareImageSelectedData> getData() {
        return data;
    }

    public int getSelectCount() {
        int count = 0;
        for (ShareImageSelectedData shareImageSelectedData : data) {
            if (shareImageSelectedData.isChecked()) {
                count++;
            }
        }
        return count;
    }

    public List<String> getSelectImageUrls() {
        List<String> shareUrlList = new ArrayList<>();
        for (ShareImageSelectedData shareImageSelectedData : data) {
            if (shareImageSelectedData.isChecked()) {
                shareUrlList.add(shareImageSelectedData.getFilePath());
            }
        }
        return shareUrlList;
    }


    public void addOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(context).inflate(R.layout.item_select_share_img, parent, false);
        return new ViewHold(layout);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(ViewHold holder, int position) {
        holder.itemView.setTag(position);
        holder.checkableImageButton.setTag(position);
        ImageLoaderUtil.displayImage(holder.img, data.get(position).getFilePath());
        if (data.get(position).isChecked()) {
            holder.checkableImageButton.setChecked(true);
        } else {
            holder.checkableImageButton.setChecked(false);
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

        @SuppressLint("RestrictedApi")
        ViewHold(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            checkableImageButton = itemView.findViewById(R.id.checkableImageButton);
            checkableImageButton.setChecked(false);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            params.height = height;
            itemView.setLayoutParams(params);
            RelativeLayout.LayoutParams img_params = (RelativeLayout.LayoutParams) img.getLayoutParams();
            img_params.height = height;
            img.setLayoutParams(img_params);
            itemView.setOnClickListener(SelectShareImageAdapter.this);
            checkableImageButton.setOnClickListener(SelectShareImageAdapter.this);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
