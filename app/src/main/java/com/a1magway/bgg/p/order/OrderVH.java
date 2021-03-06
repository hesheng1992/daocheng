package com.a1magway.bgg.p.order;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.adapter.BaseRecyclerVH;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.OrderStatus;
import com.a1magway.bgg.data.entity.SalesReturn;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.tool.TimeCountDownController;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.util.StringFormatUtil;
import com.a1magway.bgg.v.order.ImageMoreAdapter;
import com.a1magway.bgg.v.order.LogisticsActivity;
import com.a1magway.bgg.v.order.OrderDetailsActivity;
import com.a1magway.bgg.v.productReturn.ReturnContentActivity;
import com.a1magway.bgg.v.productReturn.bean.ReturnGoodsBean;
import com.a1magway.bgg.v.saleorder.SaleOrderListActivity;
import com.a1magway.bgg.widget.PingtuanCountDownTextView;
import com.almagway.common.utils.CollectionUtil;
import com.almagway.common.utils.StringUtil;
import com.almagway.common.utils.TimeUtils;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;

/**
 * 订单列表Item对应的ViewHolder Created by jph on 2017/8/16.
 */
public class OrderVH extends BaseRecyclerVH<OrderItem> {

    private OrderListAdapter.ItemOperationListener mItemOperationListener;

    @BindView(R.id.order_txt_time)
    TextView mTimeTxt;

    @BindView(R.id.order_txt_count)
    TextView mProCountTxt;

    @BindView(R.id.order_txt_price)
    TextView mPriceTxt;

    /////// 单个商品
    @BindView(R.id.order_layout_single)
    RelativeLayout mSingleProLayout;

    @BindView(R.id.order_img_pro)
    ImageView mProPicImg;

    @BindView(R.id.order_txt_brand)
    TextView mBrandTxt;

    @BindView(R.id.order_txt_name)
    TextView mNameTxt;

    @BindView(R.id.order_txt_sku)
    TextView mSkuTxt;

    @BindView(R.id.order_txt_an)
    TextView mCommodityCode;
    //物流编号
    @BindView(R.id.order_txt_outtradeno)
    TextView mOuttradenoTxt;

    ////// 多个商品
    @BindView(R.id.order_layout_multi)
    ViewGroup mMultiProLayout; // 多个商品图片





    @BindViews({
            R.id.order_img_pro_1,
            R.id.order_img_pro_2,
            R.id.order_img_pro_3,
            R.id.order_img_pro_4
    })
    ImageView[] mPro1PicImgs;

    @BindView(R.id.order_txt_mutli_complete_hint)
    TextView order_txt_mutli_complete_hint;

    //订单操作
    @BindView(R.id.order_operator_layout)
    FrameLayout mLayoutOrdertOperator;

    @BindView(R.id.order_operator_enable_view)
    View mOperatorEnableView;

    //代付款
    @BindView(R.id.oder_layout_wait_pay)
    LinearLayout mLayoutWaitPay;

    @BindView(R.id.order_txt_pay_count_down)
    TextView mCountDownTxt; // 倒计时

    @BindView(R.id.order_txt_pay)
    TextView mPayTxt;

    @BindView(R.id.order_txt_cancel)
    TextView mCancelTxt;

    //订单失效
    @BindView(R.id.oder_layout_wait_pay_cancel)
    LinearLayout mLayoutWaitPayCancel;

    @BindView(R.id.order_txt_delete_pay)
    TextView mDeleteTxt;

    //待发货
    @BindView(R.id.oder_layout_wait_deliver)
    LinearLayout mLayoutWaitDeliver;

    //提醒发货
    @BindView(R.id.order_remind_deliver_tv)
    TextView mRemindDeleverTv;

    boolean ismRemindDeleverTvClickAble=true;
    //已发货
    @BindView(R.id.oder_layout_delivered)
    LinearLayout getmLayoutDelivered;

    @BindView(R.id.order_txt_detail)
    TextView mDetailTv;

    @BindView(R.id.order_txt_delivered_confirm)
    TextView mDeliveredConfirmTv;

//    @BindView(R.id.swipe_layout)
////    SwipeMenuLayout swipeItemLayout;

//    @BindView(R.id.right_menu)
//    TextView mDeleteTv;

    @BindView(R.id.ll_root)
    LinearLayout ll_root;

    @BindView(R.id.order_txt_complete_hint)
    TextView order_txt_complete_hint;
    //申请售后
    @BindView(R.id.appley_shouhou)
    TextView appley_shouhou;
    @BindView(R.id.order_txt_mutli_complete_hint_liner)
    RelativeLayout order_txt_mutli_complete_hint_liner;
    //申请售后布局
    @BindView(R.id.relate_apply_tuikuan)
    RelativeLayout relate_apply_tuikuan;
    //物流编号
    @BindView(R.id.wuliu_number)
    TextView wuliu_number;
    @BindView(R.id.view_shuxian)
    View view;

    @BindView(R.id.bg_order_past0)
    TextView bg_order_past0;

    @BindView(R.id.bg_order_past1)
    TextView bg_order_past1;

    @BindView(R.id.order_operator_line)
    View order_operator_line;

    //代发货状态下申请退款
    @BindView(R.id.order_apply_return_money)
    TextView order_apply_return_money;

    //仓库备货中
    @BindView(R.id.text_show_beihou)
    TextView text_show_beihou;

