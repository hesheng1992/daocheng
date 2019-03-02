package com.a1magway.bgg.v.productReturn;



import android.os.Handler;
import  android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.data.entity.APIResponse;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;

import com.a1magway.bgg.data.entity.OrderStatus;
import com.a1magway.bgg.data.entity.ReturnGoodsDetailBean;
import com.a1magway.bgg.data.entity.SalesReturn;
import com.a1magway.bgg.p.productReturn.ReturnContentP;
import com.a1magway.bgg.util.GlobalData;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.order.OrderListActivity;
import com.a1magway.bgg.v.productReturn.adapter.ReturnLogisInfoAdapter;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.dialog.DialogOnclickBtnlistener;
import com.a1magway.bgg.widget.dialog.DialogUtil;
import com.almagway.common.utils.StringUtil;
import com.almagway.common.utils.ToastUtil;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by lm on 2018/8/30.
 * 退款申请提交之后的详情页面
 */
public class ReturnContentActivity extends PActivity<ReturnContentP> implements IReturnContentV {

    private static final String EXTRA_OUTTRADENO = "EXTRA_OUTTRADENO";
    private static final String SALES_RETURN = "SALES_RETURN";
    @BindView(R.id.return_content_title_bar)
    TitleBar returnContentTitleBar;
    @BindView(R.id.return_product_tip)
    TextView returnProductTip;
    @BindView(R.id.content_view)
    FrameLayout contentView;
    @BindView(R.id.contact_kf_lay)
    LinearLayout contactKfLay;
    @BindView(R.id.update_apply_lay)
    LinearLayout updateApplyLay;
    @BindView(R.id.cancel_apply)
    LinearLayout cancelApply;

    //物流信息
    @BindView(R.id.log_recycler)
    RecyclerView log_recycler;
    ReturnLogisInfoAdapter logisInfoAdapter;

    private OrderItem orderItem;
    private SalesReturn salesReturn;

    private DialogUtil dialogUtil;
    //点击的是撤销退货还是修改申请 1 撤销退货 2 修改申请
    private int clickCancleReturnOrUndo=0;

    private int refundType=0;

    private ReturnProductInfoFragment returnProductInfoFragment;
    private RefoundApprovedFragment refoundApprovedFragment;
    private ReturnLogisticsInfoFragment returnLogisticsInfoFragment;

    public static final void start(Context context, OrderItem orderItem, SalesReturn salesReturn) {
        Intent intent = new Intent(context, ReturnContentActivity.class);
        intent.putExtra(EXTRA_OUTTRADENO, orderItem);
        intent.putExtra(SALES_RETURN,salesReturn);
        JumpUtil.startActivityForResult(context, intent,20);
    }




    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mPresenter=new ReturnContentP(this);
        orderItem = (OrderItem) getIntent().getSerializableExtra(EXTRA_OUTTRADENO);
        salesReturn = (SalesReturn) getIntent().getSerializableExtra(SALES_RETURN);
        if (orderItem!=null){
            refundType = orderItem.getType();
            if (refundType==1){
                returnContentTitleBar.setTitleTxt("退货详情");
            }else if (refundType==3){
                returnContentTitleBar.setTitleTxt("退款详情");
            }
        }
        initFragment();
        dialogUtil=new DialogUtil(this, new DialogOnclickBtnlistener() {
            @Override
            public void onClickOk() {
//                Toast.makeText(getContext(),"你点击了确定",Toast.LENGTH_SHORT).show();
                //撤销退款申请
//                if (clickCancleReturnOrUndo==1){
//
//                    //修改退货申请
//                }else if (clickCancleReturnOrUndo==2){
//
//                }
                showLoadingDialog();
                mPresenter.cheXiaoReturnApply(GlobalData.getInstance().getUserId(),orderItem.getRefundId());
            }

            @Override
            public void onClickCancle() {

            }
        });

        logisInfoAdapter=new ReturnLogisInfoAdapter(new ArrayList<ReturnGoodsDetailBean.LogsBean>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        log_recycler.setLayoutManager(linearLayoutManager);
        log_recycler.setNestedScrollingEnabled(false);
        log_recycler.setAdapter(logisInfoAdapter);

        showLoadingDialog();
        mPresenter.getRefund(GlobalData.getInstance().getUserId(),String.valueOf(orderItem.getRefundId()));
    }

