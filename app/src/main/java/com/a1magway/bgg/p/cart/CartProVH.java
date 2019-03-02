package com.a1magway.bgg.p.cart;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.CartProduct;
import com.a1magway.bgg.data.repository.ICartData;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.StringFormatUtil;
import com.a1magway.bgg.util.dialog.BuyerCountDialog;
import com.almagway.common.utils.StringUtil;
import com.jakewharton.rxbinding2.view.RxView;

import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * 购物车商品item对应的ViewHolder Created by jph on 2017/8/14.
 */
public class CartProVH extends BaseRecyclerVH<CartProduct> {

    private CartAdapter.OperationListener mOperationListener;
    private ICartData mCartData;

    @BindView(R.id.pro_txt_brand)
    TextView mBrandTxt;

    @BindView(R.id.pro_img)
    ImageView mPicImg;

    @BindView(R.id.pro_txt_name)
    TextView mNameTxt;

    @BindView(R.id.pro_txt_sku)
    TextView mSkuTxt;

    @BindView(R.id.pro_txt_origin_price)
    TextView mOriginPriceTxt;

    @BindView(R.id.pro_txt_price)
    TextView mPriceTxt;

    @BindView(R.id.pro_txt_discount)
    TextView mDiscountTxt;

    @BindView(R.id.pro_txt_count)
    TextView mCountTxt;

    @BindView(R.id.pro_layout_reduce)
    ViewGroup mReduceLayout;

    @BindView(R.id.pro_layout_plus)
    ViewGroup mPlusLayout;

    @BindView(R.id.pro_txt_delete)
    TextView mDeleteTxt;

    @BindView(R.id.pro_txt_stock)
    TextView mStockTxt;

    @BindView(R.id.pro_txt_valid)
    TextView mValidTxt;

    @BindView(R.id.pro_v_layer)
    View mInvalidV;

    private int currentCount;

