package com.a1magway.bgg.v.web;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.FindWebContentData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.WebDeliveryData;
import com.a1magway.bgg.p.web.WebP;
import com.a1magway.bgg.util.AndroidWorkaroundUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.v.login.LoginActivity;
import com.almagway.common.AppConfig;
import com.almagway.common.utils.StringUtil;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.a1magway.bgg.widget.TitleBar;
import com.almagway.umeng.UmShare;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * 承载网页的Activity
 * Created by jph on 2017/8/19.
 */
public class WebActivity extends PActivity<WebP> implements IWebV{
    private static  final String FOUND_CANCLE_FC="found_cancle_fc";
    private static final String EXTRA_URL = "extra_url";
    private static final String EXTRA_TITLE = "extra_title";
    private static final String EXTRA_SHARE = "extra_share";
    private static final String EXTRA_ORDER_PAY = "extra_order_pay";
    private static final String IS_TITLE = "is_title";
    private static final int WEB_REULT_CODE=101;


    @BindView(R.id.web_title_bar)
    TitleBar mTitleBar;
    @BindView(R.id.web_web)
    WebView mWebView;
    @BindView(R.id.web_progress)
    ProgressBar mProgressBar;

    private String url;
    private String title;
    private boolean isShare;
    private UmShare umShare;
    private String mOrderNum;
    private boolean isTitle=true;
    private WebP mPresenter;

    public static void start(Context context, String url) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(EXTRA_URL, url);
        JumpUtil.startActivity(context, starter);
    }

    public static void start(Context context, String url,boolean isTitle) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(EXTRA_URL, url);
        starter.putExtra(IS_TITLE,isTitle);
        JumpUtil.startActivity(context, starter);
    }

    public static void start(Context context, String url, String title, boolean isShare) {
        Intent starter = new Intent(context, WebActivity.class);
        starter.putExtra(EXTRA_URL, url);
        starter.putExtra(EXTRA_TITLE, title);
        starter.putExtra(EXTRA_SHARE, isShare);
        JumpUtil.startActivity(context, starter);
    }

    public static void startForPayResult(Context context, String url, String orderNum, int requestCode) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_ORDER_PAY, orderNum);
        JumpUtil.startActivityForResult(context, intent, requestCode);
    }

