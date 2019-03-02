package com.a1magway.bgg.v.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerMainComponent;
import com.a1magway.bgg.di.component.MainComponent;
import com.a1magway.bgg.di.module.MainModule;
import com.a1magway.bgg.eventbus.event.FoundChangeEvent;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.MainP;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.util.DoubleClick;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.widget.TabBar;
import com.almagway.common.utils.TimeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import q.rorbin.badgeview.QBadgeView;

/**
 * 主体框架页面
 * Created by jph on 2017/7/20.
 */
public class MainActivity extends PActivity<MainP> implements IMainV {

    private static final String PAGES_TAG = "pages_tag";

    public static final String OTHERS = "others";
    public static final String RESUME = "resume";
    public static String activityStatus = OTHERS;

    private DoubleClick mDoubleClick;

    @Inject
    TabManager mTabManager;

    @BindView(R.id.main_tab_bar)
    TabBar mTabBar;

    @BindView(R.id.tab_found_img)
    ImageView mFoundImg;

    private QBadgeView mBadgeView;
    private String pages;


    public static void start(Context context) {
        start(context, MainSubPages.MAIN_HOME);
    }


    public static void start(Context context, @MainSubPages String pages) {
        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(PAGES_TAG, pages);
        JumpUtil.startActivity(context, starter);
    }


    @Override
    public int getContentViewLayoutId() {
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
        return R.layout.main_activity_main;
    }


    @Override
    protected boolean fitsSystemWindows() {
        return true;
    }


    /**
     * 为了个人中心显示效果，整个activity的内容设置是覆盖StatusBar的，
     * 故其他fragment需要设置一个view来占位status下的位置
     * 设置占位StatusBar的view的高度为获取的StatusBar的高度，
     */
    public static void setNullStatusBarHeight(View fragView) {
        View v = fragView.findViewById(R.id.null_status_bar);
        ViewGroup.LayoutParams lp = v.getLayoutParams();
        lp.height = AndroidUtil.getStatusBarHeight(fragView.getContext());
        v.setLayoutParams(lp);
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        pages = getIntent().getStringExtra(PAGES_TAG);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        pages = getIntent().getStringExtra(PAGES_TAG);
        mTabManager.init(pages);
    }


    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState) {
        super.onCreateV(savedInstanceState);

        mTabManager.init(pages);
        mDoubleClick = new DoubleClick(new DoubleClick.DoubleClickListener() {
            @Override
            public void onFirstClick() {
                Toaster.showShort(getContext(), R.string.main_toast_back);
            }


            @Override
            public void onSecondClick() {
                finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.refreshCartNum();
        mPresenter.registerLoginReceiver();
        activityStatus = OTHERS;
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityStatus = RESUME;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.unregisterLoginReceiver();
        activityStatus = OTHERS;
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        MainComponent mMainComponent = DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this, getSupportFragmentManager(), mTabBar))
                .build();
        mMainComponent.inject(this);
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mBadgeView = new QBadgeView(getContext());
        int tabWidth = AndroidUtil.getScreenWidth(getContext()) / 4;
        int badgeOffsetX = tabWidth +
                getResources().getDimensionPixelOffset(R.dimen.tab_badge_offset_x);
        int badgeOffsetY = getResources().getDimensionPixelOffset(R.dimen.tab_badge_offset_y);

        mBadgeView.bindTarget(mTabBar)
                .setBadgeGravity(Gravity.END | Gravity.TOP)
                .setGravityOffset(badgeOffsetX, badgeOffsetY, false)
                .setBadgeTextSize(9, true)
                .setShowShadow(false);
        //        mBadgeView.hide(false);
    }


    @Override
    public void onBackPressed() {
        //        super.onBackPressed();
        mDoubleClick.click();
    }


    @Override
    public void showCartNum(int num) {
        if (num > 0) {
            mBadgeView.setBadgeNumber(num);
        } else {
            mBadgeView.hide(false);
        }
    }


    @Override
    public void reduceCartNum() {
        int oldCount = mBadgeView.getBadgeNumber();
        showCartNum(oldCount - 1);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTabChange(FoundChangeEvent event) {
        if (event.isFound()) {
            mFoundImg.setImageResource(R.mipmap.tab_found);
        } else {
            mFoundImg.setImageResource(R.mipmap.tab_found_normal);
        }
    }

}
