package com.a1magway.bgg.refactor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.a1magway.bgg.App;
import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.util.SoftInputUtil;
import com.almagway.common.utils.StatusBarUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * author: Beaven
 * date: 2017/10/28 10:24
 */

@SuppressLint("Registered") public class BaseNewActivity extends RxAppCompatActivity
    implements BaseContract.BaseView {

    private LinearLayout rootLayout;
    private RelativeLayout titleLayout;
    private AppCompatImageView imageBack;
    private AppCompatTextView textTitle;
    private AppCompatImageView imageMore;
    private View.OnClickListener backListener;
    private View.OnClickListener moreListener;

    private Unbinder unbinder;


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_layout);
        setContentView(getContentViewLayoutId());
        unbinder = ButterKnife.bind(this);

        StatusBarUtil.darkMode(this);
        StatusBarUtil.setMargin(this, titleLayout);

//        initData(savedInstanceState);enid:原来的位置移动到initView下面

        injectComponent(((App) getApplication()).getAppComponent());
        initView(savedInstanceState);
        initData(savedInstanceState);
    }


    @Override public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }


    @Override public void setContentView(View view) {
        rootLayout = (LinearLayout) findViewById(R.id.root_layout);
        if (rootLayout == null) return;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootLayout.addView(view, params);
        initTitle();
    }


    @LayoutRes
    protected int getContentViewLayoutId() {
        return 0;
    }


    @Override public void injectComponent(AppComponent appComponent) {

    }


    @Override public void onCreateV(@Nullable Bundle savedInstanceState) {

    }


    @Override public Activity getActivity() {
        return this;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
    }


    @Override protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }


    @Override protected void onPause() {
        super.onPause();
        SoftInputUtil.hideSoftInput(this);
        MobclickAgent.onPause(this);
    }


    @Override protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @Override
    public void onBackPressed() {
        SoftInputUtil.hideSoftInput(this);
        if (preExitPage()) {
            return;
        }
        super.onBackPressed();
    }


    protected boolean preExitPage() {
        return false;
    }


    /**
     * 初始化title
     */
    private void initTitle() {
        titleLayout = (RelativeLayout) rootLayout.findViewById(R.id.layout_title);
        imageBack = (AppCompatImageView) rootLayout.findViewById(R.id.img_title_back);
        imageMore = (AppCompatImageView) rootLayout.findViewById(R.id.img_title_more);
        textTitle = (AppCompatTextView) rootLayout.findViewById(R.id.text_title);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (backListener != null) {
                    backListener.onClick(v);
                } else {
                    BaseNewActivity.this.finish();
                }
            }
        });
        imageMore.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (moreListener != null) {
                    moreListener.onClick(v);
                }
            }
        });
    }


    /**
     * 隐藏title,默认显示
     */
    public void hideTitle() {
        titleLayout.setVisibility(View.GONE);
    }


    /**
     * 显示title,默认显示
     */
    public void showTitle() {
        titleLayout.setVisibility(View.VISIBLE);
    }


    /**
     * 设置title中间标题
     */
    public void setTextTitle(@StringRes int resId) {
        setTextTitle(getResources().getString(resId));
    }


    public void setTextTitle(String title) {
        if (TextUtils.isEmpty(title)) return;
        textTitle.setText(title);
    }


    /**
     * 设置title左边image，是否显示和显示内容
     *
     * @param visible 是否显示
     * @param iconId 图标ID
     */
    public void setImageBack(boolean visible, @DrawableRes int iconId) {
        if (visible) {
            imageBack.setVisibility(View.VISIBLE);
            imageBack.setImageDrawable(getResources().getDrawable(iconId));
        } else {
            imageBack.setVisibility(View.GONE);
        }
    }


    /**
     * 设置title右边image，是否显示和显示内容
     *
     * @param visible 是否显示
     * @param iconId 图标ID
     */
    public void setImageMore(boolean visible, @DrawableRes int iconId) {
        if (visible) {
            imageMore.setVisibility(View.VISIBLE);
            imageMore.setImageDrawable(getResources().getDrawable(iconId));
        } else {
            imageMore.setVisibility(View.GONE);
        }
    }


    /**
     * 设置title左边image的监听
     */
    public void setBackListener(View.OnClickListener backListener) {
        this.backListener = backListener;
    }


    /**
     * 设置title右边image的监听
     */
    public void setMoreListener(View.OnClickListener moreListener) {
        this.moreListener = moreListener;
    }
}
