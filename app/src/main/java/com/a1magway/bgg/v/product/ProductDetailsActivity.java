package com.a1magway.bgg.v.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.common.adapter.FragPageAdapter;
import com.a1magway.bgg.common.shre.ShareData;
import com.a1magway.bgg.common.shre.ShareType;
import com.a1magway.bgg.data.entity.Commodity;
import com.a1magway.bgg.data.entity.HuanXinLoginInfo;
import com.a1magway.bgg.data.entity.Product;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.product.DaggerProDetailsComponent;
import com.a1magway.bgg.di.component.product.ProDetailsComponent;
import com.a1magway.bgg.di.module.product.ProDetailsModule;
import com.a1magway.bgg.eventbus.event.PingtuanBuyEvent;
import com.a1magway.bgg.huanxin.KefuHelper;
import com.a1magway.bgg.p.product.ProductDetailsP;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.v.cart.CartActivity;
import com.a1magway.bgg.widget.dialog.ShareDialogFragment;
import com.a1magway.bgg.widget.verticalviewpager.VerticalViewPager;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 商品详情界面
 * Created by jph on 2017/8/7.
 */
public class ProductDetailsActivity extends PActivity<ProductDetailsP> implements IProductDetailsV {
    public static final String EXTRA_SUBJECT_ID = "extra_subject_id";
    public static final String EXTRA_PRODUCT_ID = "extra_product_id";
    public static final String COME_FROM_NAME = "come_from_name";
    private ProDetailsComponent mProDetailsComponent;
    private DetailInfoFragment mInfoFragment;
    private TimeCountDownController timeCountDownController;

    @BindView(R.id.details_vpager)
    VerticalViewPager mVerticalViewPager;
    @BindView(R.id.details_txt_brand)
    TextView mBrandTxt;
    @BindView(R.id.details_txt_name)
    TextView mNameTxt;
    @BindView(R.id.details_layout_cart)
    TextView mCartLayoutTv;
    @BindView(R.id.details_txt_add_cart)
    TextView mAddCart;
    @BindView(R.id.details_v_cart_divider)
    View mCartDividerV;
    @BindView(R.id.details_txt_count_down)
    TextView mCountDownTxt;
    @BindView(R.id.details_txt_buy)
    TextView mBuyTxt;
    @BindView(R.id.layout_member_upgrade)
    LinearLayout layoutMemberUpgrade;
    @BindView(R.id.text_member_desc)
    TextView textMemberUpgradeDesc;
    @BindView(R.id.text_upgrade_member)
    TextView textMemberUpgrade;
    @BindView(R.id.details_layout_bottom)
    LinearLayout layoutBuy;
    @BindView(R.id.details_img_back)
    ImageView mBackTv;
    @BindView(R.id.details_txt_add_cart_parent)
    LinearLayout details_txt_add_cart_parent;
    @BindView(R.id.details_v_cart_divider_1)
    View details_v_cart_divider_1;
    @BindView(R.id.details_txt_pingtuan_buy)
    LinearLayout details_txt_pingtuan_buy;
    @BindView(R.id.details_txt_myself_createPingtuan)
    TextView details_txt_myself_createPingtuan;

    private String subject_id;
    @Inject
    APIManager apiManager;





