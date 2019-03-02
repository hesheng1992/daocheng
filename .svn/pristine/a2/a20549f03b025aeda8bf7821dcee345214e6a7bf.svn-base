package com.a1magway.bgg.v.mainhome;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragmentInPager;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.mainhome.DaggerHomeSpecialComponent;
import com.a1magway.bgg.di.module.mainhome.HomeSpecialModule;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.mainhome.HomeSpecialP;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.pintuan.PingtuanActivity;
import com.a1magway.bgg.widget.CountDownTextView;
import com.a1magway.bgg.widget.CycleGalleryViewPager;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.PingtuanBannerIndicator;
import com.a1magway.bgg.widget.PingtuanCountDownTextView;
import com.a1magway.bgg.widget.RecommendTabLayout;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.utils.ScreenUtil;
import com.xxxrecylcerview.XXXRecyclerView;

import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by enid on 2018/6/11.
 */

public class HomeSpecialFragment extends PFragmentInPager<HomeSpecialP> implements IHomeSpecialV {

    private static final String TAG = HomeSpecialFragment.class.getSimpleName();

    @BindView(R.id.home_special_division_recyclerview)
    RecyclerView mSpecialDivisionRecycler;

    @BindView(R.id.home_special_recommend_recyclerview)
    LoadMoreRecyclerView mSpecialRecommendRecycler;

    @BindView(R.id.home_special_recommend_top)
    LinearLayout recommendBanner;

    @BindView(R.id.recommend_top_count_down_tv)
    CountDownTextView countDownTextView;

    @BindView(R.id.recommend_tab_layout_top)
    RecommendTabLayout recommendTabLayout;

    @BindView(R.id.recommend_top_name_tv)
    TextView mRecommendTopNameTv;

    @BindView(R.id.home_special_scrollvew)
    NestedScrollView mNestedScrollView;

    @BindView(R.id.home_pingtuan_viewpager)
    CycleGalleryViewPager home_pingtuan_viewpager;

    @BindView(R.id.home_pintuan_rl)
    RelativeLayout home_pintuan_rl;

    @BindView(R.id.home_pingtuan_title)
    TextView home_pingtuan_title;

    @BindView(R.id.pingtuanBannerIndicator)
    PingtuanBannerIndicator pingtuanBannerIndicator;

    @BindView(R.id.home_pingtuan_viewpager_parent)
    RelativeLayout home_pingtuan_viewpager_parent;

    @BindView(R.id.pingtuan_downTime)
    PingtuanCountDownTextView pingtuan_downTime;

    @BindView(R.id.home_pingtuan_more)
    LinearLayout home_pingtuan_more;

    private static final String EXTRA_SPECIAL_TYPE = "extra_special_type";
    Unbinder unbinder;

    private HomeRecommendHeader mHomeRecommendHeader;

    private long currentTime;


    public static HomeSpecialFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_SPECIAL_TYPE, type);
        HomeSpecialFragment fragment = new HomeSpecialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.home_fragment_special;
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerHomeSpecialComponent.builder()
                .appComponent(appComponent)
                .homeSpecialModule(new HomeSpecialModule(this, getArguments().getInt(EXTRA_SPECIAL_TYPE), getTitle()))
                .build()
                .inject(this);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        MainActivity.setNullStatusBarHeight(recommendBanner);
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Log.d(TAG, "initData() execute" + getArguments().getInt(EXTRA_SPECIAL_TYPE));

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //判断是否滑到的底部
                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    //会触发多次。防止多次调用
                    if (System.currentTimeMillis() - currentTime > 1000) {
                        mSpecialRecommendRecycler.startLoadMore();
                        currentTime = System.currentTimeMillis();
                    }
                }
            }
        });
        mSpecialRecommendRecycler.setEnabled(false);
        mSpecialRecommendRecycler.setOnLoadMoreListener(new XXXRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mPresenter.loadMoreGoodSReCommend();
            }
        });
    }

    @Override
    public void setSpecialRecyclerAdapter(RecyclerView.Adapter adapter) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        linearLayoutManager.setAutoMeasureEnabled(true);
        mSpecialDivisionRecycler.setLayoutManager(linearLayoutManager);
        mSpecialDivisionRecycler.setHasFixedSize(true);
        mSpecialDivisionRecycler.setNestedScrollingEnabled(false);
        mSpecialDivisionRecycler.setAdapter(adapter);
    }

    @Override
    public void setRecommendRecyclerAdapter(RecyclerView.Adapter adapter) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSmoothScrollbarEnabled(true);
        gridLayoutManager.setAutoMeasureEnabled(true);
        mSpecialRecommendRecycler.setLayoutManager(gridLayoutManager);
        mSpecialRecommendRecycler.setHasFixedSize(true);
        mSpecialRecommendRecycler.setNestedScrollingEnabled(false);

        mSpecialRecommendRecycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int itemCount = parent.getLayoutManager().getItemCount();
                int currentItem = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
                if (currentItem % 2 == 0) {// 第一列
                    if (currentItem >= 2) {
                        outRect.left = ScreenUtil.dp2px(8f);
                        outRect.right = ScreenUtil.dp2px(16f);
                    }
                } else if (currentItem % 2 == 1) { //最后列
                    outRect.left = ScreenUtil.dp2px(16f);
                    outRect.right = ScreenUtil.dp2px(8f);
                }
                outRect.bottom = ScreenUtil.dp2px(10f);//最后一列--
            }
        });

        //add header
        mHomeRecommendHeader = new HomeRecommendHeader(getContext(), new HomeRecommendHeader.OrderByChangeListener() {
            @Override
            public void onOrderBy(int order_by, int asc_desc) {
                Log.e("enid", "header orderby callback");
                MProgressDialog.showProgress(getContext());
                mPresenter.getGoodsRecommend(order_by, asc_desc);
            }
        }, true);
        mSpecialRecommendRecycler.addHeaderView(mHomeRecommendHeader.getView());

        //set adapter
        mSpecialRecommendRecycler.setAdapter(adapter);


