package com.a1magway.bgg.widget.banner;

import android.content.Context;
import android.widget.ImageView;

import com.a1magway.bgg.util.ImageLoaderUtil;
import com.youth.banner.loader.ImageLoader;

/**
 * banner的图片加载器
 * Created by jph on 2017/7/24.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        if (path == null) {
            return;
        }
        ImageLoaderUtil.displayBannerImage(imageView, path);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView img = new ImageView(context);
        img.setScaleType(ImageView.ScaleType.CENTER);
        return img;
    }
}