    public static void start(Context context, int productId) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        starter.putExtra(EXTRA_PRODUCT_ID, productId);
        starter.putExtra(EXTRA_SUBJECT_ID, "");
        JumpUtil.startActivity(context, starter);
    }

    public static void start(Context context, int productId, String subject_id) {
        if (subject_id == null) subject_id = "";
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        starter.putExtra(EXTRA_PRODUCT_ID, productId);
        starter.putExtra(EXTRA_SUBJECT_ID, subject_id);
        JumpUtil.startActivity(context, starter);
    }


    public static void start(Context context, int productId, String subject_id, String comeFrom) {
        if (subject_id == null) subject_id = "";
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        starter.putExtra(EXTRA_PRODUCT_ID, productId);
        starter.putExtra(COME_FROM_NAME, comeFrom);
        starter.putExtra(EXTRA_SUBJECT_ID, subject_id);
        JumpUtil.startActivity(context, starter);
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        mProDetailsComponent = DaggerProDetailsComponent.builder()
                .appComponent(appComponent)
                .proDetailsModule(new ProDetailsModule(this,
                        getIntent().getIntExtra(EXTRA_PRODUCT_ID, 0)))
                .build();
        mProDetailsComponent.inject(this);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        subject_id = getIntent().getStringExtra(EXTRA_SUBJECT_ID);
        //来自下期预告就隐藏购物车和立即购买
//        String comeFromName = getIntent().getStringExtra(COME_FROM_NAME);
//        View mAddCartParent = (View) mAddCart.getParent();
//        View mBuyTextParent = (View) mBuyTxt.getParent();
//        if (comeFromName != null && comeFromName.equals(getString(R.string.next_issue))) {
//            mAddCartParent.setVisibility(View.GONE);
//            mBuyTextParent.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.product_activity_details;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.checkShowBuyStatus();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timeCountDownController != null) {
            timeCountDownController.cancel();
            timeCountDownController = null;
        }
        if (EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().unregister(this);

    }

    @Subscribe
    public void getEventBus(Integer num) {
        if (num == RequestCode.REQUEST_CODE_UNIONPAY_SUCCESS) {
            finish();
        }
    }


    @OnClick({R.id.details_img_back, R.id.details_layout_cart})
    public void onClickBack(View v) {
        switch (v.getId()) {
            case R.id.details_img_back:
                finish();
                break;
            case R.id.details_layout_cart:
                CartActivity.start(this);
                finish();
                break;
        }
    }

    /**
     * 点击立即下单
     */
    @OnClick(R.id.details_txt_buy)
    public void onClickBuy(View v) {
        String s = mBuyTxt.getText().toString();
        mPresenter.clickBuy(2);
    }

    /**
     * 拼团购买
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PingtuanBuyEvent event) {
        mPresenter.pingtuanBuy(false, event.getCollageOrderId(),event.getSelectedCommodity());
    }


    /**
     * 点击分享
     *
     * @param view
     */
    @OnClick(R.id.details_share_layout)
    public void onClickShare(View view) {
        mPresenter.clickShare();
    }


    @OnClick(R.id.details_kefu_layout)
    public void onClickKefu(View view) {
        hxLoginInfoGet();
    }

    /**
     * 点击加入购物车
     */
    @OnClick(R.id.details_txt_add_cart)
    public void onClickAddCart(View v) {
        mPresenter.clickAddCart();
    }


    /**
     * 点击升级会员
     */
    @OnClick(R.id.text_upgrade_member)
    public void onClickMemberUpgrade(View view) {
        mPresenter.clickMemberUpgrade();
    }


    @Override
    public void showBrandTxt(String brand) {
        mBrandTxt.setText(brand);
    }


    @Override
    public void showNameTxt(String name) {
        mNameTxt.setText(name);
    }


    public ProDetailsComponent getProDetailsComponent() {
        return mProDetailsComponent;
    }


    @Override
    public void showSubFragments(String brand, String title) {

        List<Fragment> fragList = new ArrayList<>();
        mInfoFragment = DetailInfoFragment.newInstance(brand, title);
        fragList.add(mInfoFragment);
        fragList.add(DetailPicsFragment.newInstance());

        mVerticalViewPager.setAdapter(new FragPageAdapter(getSupportFragmentManager(), fragList));
    }

    /**
     *
     * @param flag
     * @return
     */
    @Override
    public Serializable getSelectedCommodity(int flag) {
        if (mInfoFragment == null) {
            return null;
        }
        return mInfoFragment.getSelectedCommodity(flag);
    }

    /**
     *
     * @param selectedCommodity
     * @param flag
     */
    public void getSelectCommodity(Commodity selectedCommodity,int flag,int num){
        if (flag==1){
            mPresenter.setselectedCommodity(selectedCommodity,num);
        }else if (flag==2){
            mPresenter.clickNowBuy(selectedCommodity,num);
        }else if (flag==3){
            selectedCommodity.setCount(num);
            mPresenter.pingtuanBuy(true, 0,selectedCommodity);
        }
    }


    @Override
    public void showCountDownEnable(boolean show) {
        mCountDownTxt.setVisibility(show ? View.VISIBLE : View.GONE);
        mCartLayoutTv.setVisibility(show ? View.GONE : View.VISIBLE);
        mCartDividerV.setVisibility(show ? View.GONE : View.VISIBLE);
        mAddCart.setVisibility(show ? View.GONE : View.VISIBLE);
    }


    @Override
    public void showCountDown() {
        mPresenter.showCountDown(mCountDownTxt, mBuyTxt);
    }


    @Override
    public void showBuyBottom(boolean isShowBuyBottom) {
        if (isShowBuyBottom) {
            layoutMemberUpgrade.setVisibility(View.GONE);
            layoutBuy.setVisibility(View.VISIBLE);
        } else {
            layoutMemberUpgrade.setVisibility(View.VISIBLE);
            layoutBuy.setVisibility(View.GONE);
        }
    }


    @Override
    public void showUpgradeBottom(String desc, String clickDesc) {
        textMemberUpgradeDesc.setText(desc);
        textMemberUpgrade.setText(clickDesc);
    }

    @Override
    public void shareImages(List<String> urls, String QRcodeImageUrl, String shardAddHeadImageUrl) {
        ShareData data = new ShareData.Builder()
                .setType(ShareType.SYSTEM)
                .setMediaPath(urls)
                .setShardQRcodeImageUrl(QRcodeImageUrl)
                .setShardAddHeadImageUrl(shardAddHeadImageUrl)
                .build();

        ShareDialogFragment shareDialogFragment = ShareDialogFragment.newInstance(data,
                mPresenter.getProduct().getTitle() + " " + mPresenter.getProduct().getBriefin(),
                ShareDialogFragment.PRODUCT_DETAIL_SHARD);
        shareDialogFragment.show(getSupportFragmentManager(), "product_detail");
    }

    @Override
    public String getSubject_id() {
        return subject_id;
    }

    @Override
    public void showPingtuanView(boolean isPingtuan,Product product) {
        if (isPingtuan) {
            //设置单独购买和发起拼团按钮文字
            Commodity commodity = product.getSkuList().get(0);
            details_txt_add_cart_parent.setVisibility(View.GONE);
            mCartDividerV.setVisibility(View.GONE);
            StringBuilder listPrice = new StringBuilder();
            listPrice.append("¥")
                    .append(commodity.getListPrice())
                    .append("\n单独购买");
            mBuyTxt.setText(String.valueOf(listPrice));
            mBuyTxt.setCompoundDrawables(null,null,null,null);
            details_v_cart_divider_1.setVisibility(View.VISIBLE);
            details_txt_pingtuan_buy.setVisibility(View.VISIBLE);
            StringBuilder pingtuanPrice = new StringBuilder();
            pingtuanPrice.append("¥")
                    .append(commodity.getSellPrice())
                    .append("\n发起拼团");
            details_txt_myself_createPingtuan.setText(pingtuanPrice);
        }
    }

    public void hxLoginInfoGet() {
        HuanXinLoginInfo huanXinLoginData = GlobalData.getInstance().getHuanXinLoginData();
        if (huanXinLoginData != null) {
            huanxinLogin(huanXinLoginData.getUsername(), huanXinLoginData.getPassword());
            return;
        }
        apiManager.hxLoginInfoGet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HuanXinLoginInfo>(getContext()) {
                    @Override
                    public void onNext(HuanXinLoginInfo huanXinLoginInfo) {
                        GlobalData.getInstance().setHuanXinLoginData(huanXinLoginInfo);
                        huanxinLogin(huanXinLoginInfo.getUsername(), huanXinLoginInfo.getPassword());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void huanxinLogin(String username, String password) {
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            KefuHelper.getInstance().chatWithHefu(getActivity());
        } else {
            //未登录，需要登录后，再进入会话界面
            ChatClient.getInstance().login(username, password, new Callback() {
                @Override
                public void onSuccess() {
                    KefuHelper.getInstance().chatWithHefu(getActivity());
                }

                @Override
                public void onError(int code, final String error) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "环信登录失败：" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onProgress(int progress, String status) {

                }
            });
        }
    }


    /**
     * 自己发起拼团
     */
    @OnClick(R.id.details_txt_pingtuan_buy)
    public void onViewClicked() {
        //自己发起拼图案
        getSelectedCommodity(3);
//        mPresenter.pingtuanBuy(true, 0,null);
    }
}
