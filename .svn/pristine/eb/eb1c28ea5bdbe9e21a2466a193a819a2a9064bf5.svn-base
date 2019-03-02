package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;

import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderDetails;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.ReturnGoodsReasonBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.net.CommonParamInterceptor;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.order.OrderDetailsActivity;
import com.a1magway.bgg.v.productReturn.IApplyReturnV;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lm on 2018/8/30.
 */
public class ApplyReturnP extends BasePresenter<IApplyReturnV> {

    private IApplyReturnV mView;
    private OrderItem orderItem;
    private ReturnProductListdapter mAdapter;
    private PhotoSelectadapter mPhotoAdapter;
    private APIManager apiManager;
    private GetReturnGoodsReasonListenr getReturnGoodsReasonListenr;

    public ApplyReturnP(@NonNull IApplyReturnV view, OrderItem orderItem
    ,GetReturnGoodsReasonListenr getReturnGoodsReasonListenr) {
        super(view);
        mView=view;
        this.orderItem=orderItem;
        apiManager=new APIManager(new CommonParamInterceptor());
        this.getReturnGoodsReasonListenr=getReturnGoodsReasonListenr;
    }



    public  void initRV(){
//        mAdapter=new ReturnProductListdapter(orderItem.getSkuList());
//        mAdapter.setHideSelct(true);
//        mView.initAdapter(mAdapter);
        getOrderDetail(GlobalData.getInstance().getUserId(),orderItem.getOrderNum());
        mAdapter =new ReturnProductListdapter(orderItem.getSkuList());
        mView.GoodsInfo(mAdapter);
        mAdapter.setItemOperationListener(
                new ReturnProductListdapter.ItemOperationListener() {

                    @Override
                    public void onItemClickSelectChange(int number) {
                        mView.selectNumber(number);
                    }
                });


        List<String>list=new ArrayList<>();
        list.add("");
        mPhotoAdapter=new PhotoSelectadapter(list);
        mView.initPhotoAdapter(mPhotoAdapter);

        mPhotoAdapter.setItemOperationListener(
                new PhotoSelectadapter.ItemOperationListener() {

                    @Override
                    public void onItemClickSelectChange() {
                        
                    }
                });
    }


    public void getOrderDetail(int userId,String orderNumber){
        new APIManager(new CommonParamInterceptor()).getOrderDetails2(userId, orderNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<OrderDetails>>(getContext()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }

                    @Override
                    public void onNext(APIResponse<OrderDetails> orderDetails) {
                        super.onNext(orderDetails);
                        String orderuserPhone = orderDetails.getData().getAddress().getOrderuserPhone();
                        mView.getPhone(orderuserPhone);
                    }
                });


    }

    public void applyRefund(int user_id, String id,
                            int type,int order_id,String sku_id,
                            String num,String phone,int reason_id,
                            String reason,String photos){
        apiManager.applyRefund(user_id,id,type,order_id,sku_id,num,phone,reason_id,reason,photos)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse>(mView.getActivity()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mView.onFaile(e);
                    }

                    @Override
                    public void onNext(APIResponse apiResponse) {
                        super.onNext(apiResponse);
                        mView.onSucess(apiResponse);
                    }
                });
    }

    public void onClickAllSelect(boolean b){
//         mReturnProductListdapter.getPositions().clear();
        if (mAdapter.getList()!=null&&
                mAdapter.getList().size()>0){
//            int size=item.getSkuList().size();
            for (int i = 0; i< mAdapter.getList().size(); i++){
                if (mAdapter.getList().get(i).getRefundId()==0){
                    mAdapter.getList().get(i).setCheck(b);
                }else{
                    mAdapter.getList().get(i).setCheck(false);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 获取退货原因
     */
    public void getReturnReason(){
        apiManager.getReturnReason()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<List<ReturnGoodsReasonBean>>>(mView.getActivity()) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        getReturnGoodsReasonListenr.onGetGoodsReasonFaile(e);
                    }

                    @Override
                    public void onNext(APIResponse<List<ReturnGoodsReasonBean>> listAPIResponse) {
                        super.onNext(listAPIResponse);
                        getReturnGoodsReasonListenr.onGetGoodsReasontrue(listAPIResponse.getData());
                    }
                });
    }

    /**
     * 获取退货理由回调
     */
    public interface GetReturnGoodsReasonListenr{
        void onGetGoodsReasontrue(List<ReturnGoodsReasonBean> listAPIResponse);
        void onGetGoodsReasonFaile(Throwable e);
    }
}
