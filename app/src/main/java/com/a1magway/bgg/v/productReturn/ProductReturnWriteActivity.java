package com.a1magway.bgg.v.productReturn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.OrderStatus;
import com.a1magway.bgg.p.productReturn.ProductReturnSelectP;
import com.a1magway.bgg.p.productReturn.ProductReturnWriteP;
import com.a1magway.bgg.util.DateUtils;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lm on 2018/8/30.
 */
public class ProductReturnWriteActivity extends PActivity<ProductReturnWriteP> implements  IProductReturnWriteV {

    private static final String EXTRA_OUTTRADENO = "EXTRA_OUTTRADENO";
    @BindView(R.id.write_title_bar)
    TitleBar writeTitleBar;
    @BindView(R.id.write_rv)
    RecyclerView writeRv;
    @BindView(R.id.write_return_all_layout)
    LinearLayout writeReturnAllLayout;
    @BindView(R.id.write_return_money)
    LinearLayout writeReturnMoney;

    @BindView(R.id.text_tuikuantuihuo)
    TextView text_tuikuantuihuo;

    @BindView(R.id.text_tuikuantuihuo_dec)
    TextView text_tuikuantuihuo_dec;

    @BindView(R.id.text_tuikuan)
    TextView text_tuikuan;

    @BindView(R.id.text_tuikuan_dec)
    TextView text_tuikuan_dec;

    private OrderItem orderItem;

    private ProductReturnWriteP productReturnWriteP;

    @BindView(R.id.text_order_number)
    TextView text_order_number;

    @BindView(R.id.text_order_time)
    TextView text_order_time;

    public static final void start(Context context, OrderItem orderItem) {
        Intent intent = new Intent(context, ProductReturnWriteActivity.class);
        intent.putExtra(EXTRA_OUTTRADENO, orderItem);
        JumpUtil.startActivity(context, intent);
    }




    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        orderItem = (OrderItem) getIntent().getSerializableExtra(EXTRA_OUTTRADENO);
        productReturnWriteP=new ProductReturnWriteP(this,orderItem);
        productReturnWriteP.initRV();
        writeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        writeRv.addItemDecoration(new LinearItemDecoration(getContext(), R.color.gray_c10,
                R.dimen.product_detail_divider));
        if (orderItem!=null){
            text_order_number.setText("订单编号："+orderItem.getOrderNum());
            text_order_time.setText("下单时间："+ DateUtils.formatString(orderItem.getCreateTime(),DateUtils.TIME_DEFAULT_ONE));
            //代发货
            if (orderItem.getOrderStatus()== OrderStatus.WAIT_DELIVER){
                text_tuikuantuihuo.setTextColor(getResources().getColor(R.color.member_card_item_bg_three));
                text_tuikuantuihuo_dec.setTextColor(getResources().getColor(R.color.member_card_item_bg_three));
                writeReturnAllLayout.setClickable(false);
                //已发货 已完成
            }else if (orderItem.getOrderStatus()== OrderStatus.DELIVERED
                    || orderItem.getOrderStatus()==OrderStatus.COMPLETED){
                text_tuikuan.setTextColor(getResources().getColor(R.color.member_card_item_bg_three));
                text_tuikuan_dec.setTextColor(getResources().getColor(R.color.member_card_item_bg_three));
                writeReturnMoney.setClickable(false);
            }
        }

    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_product_return_write;
    }

    @Override
    public void initAdapter(BaseRecyclerAdapter adapter) {
        writeRv.setAdapter(adapter);
    }

    @OnClick({R.id.write_return_all_layout,R.id.write_return_money})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.write_return_money:
                orderItem.setType(3);
                ApplyReturnActivity.start(this,orderItem);
//                ApplyReturnActivity.start(this,orderItem);

                break;
            case R.id.write_return_all_layout:
                orderItem.setType(1);
                ApplyReturnActivity.start(this,orderItem);
//                ApplyReturnActivity.start(this,orderItem);

                break;
        }
    }

    @OnClick(R.id.write_title_bar)
    public void onClickBack(View v) {
        onBackButtonClick();
    }
}
