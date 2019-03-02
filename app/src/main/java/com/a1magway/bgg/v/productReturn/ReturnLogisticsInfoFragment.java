package com.a1magway.bgg.v.productReturn;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.ReturnGoodsDetailBean;
import com.a1magway.bgg.data.entity.ReturnLogInfoBean;
import com.a1magway.bgg.data.entity.SalesReturn;
import com.a1magway.bgg.data.net.APIManager;
import com.a1magway.bgg.data.net.CommonParamInterceptor;
import com.a1magway.bgg.p.productReturn.PhotoSelectadapter;
import com.a1magway.bgg.p.productReturn.ReturnProductListdapter;
import com.a1magway.bgg.v.order.LogisticsActivity;
import com.a1magway.bgg.v.productReturn.adapter.ReturnLogisInfoAdapter;
import com.almagway.common.utils.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by enid on 2018/9/8.
 *
 */

/**
 * 退货物流信息
 */
public class ReturnLogisticsInfoFragment extends PFragment {

    private static final String EXTRA_OUTTRADENO = "EXTRA_OUTTRADENO";
    private static final String SALES_RETURN = "SALES_RETURN";
    //商品列表
    @BindView(R.id.return_content_goods)
    RecyclerView return_content_goods;
    //退款金额
    @BindView(R.id.apply_money_tv)
    TextView apply_money_tv;
    //商品数量
    @BindView(R.id.apply_count_tv)
    TextView apply_count_tv;
    //寄回快递的编号
    @BindView(R.id.text_log_number)
    TextView text_log_number;

    @BindView(R.id.image_right)
    ImageView image_right;
    //物流信息
    @BindView(R.id.log_recycler)
    RecyclerView log_recycler;
    ReturnLogisInfoAdapter logisInfoAdapter;

    @BindView(R.id.return_content_phone)
    TextView returnContentPhone;
    @BindView(R.id.return_content_reason)
    TextView returnContentReason;
    @BindView(R.id.return_content_descibe)
    TextView returnContentDescibe;
    @BindView(R.id.return_content_code)
    TextView returnContentCode;
    @BindView(R.id.return_content_img_info)
    RecyclerView returnContentImgInfo;

    @BindView(R.id.liner_logs)
    LinearLayout linearLayout;

    private PhotoSelectadapter mPhotoAdapter;
    //商品列表
    private ReturnProductListdapter mGoodsAdapter;
    private OrderItem orderItem;
    private SalesReturn salesReturn;

    public static ReturnLogisticsInfoFragment newIntance(OrderItem item, SalesReturn salesReturn) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_OUTTRADENO,item);
        bundle.putSerializable(SALES_RETURN,salesReturn);
        ReturnLogisticsInfoFragment fragment = new ReturnLogisticsInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_return_logistics_info;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        orderItem= (OrderItem) getArguments().getSerializable(EXTRA_OUTTRADENO);
        salesReturn= (SalesReturn) getArguments().getSerializable(SALES_RETURN);
        return_content_goods.setLayoutManager(new LinearLayoutManager(getContext()));
        return_content_goods.setNestedScrollingEnabled(false);
        mGoodsAdapter=new ReturnProductListdapter(orderItem.getSkuList());
        mGoodsAdapter.setHideSelct(true);
        return_content_goods.setAdapter(mGoodsAdapter);

        logisInfoAdapter=new ReturnLogisInfoAdapter(new ArrayList<ReturnGoodsDetailBean.LogsBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        log_recycler.setLayoutManager(linearLayoutManager);
        log_recycler.setNestedScrollingEnabled(false);
        log_recycler.setAdapter(logisInfoAdapter);


        returnContentImgInfo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        if (salesReturn.getImgs()==null){
            mPhotoAdapter=new PhotoSelectadapter(new ArrayList<String>());
        }else{
            mPhotoAdapter=new PhotoSelectadapter(salesReturn.getImgs());
        }
        returnContentImgInfo.setAdapter(mPhotoAdapter);

        if (orderItem.getRefundId()!=0){
//            requestData(orderItem.getRefundId());
            List<OrderCommodity> skuList = orderItem.getSkuList();
            int num=0;
            if (skuList.size()>0){
                for (OrderCommodity com:skuList
                     ) {
                    num+=com.getCommodityNumbers();
                }
            }
            apply_count_tv.setText("商品数量："+num);
        }
        if (salesReturn!=null){
            if (StringUtil.isEmpty(salesReturn.getMoney())){
                apply_money_tv.setText("退款金额: "+salesReturn.getMoney());
            }
            returnContentPhone.setText("联系电话: "+salesReturn.getPhone());
            returnContentReason.setText("退货原因: "+salesReturn.getReason());
            returnContentDescibe.setText("退货说明: "+salesReturn.getDescribe());
            returnContentCode.setText("退款编号: "+salesReturn.getCode());

            if (salesReturn.getImgs()!=null && salesReturn.getImgs().size()>0){
                mPhotoAdapter.setList(salesReturn.getImgs());
            }
        }


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogisticsActivity.start(getContext(),orderItem);
            }
        });
    }


    public void setSalesReturn(SalesReturn salesReturn,String orderNum,int count){
        if (salesReturn!=null){
            if (!StringUtil.isEmpty(salesReturn.getMoney())){
                apply_money_tv.setText("退款金额: "+salesReturn.getMoney());
            }
            orderItem.setOrderNum(orderNum);
            orderItem.setCount(count);
            returnContentPhone.setText("联系电话: "+salesReturn.getPhone());
            returnContentReason.setText("退货原因: "+salesReturn.getReason());
            returnContentDescibe.setText("退货说明: "+salesReturn.getDescribe());
            returnContentCode.setText("退款编号: "+salesReturn.getCode());

            if (salesReturn.getImgs()!=null && salesReturn.getImgs().size()>0){
                mPhotoAdapter.setList(salesReturn.getImgs());
            }
        }
    }

    public void setData(List<ReturnGoodsDetailBean.LogsBean> list){

        logisInfoAdapter.setList(list);
    }

    /**
     * 请求数据
     * @param refundId
     */
    public void requestData(String refundId){
        new APIManager(new CommonParamInterceptor()).getRefundLogsInfo(refundId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleObserver<APIResponse<ReturnLogInfoBean>>(getContext()) {
                    @Override
                    public void onNext(APIResponse<ReturnLogInfoBean> returnLogInfoBeanAPIResponse) {
                        super.onNext(returnLogInfoBeanAPIResponse);
                        ReturnLogInfoBean data = returnLogInfoBeanAPIResponse.getData();
                        if (data!=null){
                            ReturnLogInfoBean.LogisticsBean logistics = data.getLogistics();
                            if (logistics!=null){
                                String nu = logistics.getNu();
                                if (!StringUtil.isEmpty(nu)){
                                    text_log_number.setText("寄回物流编号："+nu);
                                }
                                List<ReturnLogInfoBean.LogisticsBean.DataBean> data1 = logistics.getData();
                                if (data1!=null &&data1.size()>0){
//                                    Collections.reverse(data1);
//                                    logisInfoAdapter.setList(data1);
                                }
                            }
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });

    }
}