    //代发货状态下，竖线
    @BindView(R.id.view_wait_deliver)
    View view_wait_deliver;

    //待收货状态显示售后状态
    @BindView(R.id.order_txt_delivered_show_refund)
    TextView order_txt_delivered_show_refund;


    //拼团相关
    @BindView(R.id.order_top_info_normal)
    RelativeLayout order_top_info_normal;
    @BindView(R.id.order_top_info_pingtuan)
    RelativeLayout order_top_info_pingtuan;
    @BindView(R.id.order_top_info_pingtuan_createTime)
    TextView order_top_info_pingtuan_createTime;
    @BindView(R.id.order_top_info_pingtuan_count)
    TextView order_top_info_pingtuan_count;
    @BindView(R.id.order_top_info_pingtuan_price)
    TextView order_top_info_pingtuan_price;
    @BindView(R.id.order_top_info_pingtuan_status)
    TextView order_top_info_pingtuan_status;
    @BindView(R.id.order_top_info_pingtuan_downTime)
    PingtuanCountDownTextView order_top_info_pingtuan_downTime;
    @BindView(R.id.oder_layout_pingtuan_fail)
    LinearLayout oder_layout_pingtuan_fail;
    @BindView(R.id.oder_layout_pingtuan_invite_friend)
    LinearLayout oder_layout_pingtuan_invite_friend;
    @BindView(R.id.order_tv_pingtuan_invite_friend)
    TextView order_tv_pingtuan_invite_friend;
    @BindView(R.id.pingtuan_fail_cause)
    TextView pingtuan_fail_cause;

    private boolean mIsSaleOrderList = false;
    //多商品个图片显示
    @BindView(R.id.recycler_image)
    RecyclerView recycler_image;
    private ImageMoreAdapter imageMoreAdapter;

    @BindView(R.id.image_delete)
    ImageView deleteImage;

    public OrderVH(@NonNull ViewGroup parent, boolean isSaleOrderList) {
        super(parent, R.layout.order_item_list_order);
        this.mIsSaleOrderList = isSaleOrderList;
    }

    private OrderItem orderItem2;
    /**
     * 装有退款中的全部状态
     */
    private List<Integer> listStatus=new ArrayList<>();


    public void showViewContent(final OrderItem orderItem, OrderListAdapter adapter, int position) {
        super.showViewContent(orderItem);
        resetItemView();
        listStatus.add(1);
        listStatus.add(2);
        listStatus.add(3);
        listStatus.add(5);
        listStatus.add(7);
        listStatus.add(8);
        listStatus.add(9);

        orderItem2=orderItem;
        //状态不等于 已取消 待支付 显示退货
//        if (orderItem.getOrderStatus()!=OrderStatus.WAIT_PAY || orderItem.getOrderStatus()!=OrderStatus.CANCELED){
//            order_txt_mutli_complete_hint_liner.setVisibility(View.VISIBLE);
//            relate_apply_tuikuan.setVisibility(View.VISIBLE);
//        }else{
//            order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);
//            relate_apply_tuikuan.setVisibility(View.GONE);
//        }

        wuliu_number.setText("物流编号: "+orderItem.getOuttradeno());
        ismRemindDeleverTvClickAble=true;
        //订单已完成则显示文字提示，否则隐藏
        if (orderItem.getOrderStatus() == OrderStatus.COMPLETED) {
            order_txt_mutli_complete_hint_liner.setVisibility(View.VISIBLE);
            order_txt_mutli_complete_hint.setVisibility(View.VISIBLE);
            relate_apply_tuikuan.setVisibility(View.VISIBLE);
            deleteImage.setVisibility(View.VISIBLE);
        } else {
            order_txt_mutli_complete_hint.setVisibility(View.GONE);
            order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);
            relate_apply_tuikuan.setVisibility(View.GONE);
            deleteImage.setVisibility(View.GONE);
        }

//        if (orderItem.getOrderStatus() != OrderStatus.COMPLETED) {
//            mDeleteTv.setVisibility(View.GONE);
//        }
//        mDeleteTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mItemOperationListener.onItemClickDelete(orderItem);
//            }
//        });
        //1. 公共显示
        mTimeTxt.setText(
                String.format(
                        getContext().getString(R.string.order_list_format_time),
                        TimeUtils.dateToStrThread(orderItem.getCreateTime())));
        mProCountTxt.setText(
                String.format(
                        getContext().getString(R.string.order_list_format_count),
                        orderItem.getCount()));
        mPriceTxt.setText("实付款："+StringFormatUtil.getPrice(getContext(), orderItem.getTotalPrice()));


        //3. 显示不同订单状态的特殊状态
        if (mIsSaleOrderList) {//销售不允许底部的操作，不可滑动
            mOperatorEnableView.setVisibility(View.VISIBLE);
            deleteImage.setVisibility(View.GONE);
        } else {
            mOperatorEnableView.setVisibility(View.INVISIBLE);
            deleteImage.setVisibility(View.VISIBLE);
        }

        bg_order_past0.setVisibility(View.GONE);
        bg_order_past1.setVisibility(View.GONE);

