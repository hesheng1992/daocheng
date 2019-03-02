package com.a1magway.bgg.p.order;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AddressData;
import com.a1magway.bgg.data.entity.AgainRequestCatDataTag;
import com.a1magway.bgg.data.entity.CartProduct;
import com.a1magway.bgg.data.entity.CheckOrderProResult;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.OrderCommitResult;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.IOrderCommitData;
import com.a1magway.bgg.di.scope.PerActivity;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.p.personal.AddressManagerP;
import com.a1magway.bgg.util.EntityTransUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.util.StringFormatUtil;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.code.RequestCode;
import com.a1magway.bgg.util.dialog.GeneralImageTextDialog;
import com.a1magway.bgg.v.order.IOrderCommitV;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jph on 2017/8/23.
 */
@PerActivity
public class OrderCommitP extends BasePresenter<IOrderCommitV> {
    public static boolean showPingTuanPrice;//是否显示拼成价

    @Inject
    ProListAdapter mAdapter;
    AddressData mAddress;
    @Inject
    IOrderCommitData mOrderCommitData;
    @Named(value = "IsSeckill")
    @Inject
    boolean mIsSeckill;

    @Named(value = "IsPingtuan")
    @Inject
    boolean isPingtuan;//是否是拼团

    @Named(value = "IsMyCreatePt")
    @Inject
    boolean isMyCreatePt;//是否是我创建的拼团

    @Named(value = "CollageOrderId")
    @Inject
    int collageOrderId;//拼团id;-1拼团单独购买，等于0是自己创建拼团，其余值是参与别人的拼团

    @Inject
    AgainRequestCatDataTag orderRefreshTag;

    @Inject
    APIManager apiManager;


    private CheckOrderProResult mCheckOrderProResult;

    /**
     * 重新请求购物车数据
     */
    private List<CartProduct> mcartProducts = new ArrayList<>();
    private volatile int requestSize;
    private boolean isOverSea;//是海外仓
    private String buyStatus;


    @Inject
    public OrderCommitP(@NonNull IOrderCommitV view) {
        super(view);
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initBuyStatus();
        getCartIds();
//        mAdapter.setOnItemClickListener(new DetailsProItemCLickListener(mAdapter));
        /*if (orderRefreshTag.getSelectedIds() == null) {
            mView.setRecyclerViewAdapter(mAdapter);
        } else {
            againRequestCartData();
        }*/

        mAddress = GlobalData.getInstance().getLoginData().getDefaultAddress();
//        if (mAddress == null) {
//            //无默认地址
//            mView.showDefaultAddressView(false);
//        } else {
//            mView.showDefaultAddressView(true);
//
//            showAddress(mAddress);
//        }

        checkPro();

        LogUtil.e("ppppppfff", isPingtuan + " " + isMyCreatePt + " " + collageOrderId);
    }