    public CartProVH(@NonNull ViewGroup parent) {
        super(parent, R.layout.cart_item_list_pro);

        mOriginPriceTxt.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); // 中划线
    }

    public void setOperationListener(CartAdapter.OperationListener operationListener) {
        mOperationListener = operationListener;
    }

    public void setCartData(ICartData cartData) {
        mCartData = cartData;
    }

    public void showViewContent(final CartProduct cartProduct, final int position) {
        super.showViewContent(cartProduct);

        ImageLoaderUtil.displayImage(mPicImg, cartProduct.getCoverUrl());
        mBrandTxt.setText(cartProduct.getBrand());
        mSkuTxt.setText(cartProduct.getSpecs());
        mOriginPriceTxt.setText(
                StringFormatUtil.getPrice(getContext(), cartProduct.getListPrice()));

        String priceText = StringFormatUtil.getPrice(getContext(), cartProduct.getSellPrice());
        String memberPrice =
                getContext().getResources().getString(R.string.member_price)
                        + StringFormatUtil.getPrice(getContext(), cartProduct.getMemberPrice());
        if (cartProduct.getProductType() == 2) {
            memberPrice = StringFormatUtil.getPrice(getContext(), cartProduct.getMemberPrice());
            priceText = "";
        }
        mPriceTxt.setText(priceText);
        mDiscountTxt.setText(memberPrice);

        // 显示名称和商品货号
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        ssb.append(cartProduct.getProductName());
        String code = cartProduct.getProductCode();
        if (StringUtil.isNotEmpty(code)) {
            int begin =
                    cartProduct.getProductName() == null
                            ? 0
                            : cartProduct.getProductName().length();
            ssb.append(code);
            ssb.setSpan(
                    new ForegroundColorSpan(
                            ContextCompat.getColor(getContext(), R.color.text_grey)) {
                        @Override
                        public void updateDrawState(TextPaint ds) {
                            ds.setTextSize(
                                    getContext()
                                            .getResources()
                                            .getDimensionPixelSize(R.dimen.text_size_s));
                            super.updateDrawState(ds);
                        }
                    },
                    begin,
                    ssb.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        mNameTxt.setText(ssb);

        showCount(cartProduct, position);
        showValid(cartProduct, position, currentCount);

        setDeleteClick(cartProduct);
    }

    /**
     * 根据是否有效显示不同样式
     */
    private void showValid(final CartProduct cartProduct, final int position, int count) {
        if (!cartProduct.isEnable()) {
            mInvalidV.setVisibility(View.VISIBLE);
            mValidTxt.setVisibility(View.VISIBLE);
            mDeleteTxt.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            mDeleteTxt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_cart_delete_white, 0, 0, 0);
            mBrandTxt.setSelected(false);
            cartProduct.setSelected(false);
            mBrandTxt.setOnClickListener(null);

            if (!cartProduct.isValid()) {
                // 已下架
                mValidTxt.setText(R.string.cart_txt_invalid);
            } else {
                // 无货
                mValidTxt.setText(R.string.cart_txt_null_stock);
            }

        } else if (cartProduct.getTotalStock() < count) {
            //库存不足
            mInvalidV.setVisibility(View.VISIBLE);
            mValidTxt.setVisibility(View.VISIBLE);
            mDeleteTxt.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            mDeleteTxt.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_cart_delete_white, 0, 0, 0);
            mBrandTxt.setSelected(false);
            cartProduct.setSelected(false);
            mBrandTxt.setOnClickListener(null);
            // 商品数量不足
            mValidTxt.setText(R.string.cart_txt_insufficient);
        } else {
            mInvalidV.setVisibility(View.GONE);
            mValidTxt.setVisibility(View.GONE);
            mDeleteTxt.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
            mDeleteTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_clear, 0, 0, 0);
            mBrandTxt.setSelected(cartProduct.isSelected());
            mBrandTxt.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean newStatus = !cartProduct.isSelected();
                            cartProduct.setSelected(newStatus);
                            mBrandTxt.setSelected(newStatus);

                            if (mOperationListener != null) {
                                mOperationListener.onItemSelectedChange(position, newStatus);
                            }
                        }
                    });
        }
    }

    /**
     * 购买数量的显示
     */
    private void showCount(final CartProduct cartProduct, final int position) {
        // 恢复初始状态，避免item view重用的问题
        currentCount = cartProduct.getCount();
        mCountTxt.setText(String.valueOf(cartProduct.getCount()));
        mStockTxt.setText(
                String.format(
                        getContext().getString(R.string.cart_format_stock),
                        cartProduct.getTotalStock()));
        mStockTxt.setVisibility(View.GONE);
        mReduceLayout.setEnabled(true);
        mPlusLayout.setEnabled(true);

        if (!cartProduct.isEnable()) {
            // 已经下架||无货
            mReduceLayout.setOnClickListener(null);
            mPlusLayout.setOnClickListener(null);

            return;
        }
        final CountHelper countHelper =
                CountHelper.create(
                        1,
                        Integer.MAX_VALUE,
//                        cartProduct.getTotalStock(),
                        new CountHelper.OnCountChangeListener() {
                            @Override
                            public void onPlusEnableChange(boolean enable) {
                                mPlusLayout.setEnabled(enable);
                                mStockTxt.setVisibility(enable ? View.GONE : View.VISIBLE);
                            }

                            @Override
                            public void onReduceEnableChange(boolean enable) {
                                mReduceLayout.setEnabled(enable);
                            }

                            @Override
                            public void onCountChange(int newCount) {
                                cartProduct.setCount(newCount);
                                mCountTxt.setText(String.valueOf(newCount));
                                showValid(cartProduct, position, newCount);
                                if (mOperationListener != null) {
                                    mOperationListener.onItemCountChange(position, newCount);
                                }
                            }
                        });

        countHelper.init(cartProduct.getCount());

        setChangeCountClick(0, cartProduct.getId(), countHelper);
        setChangeCountClick(1, cartProduct.getId(), countHelper);

        //手动输入购买商品数量
        mCountTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperationListener.inputGoodsCount((TextView) v, position);
            }
        });
    }


    /**
     * 设置改变数量点击事件
     *
     * @param type 0+，1-
     */
    private void setChangeCountClick(
            final int type, final int cartId, final CountHelper countHelper) {
        final View v = type == 0 ? mPlusLayout : mReduceLayout;
        RxView.clicks(v)
                .subscribe(
                        new Consumer<Object>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Object o)
                                    throws Exception {
                                mCartData
                                        .changeProCount(
                                                GlobalData.getInstance().getUserId(), type, cartId)
                                        .subscribe(
                                                new SimpleObserver<Void>(getContext()) {
                                                    @Override
                                                    public void onFinish() {
                                                        super.onFinish();
                                                        v.setClickable(true);
                                                    }

                                                    @Override
                                                    public void onComplete() {
                                                        // 请求完成后再改变本地数据和显示
                                                        if (0 == type) {
                                                            countHelper.plus();
                                                        } else {
                                                            countHelper.reduce();
                                                        }
                                                    }
                                                });
                            }
                        });
    }

    /**
     * 设置删除点击事件
     */
    private void setDeleteClick(final CartProduct cartProduct) {
        RxView.clicks(mDeleteTxt)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(
                        new Consumer<Object>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Object o)
                                    throws Exception {
                                mCartData
                                        .deleteProduct(
                                                GlobalData.getInstance().getUserId(),
                                                cartProduct.getId())
                                        .compose(CartProVH.this.<Void>bindToLifecycle())
                                        .subscribe(
                                                new SimpleObserver<Void>(getContext()) {
                                                    @Override
                                                    public void onComplete() {
                                                        if (mOperationListener != null) {
                                                            mOperationListener.onItemDeleted(
                                                                    cartProduct);
                                                        }
                                                    }
                                                });
                            }
                        });
    }
}
