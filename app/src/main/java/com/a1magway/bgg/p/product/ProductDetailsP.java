package com.a1magway.bgg.p.product;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.broadcast.RefreshCartReceiver;
import com.a1magway.bgg.data.entity.Commodity;
import com.a1magway.bgg.data.entity.InvitationCodeData;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.entity.Product;
import com.a1magway.bgg.data.entity.ShardXcxQRCode;
import com.a1magway.bgg.data.net.APICodes;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IProductData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.eventbus.event.BindInvitationSuccessEvent;
import com.a1magway.bgg.eventbus.event.LoginSuccessEvent;
import com.a1magway.bgg.eventbus.event.PingtuanBuyEvent;
import com.a1magway.bgg.eventbus.event.PingtuanPaySuccess;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.EntityTransUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.v.invitation.InvitationActivity;
import com.a1magway.bgg.v.member.MemberUpgradeInfoActivity;
import com.a1magway.bgg.v.order.OrderCommitActivity;
import com.a1magway.bgg.util.DownloadShardImageUtils;
import com.a1magway.bgg.v.product.IProductDetailsV;
import com.almagway.common.utils.ToastUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jph on 2017/8/7.
 */
@PerActivity
public class ProductDetailsP extends BaseLoadP<Product, IProductDetailsV> {

    @Inject
    IProductData mProductData;

    @Named(value = "productId")
    @Inject
    int mProductId;

    @Inject
    APIManager apiManager;


    private Product mProduct;
    private TimeCountDownController timeCountDownController;
    private String QRcodeImageUrl;//分享添加到最后一张的图片url
    private String shardAddHeadImageUrl;//分享添加到第一张的图片url
    private boolean noBindInvitation;

    @Inject
    public ProductDetailsP(@NonNull IProductDetailsV view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        EventBus.getDefault().register(this);

        loadData();
        getInvitationCode();
        getAddHeadShardImageUrl();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timeCountDownController != null) {
            timeCountDownController.cancel();
            timeCountDownController = null;
        }
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public Observable<Product> getDataObservable() {
        if (mView.getSubject_id().isEmpty()) {
            return mProductData.getProductDetails(mProductId);
        } else {
            return mProductData.getProductDetails(mProductId, Integer.valueOf(mView.getSubject_id()));
        }
    }

    @Override
    protected void onLoadError(Throwable e) {
        super.onLoadError(e);
        //        mView.getActivity().finish();
    }


    @Override
    protected void onLoadSuccess(Product product) {
        super.onLoadSuccess(product);
        mProduct = product;

        if (mProduct.isSeckill()) {
            mView.showCountDownEnable(true);
            mView.showCountDown();
        } else {
            mView.showCountDownEnable(false);
        }
        mView.showSubFragments(product.getBrand(), product.getTitle());
        checkShowBuyStatus();
        //
//        DownloadShardImageUtils.saveMultipleImagesToSd(mProduct.getBigPicList());
        if (product.getIsCollage() == 1) {
            mView.showPingtuanView(true,product);
        } else {
            mView.showPingtuanView(false,product);
        }
    }


    public Product getProduct() {
        return mProduct;
    }

    public void checkShowBuyStatus() {
        if (mProduct == null) return;
        boolean isShowBuyBottom = true;
        String desc = "";
        String clickDesc = "";
        // 2代表是会员商品
        if (mProduct.getProductType().equals("2")) {
            LoginData loginData = GlobalData.getInstance().getLoginData();
            if (loginData == null) {
                isShowBuyBottom = false;
                desc = "请先登录";
                clickDesc = "立刻登录";
            } else if (loginData.getRoleType() == GlobalData.USER_ROLE_TYPE_DEFAULT) {
                isShowBuyBottom = false;
                desc = "您还不是会员";
                clickDesc = "立即升级";
            }
        }
        mView.showBuyBottom(isShowBuyBottom);
        mView.showUpgradeBottom(desc, clickDesc);
    }

