package com.a1magway.bgg.p.productReturn;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.a1magway.bgg.common.BaseObserver;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.HuanXinLoginInfo;
import com.a1magway.bgg.data.entity.ReturnGoodsDetailBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.net.CommonParamInterceptor;
import com.a1magway.bgg.data.repository.IHuanxinData;
import com.a1magway.bgg.huanxin.KefuHelper;
import com.a1magway.bgg.p.BasePresenter;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.v.productReturn.IApplyReturnV;
import com.a1magway.bgg.v.productReturn.IReturnContentV;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.helpdesk.callback.Callback;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lm on 2018/8/30.
 */
public class ReturnContentP extends BasePresenter<IReturnContentV> {
    private APIManager apiManager;
    private IReturnContentV iReturnContentV;

    public ReturnContentP(@NonNull IReturnContentV view) {
        super(view);
        iReturnContentV=view;
        apiManager=new APIManager(new CommonParamInterceptor());
    }

    public void hxLoginInfoGet() {
        HuanXinLoginInfo huanXinLoginData = GlobalData.getInstance().getHuanXinLoginData();
        if (huanXinLoginData != null) {
            huanxinLogin(huanXinLoginData.getUsername(), huanXinLoginData.getPassword());
            return;
        }
        new IHuanxinData() {
            @Override
            public Observable<HuanXinLoginInfo> hxLoginInfoGet() {

                return new APIManager(new CommonParamInterceptor()).hxLoginInfoGet();
            }

            @Override
            public Observable<APIResponse> hxLoginInfoRelease(int id) {
                return null;
            }
        }.hxLoginInfoGet()
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
    //调用环信
    public void huanxinLogin(String username, String password) {
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            KefuHelper.getInstance().chatWithHefu(mView.getActivity());
        } else {
            //未登录，需要登录后，再进入会话界面
            ChatClient.getInstance().login(username, password, new Callback() {
                @Override
                public void onSuccess() {
                    KefuHelper.getInstance().chatWithHefu(mView.getActivity());
                }

                @Override
                public void onError(int code, final String error) {
                    mView.getActivity().runOnUiThread(new Runnable() {
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
     * 撤销退货
     */
    public void cheXiaoReturnApply(int useId,int id){
        apiManager.cheXiaoReturnApply(useId,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse>(mView.getActivity()) {
                    @Override
                    public void onNext(APIResponse apiResponse) {
                        super.onNext(apiResponse);
                        iReturnContentV.undoReturnGoodsTrue(apiResponse);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        iReturnContentV.undoReturnGoodsFalse(e);
                    }
                });
    }

    /**
     * 获取退货详情
     * @param useId
     * @param id
     */
    public void getRefund(int useId,String id){
        apiManager.getRefund(useId,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<ReturnGoodsDetailBean>>(mView.getActivity()) {
                    @Override
                    public void onNext(APIResponse<ReturnGoodsDetailBean> returnGoodsDetailBeanAPIResponse) {
                        super.onNext(returnGoodsDetailBeanAPIResponse);
                        iReturnContentV.getRefundDetailTrue(returnGoodsDetailBeanAPIResponse.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        iReturnContentV.getRefundDetailFaile(e);
                    }
                });

    }

}
