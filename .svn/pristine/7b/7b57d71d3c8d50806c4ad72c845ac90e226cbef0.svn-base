package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.AfterSaleBean;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.net.CommonParamInterceptor;
import com.a1magway.bgg.p.BaseLoadP;
import com.a1magway.bgg.v.order.IOrderListV;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/9/8.
 */

/**
 * 售后列表P层
 */
public class AfterSaleP extends BaseLoadP<List<AfterSaleBean>, IOrderListV> {
    APIManager apiManager;
    private OnAfterSaleLister onAfterSaleLister;
    public AfterSaleP(@NonNull IOrderListV view) {
        super(view);
        apiManager=new APIManager(new CommonParamInterceptor());
    }

    public OnAfterSaleLister getOnAfterSaleLister() {
        return onAfterSaleLister;
    }

    public void setOnAfterSaleLister(OnAfterSaleLister onAfterSaleLister) {
        this.onAfterSaleLister = onAfterSaleLister;
    }

    @Nullable
    @Override
    public Observable<List<AfterSaleBean>> getDataObservable() {
        return null;
    }

    /**
     * 获取售后列表
     * @param user_id
     * @param current_page
     * @param page_num
     * @param status
     */
    public void getRefundList(int user_id,
                              int current_page,
                              int page_num,
                              int status){
        apiManager.getRefundList(user_id,current_page,page_num,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<APIResponse<List<AfterSaleBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(APIResponse<List<AfterSaleBean>> listAPIResponse) {
                        if (onAfterSaleLister!=null){

                            onAfterSaleLister.onAfterSucess(listAPIResponse.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (onAfterSaleLister!=null){
                            onAfterSaleLister.onAfterFaies(e);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface OnAfterSaleLister{
        void onAfterSucess(List<AfterSaleBean> listAPIResponse);
        void onAfterFaies(Throwable e);
    }

}
