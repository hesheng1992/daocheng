package com.a1magway.bgg.v.found;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragmentInPager;
import com.a1magway.bgg.data.entity.FindWebContentData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.WebDeliveryData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.found.FoundP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.articleManager.ArticleManagerWebActivity;
import com.a1magway.bgg.v.login.LoginActivity;
import com.a1magway.bgg.v.web.WebActivity;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.AppConfig;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FoundFragment extends PFragmentInPager<FoundP> implements IFoundV {

    private static final String WEB_URL = "web_url";
    private static final String FOUND_CANCLE_FC = "found_cancle_fc";
    //    @BindView(R.id.web_title_bar)
//    TitleBar mTitleBar;
    @BindView(R.id.web_web)
    WebView mWebView;
    @BindView(R.id.web_progress)
    ProgressBar mProgressBar;


    private String url;


    public static FoundFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString(WEB_URL, url);
        FoundFragment fragment = new FoundFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /*
     * 判断有无虚拟导航栏
     */
    public static boolean checkDeviceHasNavigationBar(Context activity) {

        //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasMenuKey = ViewConfiguration.get(activity)
                .hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap
                .deviceHasKey(KeyEvent.KEYCODE_BACK);

        if (!hasMenuKey && !hasBackKey) {
            // 做任何你需要做的,这个设备有一个导航栏
            return true;
        }
        return false;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_web;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mPresenter = new FoundP(this);
        Bundle arguments = getArguments();
        if (arguments != null) {
            url = arguments.getString(WEB_URL);
        }

        MProgressDialog.showProgress(getContext());//加载框
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
//        mWebView.getSettings().setUserAgentString(
//                mWebView.getSettings().getUserAgentString()
//                        + " Mocha(Android)Version/" + AppConstants.VERSION);
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
//        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
        mWebView.getSettings().setDomStorageEnabled(true);
//        mWebView.getSettings().setAppCacheEnabled(!AppConstants.IS_DEV_SERVER);
//        String appCacheDir = this.getApplicationContext()
//                .getDir("cache", Context.MODE_PRIVATE).getPath();
//        mWebView.getSettings().setAppCachePath(appCacheDir);
//        mWebView.getSettings().setCacheMode(AppConstants.IS_DEV_SERVER ?
//                WebSettings.LOAD_NO_CACHE : WebSettings.LOAD_DEFAULT);
//        mWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 1);// 设置缓冲大小，我设的是10M
        mWebView.addJavascriptInterface(new JiaoHu(), "test");
//        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition,
                                        String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        mWebView.loadUrl(url);
        LogUtil.e("found_url", url);
        mWebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                //当进度走到100的时候做自己的操作，我这边是弹出dialog
                if (progress == 100) {
                    MProgressDialog.dismissProgress();
                    LoginData loginData = GlobalData.getInstance().getLoginData();
                    if (loginData == null) {
                        loginData = new LoginData();
                        loginData.setId(0);
                    }
                    try {
                        mWebView.loadUrl("javascript:Utils.toSaveUserId(" + loginData.getId() + ")");
                    }catch (NullPointerException e){
                        e.printStackTrace();
                    }
                }

            }
        });

//        mWebView.addJavascriptInterface(new JiaoHu(),"");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        try {
            if (mWebView != null) {
                ViewParent parent = mWebView.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(mWebView);
                }
                mWebView.stopLoading();
                mWebView.getSettings().setJavaScriptEnabled(false);
                mWebView.clearHistory();
                mWebView.removeAllViews();
                mWebView.destroy();
                mWebView = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    @Override
    public void onPayFailed(String msg) {
        mWebView.loadUrl("javascript:Utils.addListerDiscoverJs()");
    }

    @JavascriptInterface
    @Override
    public void onPaySuccess(int type) {
        mWebView.loadUrl("javascript:Utils.addListerDiscoverJs()");
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, final String title) {
            super.onReceivedTitle(view, title);
//            if (mTitleBar == null) return;
//            if (StringUtil.isEmpty(mTitleBar.getTitleText())) {
//                if (mTitleBar != null) {
//                    mTitleBar.setTitleTxt(title);
//                }
//            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (mProgressBar == null) return;
            mProgressBar.setProgress(newProgress);
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }
    }


    public class JiaoHu {
        @JavascriptInterface
        public void returnMobile(String jsonData) {
            FindWebContentData findWebContentData = new Gson().fromJson(jsonData, FindWebContentData.class);
            LoginData loginData = GlobalData.getInstance().getLoginData();
            if (loginData == null) {
                loginData = new LoginData();
                loginData.setId(0);
            }
            if (findWebContentData.getType() == 5) {
                WebActivity.start(getContext(), AppConfig.FOUND_WEB_URL + "HotDetail?id=" + findWebContentData.getId() + "&user_id=" + loginData.getId() + "&type=" + AppConfig.BASE_URL, findWebContentData.getTitle(), false);
            } else if (findWebContentData.getType() == 6) {
                WebActivity.start(getContext(), AppConfig.FOUND_WEB_URL + "PAY?id=" + findWebContentData.getId() + "&type=" + AppConfig.BASE_URL + "&user_id=" + loginData.getId(), findWebContentData.getTitle(), false);
            }
        }

        @JavascriptInterface
        public void onClickEvent(String data) {
            LogUtil.e("found", "data");
            if (data == null || data.equals("")) {
                return;
            }

            WebDeliveryData deliveryData = new Gson().fromJson(data, WebDeliveryData.class);
            if (deliveryData.getType() == 4) {
                mPresenter.invokeWXPay(deliveryData.getPayDataWX());
            } else if (deliveryData.getType() == 3) {
                mPresenter.invokeAliPay(deliveryData.getPayData());
            } else if (deliveryData.getType() == 6) {
                //发表文章
                mPresenter.createArticle(deliveryData.getTitle());
            }
        }

        @JavascriptInterface
        public void login() {
            LoginActivity.start(getContext());
        }


//        /**
//         * 发表文章接口
//         */
//        @JavascriptInterface
//        public void createArticle(){
//            String url = "";
//            ArticleManagerWebActivity.start(getContext(),url);
//        }
    }

    @JavascriptInterface
    @Subscribe
    public void getEvent(String event) {
        if (event.equals(FOUND_CANCLE_FC)) { //如果登录成功 或者退出登录都需要把用户id重新发送给网页
            LoginData loginData = GlobalData.getInstance().getLoginData();
            if (loginData == null) {
                loginData = new LoginData();
                loginData.setId(0);
            }
            mWebView.loadUrl("javascript:Utils.toSaveUserId(" + loginData.getId() + ")");

        }
    }

    private Handler handler = new Handler();

    @JavascriptInterface
    @Subscribe
    public void getLikeEvent(final FindWebContentData data) {
        if (data == null)
            return;


        handler.post(new Runnable() {
            @Override
            public void run() {
                data.setType(1);
                String data1 = new Gson().toJson(data);
                String js = "javascript:Utils.toBack(" + "'" + data1 + "'" + ")";
                mWebView.loadUrl(js);
            }
        });
//        int type= (int) map.get("type");
    }


}
