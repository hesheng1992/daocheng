package com.a1magway.bgg.p.cart;

import android.view.ViewGroup;
import android.widget.TextView;

import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.CartProduct;
import com.a1magway.bgg.data.repository.ICartData;

import java.util.List;

/**
 * 购物车列表对应的适配器
 * Created by jph on 2017/8/14.
 */
public class CartAdapter extends BaseRecyclerAdapter<CartProVH, CartProduct> {

    private OperationListener mOperationListener;
    private ICartData mCartData;

    public CartAdapter(List<CartProduct> list) {
        super(list);
    }

    public void setOperationListener(OperationListener operationListener) {
        mOperationListener = operationListener;
    }

    public void setCartData(ICartData cartData) {
        mCartData = cartData;
    }

    @Override
    public CartProVH onRealCreateViewHolder(ViewGroup parent, int viewType) {
        CartProVH cartProVH = new CartProVH(parent);
        cartProVH.setOperationListener(mOperationListener);
        cartProVH.setCartData(mCartData);
        return cartProVH;
    }

    @Override
    public void onRealBindViewHolder(CartProVH holder, int position) {
        super.onRealBindViewHolder(holder, position);
        holder.showViewContent(getItem(position), position);
    }

    public interface OperationListener {

        /**
         * 选中状态发生改变
         *
         * @param position
         * @param isSelected
         */
        void onItemSelectedChange(int position, boolean isSelected);

        /**
         * 数量发生改变
         *
         * @param position
         * @param newCount
         */
        void onItemCountChange(int position, int newCount);

        /**
         * 某个item已经被删除掉
         *
         * @param cartProduct
         */
        void onItemDeleted(CartProduct cartProduct);


        /**
         * 输入商品数量
         */
        void inputGoodsCount(TextView v,int position);
    }
}
