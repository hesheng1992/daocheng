package com.a1magway.bgg.common.shre;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.a1magway.bgg.eventbus.event.ShardImageDownloadCallBack;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.DownloadShardImageUtils;
import com.a1magway.bgg.v.search.IFilterCateV;
import com.almagway.common.utils.ToastUtil;
import com.tencent.mm.opensdk.modelmsg.WXEmojiObject;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.zxy.tiny.Tiny;
import com.zxy.tiny.callback.BitmapCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/6/12.
 */

public class WXShareTools {

    private int mShareSize = 0;

    private List<File> fileList = new Vector<>();

    private String qrCodeImagePath;

    private List<String> shardImageUrlList;

    private WXShareTools() {
    }

    static class Holder {
        public static final WXShareTools wxShareTools = new WXShareTools();
    }

    public static WXShareTools getInstance() {
        return Holder.wxShareTools;
    }

    private ProgressDialog progressDialog;
    private Context context;
    private int flag;
    private String Kdescription;
    private String shardAddHeadImageUrl;
    private ShareData mShareData;
    private boolean shard_invitationCode;
    private String goodsDescription;
    private List<String> downFailImageList = new Vector<>();//保存下载失败的图片的url


    public void shareImags(ShareData mShareData, final Context context, final int flag, final String Kdescription,
                           boolean shard_invitationCode, String goodsDescription) {
        if (flag == 2) {
            if (!ShareUtils.uninstallSoftware(context, "com.sina.weibo")) {
                ToastUtil.showShort("微博未安装");
                return;
            }
        } else {
            if (!ShareUtils.isWeixinAvilible(context)) {
                ToastUtil.showShort("微信未安装");
                return;
            }
        }
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("请稍后...");
        progressDialog.show();

        this.mShareData = mShareData;
        this.context = context;
        this.flag = flag;
        this.Kdescription = Kdescription;
        this.shardAddHeadImageUrl = mShareData.getShardAddHeadImageUrl();
        this.qrCodeImagePath = mShareData.getShardQRcodeImageUrl();
        this.shard_invitationCode = shard_invitationCode;
        this.goodsDescription = goodsDescription;
        downFailImageList.clear();
        shardImageUrlList = new ArrayList<>();
        shardImageUrlList.addAll(mShareData.getMediaPath());
        if (!this.shard_invitationCode) {
            //来自商品详情页面才有这两张图片
            shardImageUrlList.add(shardAddHeadImageUrl);
            shardImageUrlList.add(qrCodeImagePath);
        }
        mShareSize = shardImageUrlList.size();
        fileList.clear();
        for (String url : shardImageUrlList) {
            File file = new File(DownloadShardImageUtils.getFileName(url));
            if (file.exists()) {
                fileList.add(file);
            } else {
                Observable.just(url)
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                DownloadShardImageUtils.saveSingleImageToSdCard(s);
                            }
                        });
            }
        }
        if (fileList.size() == mShareSize) {
            //分享图片
            dimissDialog();
            if (this.shard_invitationCode) {
                //来自分享邀请码页面
                startShare(context, fileList, flag, Kdescription);
            } else {
                //来自商品详情页面
                ActivityIntentUtil.toShareImageSelectedActivity(context, this.mShareData, flag, goodsDescription, downFailImageList.size());
            }
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ShardImageDownloadCallBack shardImageDownloadFinish) {
        synchronized (this) {
            if (!shardImageDownloadFinish.isDownLoadSucceed()) {
                downFailImageList.add(shardImageDownloadFinish.getImgUrl());
            }
            fileList.clear();
            for (String url : shardImageUrlList) {
                File file = new File(DownloadShardImageUtils.getFileName(url));
                if (file.exists() || isDownFailImage(url)) {
                    if (file.exists()) {
                        fileList.add(file);
                    }
                    //检测图片是否下载
                    if (fileList.size() + downFailImageList.size() == mShareSize) {
                        dimissDialog();
                        if (shard_invitationCode) {
                            startShare(context, fileList, flag, Kdescription);
                        } else {
                            ActivityIntentUtil.toShareImageSelectedActivity(context, this.mShareData, flag, goodsDescription, downFailImageList.size());
                        }
                    }
                }
            }
        }
    }

    //判断这个url是否下载失败
    private boolean isDownFailImage(String url) {
        for (String downFailUrl : downFailImageList) {
            if (downFailUrl.equals(url)) {
                return true;
            }
        }
        return false;
    }

    public static void startShare(Context context, List<File> fileList, int flag, String Kdescription) {
        Intent intent = new Intent();
        ComponentName comp = null;
        if (flag == 0) {
            comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        } else if (flag == 1) {
            comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.putExtra("Kdescription", Kdescription);
        } else if (flag == 2) {
            intent.setPackage("com.sina.weibo");
        }
        if (comp != null) {
            intent.setComponent(comp);
        }
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image");

        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (File f : fileList) {
            if (null != f && f.exists()) {
                imageUris.add(Uri.fromFile(f));
            }
        }
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        context.startActivity(intent);
    }

    private void startShare(Context context, int flag, String Kdescription) {
        Intent intent = new Intent();
        ComponentName comp = null;
        if (flag == 0) {
            comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        } else if (flag == 1) {
            comp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
            intent.putExtra("Kdescription", Kdescription);
        } else if (flag == 2) {
            intent.setPackage("com.sina.weibo");
        }
        if (comp != null) {
            intent.setComponent(comp);
        }
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image");

        ArrayList<Uri> imageUris = new ArrayList<Uri>();
        for (File f : fileList) {
            if (null != f && f.exists()) {
                imageUris.add(Uri.fromFile(f));
            }
        }
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
        context.startActivity(intent);

        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog.dismiss();
        }
    }

    private void dimissDialog() {
        if (progressDialog != null) {
            progressDialog.cancel();
            progressDialog.dismiss();
        }
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
