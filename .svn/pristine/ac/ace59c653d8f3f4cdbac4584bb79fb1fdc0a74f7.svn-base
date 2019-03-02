package com.a1magway.bgg.p.productReturn;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.StringUtil;
import com.bumptech.glide.Glide;

import butterknife.BindView;


/**
 * Created by lm on 2018/8/30.
 */
public class PhotoVH extends BaseRecyclerVH<String> {
    @BindView(R.id.select_photo)
    ImageView selectPhoto;
    private Context context;

    public PhotoVH(@NonNull ViewGroup parent, int layoutResId) {
        super(parent, R.layout.item_photo_layout);
        context=parent.getContext();
    }


    private PhotoSelectadapter.ItemOperationListener mItemOperationListener;


    public PhotoVH setItemOperationListener(PhotoSelectadapter.ItemOperationListener itemOperationListener) {
        mItemOperationListener = itemOperationListener;
        return this;
    }

    public void showViewContent(String url, final PhotoSelectadapter returnProductListdapter, final int position) {

        if (!StringUtil.isEmpty(url)) {
            ImageLoaderUtil.displayImage(selectPhoto,AppConfig.BASE_URL+url);
//            ImageLoaderUtil.displayImage();
//            Glide.with(context).load(AppConfig.BASE_URL+url).into(selectPhoto);
            selectPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    mItemOperationListener.onItemClickSelectChange();
                }
            });
        }


    }
}
