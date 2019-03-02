package com.a1magway.bgg.p.mainhome;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.broadcast.LoginReceiver;
import com.a1magway.bgg.data.entity.GoodsRecommendData;
import com.a1magway.bgg.data.entity.HomeSubjectBean;
import com.a1magway.bgg.data.entity.PingtuanBannerData;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.mainhome.IHomeRecommendData;
import com.a1magway.bgg.data.repository.mainhome.IHomeSpecialData;
import com.a1magway.bgg.eventbus.event.HomeRefreshEvent;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.v.mainhome.IHomeSpecialV;
import com.a1magway.bgg.v.mainhome.ProductGridAdapter;
import com.a1magway.bgg.v.mainhome.SpecialDivisionAdapter;
import com.a1magway.bgg.v.mainhome.SpecialProductActivity;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.a1magway.bgg.v.web.WebActivity;
import com.a1magway.bgg.widget.CycleGalleryViewPager;
import com.almagway.common.utils.CollectionUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/6/11.
 */

public class HomeSpecialP extends BaseLoadP<List<HomeSubjectBean>, IHomeSpecialV> {
    private IHomeSpecialData mIHomeSpecialData;
    private IHomeRecommendData mIHomeRecommendData;

    @Inject
    int mType;

    @Inject
    String comeFrom;

    @Inject
    SpecialDivisionAdapter mDivisionAdapter;

    @Inject
    ProductGridAdapter mRecommendAdapter;

    @Inject
    APIManager apiManager;

    private boolean isSetRecommendAdapter = false;
    private int last_order_by = 1;//刷新使用
    private int last_asc_desc = 1;//刷新使用
    //
    private int current_page = 0;
    private int page_num = 20;


    @Inject
    public HomeSpecialP(@NonNull IHomeSpecialV view, IHomeSpecialData homeSpecialData, IHomeRecommendData homeRecommendData) {
        super(view);
        this.mIHomeSpecialData = homeSpecialData;
        this.mIHomeRecommendData = homeRecommendData;
    }


    @Nullable
    @Override
    public Observable<List<HomeSubjectBean>> getDataObservable() {
        return mIHomeSpecialData.getSubjectList(mType);
    }

    @Override
    protected void onLoadSuccess(List<HomeSubjectBean> homeSubjectBeans) {
        super.onLoadSuccess(homeSubjectBeans);
        mDivisionAdapter.setList(homeSubjectBeans);
        checkShowNoneData(homeSubjectBeans);
        mView.hideLoginLoading();
    }

    public void loadMoreGoodSReCommend() {
        LogUtil.e("current_page", current_page + "");
        mIHomeRecommendData.getGoodGoodsRecommendsList(GlobalData.getInstance().getUserId(), mType,
                last_order_by, last_asc_desc, current_page, page_num)
                .subscribeOn(Schedulers.io())
                .compose(this.<GoodsRecommendData>bindToDestroyEvent())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<GoodsRecommendData>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stopLoadMore();
                        mView.setLoadable(false);
                    }

                    @Override
                    public void onNext(GoodsRecommendData goodsRecommendData) {
                        mRecommendAdapter.addList(goodsRecommendData.getProducts());
                        current_page++;
                        mView.setLoadable(CollectionUtil.isNotEmpty(goodsRecommendData.getProducts()));
                        mView.stopLoadMore();
                    }

