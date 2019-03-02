package com.a1magway.bgg.v.productReturn;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.RefundLoginfoBean;
import com.a1magway.bgg.data.entity.ReturnGoodsDetailBean;
import com.a1magway.bgg.data.entity.ReturnLogInfoBean;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.net.CommonParamInterceptor;
import com.a1magway.bgg.v.productReturn.adapter.RefundGoodsLogInfoAdapter;
import com.a1magway.bgg.v.productReturn.adapter.ReturnLogisInfoAdapter;
import com.a1magway.bgg.widget.TitleBar;
import com.almagway.common.json.Json;
import com.almagway.common.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 退货物流页面
 */
public class RefundGoodsLogInfoActivity extends PActivity {

    //物流信息
    @BindView(R.id.log_recycler)
    RecyclerView log_recycler;
    ReturnLogisInfoAdapter logisInfoAdapter;

    @BindView(R.id.image_recycler)
    RecyclerView image_recycler;

    private RefundGoodsLogInfoAdapter refundGoodsLogInfoAdapter;

    //全部数量
    @BindView(R.id.text_all_number)
    TextView text_all_number;
    //物流公司
    @BindView(R.id.text_wuliu_commpany)
    TextView text_wuliu_commpany;
    //物流单号
    @BindView(R.id.text_wuliu_number)
    TextView text_wuliu_number;

    @BindView(R.id.return_content_title_bar)
    TitleBar returnContentTitleBar;
    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_refund_goods_log_info;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        returnContentTitleBar.setTitleTxt("物流详情");
        logisInfoAdapter=new ReturnLogisInfoAdapter(new ArrayList<ReturnGoodsDetailBean.LogsBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        log_recycler.setLayoutManager(linearLayoutManager);
        log_recycler.setNestedScrollingEnabled(false);
        log_recycler.setAdapter(logisInfoAdapter);

        refundGoodsLogInfoAdapter=new RefundGoodsLogInfoAdapter(new ArrayList<String>());
        image_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        image_recycler.setAdapter(refundGoodsLogInfoAdapter);

        String refundId = getIntent().getStringExtra("refundId");
        showLoadingDialog();
        requestData(refundId);

    }

    public void requestData(String refundId){
        new APIManager(new CommonParamInterceptor()).getRefundLogsInfo(refundId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<ReturnLogInfoBean>>(this) {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        hideLoadingDialog();
                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onNext(APIResponse<ReturnLogInfoBean> apiResponse) {
                        super.onNext(apiResponse);
                        hideLoadingDialog();
                        if (apiResponse.getData()!=null){
                            ReturnLogInfoBean data = apiResponse.getData();
                            try {
                                if (data!=null){
                                    ReturnLogInfoBean.LogisticsBean logisticsBean =data.getLogistics();
                                    List<ReturnLogInfoBean.LogisticsSkuListBean> logisticsSkuList
                                            = data.getLogisticsSkuList();

                                    if (logisticsBean!=null){
                                        if (!StringUtil.isEmpty(logisticsBean.getCom())){

                                            SpannableStringBuilder spannableStringBuilder=
                                                    new SpannableStringBuilder("物流公司: "+logisticsBean.getCom());
                                            spannableStringBuilder.setSpan(new ForegroundColorSpan(getColor(R.color.primaryDarkColor))
                                            ,0,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                            text_wuliu_commpany.setText(spannableStringBuilder);
                                        }

                                        if (!StringUtil.isEmpty(logisticsBean.getNu())){
                                            SpannableStringBuilder spannableStringBuilder=
                                                    new SpannableStringBuilder("物流单号: "+logisticsBean.getNu());
                                            spannableStringBuilder.setSpan(new ForegroundColorSpan(getColor(R.color.primaryDarkColor))
                                                    ,0,5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                            text_wuliu_number.setText(spannableStringBuilder);
                                        }

                                        List<ReturnGoodsDetailBean.LogsBean> logsBeanList=new ArrayList<>();
                                        if (logisticsBean.getData()!=null && logisticsBean.getData().size()>0){
                                            for (ReturnLogInfoBean.LogisticsBean.DataBean databen:logisticsBean.getData()
                                                 ) {
                                                ReturnGoodsDetailBean.LogsBean logsBean=new ReturnGoodsDetailBean.LogsBean();
                                                logsBean.setCreateDate(databen.getTime());
                                                logsBean.setDescription(databen.getContext());
                                                logsBeanList.add(logsBean);
                                            }
                                        }
                                        logisInfoAdapter.setList(logsBeanList);
                                    }
                                    List<String> listImage=new ArrayList<>();
                                    if (logisticsSkuList!=null && logisticsSkuList.size()>0){
                                        text_all_number.setText("共"+logisticsSkuList.size()+"个商品");
                                        for (ReturnLogInfoBean.LogisticsSkuListBean logImge:logisticsSkuList
                                             ) {
                                            listImage.add(logImge.getPath());
                                        }
                                    }
                                    refundGoodsLogInfoAdapter.addList(listImage);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }
}
