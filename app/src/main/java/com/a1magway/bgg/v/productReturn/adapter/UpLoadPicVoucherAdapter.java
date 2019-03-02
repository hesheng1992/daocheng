package com.a1magway.bgg.v.productReturn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.GlideApp;
import com.a1magway.bgg.GlideRequests;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enid on 2018/9/4.
 *
 * 上传申请退款退货凭证  list需要传入一个默认的值为“1”
 */
public class UpLoadPicVoucherAdapter extends BaseRecyclerAdapter<UpLoadPicVoucherAdapter.ViewHold,String> {
    private Context context;
    private OnClickImageAdd onClickImageAdd;
    private int picNum;//照片数量
    private int defoufltPic=R.drawable.icon_add_pic;//默认图
    public UpLoadPicVoucherAdapter(List<String> list, Context context) {
        super(list);
        this.context=context;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }

    public int getDefoufltPic() {
        return defoufltPic;
    }

    public void setDefoufltPic(int defoufltPic) {
        this.defoufltPic = defoufltPic;
    }

    public void setOnClickImageAdd(OnClickImageAdd onClickImageAdd){
        if (onClickImageAdd!=null){
            this.onClickImageAdd=onClickImageAdd;
        }
    }

    @Override
    public UpLoadPicVoucherAdapter.ViewHold onRealCreateViewHolder(ViewGroup parent, int viewType) {
        return new UpLoadPicVoucherAdapter.ViewHold(LayoutInflater.from(context)
                .inflate(R.layout.item_upload_image,parent,false));
    }

    @Override
    public void onRealBindViewHolder(ViewHold holder, final int position) {
        super.onRealBindViewHolder(holder, position);
        final String s=getList().get(position);
        //默认图
        if (s.equals("1")){
            holder.imageView.setImageResource(defoufltPic);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickImageAdd.onClickImageChange(s,position);
                }
            });
            holder.image_delete.setVisibility(View.GONE);
        }else{
            ImageLoaderUtil.displayImage(holder.imageView,s);
            holder.imageView.setClickable(false);
            holder.image_delete.setVisibility(View.VISIBLE);
        }
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickImageAdd.deleteImage(s,position);
            }
        });
    }

    @Override
    public void addItem(String item) {
        if (getList().size()<getPicNum()){
            if (getList().size()>0){
                getList().add(getList().size()-1,item);
            }
        }else if (getList().size()==getPicNum()){
            if (getList().contains("1")){
                getList().remove(getList().size()-1);
                getList().add(item);
            }
        }
        notifyDataSetChanged();
    }

    public interface OnClickImageAdd{
        void onClickImageChange(String urlImage,int postion);
        void deleteImage(String urlImage,int postion);
    }

    static class ViewHold extends RecyclerView.ViewHolder{
        ImageView imageView;
        ImageView image_delete;
        public ViewHold(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            image_delete=itemView.findViewById(R.id.image_delete);
        }
    }
}