                    @Override
                    public void onComplete() {
                        mView.stopLoadMore();
                    }
                });
    }


    public void getGoodsRecommend(final int order_by, int asc_desc) {
        last_order_by = order_by;
        last_asc_desc = asc_desc;
        mIHomeRecommendData.getGoodGoodsRecommendsList(GlobalData.getInstance().getUserId(), mType,
                order_by, asc_desc, 0, page_num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<GoodsRecommendData>bindToDestroyEvent())
                .subscribe(new BaseObserver<GoodsRecommendData>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.stopLoadMore();
                        mView.setLoadable(false);
                    }

                    @Override
                    public void onNext(GoodsRecommendData goodsRecommendData) {
                        setGoodRecommend();
                        if (goodsRecommendData.getProducts() != null | goodsRecommendData.getProducts().size() != 0) {
                            mView.hideNoneLayout();
                        }
                        mView.getHeader().setTitle(goodsRecommendData.getTitle(), goodsRecommendData.getRemainTime());
                        mRecommendAdapter.setList(goodsRecommendData.getProducts());
                        mView.loadComplete();
                        current_page = 1;
                        //
                        mView.stopLoadMore();
                        mView.setLoadable(CollectionUtil.isNotEmpty(goodsRecommendData.getProducts()));
                    }

                    @Override
                    public void onComplete() {
                        mView.stopLoadMore();
                    }
                });
    }

    private void setGoodRecommend() {
        if (isSetRecommendAdapter) return;
        isSetRecommendAdapter = true;
        mView.setRecommendRecyclerAdapter(mRecommendAdapter);
        mRecommendAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        ProductDetailsActivity.start(
                                v.getContext(), mRecommendAdapter.getItem(position).getId(),
                                mRecommendAdapter.getItem(position).getSubject_id());
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
    }


    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
        registerLoginReceiver();
        mView.setSpecialRecyclerAdapter(mDivisionAdapter);
        mDivisionAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //除下期预告外的主题才能点击

                if (mType != 4) {
                    if (mDivisionAdapter.getList().get(position).getMode() == 0) {

                        int id = mDivisionAdapter.getList().get(position).getId();
                        String title = mDivisionAdapter.getList().get(position).getTitle();
                        SpecialProductActivity.start(getContext(), id, comeFrom, title);
                    }
                    if (mDivisionAdapter.getList().get(position).getMode() == 3) { //mode=3 表示链接
                        WebActivity.start(getContext(), mDivisionAdapter.getList().get(position).getLink(), mDivisionAdapter.getList().get(position).getTitle(), false);
                    } else {
                        int id = mDivisionAdapter.getList().get(position).getId();
                        String title = mDivisionAdapter.getList().get(position).getTitle();
                        SpecialProductActivity.start(getContext(), id, comeFrom, title);
                    }
                }
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        loadData();
        getGoodsRecommend(1, 2);
        //
        getPingtuanBannerData();
    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        unregisterLoginReceiver();
        super.onDestroy();
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(HomeRefreshEvent homeRefreshEvent) {
        //是当前页才刷新
        if (homeRefreshEvent.getCurrentPage() + 1 == mType) {
            current_page = 0;
            loadData(false);
            getGoodsRecommend(last_order_by, last_asc_desc);
        }
    }

    public void registerLoginReceiver() {
        getContext().registerReceiver(mLoginReceiver, mLoginReceiver.getIntentFilter());
    }

    public void unregisterLoginReceiver() {
        getContext().unregisterReceiver(mLoginReceiver);
    }


    private LoginReceiver mLoginReceiver = new LoginReceiver() {
        @Override
        public void onLogin() {
            mRecommendAdapter.notifyDataSetChanged();
        }

        @Override
        public void onLogout() {
            mRecommendAdapter.notifyDataSetChanged();
        }
    };

    /**
     * 请求首页拼团数据
     */
    private void getPingtuanBannerData() {
        apiManager.getPingtuanBannerData(mType)
                .compose(this.<PingtuanBannerData>bindToDestroyEvent())
                .subscribe(new SimpleObserver<PingtuanBannerData>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(PingtuanBannerData pingtuanBannerData) {
                        super.onNext(pingtuanBannerData);
                        initPingtuanBanner(pingtuanBannerData);
                    }
                });
    }


    /**
     * 初始化拼团广告轮播
     */

    public void initPingtuanBanner(final PingtuanBannerData data) {
        if (data.getCollageProduct() == null || data.getCollageProduct().size() == 0) {
            return;
        }
        final List<PingtuanBannerData.CollageProductBean> PIC_RES = data.getCollageProduct();
        CycleGalleryViewPager home_pingtuan_viewpager = mView.getPingtuanViewPager();
        mView.setPingtuanTitle(data.getTitle());
        mView.getpingtuanBannerIndicator().init(getContext(), PIC_RES.size());
        mView.getpingtuanBannerIndicator().setCurrentSelecte(0);
        if (PIC_RES.size() > 4) {
            //拼团广告数大于等于5，才显示拼团更多
            mView.showBannerPingtuanMore();
        }
        home_pingtuan_viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return PIC_RES.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                View view = View.inflate(getContext(), R.layout.gallery_item, null);
                mView.setPingtuanDownTime(data.getEndTime());
                ImageView imageView = view.findViewById(R.id.imageview);
                ImageLoaderUtil.displayImage(imageView, PIC_RES.get(position).getCover());
                container.addView(view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //banner点击监听
                        ProductDetailsActivity.start(getContext(), PIC_RES.get(position).getProductId());
                    }
                });
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public float getPageWidth(int position) {
                return 0.8f;//建议值为0.6~1.0之间
            }
        });
        home_pingtuan_viewpager.setNarrowFactor(0.95f);
        home_pingtuan_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mView.getpingtuanBannerIndicator().setCurrentSelecte(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {

                }
            }
        });

        //大于1张轮播图才开启自动播放
        if (PIC_RES.size() > 1) {
            pingtuanBannerAutoPlay(PIC_RES.size(), home_pingtuan_viewpager);
        }
    }

    //拼团广告自动播放
    private void pingtuanBannerAutoPlay(final int viewPageChildCount, final CycleGalleryViewPager home_pingtuan_viewpager) {
        Observable.interval(3000, TimeUnit.MILLISECONDS)
                .compose(this.<Long>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int currentPage = home_pingtuan_viewpager.getCurrentItem();
                        if (currentPage < viewPageChildCount) {
                            currentPage++;
                        } else {
                            currentPage = 0;
                        }
                        home_pingtuan_viewpager.setCurrentItem(currentPage);
                    }
                });
    }


}
