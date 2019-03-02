package com.a1magway.bgg.widget.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by jph on 2017/7/24.
 */
public class BannerView extends Banner {
    public BannerView(Context context) {
        super(context);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public Banner setImages(List<?> imageUrls) {
        setImageLoader(new GlideImageLoader());
        return super.setImages(imageUrls);
    }
}