        if (orderItem.getOrderStatus() == OrderStatus.COMPLETED) {//已完成
            order_txt_complete_hint.setVisibility(View.GONE);
            mLayoutOrdertOperator.setVisibility(View.GONE);
            deleteImage.setVisibility(View.VISIBLE);
            wuliu_number.setVisibility(View.VISIBLE);
            if (getContext() instanceof SaleOrderListActivity){
                order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);
                order_txt_complete_hint.setVisibility(View.VISIBLE);
                order_txt_complete_hint.setText("订单状态: 已完成");
            }
        } else if (getContext() instanceof SaleOrderListActivity) { //销售订单界面另外处理
            //样式处理
            order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);
            order_txt_mutli_complete_hint.setVisibility(View.GONE);
            relate_apply_tuikuan.setVisibility(View.GONE);

            mOperatorEnableView.setVisibility(View.INVISIBLE);
            mLayoutOrdertOperator.setVisibility(View.GONE);
            order_txt_complete_hint.setVisibility(View.VISIBLE);
//            mDeleteTv.setVisibility(View.GONE);

            if (orderItem.getOrderStatus() == OrderStatus.WAIT_PAY) {
                order_txt_complete_hint.setText("订单状态: 待付款");

            } else if (orderItem.getOrderStatus() == OrderStatus.WAIT_DELIVER) {
                order_txt_complete_hint.setText("订单状态: 待发货");
                order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);

            } else if (orderItem.getOrderStatus() == OrderStatus.DELIVERED) {
                order_txt_complete_hint.setText("订单状态: 待收货");
                order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);
            }

        } else {
            deleteImage.setVisibility(View.GONE);
            wuliu_number.setVisibility(View.VISIBLE);
            order_txt_complete_hint.setText("");
            order_txt_complete_hint.setVisibility(View.INVISIBLE);
            mLayoutOrdertOperator.setVisibility(View.VISIBLE);
            initOperatorLayout();
            if (orderItem.getOrderStatus() == OrderStatus.CANCELED || orderItem.isInvalid()) {
                // 先判断无效状态，可能服务器返回的订单已经过期，但是状态是待支付
                showInvalid(orderItem);
            } else if (orderItem.getOrderStatus() == OrderStatus.WAIT_PAY) {
                showWaitPay(orderItem, adapter, position);
            } else if (orderItem.getOrderStatus() == OrderStatus.WAIT_DELIVER) {
                showWaitDeliver(orderItem, adapter, position);
            } else if (orderItem.getOrderStatus() == OrderStatus.DELIVERED) {
                showDelivered(orderItem, adapter, position);
            }
        }

        //2. 根据商品个数不同，显示不同的展示样式
        if (CollectionUtil.getSize(orderItem.getSkuList()) == 1) {
            // 单个商品
            showSinglePro(orderItem.getSkuList().get(0));
        } else {
            // 多个商品
            showMultiPro(orderItem.getSkuList());
        }

        ll_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("tag", "-----------------");
                Context context = getContext();
                boolean invalid = false;
                if (orderItem.getOrderStatus() == OrderStatus.CANCELED || orderItem.isInvalid()) {
                    invalid = true;
                }
                if (context instanceof SaleOrderListActivity) {
                    OrderDetailsActivity.start(
                            getContext(), orderItem.getOrderNum(), orderItem.getCreatorId(), invalid);
                } else {
                    OrderDetailsActivity.start(
                            getContext(), orderItem.getOrderNum(), -1, invalid);
                }

            }
        });

        //订单完成申请售后
        appley_shouhou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemOperationListener != null) {
                    mItemOperationListener.onItemClickApplyAfterSale(orderItem);
                }

            }
        });

        order_apply_return_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemOperationListener != null) {
                    mItemOperationListener.onItemClickApplyAfterSale(orderItem);
                }
            }
        });
        //
        showPintuanGoods(orderItem);

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemOperationListener.onItemClickDelete(orderItem);
            }
        });


        wuliu_number.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LogisticsActivity.start(getContext(),orderItem);
            }
        });

    }

    public OrderVH setItemOperationListener(OrderListAdapter.ItemOperationListener itemOperationListener) {
        mItemOperationListener = itemOperationListener;
        return this;
    }

    /**
     * 显示单个商品的样式
     */
    private void showSinglePro(OrderCommodity commodity) {
        // 隐藏多个的控件状态
        mMultiProLayout.setVisibility(View.VISIBLE);
        // 显示单个的控件状态
        mSingleProLayout.setVisibility(View.VISIBLE);

        recycler_image.setVisibility(View.GONE);

        ImageLoaderUtil.displayImage(mProPicImg, commodity.getCommodityPicturePath());
        mBrandTxt.setText(commodity.getCommodityBrand());
        mNameTxt.setText(commodity.getCommodityName());
        mSkuTxt.setText(commodity.getCommoditySpec());
        mCommodityCode.setText("货号:" + commodity.getCommodityCode());
        //是否在退款退货
        orderStatusShow(1,commodity,false,false);
    }

    //是否可以点击物流
    private boolean isClickmOuttradenoTxt=true;
    //是否可以点击申请退换
    private boolean isClickmDetailTv=true;
    //是否可以点击确认收货
    private boolean isClickmDeliveredConfirmTv=true;


    /**
     * 退款 退货状态显示
     * @param flag 多个商品还是单个商品  1为单个 2为多个
     *             isOneRefund 多个商品里面是否是一个或者几个退款退货
     */
    public void orderStatusShow(int flag,OrderCommodity commodity,boolean isOneRefund,boolean isHasRefundID){
        if (commodity.getRefundType()!=0){
            if (listStatus.contains(commodity.getRefundStatus())){
                //首先隐藏三种状态。根据后续退款状态判断显示
                getmLayoutDelivered.setVisibility(View.GONE);
                order_txt_mutli_complete_hint_liner.setVisibility(View.GONE);
                mLayoutWaitDeliver.setVisibility(View.GONE);
                mLayoutOrdertOperator.setVisibility(View.GONE);
                mLayoutWaitPay.setVisibility(View.GONE);
                //退货 并且当前订单中其他商品不处于退款退货中
                if (commodity.getRefundType()==1||isHasRefundID){
                    //已完成
                    if (orderItem2.getOrderStatus()==OrderStatus.COMPLETED){
                        order_txt_mutli_complete_hint_liner.setVisibility(View.VISIBLE);
                        relate_apply_tuikuan.setVisibility(View.VISIBLE);
                        order_txt_mutli_complete_hint.setVisibility(View.VISIBLE);
                        wuliu_number.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                        appley_shouhou.setVisibility(View.VISIBLE);
                        //订单只有一个商品
                        if (flag==1 ){
                            view.setVisibility(View.INVISIBLE);
                            appley_shouhou.setVisibility(View.INVISIBLE);
                            order_txt_mutli_complete_hint.setText("退货中");
                            order_txt_complete_hint.setText("订单状态: 退货中");
                        }else{
                            //全部退货
                            if (isOneRefund){
                                view.setVisibility(View.INVISIBLE);
                                appley_shouhou.setVisibility(View.INVISIBLE);
                                order_txt_mutli_complete_hint.setText("退货中");
                                order_txt_complete_hint.setText("订单状态: 退货中");
                            }else{
                                //处理一个订单中有可能会有退款中的状态
                                relate_apply_tuikuan.setVisibility(View.VISIBLE);
                                if (isHasRefundID){
                                    order_txt_mutli_complete_hint.setText("部分退款中");
                                    order_txt_complete_hint.setText("订单状态: 部分退款中");
                                }else{
                                    order_txt_mutli_complete_hint.setText("部分退货中");
                                    order_txt_complete_hint.setText("订单状态: 部分退货中");
                                }
                            }
                        }
                        //待收货状态
                    }else if (orderItem2.getOrderStatus()==OrderStatus.DELIVERED){
                        mLayoutOrdertOperator.setVisibility(View.VISIBLE);
                        getmLayoutDelivered.setVisibility(View.VISIBLE);
                        //物流
                        mOuttradenoTxt.setVisibility(View.VISIBLE);
                        //售后申请
                        mDetailTv.setVisibility(View.VISIBLE);
                        //确认收货
                        mDeliveredConfirmTv.setVisibility(View.VISIBLE);
                        //多个商品显示状态
                        order_txt_delivered_show_refund.setVisibility(View.VISIBLE);
                        if (flag == 1) {
                            isClickmOuttradenoTxt = true;
                            isClickmDetailTv = false;
                            mDetailTv.setVisibility(View.INVISIBLE);
                            isClickmDeliveredConfirmTv = false;
                            mDeliveredConfirmTv.setVisibility(View.GONE);
                            order_txt_delivered_show_refund.setText("退货中");
                            order_txt_complete_hint.setText("订单状态: 退货中");
                        } else {
                            if (isOneRefund) {
                                isClickmOuttradenoTxt = true;
                                isClickmDetailTv = false;
                                mDetailTv.setVisibility(View.INVISIBLE);
                                isClickmDeliveredConfirmTv = false;
                                mDeliveredConfirmTv.setVisibility(View.GONE);
                                order_txt_delivered_show_refund.setText("退货中");
                                order_txt_complete_hint.setText("订单状态: 退货中");
                            } else {
                                isClickmOuttradenoTxt = true;
                                isClickmDetailTv = true;
                                isClickmDeliveredConfirmTv = true;
                                order_txt_delivered_show_refund.setVisibility(View.GONE);
                                mDeliveredConfirmTv.setVisibility(View.VISIBLE);
                                order_txt_complete_hint.setText("订单状态: 部分退货中");
                            }
                        }
                    }
                    //退款
                }else if (commodity.getRefundType()==3){
                    mLayoutOrdertOperator.setVisibility(View.VISIBLE);
                    mLayoutWaitDeliver.setVisibility(View.VISIBLE);
                    text_show_beihou.setVisibility(View.VISIBLE);
                    view_wait_deliver.setVisibility(View.VISIBLE);
                    mRemindDeleverTv.setVisibility(View.VISIBLE);
                    mRemindDeleverTv.setClickable(false);
                    ismRemindDeleverTvClickAble=false;
                    order_apply_return_money.setVisibility(View.VISIBLE);
                    if (flag==1){
                        order_apply_return_money.setVisibility(View.INVISIBLE);
                        text_show_beihou.setVisibility(View.INVISIBLE);
                        view_wait_deliver.setVisibility(View.INVISIBLE);
                        mRemindDeleverTv.setText("退款中");
                        order_txt_complete_hint.setText("订单状态: 退款中");
                    }else{
                        if (isOneRefund){
                            text_show_beihou.setVisibility(View.INVISIBLE);
                            view_wait_deliver.setVisibility(View.INVISIBLE);
                            order_apply_return_money.setVisibility(View.INVISIBLE);
                            mRemindDeleverTv.setText("退款中");
                            order_txt_complete_hint.setText("订单状态: 退款中");
                        }else{
                            view_wait_deliver.setVisibility(View.VISIBLE);
                            text_show_beihou.setVisibility(View.VISIBLE);
                            mRemindDeleverTv.setText("部分退款中");
                            order_txt_complete_hint.setText("订单状态: 部分退款中");
                        }
                    }
                }
            }else if (commodity.getRefundStatus()==6){
                //退货
                if (commodity.getRefundType()==1||isHasRefundID){
                    if (orderItem2.getOrderStatus()==OrderStatus.COMPLETED){
                        mLayoutOrdertOperator.setVisibility(View.GONE);
                        order_txt_mutli_complete_hint_liner.setVisibility(View.VISIBLE);
                        relate_apply_tuikuan.setVisibility(View.VISIBLE);
                        order_txt_mutli_complete_hint.setVisibility(View.VISIBLE);
                        wuliu_number.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                        appley_shouhou.setVisibility(View.VISIBLE);
                        //订单只有一个商品
                        if (flag==1 ){
                            view.setVisibility(View.INVISIBLE);
                            appley_shouhou.setVisibility(View.INVISIBLE);
                            order_txt_mutli_complete_hint.setText("已退货");
                            order_txt_complete_hint.setText("订单状态: 已退货");
                        }else{
                            if (isOneRefund){
                                view.setVisibility(View.INVISIBLE);
                                appley_shouhou.setVisibility(View.INVISIBLE);
                                order_txt_mutli_complete_hint.setText("已退货");
                                order_txt_complete_hint.setText("订单状态: 已退货");
                            }else{
                                relate_apply_tuikuan.setVisibility(View.VISIBLE);
                                order_txt_mutli_complete_hint.setText("部分已退货");
                                order_txt_complete_hint.setText("订单状态: 部分已退货");
                            }
                        }
                    }else if (orderItem2.getOrderStatus()==OrderStatus.DELIVERED){
                        getmLayoutDelivered.setVisibility(View.VISIBLE);
                        mLayoutOrdertOperator.setVisibility(View.VISIBLE);
                        //物流
                        mOuttradenoTxt.setVisibility(View.VISIBLE);
                        //售后申请
                        mDetailTv.setVisibility(View.VISIBLE);
                        //确认收货
                        mDeliveredConfirmTv.setVisibility(View.VISIBLE);
                        //多个商品显示状态
                        order_txt_delivered_show_refund.setVisibility(View.VISIBLE);
                        if (flag == 1) {
                            isClickmOuttradenoTxt = true;
                            isClickmDetailTv = false;
                            mDetailTv.setVisibility(View.INVISIBLE);
                            isClickmDeliveredConfirmTv = false;
                            mDeliveredConfirmTv.setVisibility(View.GONE);
                            order_txt_delivered_show_refund.setText("已退货");
                            order_txt_complete_hint.setText("订单状态: 已退货");
                        } else {
                            if (isOneRefund) {
                                isClickmOuttradenoTxt = true;
                                isClickmDetailTv = false;
                                mDetailTv.setVisibility(View.INVISIBLE);
                                isClickmDeliveredConfirmTv = false;
                                mDeliveredConfirmTv.setVisibility(View.GONE);
                                order_txt_delivered_show_refund.setText("已退货");
                                order_txt_complete_hint.setText("订单状态: 已退货");
                            } else {
                                isClickmOuttradenoTxt = true;
                                isClickmDetailTv = true;
                                isClickmDeliveredConfirmTv = true;
                                order_txt_delivered_show_refund.setVisibility(View.GONE);
                                mDeliveredConfirmTv.setVisibility(View.VISIBLE);
                                order_txt_complete_hint.setText("订单状态: 部分已退货");
                            }
                        }
                    }
                    //退款
                }else if (commodity.getRefundType()==3){
                    mLayoutOrdertOperator.setVisibility(View.VISIBLE);
                    mLayoutWaitDeliver.setVisibility(View.VISIBLE);
                    text_show_beihou.setVisibility(View.VISIBLE);
                    view_wait_deliver.setVisibility(View.VISIBLE);
                    mRemindDeleverTv.setVisibility(View.VISIBLE);
                    mRemindDeleverTv.setClickable(false);
                    ismRemindDeleverTvClickAble=false;
                    order_apply_return_money.setVisibility(View.VISIBLE);
                    if (flag==1){
                        order_apply_return_money.setVisibility(View.INVISIBLE);
                        text_show_beihou.setVisibility(View.INVISIBLE);
                        view_wait_deliver.setVisibility(View.INVISIBLE);
                        mRemindDeleverTv.setText("已退款");
                        order_txt_complete_hint.setText("订单状态: 已退款");
                    }else{
                        if (isOneRefund){
                            text_show_beihou.setVisibility(View.INVISIBLE);
                            view_wait_deliver.setVisibility(View.INVISIBLE);
                            order_apply_return_money.setVisibility(View.INVISIBLE);
                            mRemindDeleverTv.setText("已退款");
                            order_txt_complete_hint.setText("订单状态: 已退款");
                        }else{
                            view_wait_deliver.setVisibility(View.VISIBLE);
                            text_show_beihou.setVisibility(View.VISIBLE);
                            mRemindDeleverTv.setText("部分退款中");
                            order_txt_complete_hint.setText("订单状态: 部分已退款");
                        }
                    }
                }
             }
        }
    }


    /**
     * 显示多个商品的样式
     */
    private void showMultiPro(List<OrderCommodity> commodityList) {
        // 隐藏单个的控件状态
        mSingleProLayout.setVisibility(View.INVISIBLE);
        // 显示多个的控件状态
        mMultiProLayout.setVisibility(View.VISIBLE);

        recycler_image.setVisibility(View.VISIBLE);

        imageMoreAdapter=new ImageMoreAdapter(getContext());

        boolean invalid = false;
        if (orderItem2.getOrderStatus() == OrderStatus.CANCELED || orderItem2.isInvalid()) {
            invalid = true;
        }
        final boolean invalid_2=invalid;
        imageMoreAdapter.setOrderItem(orderItem2,invalid);
        imageMoreAdapter.setOnClickImageListenr(new ImageMoreAdapter.OnClickImageListenr() {
            @Override
            public void onClickItem() {
                if (getContext() instanceof SaleOrderListActivity) {
                    OrderDetailsActivity.start(
                            getContext(), orderItem2.getOrderNum(), orderItem2.getCreatorId(), invalid_2);
                } else {
                        OrderDetailsActivity.start(
                                getContext(), orderItem2.getOrderNum(), -1, invalid_2);
                }
            }
        });
        recycler_image.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recycler_image.setAdapter(imageMoreAdapter);
        List<ReturnGoodsBean> list=new ArrayList<>();
        if (commodityList!=null && commodityList.size()>0){
            for (OrderCommodity com:commodityList
                 ) {
                ReturnGoodsBean returnGoodsBean=new ReturnGoodsBean();
                returnGoodsBean.setImageUrl(com.getCommodityPicturePath());
                if (com.getRefundId()!=0){
                    returnGoodsBean.setReturn(true);
                    if (com.getRefundStatus()!=0){
                        returnGoodsBean.setRefundID(com.getRefundId());
                        setStatusShow(returnGoodsBean,com);
                    }
                }
                list.add(returnGoodsBean);
            }
        }
        imageMoreAdapter.setList(list);

//    }        int listSize = CollectionUtil.getSize(commodityList);
//        for (int i = 0, c = mPro1PicImgs.length; i < c; i++) {
//            ImageView img = mPro1PicImgs[i];
//            if (listSize < i + 1) {
//                // 数据没达到数量，隐藏固定的控件位
//                img.setVisibility(View.INVISIBLE);
//                continue;
//            }
//            img.setVisibility(View.VISIBLE);
//            ImageLoaderUtil.displayImage(img, commodityList.get(i).getCommodityPicturePath());
        int tatol=0;
        OrderCommodity commodity=null;
        OrderCommodity commodityFinsh=null;
        int newStatus=0;
        int newStatusFinsh=0;
        boolean isReund=false;//是否在退退货款中
        boolean isReundFinsh=false;//是否退货退款完成
        boolean isHasRefundID=false;//判断一个订单中是否有没有退款退货的
        for (int i = 0; i < commodityList.size(); i++) {
            OrderCommodity com=commodityList.get(i);
            //有退款退货状态的
            if (com.getRefundType()!=0){
                tatol++;
                if (listStatus.contains(com.getRefundStatus())){
                    isReund=true;
                    if (newStatus<com.getRefundStatus()){
                        newStatus=com.getRefundStatus();
                        commodity=com;
                    }
                    //退款完成
                }else if (com.getRefundStatus()==6){
                    isReundFinsh=true;
                    if (newStatusFinsh<com.getRefundStatus()){
                        newStatusFinsh=com.getRefundStatus();
                        commodityFinsh=com;
                    }
                }
                //无退款退货状态的
            }else if (com.getRefundId()==0){
                //已发货了或者已完成的呢
                if (orderItem2.getOrderStatus()==OrderStatus.DELIVERED
                        || orderItem2.getOrderStatus()==OrderStatus.COMPLETED){
                    isHasRefundID=true;
                }
            }
        }

        boolean isOneAndAll=false;
        //全部退款退货中
        if (tatol==commodityList.size()){
            isOneAndAll=true;
            //部分退款退货中
        }else{
            isOneAndAll=false;
        }
        //处于部分退货退款中，或者全部退货退款中
        if (isReund){
            orderStatusShow(2,commodity,isOneAndAll,isHasRefundID);
            //当没有退款退货中的时候，就是全部退款货退货中
        }else if (isReundFinsh){
            orderStatusShow(2,commodityFinsh,isOneAndAll,isHasRefundID);
        }
    }

    /**
     * 各种状态下的显示 flag 1 表示订单列表上显示的状态，2表示订单列表上，商品的状态
     */
    public void setStatusShow(ReturnGoodsBean returnGoodsBean,OrderCommodity com){
        switch (com.getRefundStatus()){
            //申请中
            case 1:
                //退货退款
                returnGoodsBean.setTextReturn("待审核...");

                break;
            //审核成功
            case 2:
                //退货退款
                returnGoodsBean.setTextReturn("审核通过");

                break;
            //审核失败
            case 3:
                returnGoodsBean.setTextReturn("审核未通过...");
                break;
            //已撤销
            case 4:
                //退货退款
                break;
            //评估中
            case 5:
                if (com.getRefundType()==1){
                    returnGoodsBean.setTextReturn("商品评估中...");
                    //退款
                }else if (com.getRefundType()==3){
                    returnGoodsBean.setTextReturn("退款中...");
                }
                break;
            //已退款
            case 6:
                if (com.getRefundType()==1){
                    returnGoodsBean.setTextReturn("已退货");
                    //退款
                }else if (com.getRefundType()==3){
                    returnGoodsBean.setTextReturn("已退款");
                }
                break;
            //退款中
            case 7:
                if (com.getRefundType()==1){
                    returnGoodsBean.setTextReturn("退货中...");
                                    //退款
                }else if (com.getRefundType()==3){

                    returnGoodsBean.setTextReturn("退款中...");
                }
                //退货退款
                break;
            //寄回中
            case 8:
                if (com.getRefundType()==1){
                    returnGoodsBean.setTextReturn("商品寄回中...");
                    //退款
                }else if (com.getRefundType()==3){
                    returnGoodsBean.setTextReturn("退款中...");
                }
                //退货退款
                break;
            //退款失败
            case 9:
                if (com.getRefundType()==1){
                    returnGoodsBean.setTextReturn("退货中...");
                    //退款
                }else if (com.getRefundType()==3){
                    returnGoodsBean.setTextReturn("退款中...");
                }
                break;
        }

    }

    /**
     * 待支付状态的显示
     */
    private void showWaitPay(final OrderItem orderItem, final OrderListAdapter adapter, final int position) {
        mLayoutWaitPay.setVisibility(View.VISIBLE);

        TimeCountDownController countDownController =
                createCountdownController(
                        orderItem.getInvalidTime(),
                        mCountDownTxt,
                        new TimeCountDownController.CountDownListener() {
                            @Override
                            public void onFinish() {
                                adapter.removeCountdownController(position);
                                showViewContent(orderItem);
                            }
                        });
        countDownController.start();
        adapter.addCountdownController(position, countDownController);

        mPayTxt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemOperationListener != null) {
                            mItemOperationListener.onItemCLickPay(orderItem);
                        }
                    }
                });
        mCancelTxt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemOperationListener != null) {
                            mItemOperationListener.onItemClickCancel(orderItem);
                        }
                    }
                });
    }

    private void showWaitDeliver(final OrderItem orderItem, final OrderListAdapter adapter, final int position) {
        mLayoutWaitDeliver.setVisibility(View.VISIBLE);
        text_show_beihou.setVisibility(View.VISIBLE);
        order_apply_return_money.setVisibility(View.VISIBLE);
        view_wait_deliver.setVisibility(View.VISIBLE);
        mRemindDeleverTv.setVisibility(View.VISIBLE);
        mRemindDeleverTv.setText("提醒发货");
        mRemindDeleverTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemOperationListener != null&& ismRemindDeleverTvClickAble) {
                    mItemOperationListener.onItemClickRemindDeliver(orderItem);
                }
            }
        });
    }

    private void showDelivered(final OrderItem orderItem, final OrderListAdapter adapter, final int position) {
        getmLayoutDelivered.setVisibility(View.VISIBLE);
        mOuttradenoTxt.setVisibility(View.VISIBLE);
        mDetailTv.setVisibility(View.VISIBLE);
        mDeliveredConfirmTv.setVisibility(View.VISIBLE);
        mDetailTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mItemOperationListener != null) {
////                    mItemOperationListener.onItemClickLogisticsDetail(orderItem);
////                }
                if (mItemOperationListener != null&& isClickmDetailTv) {
                    mItemOperationListener.onItemClickApplyAfterSale(orderItem);
                }

            }
        });
        mDeliveredConfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemOperationListener != null && isClickmDeliveredConfirmTv) {
                    mItemOperationListener.onItemClickConfirmDelivered(orderItem);
                }
            }
        });
        mOuttradenoTxt.setText(
                String.format(
                        getContext().getString(R.string.order_outtradeno_format_code),
                        orderItem.getOuttradeno()));
        mOuttradenoTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClickmOuttradenoTxt){
                    LogisticsActivity.start(getContext(),orderItem);
                }
            }
        });
    }

    private TimeCountDownController createCountdownController(
            Date date, TextView textView, TimeCountDownController.CountDownListener listener) {
        return new TimeCountDownController(date, textView, null, listener);
    }


    /**
     * 已经失效的显示
     */
    private void showInvalid(final OrderItem orderItem) {
        //显示失效的操作
 /*       mLayoutWaitPayCancel.setVisibility(View.VISIBLE);
        mDeleteTxt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemOperationListener != null) {
                            mItemOperationListener.onItemClickDelete(orderItem);
                        }
                    }
                });*/
        //显示订单失效
        bg_order_past0.setVisibility(View.VISIBLE);
        bg_order_past1.setVisibility(View.VISIBLE);
        mLayoutOrdertOperator.setVisibility(View.GONE);
        mLayoutWaitPayCancel.setVisibility(View.GONE);
//        mDeleteTv.setVisibility(View.VISIBLE);
//        mDeleteTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mItemOperationListener != null) {
//                    mItemOperationListener.onItemClickDelete(orderItem);
//                }
//            }
//        });
    }


    private void initOperatorLayout() {
        mLayoutWaitPayCancel.setVisibility(View.INVISIBLE);
        mLayoutWaitPay.setVisibility(View.INVISIBLE);
        mLayoutWaitDeliver.setVisibility(View.INVISIBLE);
        getmLayoutDelivered.setVisibility(View.INVISIBLE);
    }


    /**
     * 重置view，防止复用
     */
    private void resetItemView() {
        order_top_info_normal.setVisibility(View.VISIBLE);
        order_top_info_pingtuan.setVisibility(View.GONE);
        order_top_info_pingtuan_downTime.stopTickWork();
        //
        mLayoutWaitPay.setVisibility(View.VISIBLE);//待付款
        mLayoutWaitPayCancel.setVisibility(View.GONE);//订单失效
        mLayoutWaitDeliver.setVisibility(View.GONE);//待发货
        getmLayoutDelivered.setVisibility(View.GONE);//已发货
        //
        oder_layout_pingtuan_fail.setVisibility(View.GONE);
        oder_layout_pingtuan_invite_friend.setVisibility(View.GONE);
    }

    /**
     * 显示拼团商品
     */
    private void showPintuanGoods(final OrderItem orderItem) {
        if (StringUtil.isEmpty(orderItem.getActivityType()) || !orderItem.getActivityType().equals("2")) {
            return;
        }
        order_apply_return_money.setVisibility(View.INVISIBLE);
        mDetailTv.setVisibility(View.INVISIBLE);
        appley_shouhou.setVisibility(View.INVISIBLE);
        view.setVisibility(View.INVISIBLE);

        order_top_info_normal.setVisibility(View.GONE);
        order_top_info_pingtuan.setVisibility(View.VISIBLE);
//        mLayoutWaitPay.setVisibility(View.GONE);//待付款
//        mLayoutWaitPayCancel.setVisibility(View.GONE);//订单失效
//        mLayoutWaitDeliver.setVisibility(View.GONE);//待发货
//        getmLayoutDelivered.setVisibility(View.GONE);//已发货
        oder_layout_pingtuan_fail.setVisibility(View.GONE);//拼团失败
        oder_layout_pingtuan_invite_friend.setVisibility(View.GONE);//邀请朋友拼团
        order_top_info_pingtuan_downTime.stopTickWork();
        //
        order_top_info_pingtuan_createTime.setText(TimeUtils.timeToStrThread(orderItem.getCreateTime()));
        order_top_info_pingtuan_count.setText(String.valueOf("数量:" + orderItem.getCount()));
        //"实付款:" +
        order_top_info_pingtuan_price.setText(String.valueOf(StringFormatUtil.getPrice(getContext(), orderItem.getTotalPrice())));
        if (orderItem.getCollageStatus() == null) {
            order_top_info_pingtuan_status.setText("拼团商品");
            order_top_info_pingtuan_downTime.setText("");
            return;
        }
        switch (orderItem.getCollageStatus()) {
            case "0"://拼团失败
                mLayoutWaitPay.setVisibility(View.GONE);//待付款
                mLayoutWaitPayCancel.setVisibility(View.GONE);//订单失效
                mLayoutWaitDeliver.setVisibility(View.GONE);//待发货
                getmLayoutDelivered.setVisibility(View.GONE);//已发货
                order_top_info_pingtuan_status.setText("拼团商品：失败");
                order_top_info_pingtuan_downTime.setText("");
                oder_layout_pingtuan_fail.setVisibility(View.VISIBLE);
                if (orderItem.getOrderStatus() == OrderStatus.PAY_BACK_SUCCEED){
                    pingtuan_fail_cause.setText("退款状态:退款成功");
                }else {
                    pingtuan_fail_cause.setText("退款状态:正在退款");
                }
                break;
            case "1"://拼团中
                if (orderItem.getOrderStatus() == OrderStatus.WAIT_DELIVER) {
                    mLayoutWaitPay.setVisibility(View.GONE);//待付款
                    mLayoutWaitPayCancel.setVisibility(View.GONE);//订单失效
                    mLayoutWaitDeliver.setVisibility(View.GONE);//待发货
                    getmLayoutDelivered.setVisibility(View.GONE);//已发货
                    oder_layout_pingtuan_invite_friend.setVisibility(View.VISIBLE);
                }
                StringBuilder builder = new StringBuilder();
                builder.append("拼团商品：")
                        .append("待拼成 ")
                        .append("差")
                        .append(orderItem.getRemainNum())
                        .append("人");
                order_top_info_pingtuan_status.setText(String.valueOf(builder));
                Date date = new Date();
                if (Long.valueOf(orderItem.getEndTime()) > date.getTime()) {
                    order_top_info_pingtuan_downTime.startTickWork(Long.valueOf(orderItem.getEndTime()) - date.getTime(),
                            PingtuanCountDownTextView.PING_TUAN_ORDER_LIST);
                } else {
                    order_top_info_pingtuan_downTime.setText(String.valueOf("剩余时间: 已结束"));
                }
                order_tv_pingtuan_invite_friend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mItemOperationListener != null) {
                            mItemOperationListener.onItemClickInviteFriendPintuan(orderItem);
                        }
                    }
                });
                break;
            case "2"://拼团成功
                order_top_info_pingtuan_status.setText("拼团商品：已拼成");
                order_top_info_pingtuan_downTime.setText("");
//                getmLayoutDelivered.setVisibility(View.VISIBLE);
                break;
        }
    }



}