    public ReturnContentP getReturnP(){
        if (mPresenter!=null){
            return mPresenter;
        }
        return null;
    }

    private void initFragment() {
        if (salesReturn==null){
            salesReturn=new SalesReturn();
        }
        returnProductInfoFragment=ReturnProductInfoFragment.newIntance(orderItem,salesReturn);
        refoundApprovedFragment=RefoundApprovedFragment.newIntance(orderItem,salesReturn);
        returnLogisticsInfoFragment=ReturnLogisticsInfoFragment.newIntance(orderItem,salesReturn);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content_view, returnProductInfoFragment);
        transaction.add(R.id.content_view, refoundApprovedFragment);
        transaction.add(R.id.content_view, returnLogisticsInfoFragment);
        transaction.show(returnProductInfoFragment);
        transaction.hide(refoundApprovedFragment);
        transaction.hide(returnLogisticsInfoFragment);
        transaction.commit();
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_return_content;
    }


    @OnClick(R.id.return_content_title_bar)
    public void onClickBack(View v) {
        onBackButtonClick();

    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0){
//
//
//
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @OnClick({R.id.cancel_apply,R.id.update_apply_lay,R.id.contact_kf_lay})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.cancel_apply:
                dialogUtil.showDialog("确定撤销退款申请？");
                clickCancleReturnOrUndo=1;
                break;
            case R.id.update_apply_lay:
                dialogUtil.showDialog("确定放弃该操作\n重新发起申请？");
                clickCancleReturnOrUndo=2;
                break;
            case R.id.contact_kf_lay:
                //联系客服
                mPresenter.hxLoginInfoGet();
                break;
        }
    }
    //撤销申请成功
    @Override
    public void undoReturnGoodsTrue(APIResponse apiResponse) {
//        ToastUtil.showShort(apiResponse.getData().toString());
        hideLoadingDialog();
        //撤销申请
        if (clickCancleReturnOrUndo==1){
            ToastUtil.showShort("撤销申请成功!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    OrderListActivity.start(ReturnContentActivity.this);
                    finish();
                }
            },2000);
            //修改
        }else{
            ToastUtil.showShort("发起修改申请成功!");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

//                    Intent intent=new Intent();
//                    intent.putExtra("refoundID",orderItem.getRefundId());
//                    setResult(20,intent);
//                    finish();
                    ApplyReturnActivity.start(ReturnContentActivity.this,orderItem);


                }
            },100);
        }
    }
    //撤销申请失败
    @Override
    public void undoReturnGoodsFalse(Throwable e) {
        hideLoadingDialog();
        ToastUtil.showShort(e.getMessage());
    }

    /**
     * 获取退货详情成功
     * @param bean
     */
    @Override
    public void getRefundDetailTrue(ReturnGoodsDetailBean bean) {
        hideLoadingDialog();
        if (bean!=null){
            getStatus(bean);
        }
    }

    /**
     * 判断状态
     */
    public void getStatus(ReturnGoodsDetailBean bean){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            switch (bean.getStatus()){
            //申请中
            case 1:
                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("您已成功发起退货申请，请等待系统审核。");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("您已成功发起退款申请，请等待系统审核。");


                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                break;
            //审核成功
            case 2:

                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("系统已确认您的退货申请，请将商品寄回以下地址。");
                    transaction.show(refoundApprovedFragment);
                    transaction.hide(returnProductInfoFragment);
                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("系统已确认您的换货申请，请将商品寄回以下地址。");

                    //退款
                }else {
                    returnProductTip.setText("系统已确认您的退款申请");
                    transaction.show(returnProductInfoFragment);
                    transaction.hide(refoundApprovedFragment);
                }
                transaction.hide(returnLogisticsInfoFragment);
                if (bean.getType()==3){
                    updateApplyLay.setVisibility(View.GONE);
                    cancelApply.setVisibility(View.GONE);
                }
                break;
            //审核失败
            case 3:

                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("您发起的退货申请，未审核通过。 原因："+bean.getResult());

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("您发起的退款申请，未审核通过。 原因："+bean.getResult());
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);


                break;
            //已撤销
            case 4:

                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("您已撤销申请，如果仍然没有解决问题，在保障期内, 您仍可以发起售后申请。");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("您已撤销申请，如果仍然没有解决问题，在保障期内, 您仍可以发起售后申请。");
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                updateApplyLay.setVisibility(View.GONE);
                cancelApply.setVisibility(View.GONE);
                break;
            //评估中
            case 5:
                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("您发起的退货申请，正在处理中...");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("您发起的退款申请，正在处理中...");
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                if (bean.getType()==3){
                    updateApplyLay.setVisibility(View.GONE);
                    cancelApply.setVisibility(View.GONE);
                }
                break;
            //已退款
            case 6:
                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("您发起的退货申请处理完成，已退货");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("您发起的退款申请处理完成，已退款");
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                if (bean.getType()==3){
                    updateApplyLay.setVisibility(View.GONE);
                    cancelApply.setVisibility(View.GONE);
                }

                break;
            //退款中
            case 7:
                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("您发起的退货申请处理完成，正在退货");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("您发起的退款申请处理完成，正在退款");
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                if (bean.getType()==3){
                    updateApplyLay.setVisibility(View.GONE);
                    cancelApply.setVisibility(View.GONE);
                }
                break;
            //寄回中
            case 8:
                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("系统已确认您的退货申请， 商品正在寄回中。");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("您已成功发起换货申请，请等待系统审核。");

                    //退款
                }else {
                    returnProductTip.setText("系统已确认您的退货申请， 商品正在寄回中。");
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                if (bean.getType()==3){
                    updateApplyLay.setVisibility(View.GONE);
                    cancelApply.setVisibility(View.GONE);
                }
                break;
            //退款失败
            case 9:
                //退货退款
                if (bean.getType()==1){
                    returnProductTip.setText("退货中");

                    //换货
                }else if (bean.getType()==2){
                    returnProductTip.setText("换货中");

                    //退款
                }else {
                    returnProductTip.setText("退款中");
                }
                transaction.hide(refoundApprovedFragment);
                transaction.show(returnProductInfoFragment);
                transaction.hide(returnLogisticsInfoFragment);
                break;
        }