    public void clickShare() {
        //未登录跳转到登录页面
        if (JumpUtil.processCheckLogin(getContext())) {
            return;
        }
        //提示用户去绑定邀请码
        if (noBindInvitation) {
            ToastUtil.showShort("绑定邀请码后才能分享图片");
            Observable.timer(100, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            new AlertDialog.Builder(getContext()).setMessage("是否跳转到绑定邀请码页面?")
                                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            InvitationActivity.start(getContext());
                                        }
                                    }).setNegativeButton("否", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setCancelable(false).show();
                        }
                    });
            return;
        }
        if (QRcodeImageUrl == null || shardAddHeadImageUrl == null) {
            ToastUtil.showShort("正在获取分享数据...");
            return;
        }
        if (mProduct == null) {
            ToastUtil.showShort("商品已下架");
            return;
        }

        List<String> sharePics = new ArrayList<>();
        Commodity selectedCommodity = (Commodity) mView.getSelectedCommodity(0);
        if (selectedCommodity == null) {//选中规格组合为空时分享第一条商品的图片
            sharePics.addAll(mProduct.getSkuList().get(0).getSkuCover());
        } else {
            sharePics.addAll(selectedCommodity.getSkuCover());
        }

        if (sharePics == null || sharePics.size() <= 0) {
            ToastUtil.showShort("没有可以分享的图片");
            return;
        }
        mView.shareImages(sharePics, QRcodeImageUrl, shardAddHeadImageUrl);
    }

    /**
     * 立即购买
     */
    public void clickBuy(int flag) {
        if (JumpUtil.processCheckLogin(getContext())) {
            return;
        }
        mView.getSelectedCommodity(flag);
    }

    /**
     * 立即购买 选择商品属性回调
     * @param selectedCommodity
     */
    public void clickNowBuy(Commodity selectedCommodity,int num){

        if (selectedCommodity == null) {
            // 未选择完整的规格组合
            Toaster.showShort(getContext(), R.string.toast_not_whole_sku);
            return;
        }

        List<OrderDetailsCommodity> commodityList = new ArrayList<>();
        selectedCommodity.setCount(num);
        commodityList.add(EntityTransUtil.commodity2OrderDetailsCommodity(selectedCommodity));

        if (mProduct.getIsCollage() == 1){
            //拼团商品，自己单独购买
            EventBus.getDefault().post(new PingtuanBuyEvent(-1,selectedCommodity));
        }else {
            //正常商品
            OrderCommitActivity.start(getContext(), commodityList, mProduct.isSeckill(), null);
        }
    }


    /**
     * 加入购物车
     * @param selectedCommodity
     */
    public void setselectedCommodity(Commodity selectedCommodity,int num){

        if (selectedCommodity == null) {
            // 未选择完整的规格组合
            Toaster.showShort(getContext(), R.string.toast_not_whole_sku);
            return;
        }

        mView.showLoadingDialog();
        mProductData
                .add2Cart(
                        GlobalData.getInstance().getUserId(),
                        mProduct.getId(),
                        selectedCommodity.getSkuId(),
                        num)
                .subscribe(
                        new BaseObserver<String>(getContext()) {
                            @Override
                            public void onNext(@io.reactivex.annotations.NonNull String s) {
                                Toaster.showShort(getContext(), s);
                                RefreshCartReceiver.sendRefreshCartBroadcast(getContext());
                            }

                            @Override
                            public void onComplete() {
                                mView.hideLoadingDialog();
                            }
                        });
    }

    /**
     * 加入购物车
     */
    public void clickAddCart() {
        if (JumpUtil.processCheckLogin(getContext())) {
            return;
        }
        Commodity selectedCommodity = (Commodity) mView.getSelectedCommodity(1);

//        if (selectedCommodity == null) {
//            // 未选择完整的规格组合
//            Toaster.showShort(getContext(), R.string.toast_not_whole_sku);
//            return;
//        }
//
//        mView.showLoadingDialog();
//        mProductData
//                .add2Cart(
//                        GlobalData.getInstance().getUserId(),
//                        mProduct.getId(),
//                        selectedCommodity.getSkuId(),
//                        1)
//                .subscribe(
//                        new BaseObserver<String>(getContext()) {
//                            @Override
//                            public void onNext(@io.reactivex.annotations.NonNull String s) {
//                                Toaster.showShort(getContext(), s);
//                                RefreshCartReceiver.sendRefreshCartBroadcast(getContext());
//                            }
//
//                            @Override
//                            public void onComplete() {
//                                mView.hideLoadingDialog();
//                            }
//                        });
    }

    /**
     * 倒计时 即将开始的到开始的时候回进行正在进行的倒计时{@link com.a1magway.bgg.data.entity.SecKillTypes}
     */
    public void showCountDown(final TextView mCountDownTxt, final TextView mBuyTxt) {
        Calendar calendar = Calendar.getInstance();
        final boolean isEnable = calendar.getTimeInMillis() > mProduct.getStartTime();
        mBuyTxt.setEnabled(isEnable);
        long millis = isEnable ? mProduct.getEndTime() : mProduct.getStartTime();
        calendar.setTimeInMillis(millis);
        int formatResId =
                isEnable
                        ? R.string.product_details_format_count_down
                        : R.string.product_details_format_count_down_wait;
        timeCountDownController =
                new TimeCountDownController(
                        calendar.getTime(),
                        mCountDownTxt,
                        getContext().getString(formatResId),
                        new TimeCountDownController.CountDownListener() {
                            @Override
                            public void onFinish() {
                                if (isEnable) {
                                    mCountDownTxt.setText(
                                            TimeCountDownController.TIME_DEFAULT);
                                    mBuyTxt.setEnabled(false);
                                } else {
                                    showCountDown(mCountDownTxt, mBuyTxt);
                                }
                            }
                        })
                        .start();
    }

    /**
     * 跳转会员分享界面，如果未登录则跳转登录页面
     */
    public void clickMemberUpgrade() {
        if (JumpUtil.processCheckLogin(getContext())) {
            return;
        }
        MemberUpgradeInfoActivity.start(getContext());
    }

    /**
     * 得到邀请码，为分享做准备
     */
    private void getInvitationCode() {
        final LoginData data = GlobalData.getInstance().getLoginData();
        if (data != null) {
            apiManager.getInviteCode(data.getId())
                    .compose(this.<InvitationCodeData>bindToLifecycle())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<InvitationCodeData>() {
                        @Override
                        public void accept(InvitationCodeData invitationCodeData) throws Exception {
                            if (invitationCodeData.getInviteCode() != null) {
                                noBindInvitation = false;
                                getShardQRcodeImage(mProductId, invitationCodeData.getInviteCode());
                            } else {
                                noBindInvitation = true;
                                LogUtil.i("productDetailsP", "获取邀请码失败");
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            noBindInvitation = true;
                            LogUtil.i("productDetailsP", "获取邀请码失败");
                        }
                    });
        }
    }

    /**
     * 得到要分享的二维码图片的url
     */
    private void getShardQRcodeImage(int mProductId, String inviteCode) {
        //得到分享添加到最后一张的图片
        apiManager.getShardQRCodeImage(mProductId, inviteCode)
                .subscribeOn(Schedulers.io())
                .compose(this.<ShardXcxQRCode>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShardXcxQRCode>() {
                    @Override
                    public void accept(ShardXcxQRCode shardXcxQRCode) throws Exception {
                        if (shardXcxQRCode.getCode() == APICodes.SUCCESS) {
                            QRcodeImageUrl = shardXcxQRCode.getData();
                            saveQRCodeToSd(QRcodeImageUrl);
                        } else {
                            Toaster.showShort(getContext(), shardXcxQRCode.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    private void getAddHeadShardImageUrl() {
        //得到分享添加到第一张的图片
        apiManager.getAddHeadShardImage()
                .subscribeOn(Schedulers.io())
                .compose(this.<ShardXcxQRCode>bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ShardXcxQRCode>() {
                    @Override
                    public void accept(ShardXcxQRCode shardXcxQRCode) throws Exception {
                        if (shardXcxQRCode.getCode() == APICodes.SUCCESS) {
                            shardAddHeadImageUrl = shardXcxQRCode.getData();
                            saveQRCodeToSd(shardAddHeadImageUrl);
                        } else {
                            Toaster.showShort(getContext(), shardXcxQRCode.getMsg());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


    private void saveQRCodeToSd(String qRcodeImageUrl) {
        Observable.just(qRcodeImageUrl)
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        DownloadShardImageUtils.saveSingleImageToSdCard(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }


    private boolean myCreate=false;
    private int collageOrderId=0;
    /**
     * 拼团购买
     *
     * @param myCreate 是否是自己发起的拼团，true是，false不是
     */
    public void pingtuanBuy(boolean myCreate, int collageOrderId, Commodity selectedCommodity) {
        if (JumpUtil.processCheckLogin(getContext())) {
            return;
        }
        if (selectedCommodity == null) {
            // 未选择完整的规格组合
            Toaster.showShort(getContext(), R.string.toast_not_whole_sku);
            return;
        }
//        this.myCreate=myCreate;
//        this.collageOrderId=collageOrderId;
        //自己发起的拼团
//        if (myCreate){
//            mView.getSelectedCommodity(3);
//            //拼团购买
//        }else{
//            danduPinTuanBuy(selectedCommodity);
//        }
//        danduPinTuanBuy(selectedCommodity);

        //        Commodity selectedCommodity = (Commodity) mView.getSelectedCommodity(3);
        List<OrderDetailsCommodity> commodityList = new ArrayList<>();
        commodityList.add(EntityTransUtil.commodity2OrderDetailsCommodity(selectedCommodity));

        OrderCommitActivity.start(getContext(), commodityList, mProduct.isSeckill(), null,
                true, myCreate, collageOrderId);

    }

    /**
     * 拼团购买 选择产品之后的回调
     * @param selectedCommodity
     */
    public void danduPinTuanBuy(Commodity selectedCommodity){
        if (selectedCommodity == null) {
            // 未选择完整的规格组合
            Toaster.showShort(getContext(), R.string.toast_not_whole_sku);
            return;
        }

        List<OrderDetailsCommodity> commodityList = new ArrayList<>();
        commodityList.add(EntityTransUtil.commodity2OrderDetailsCommodity(selectedCommodity));

        OrderCommitActivity.start(getContext(), commodityList, mProduct.isSeckill(), null,
                true, myCreate, collageOrderId);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(LoginSuccessEvent event) {
        getInvitationCode();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BindInvitationSuccessEvent event) {
        noBindInvitation = false;
        getInvitationCode();
    }

}