//        mSpecialRecommendRecycler.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d("enid", "mSpecialRecommendRecycler onTouch");
//                showTop();
//                return false;
//            }
//        });

        mNestedScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("enid", "mNestedScrollView onTouch");
                showTop();
                return false;
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    showTop();
                }
            });
        }
        //add scroll listener
        mSpecialRecommendRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("enid", "mSpecialRecommendRecycler onScrollStateChanged");
                showTop();

            }
        });
    }

    private void showTop() {
        View childAt0 = mSpecialRecommendRecycler.getChildAt(0);
        if (childAt0 == null) return;
        int[] position = new int[2];
        childAt0.getLocationInWindow(position);
//        Log.e("enid", "childAt0 position1 = " + position[0]);
//        Log.e("enid", "childAt0 position2 = " + position[1]);
        int statusBarHeight = AndroidUtil.getStatusBarHeight(getContext());

        if (position[1] > statusBarHeight) {
            if (recommendBanner.getVisibility() == View.INVISIBLE) return;
            recommendBanner.setVisibility(View.INVISIBLE);
            mHomeRecommendHeader.setOrderByType(recommendTabLayout.getOrderByType());
        } else {
            if (recommendBanner.getVisibility() == View.VISIBLE) return;
            recommendBanner.setVisibility(View.VISIBLE);
            mRecommendTopNameTv.setText(mHomeRecommendHeader.getTitle());
//            countDownTextView.startTickWork(mHomeRecommendHeader.getCountDownTime(), true);
            recommendTabLayout.setOrderByType(mHomeRecommendHeader.getOrderByType(), false);
            recommendTabLayout.setOrderByChangeListener(new RecommendTabLayout.OrderByChangeListener() {
                @Override
                public void onOrderByChange(int newOrderByType, boolean isSynthesize) {
                    LogUtil.e("ccc", "111");
                    MProgressDialog.showProgress(getContext());
                    if (isSynthesize) {
                        //综合排序
                        mPresenter.getGoodsRecommend(newOrderByType / 10, 2);
                    } else {
                        mPresenter.getGoodsRecommend(newOrderByType / 10, newOrderByType % 10);
                    }

                }
            });
        }
    }

    @Override
    public HomeRecommendHeader getHeader() {
        return mHomeRecommendHeader;
    }

    @Override
    public void loadComplete() {
        MProgressDialog.dismissProgress();
    }


    @Override
    public void setLoadable(boolean loadable) {
//        this.canLoadMore = loadable;
    }

    @Override
    public void stopLoadMore() {
        mSpecialRecommendRecycler.stopLoadMore();
    }

    @Override
    public CycleGalleryViewPager getPingtuanViewPager() {
        return home_pingtuan_viewpager;
    }

    @Override
    public void setPingtuanTitle(String title) {
        home_pintuan_rl.setVisibility(View.VISIBLE);
        home_pingtuan_viewpager_parent.setVisibility(View.VISIBLE);
        home_pingtuan_title.setText(title);
    }

    @Override
    public PingtuanBannerIndicator getpingtuanBannerIndicator() {
        return pingtuanBannerIndicator;
    }

    @Override
    public void setPingtuanDownTime(long engTime) {
        Date nowDate = new Date();
        pingtuan_downTime.startTickWork(engTime - nowDate.getTime(),PingtuanCountDownTextView.NORMAL);
    }

    @Override
    public void showBannerPingtuanMore() {
        home_pingtuan_more.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHomeRecommendHeader != null) {
            mHomeRecommendHeader.stopTimer();
        }
        if (countDownTextView != null) {
            countDownTextView.stopTickWork();
        }
        pingtuan_downTime.stopTickWork();
    }

    public void hideTopLayout() {
        if (recommendBanner != null) {
            if (recommendBanner.getVisibility() == View.INVISIBLE) return;
            recommendBanner.setVisibility(View.INVISIBLE);
        }

        if (mHomeRecommendHeader != null)
            mHomeRecommendHeader.setOrderByType(recommendTabLayout.getOrderByType());
    }


    @OnClick(R.id.home_pingtuan_more)
    public void onViewClicked() {
        if (JumpUtil.processCheckLogin(getContext())) {
            return;
        }
        PingtuanActivity.start(getContext());
    }
}