    /**
     * 提交订单
     */
    public void commitOrder(String remark) {
        if (JumpUtil.processCheckInvited(getContext())) {
            return;
        }
        if (mAddress == null || mAddress.getId() <= 0) {
            Toaster.showShort(getContext(), R.string.order_commit_toast_null_address);
            return;
        }
        if (mCheckOrderProResult == null) {
            return;
        }


        if (buyStatus.equals("1")) {
            //拼团商品
            if (!mView.agreePingtuanRule()) {
                Toaster.showShort(getContext(), "请同意并勾选拼团规则");
                return;
            } else {
                mCheckOrderProResult.setCollageOrderId(collageOrderId);
            }
        }
        mCheckOrderProResult.setBuyStatus(Integer.valueOf(buyStatus));
        mView.showLoadingDialog();
        mOrderCommitData.commitOrder(mIsSeckill, mAddress.getId(), remark, mAdapter.getList(),
                mCheckOrderProResult)
                .compose(this.<OrderCommitResult>bindToDestroyEvent())
                .subscribe(new BaseObserver<OrderCommitResult>(getContext()) {
                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        super.onError(e);
                        mView.hideLoadingDialog();
                    }


                    @Override
                    public void onNext(OrderCommitResult orderCommitResult) {
                        //创建订单成功,显示支付弹窗
//                       OrderPayActivity.start(getContext(), orderCommitResult.getOrderNum(), orderCommitResult.getOrderId());
                        mView.backOrderInfo(orderCommitResult.getOrderNum(), orderCommitResult.getOrderId());
                    }


                    @Override
                    public void onComplete() {
                        mView.hideLoadingDialog();
                    }
                });
    }

    private void showAddress(AddressData address) {
        //显示地址信息
        mView.showName(address.getOrderuserName());
        mView.showPhone(address.getOrderuserPhone());
        mView.showDetailsAddress(address.getOrderuserDetailAddress());
        mView.showPostcode(address.getPostCode());
        if (isOverSea) {
            mView.setIdentityNumber(address.getIdCard());
            mView.setRealName(address.getRealName());
            LogUtil.e("number-------", address.getIdCard() + " " + address.getRealName());
        }
    }


    /**
     * 处理选择地址的结果
     */
    public void handleSelectAddressResult(int requestCode, int resultCode, Intent data) {

        if (requestCode != RequestCode.REQUEST_CODE_ADDRESS_SELECT ||
                resultCode != Activity.RESULT_OK || data == null) {
            //在地址管理界面可能会更改默认地址 此时需要更新默认地址
            mAddress = GlobalData.getInstance().getLoginData().getDefaultAddress();
            if (mAddress == null) {
                //无默认地址
                mView.showDefaultAddressView(false);
            } else {
                mView.showDefaultAddressView(true);

                showAddress(mAddress);
            }
            return;
        }

        AddressData addressData = (AddressData) data.getSerializableExtra(
                AddressManagerP.EXTRA_ADDRESS_DATA);
        if (addressData != null) {
            mAddress = addressData;
            mView.showDefaultAddressView(true);
            showAddress(mAddress);
        }
    }

    private List<Integer> cartIds = new ArrayList<>();

    private void getCartIds() {
        List<OrderDetailsCommodity> oldCommodityList = mAdapter.getList();
        for (OrderDetailsCommodity orderDetailsCommodity : oldCommodityList) {
            cartIds.add(orderDetailsCommodity.getCartId());
        }
    }


    private void initBuyStatus() {
        //0普通购买，2拼团购买，1发起拼团；
        switch (collageOrderId) {
            case -100://默认值
                buyStatus = "0";//正常商品
                showPingTuanPrice = false;
                break;
            case -1:
                buyStatus = "0";//拼团商品自己购买
                showPingTuanPrice = false;
                break;
            case 0:
                buyStatus = "1";//参与拼团
                showPingTuanPrice = true;
                break;
            default:
                buyStatus = "1";//参与拼团
                showPingTuanPrice = true;
                break;
        }
        LogUtil.e("gggggg", buyStatus + "");
    }

    /**
     * 校验商品
     */
    private void checkPro() {
        mView.showLoadingDialog();
        //Module里提供的Adapter已经放入了实际数据
        mOrderCommitData.checkOrderPro(mAdapter.getList(), buyStatus)
                .compose(this.<CheckOrderProResult>bindToDestroyEvent())
                .subscribe(new BaseObserver<CheckOrderProResult>(getContext()) {
                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        super.onError(e);
                        //校验失败关闭界面
                        mView.hideLoadingDialog();
                        mView.finish();
                    }


                    @Override
                    public void onNext(
                            @io.reactivex.annotations.NonNull CheckOrderProResult checkOrderProResult) {
                        mCheckOrderProResult = checkOrderProResult;
                        checkIsOverSea(mCheckOrderProResult);//检查是否是海外仓
                        if (mAddress == null) {
                            //无默认地址
                            mView.showDefaultAddressView(false);
                        } else {
                            mView.showDefaultAddressView(true);
                            showAddress(mAddress);
                        }
                        showTotalInfo(mCheckOrderProResult);

                        //显示拼团须知
                        if (isPingtuan) {
                            mView.showPingtuanView();
                        }

                        //返回数据中没有cartId,将购物车或商品详情传递过来的carId设置到数据中，提交订单有用
                        List<OrderDetailsCommodity> orderDetailsCommodityList = mCheckOrderProResult.getShopcartList();
                        for (int i = 0; i < orderDetailsCommodityList.size(); i++) {
                            orderDetailsCommodityList.get(i).setCartId(cartIds.get(i));
                        }
                        //设置列表数据
                        mAdapter.clearList();
                        mAdapter.addList(mCheckOrderProResult.getShopcartList());
                        mView.setRecyclerViewAdapter(mAdapter);
                    }


                    @Override
                    public void onComplete() {
                        mView.hideLoadingDialog();
                    }
                });
    }

    /**
     * 检查是否是海外仓
     */
    private void checkIsOverSea(CheckOrderProResult checkOrderProResult) {
        //是海外仓
        if (checkOrderProResult.getIsOverSea() == 1) {
            isOverSea = true;
//            mView.showIsOverSeaView();
        }
    }

    /**
     * 返回是否是海外仓
     */
    public boolean isOverSea() {
        return isOverSea;
    }

    /**
     * 显示小计
     */
    private void showTotalInfo(CheckOrderProResult checkOrderProResult) {
        mView.showTotalProPrice(StringFormatUtil.format(getContext(),
                R.string.order_details_format_pro_price,
                StringFormatUtil.getPrice(getContext(), checkOrderProResult.getSellPrice())));
        mView.showTax(StringFormatUtil.format(getContext(), R.string.order_details_format_tax,
                StringFormatUtil.getPrice(getContext(), checkOrderProResult.getTax())));
        mView.showFreight(checkFreight(checkOrderProResult));
        mView.showTotalPrice(StringFormatUtil.format(getContext(),
                R.string.order_details_format_total_price,
                StringFormatUtil.getPrice(getContext(), checkOrderProResult.getTotalCost())));
        mView.showPayPrice(StringFormatUtil.format(getContext(),
                R.string.order_details_format_pay_price,
                StringFormatUtil.getPrice(getContext(), checkOrderProResult.getTotalCost())));
        mView.showPayEnable(true);
        mView.showTotalCount(checkOrderProResult.getCount() + "件商品");
    }


    private CharSequence checkFreight(CheckOrderProResult checkOrderProResult) {
        String freight = StringFormatUtil.format(getContext(),
                R.string.order_details_format_freight,
                StringFormatUtil.getPrice(getContext(), checkOrderProResult.getFreight()));
        if (checkOrderProResult.getPackageMail() == CheckOrderProResult.PACKAGE_MAIL_ON) {
            String postageOff = freight + getContext().getString(R.string.cancel_postage);
            SpannableString spannableString = new SpannableString(postageOff);
            spannableString.setSpan(new StrikethroughSpan(), 3, freight.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(
                    new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_grey)), 0,
                    freight.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableString.setSpan(
                    new ForegroundColorSpan(getContext().getResources().getColor(R.color.text_red)),
                    freight.length(),
                    postageOff.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
        }
        return freight;
    }


    //购物车来的页面重新请求数据(保证价格一致)
    private void againRequestCartData() {
        final List<Integer> mLastCartIds = distinctBySetOrder(orderRefreshTag.getLastCartIds());
        for (Integer lastCartId : mLastCartIds) {
            apiManager.getCart(GlobalData.getInstance().getUserId(), lastCartId)
                    .subscribe(new Consumer<List<CartProduct>>() {
                        @Override
                        public void accept(List<CartProduct> cartProducts) throws Exception {
                            mcartProducts.addAll(cartProducts);
                            requestSize++;
                            if (mLastCartIds.size() == requestSize) {
                                getAdapterData();
                            }
                        }
                    });
        }
    }

    //购物车来的页面封装adapter数据
    private void getAdapterData() {
        Observable.fromIterable(mcartProducts)
                .filter(
                        new Predicate<CartProduct>() {
                            @Override
                            public boolean test(@NonNull CartProduct cartProduct) throws Exception {
                                return isSelected(cartProduct.getProductId());
                            }
                        })
                .map(
                        new Function<CartProduct, OrderDetailsCommodity>() {
                            @Override
                            public OrderDetailsCommodity apply(@NonNull CartProduct cartProduct)
                                    throws Exception {
                                return EntityTransUtil.cartPro2OrderDetailsCommodity(cartProduct);
                            }
                        })
                .toList()
                .subscribe(
                        new Consumer<List<OrderDetailsCommodity>>() {
                            @Override
                            public void accept(@NonNull List<OrderDetailsCommodity> commodityList)
                                    throws Exception {
                                mAdapter.clearList();
                                mAdapter.addList(commodityList);
                                mView.setRecyclerViewAdapter(mAdapter);
                            }
                        });
    }

    //购物车提交订单的商品id
    private boolean isSelected(int id) {
        for (Integer selectedProductId : orderRefreshTag.getSelectedIds()) {
            if (id == selectedProductId) {
                return true;
            }
        }
        return false;
    }

    //去重
    private List<Integer> distinctBySetOrder(List<Integer> list) {
        Set<Integer> set = new HashSet<>();
        List<Integer> newList = new ArrayList<>();
        for (Integer t : list) {
            if (set.add(t)) {
                newList.add(t);
            }
        }
        return newList;
    }


    /**
     * 身份证实名认证
     */
    public void toUserCertification(final String realName, final String idCard) {
        apiManager.userCertification(realName, idCard)
                .compose(this.<APIResponse<String>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<String>>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(APIResponse<String> stringAPIResponse) {
                        super.onNext(stringAPIResponse);
                        if (stringAPIResponse.isSuccess()) {
                            saveUserIcardInfo(realName, idCard);
                            toAdressAutonym(GlobalData.getInstance().getUserId(),
                                    mView.getRealName(),
                                    mView.getIdentityNumber(),
                                    String.valueOf(mAddress.getId()));
                        } else {
                            GeneralImageTextDialog.show(mView.getActivity(), R.drawable.ic_warning_gray, R.string.dialog_identity_card_error);
                        }
                    }
                });
    }

    /**
     * 收货地址添加实名信息
     */
    private void toAdressAutonym(int user_id, String realName, String idCard, String addressId) {
        apiManager.adressAutonym(user_id, realName, idCard, addressId)
                .compose(this.<APIResponse<String>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .subscribe(new SimpleObserver<APIResponse<String>>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(APIResponse<String> stringAPIResponse) {
                        super.onNext(stringAPIResponse);
                        if (stringAPIResponse.isSuccess()) {
                            commitOrder(mView.getBeizhu());
                        }
                    }
                });
    }


    /**
     * 保存用户实名信息到本地
     */
    private void saveUserIcardInfo(String realName, String idCard) {
        if (!mAddress.getIsDefault()) {
            return;
        }
        LoginData loginData = GlobalData.getInstance().getLoginData();
        mAddress.setRealName(realName);
        mAddress.setIdCard(idCard);
        loginData.setDefaultAddress(mAddress);
        GlobalData.getInstance().setLoginData(loginData);
    }
}
