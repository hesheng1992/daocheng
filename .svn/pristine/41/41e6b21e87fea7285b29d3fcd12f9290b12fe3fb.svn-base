package com.a1magway.bgg.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.okhttp.LogUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;


import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by jph on 2017/7/25.
 */
public class ImageLoaderUtil {

    public static void displayImage(@NonNull ImageView img, @Nullable final Object loadUri) {
       /* GlideApp.with(img.getContext())
                .load(loadUri)
                .placeholder(R.drawable.default_icon)
                .error(R.drawable.default_icon)
                .into(img);*/

        RequestOptions requestOptions = new RequestOptions()
                .dontAnimate()
                .override(img.getWidth(), img.getHeight())
                .placeholder(R.drawable.default_icon)
                .error(R.drawable.default_icon);
        Glide.with(img.getContext()).load(loadUri).transition(withCrossFade()).apply(requestOptions).into(img);
    }


    //宽固定，高度自适应，scaleType要为fitCenter，
    public static void displayImageFixWith(@NonNull final ImageView img, @Nullable final Object loadUri, final LoadCompleteListener listener) {
        final RequestOptions requestOptions = new RequestOptions()
                .dontAnimate()
                .override(img.getWidth(), img.getHeight())
                .placeholder(R.drawable.default_icon)
                .error(R.drawable.default_icon);
        final ViewTreeObserver viewTreeObserver = img.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                img.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Glide.with(img.getContext()).load(loadUri).transition(withCrossFade()).apply(requestOptions)
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                int imgWidth = img.getWidth();
                                double resourceWidth = resource.getIntrinsicWidth();
                                double resourceHeight = resource.getIntrinsicHeight();
                                int newImgHeight = (int) ((resourceHeight / resourceWidth) * imgWidth);
                                ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                                layoutParams.width = imgWidth;
                                layoutParams.height = newImgHeight;
                                img.setLayoutParams(layoutParams);
                                img.setImageDrawable(resource);
                                if (listener != null) {
                                    listener.imgLoadComplete();
                                }
                            }
                        });
            }
        });

    }


    //分类页面加载图片
    public static void displayImage(@NonNull ImageView img, @Nullable final Object loadUri, int placeholderId) {
        RequestOptions requestOptions = new RequestOptions()
                .dontAnimate()
                .override(img.getWidth(), img.getHeight())
                .placeholder(placeholderId)
                .priority(Priority.HIGH)
                .error(placeholderId);
        Glide.with(img.getContext()).load(loadUri).transition(withCrossFade()).apply(requestOptions).into(img);
    }


    public static void displayBannerImage(@NonNull final ImageView img, @Nullable final Object loadUri) {
        RequestOptions requestOptions = new RequestOptions()
                .dontAnimate()
                .override(img.getWidth(), img.getHeight())
                .placeholder(R.drawable.default_icon)//into(new SimpleTarget)这里设置的占位图无效
                .error(R.drawable.default_icon);
        //设置默认图片
        img.setImageResource(R.drawable.default_icon);
        Glide.with(img.getContext()).load(loadUri)
                .transition(withCrossFade())
                .apply(requestOptions)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        img.setImageDrawable(resource);
                        Observable.just(resource)
                                .subscribeOn(Schedulers.io())
                                .subscribe(new Consumer<Drawable>() {
                                    @Override
                                    public void accept(Drawable drawable) throws Exception {
                                        DrawableToFile.drawableToFile(drawable, DownloadShardImageUtils.getFileName((String) loadUri));
                                    }
                                });

                    }
                });
    }

    public interface LoadCompleteListener {
        void imgLoadComplete();
    }

    public static void loadCircleImage(Context context, ImageView iv, Object model) {
        RequestOptions requestOptions = new RequestOptions()
                .transform(new CircleCrop())
                .override(iv.getWidth(), iv.getHeight());
        Glide.with(context).load(model).apply(requestOptions)
                .into(iv);
    }


    public static File getCacheFile(final Context context, String url) {
        //需在子线程中调用，否则会报错
        FutureTarget<File> future = Glide.with(context)
                .load(url)
                .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
        File file = null;
        try {
            file = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        LogUtil.e("getGlideFile", file == null ? "null" : file.getAbsolutePath());
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(DownloadShardImageUtils.getFileName(url));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            try {
                fileOutputStream.flush();
                fileOutputStream.close();
                bitmap.recycle();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new File(DownloadShardImageUtils.getFileName(url));
    }


}
