package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;

import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.p.order.OrderListAdapter;
import com.a1magway.bgg.v.productReturn.IProductReturnSelectV;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lm on 2018/8/30.
 */
public class ProductReturnSelectP extends BasePresenter<IProductReturnSelectV> {

    private  IProductReturnSelectV mView;
    private OrderItem item;
    private ReturnProductListdapter mReturnProductListdapter;

    public ProductReturnSelectP(@NonNull IProductReturnSelectV view, OrderItem item) {
        super(view);
        this.mView=view;
        this.item=item;
    }

     public void getGoodsInfo(){

        mReturnProductListdapter =new ReturnProductListdapter(item.getSkuList());
        mView.GoodsInfo(mReturnProductListdapter);
         mReturnProductListdapter.setItemOperationListener(
                 new ReturnProductListdapter.ItemOperationListener() {

                     @Override
                     public void onItemClickSelectChange(int number) {
                         mView.selectNumber(number);
                     }
                 });
     }

     public void onClickAllSelect(boolean b){
//         mReturnProductListdapter.getPositions().clear();
        if (mReturnProductListdapter.getList()!=null&&
                mReturnProductListdapter.getList().size()>0){
//            int size=item.getSkuList().size();
            for (int i = 0; i< mReturnProductListdapter.getList().size(); i++){
                if (mReturnProductListdapter.getList().get(i).getRefundId()==0){
                    mReturnProductListdapter.getList().get(i).setCheck(b);
                }
            }
        }
        mReturnProductListdapter.notifyDataSetChanged();
     }

     public void  getSelectGood(){
         List<OrderCommodity>lists=new ArrayList<>();
         for (int i = 0; i< mReturnProductListdapter.getList().size(); i++){
             if (mReturnProductListdapter.getList().get(i).isCheck()){
                 lists.add(mReturnProductListdapter.getList().get(i));
             }
         }
         OrderItem  orderItem=item;
//         orderItem.getSkuList().clear();
         orderItem.setSkuList(lists);
         mView.selectGoods(orderItem);
     }
}
