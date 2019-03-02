package com.a1magway.bgg.p.cart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.common.broadcast.LoginReceiver;
import com.a1magway.bgg.common.broadcast.RefreshCartReceiver;
import com.a1magway.bgg.data.entity.AgainRequestCatDataTag;
import com.a1magway.bgg.data.entity.CartProduct;
import com.a1magway.bgg.data.entity.LoginData;
import com.a1magway.bgg.data.entity.OrderDetailsCommodity;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.repository.ICartData;
import com.a1magway.bgg.di.scope.PerFragment;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.util.AmtUtil;
import com.a1magway.bgg.util.EntityTransUtil;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.Toaster;
import com.a1magway.bgg.util.dialog.BuyerCountDialog;
import com.a1magway.bgg.v.cart.ICartV;
import com.a1magway.bgg.v.main.IMainV;
import com.a1magway.bgg.v.order.OrderCommitActivity;
import com.a1magway.bgg.v.product.ProductDetailsActivity;
import com.almagway.common.utils.CollectionUtil;
import com.almagway.common.utils.StringUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.SafeObserver;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by jph on 2017/8/14.
 */
@PerFragment
public class CartP extends BaseLoadP<List<CartProduct>, ICartV> {
    private int mLastCartId = -1; // 分页使用

    @Inject
    CartAdapter mCartAdapter;
    @Inject
    ICartData mCartData;

    @Inject
    APIManager apiManager;

    private List<Integer> lastCartIds = new ArrayList<>();//填写订单去重新刷新数据需要的mLastCartId

    @Inject
    public CartP(@NonNull ICartV view) {
        super(view);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mCartAdapter.setCartData(mCartData);
        mCartAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        CartProduct cartProduct = mCartAdapter.getItem(position);
                        //商品未下架和未暂却和商品库存大于等于添加商品数，才能跳到商品详情页面
                        if (cartProduct.isEnable() && cartProduct.getTotalStock() >= cartProduct.getCount()) {
                            ProductDetailsActivity.start(
                                    v.getContext(), mCartAdapter.getItem(position).getProductId(),
                                    mCartAdapter.getItem(position).getSubject_id());
                        }
                    }