//
//    @Override
//    public void injectComponent(AppComponent appComponent) {
//        super.injectComponent(appComponent);
//        DaggerOrderPayComponent.builder()
//                .appComponent(appComponent)
//                .webViewModule(new WebViewModule(this))
//                .build().inject(this);
//    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

        title = getIntent().getStringExtra(EXTRA_TITLE);
        isShare = getIntent().getBooleanExtra(EXTRA_SHARE, false);
        mOrderNum = getIntent().getStringExtra(EXTRA_ORDER_PAY);
        url = getIntent().getStringExtra(EXTRA_URL);
        isTitle=getIntent().getBooleanExtra(IS_TITLE,true);
    }

    /*
     *获取状态栏高度
     *
     */
    private int getStatusHeight(){
        int result = 0;
        int resourceId = getActivity().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getActivity().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     * @param context
     * @return
     */
    public  int getDaoHangHeight(Context context) {
        int resourceId=0;
        if (checkDeviceHasNavigationBar(this)){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
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



        private void shibei(){
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)mWebView.getLayoutParams();

        int hight=getDaoHangHeight(this);
        layoutParams.setMargins(0,0,0,getStatusHeight()+getDaoHangHeight(this));//4个参数按顺序分别是左上右下


        mWebView.setLayoutParams(layoutParams);
    }




    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        if (!isTitle){
            mTitleBar.setVisibility(View.GONE);
        }
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        AndroidWorkaroundUtil.assistActivity(getActivity());
        shibei();
//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|View.SYSTEM_UI_FLAG_IMMERSIVE;
//        window.setAttributes(params);
        mPresenter=new WebP(this);
        mTitleBar.setTitleTxt(title);
        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (isShare) {
            mTitleBar.setShowMore(R.drawable.ic_share_black);
            mTitleBar.setMoreClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    umShare = new UmShare(WebActivity.this);
                    umShare.open(url, title, title);
                }
            });
        }
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
        mWebView.addJavascriptInterface(new JiaoHu(), "AndroidWebView");
        mWebView.addJavascriptInterface(new JiaoHu(), "test");
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
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
//        mWebView.addJavascriptInterface(new JiaoHu(),"");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
        if (umShare != null) {
            umShare.destroy();
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
        finish();
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, final String title) {
            super.onReceivedTitle(view, title);
            if (mTitleBar == null) return;
            if (StringUtil.isEmpty(mTitleBar.getTitleText())) {
                if (mTitleBar != null) {
                    mTitleBar.setTitleTxt(title);
                }
            }
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

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            if (!TextUtils.isEmpty(mOrderNum)) {
                new AlertDialog.Builder(getContext())
                        .setMessage(message)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                setResult(RESULT_OK);
                                getActivity().finish();
                            }
                        }).show();
                return true;
            }
            return super.onJsAlert(view, url, message, result);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Uri uri = Uri.parse(url);
            if ("product".equals(uri.getScheme())) {
                int productId = Integer.parseInt(uri.getHost());
                if (productId > 0) {
                    ProductDetailsActivity.start(getContext(), productId);
                    return true;
                }
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (umShare != null) {
            umShare.activityResult(requestCode, resultCode, data);
        }
    }


    public class JiaoHu {
        @JavascriptInterface
        public void returnMobile() {
            setResult(RequestCode.REQUEST_CODE_UNIONPAY_SUCCESS);
            finish();
        }

        @JavascriptInterface
        public void returnMobile(String jsonData) {
            FindWebContentData findWebContentData=new Gson().fromJson(jsonData,FindWebContentData.class);
            LoginData loginData= GlobalData.getInstance().getLoginData();
            if (loginData==null){
                loginData=new LoginData();
                loginData.setId(0);
            }
            if (findWebContentData.getType()==5) {
                WebActivity.start(getContext(), AppConfig.FOUND_WEB_URL + "HotDetail?id=" + findWebContentData.getId() + "&user_id=" + loginData.getId(), findWebContentData.getTitle(), false);
            }else if (findWebContentData.getType()==6){
                WebActivity.start(getContext(), AppConfig.FOUND_WEB_URL + "PAY?id=" + findWebContentData.getId() + "&type="+AppConfig.BASE_URL+"&user_id=" + loginData.getId(), findWebContentData.getTitle(), false);
            }
        }

        @JavascriptInterface
        public void onClickEvent(String data){
            if (data==null||data.equals("")) {
                return;
            }
            WebDeliveryData deliveryData=new Gson().fromJson(data,WebDeliveryData.class);
            if (deliveryData.getType()==1){ //网页调本地方法 type 1分享 2 根据产品id打开商品详情 3支付宝支付 4 微信支付
                umShare = new UmShare(WebActivity.this);
                 umShare.open(deliveryData.getShareURL(), deliveryData.getTitle()+"", deliveryData.getContent()+"");
            }else if (deliveryData.getType()==2){
                if (deliveryData.getProductId()==0){
                    return;
                }
                ProductDetailsActivity.start(getContext(),deliveryData.getProductId(),deliveryData.getSubject_id());
            } else if (deliveryData.getType()==4){
                mPresenter.invokeWXPay(deliveryData.getPayDataWX());
            }else if (deliveryData.getType()==3){
                mPresenter.invokeAliPay(deliveryData.getPayData());
            }
        }

        @JavascriptInterface
        public void login(){
            LoginActivity.start(getContext());
        }

        @JavascriptInterface
        public void giveLike(String data){ //点赞 观看
            FindWebContentData findWebContentData=new Gson().fromJson(data,FindWebContentData.class);
            EventBus.getDefault().post(findWebContentData);

        }
    }
    @JavascriptInterface
    @Subscribe
    public void getEvent(String event){
        if (event.equals(FOUND_CANCLE_FC)){
            LoginData loginData= GlobalData.getInstance().getLoginData();
            if (loginData==null){
                loginData=new LoginData();
                loginData.setId(0);
            }
            mWebView.loadUrl("javascript:Utils.toSaveUserId("+loginData.getId()+")");
        }
    }

}
