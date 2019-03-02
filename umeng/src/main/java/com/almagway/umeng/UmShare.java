package com.almagway.umeng;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.almagway.umeng.bean.AuthBean;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMMin;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * author: Beaven
 * date: 2017/10/23 15:26
 */

public class UmShare {
    private Activity activity;
    private PlatformAuthListener mPlatformAuthListener;

    public UmShare(Activity activity) {
        this.activity = activity;
    }

    /**
     * 调用友盟分享，进行链接网页分享
     *
     * @param url         连接地址
     * @param title       标题
     * @param description 说明
     */
    public void open(String url, String title, String description) {
        UMWeb umWeb = new UMWeb(url);
        umWeb.setTitle(title);
        umWeb.setDescription(description);
        umWeb.setThumb(new UMImage(activity, R.drawable.ic_luncher));
        new ShareAction(activity)
                .setDisplayList(SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE)
                .withMedia(umWeb)
                .setCallback(shareListener)
                .open();
    }

    public void share(String url, String title, String description, int platformType) {
        UMWeb umWeb = new UMWeb(url);
        umWeb.setTitle(title);
        umWeb.setDescription(description);
        umWeb.setThumb(new UMImage(activity, R.drawable.ic_luncher));
        new ShareAction(activity)
                .setPlatform(PlatformType.getUmShareMedai(platformType))//传入平台
                .withMedia(umWeb)
                .setCallback(shareListener)//回调监听器
                .share();
    }

    public void openPic(String url, String title, String description, List<String> imageList) {
        UMWeb umWeb = new UMWeb(url);
        UMImage[] umImages = new UMImage[imageList.size()];
        UMImage umImage;
        for (int i = 0; i < imageList.size(); i++) {
            umImage = new UMImage(activity, imageList.get(i));
            umImages[i] = umImage;
        }
        umWeb.setTitle(title);
        umWeb.setDescription(description);
        umWeb.setThumb(new UMImage(activity, R.drawable.ic_luncher));
        new ShareAction(activity)
                .setPlatform(SHARE_MEDIA.SINA)//传入平台
                .withMedia(umWeb)
                .withMedias(umImages)
                .setCallback(shareListener)
                .share();


    }

    //微信分享现在只支持一张图
    public void shareWeixinCirle(List<String> imageList) {
        UMImage[] umImages = new UMImage[imageList.size()];
        UMImage umImage;
        for (int i = 0; i < imageList.size(); i++) {
            umImage = new UMImage(activity, imageList.get(i));
            umImages[i] = umImage;
        }
        new ShareAction(activity)
                .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)//传入平台
                .withMedias(umImages[0], umImages[1])
                .setCallback(shareListener)
                .share();
    }


    public void shareMini(String miniTitle, String miniImageUrl, String miniPath) {
        //兼容低版本的网页链接
        UMMin umMin = new UMMin(DefaultContent.url);
        if (miniTitle == null) {
            // 小程序消息封面图片
            umMin.setThumb(new UMImage(activity, R.drawable.ic_luncher));
            // 小程序消息title
            umMin.setTitle(DefaultContent.title);
            //小程序页面路径
            umMin.setPath("pages/classify/classify");
        }else {
            umMin.setThumb(new UMImage(activity, miniImageUrl));
            umMin.setTitle(miniTitle);
            umMin.setPath(miniPath);
        }
        // 小程序消息描述
        umMin.setDescription(DefaultContent.text);
        // 小程序原始id,在微信平台查询
//        umMin.setUserName("gh_df57df95cbbd");
        umMin.setUserName("gh_7b1ef83a8fcf");
//        umMin.setUserName("wx65b4dccd227066b8");

        new ShareAction(activity)
                .withMedia(umMin)
                .setPlatform(SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).share();
    }

    public void login(int platform, PlatformAuthListener platformAuthListener) {
        this.mPlatformAuthListener = platformAuthListener;

        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(activity).setShareConfig(config);
        UMShareAPI.get(activity).getPlatformInfo(activity, PlatformType.getUmShareMedai(platform), authListener);

    }


    UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(activity, "分享成功", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(activity, "分享失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(activity, "分享取消", Toast.LENGTH_SHORT).show();
        }
    };

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            Log.e("授权", "onStart: ");
        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            if (data == null) {
                return;
            }
            AuthBean authBean = new AuthBean();
            authBean.setUid(data.get("uid"));
            authBean.setName(data.get("name"));
            authBean.setGender(data.get("gender"));
            authBean.setIconurl(data.get("iconurl"));
            if (mPlatformAuthListener != null) {
                mPlatformAuthListener.onSuccess(authBean);
            }

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(activity, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(activity, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    public void activityResult(int requestCode, int resultCode, Intent data) {
        UMShareAPI.get(activity).onActivityResult(requestCode, resultCode, data);
    }

    public void destroy() {
        UMShareAPI.get(activity).release();
    }

}