                    @Override
                    public void onItemLongClick(View v, int position) {
                    }
                });
        mCartAdapter.setOperationListener(
                new CartAdapter.OperationListener() {
                    @Override
                    public void onItemSelectedChange(int position, boolean isSelected) {
                        calcTotal();
                    }

                    @Override
                    public void onItemCountChange(int position, int newCount) {
                        calcTotal();
                    }

                    @Override
                    public void onItemDeleted(CartProduct cartProduct) {
                        mCartAdapter.removeItem(cartProduct);
                        calcTotal();

                        if (mView.getActivity() instanceof IMainV) {
                            // tab小红点数字-1
                            ((IMainV) mView.getActivity()).reduceCartNum();
                        }

                        checkShowNoneData(mCartAdapter.getList());
                    }

                    @Override
                    public void inputGoodsCount(final TextView v, final int position) {
                        BuyerCountDialog.show((Activity) getContext(), Integer.valueOf(v.getText().toString()),
                                new BuyerCountDialog.CommitCallBack() {
                                    @Override
                                    public void commit(final int count) {
                                        apiManager.changeCartProCount(GlobalData.getInstance().getUserId(), 2,
                                                mCartAdapter.getItem(position).getId(), count)
                                                .observeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                .subscribe(new SimpleObserver<Void>(getContext()) {
                                                    @Override
                                                    public void onFinish() {
                                                        super.onFinish();
                                                        mCartAdapter.getItem(position).setCount(count);
                                                        mCartAdapter.notifyItemChanged(position);
                                                        calcTotal();
                                                    }
                                                });
                                    }
                                });
//                        mCartAdapter.notifyItemChanged(position);
                    }
                });
        mView.setRecyclerViewAdapter(mCartAdapter);

        if (GlobalData.getInstance().getLoginData() == null) {
            mView.showNoneLayout4NotLogin();
        } else {
            loadData();
        }

        registerBroadCast(mLoginReceiver, mRefreshCartReceiver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterBroadCast(mLoginReceiver, mRefreshCartReceiver);
    }

    @Nullable
    @Override
    public Observable<List<CartProduct>> getDataObservable() {
        lastCartIds.add(mLastCartId);
        return mCartData
                .getCart(GlobalData.getInstance().getUserId(), mLastCartId)
                .defaultIfEmpty(new ArrayList<CartProduct>());
    }


    @Override
    protected void onLoadSuccess(List<CartProduct> cartProducts) {
        super.onLoadSuccess(cartProducts);

        if (mLastCartId == -1) {
            mCartAdapter.setList(cartProducts);
            checkShowNoneData(cartProducts);
        } else {
            if (CollectionUtil.isEmpty(cartProducts)) {
                mView.setLoadEnable();
            } else {
                mCartAdapter.addList(cartProducts);
            }
        }

        calcTotal();
    }

    @Override
    protected void onLoadFinish() {
        super.onLoadFinish();
        mView.stopRefresh();
        mView.stopLoadMore();
    }

    public void reload() {
        lastCartIds.clear();
        mLastCartId = -1;
        loadData(false);
    }

    public void loadMore() {
        if (mCartAdapter.getRealItemCount() == 0) {
            mLastCartId = -1;
        } else {
            mLastCartId = mCartAdapter.getItem(mCartAdapter.getRealItemCount() - 1).getId();
        }
        loadData(false);
    }

    /**
     * 点击下单
     */
    public void clickOrder() {
        Observable.fromIterable(mCartAdapter.getList())
                .filter(
                        new Predicate<CartProduct>() {
                            @Override
                            public boolean test(@NonNull CartProduct cartProduct) throws Exception {
                                return cartProduct.isSelected();
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
                                if (commodityList.isEmpty()) {
                                    Toaster.showShort(getContext(), R.string.cart_no_select);
                                    return;
                                }
                                OrderCommitActivity.start(getContext(), commodityList, getSRTag(commodityList));
                            }
                        });
    }

    /**
     * 计算小计
     */
    private void calcTotal() {
        int totalCount = 0;
        double totalPrice = 0;
        for (int i = 0, c = mCartAdapter.getRealItemCount(); i < c; i++) {
            CartProduct pro = mCartAdapter.getItem(i);
            if (!pro.isEnable()) {
                continue;
            }
            if (!pro.isSelected()) {
                continue;
            }
            if (pro.getTotalStock() < pro.getCount()) {
                continue;
            }
            totalCount += pro.getCount();
            totalPrice =
                    AmtUtil.add(
                            totalPrice,
                            pro.getCount() * StringUtil.parseDouble(getProductPrice(pro)));
        }
        mView.showTotal(totalCount, totalPrice);
    }

    private String getProductPrice(CartProduct product) {
        String price;
        LoginData loginData = GlobalData.getInstance().getLoginData();
        if (loginData.getRoleType() == GlobalData.USER_ROLE_TYPE_DEFAULT) {
            price = product.getSellPrice();
        } else {
            price = product.getMemberPrice();
        }
        return price;
    }

    private LoginReceiver mLoginReceiver =
            new LoginReceiver() {
                @Override
                public void onLogin() {
                    reload();
                }

                @Override
                public void onLogout() {
                    mCartAdapter.clearList();
                    mView.showNoneLayout4NotLogin();
                    mView.showTotal(0, 0);
                }
            };

    private RefreshCartReceiver mRefreshCartReceiver =
            new RefreshCartReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    reload();
                }
            };

    //传递到填写订单页面，重新刷新产品数据需要
    private AgainRequestCatDataTag getSRTag(List<OrderDetailsCommodity> commodityList) {
        AgainRequestCatDataTag orderRefreshTag = new AgainRequestCatDataTag();
        orderRefreshTag.setLastCartIds(lastCartIds);
        List<Integer> selectedIds = new ArrayList<>();
        for (OrderDetailsCommodity orderDetailsCommodity : commodityList) {
            selectedIds.add(orderDetailsCommodity.getProductId());
        }
        orderRefreshTag.setSelectedIds(selectedIds);
        return orderRefreshTag;
    }
}