//        }
        transaction.commit();
        initFragmentData(bean);
        if (!StringUtil.isEmpty(bean.getLogisticsNum())){
            updateApplyLay.setVisibility(View.GONE);
            cancelApply.setVisibility(View.GONE);
        }
    }

    //获取退货详情失败
    @Override
    public void getRefundDetailFaile(Throwable e) {
        hideLoadingDialog();
    }

    /**
     * 装载数据
     */
    public void initFragmentData(ReturnGoodsDetailBean bean){
        List<OrderCommodity> listSku=bean.getSkuList();
        if (listSku!=null){
            orderItem.setSkuList(listSku);
            if (listSku.size()>0){
                orderItem.setId(listSku.get(0).getOrderId());
            }
        }
        orderItem.setOrderNum(bean.getOrderNum());
        orderItem.setCount(bean.getTotalCount());
        orderItem.setRefundId(bean.getId());
        orderItem.setType(bean.getType());
        //设置金额
        salesReturn.setMoney(bean.getTotalPrice());
        //设置退货编号
        salesReturn.setCode(bean.getRefundNumber());
        //说明
        salesReturn.setDescribe(bean.getExplain());
        //设置电话号码
        salesReturn.setPhone(bean.getPhone());
        //设置原因
        salesReturn.setReason(bean.getReason());
        String pic=bean.getPictures();
        if (!StringUtil.isEmpty(pic)){
            List<String> listPic=new ArrayList<>();
            String []picArry=pic.split(";");
            if (picArry!=null&&picArry.length>0){
                for (int i = 0; i <picArry.length ; i++) {
                    listPic.add(picArry[i]);
                }
            }
            salesReturn.setImgs(listPic);
        }
        returnProductInfoFragment.setSalesReturn(salesReturn,bean.getLogisticsNum(),refundType);
        refoundApprovedFragment.setReturnAdress(bean.getAddress(),
                bean.getConsignee(),bean.getConsigneePhone());
        returnLogisticsInfoFragment.setSalesReturn(salesReturn,bean.getOrderNum(),bean.getTotalCount());
        if (bean.getLogs()!=null && bean.getLogs().size()>0){
            //设置系统返回的日志
            logisInfoAdapter.setList(bean.getLogs());
        }
    }

    /**
     * 填写的物流单号完成，回到主界面
     */
    public void wirteLogFinsh(String logNumber){
        returnProductTip.setText(logNumber);
        mPresenter.getRefund(GlobalData.getInstance().getUserId(),String.valueOf(orderItem.getRefundId()));
    }
}
