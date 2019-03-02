package com.a1magway.bgg.v.authorization;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.AppConfig;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import butterknife.BindView;

/**
 * 授权书页面
 * Created by enid on 2018/8/6.
 */

public class AuthorizationBookActivity extends AnimTransitionActivity {
    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_authorization_book;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("授权书");
        initWebView();

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                MProgressDialog.dismissProgress();
            }
        });
        LoginData loginData = GlobalData.getInstance().getLoginData();
        StringBuilder builder = new StringBuilder();
        builder.append("invText = ")
                .append(loginData.getInviteCode())
                .append("&autoTitle=")
                .append(loginData.getAuthorizeTitle())
                .append("&autoAppellation=")
                .append(loginData.getRank())
                .append("&autoDate=")
                .append(loginData.getExpirationDate())
                .append("&footerDate=")
                .append(loginData.getAuthorizeDate())
                .append("&authName=")
                .append(loginData.getNickName());
        String url = null;
        try {
            //因为参数中有中文，所以需要转码!
            url = AppConfig.BASE_URL + "/pictures/platform/commodity/picture/html/shouquan/index.html#/?" +
                    URLEncoder.encode(String.valueOf(builder), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        MProgressDialog.showProgress(this);
        mWebView.loadUrl(url);
    }

    private void initWebView() {
        mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setDomStorageEnabled(true);
    }
}
