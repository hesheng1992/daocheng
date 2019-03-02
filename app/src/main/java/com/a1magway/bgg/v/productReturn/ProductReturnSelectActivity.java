package com.a1magway.bgg.v.productReturn;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.common.adapter.BaseRecyclerAdapter;
import com.a1magway.bgg.data.entity.OrderCommodity;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.p.productReturn.ProductReturnSelectP;
import com.a1magway.bgg.util.DateUtils;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.v.login.LoginContract;
import com.a1magway.bgg.widget.TitleBar;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lm on 2018/8/30.
 */
public class ProductReturnSelectActivity extends PActivity<ProductReturnSelectP> implements IProductReturnSelectV {

    private static final String EXTRA_OUTTRADENO = "EXTRA_OUTTRADENO";

    @BindView(R.id.select_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.select_ok_btn)
    Button selectOkBtn;
    @BindView(R.id.select_title_bar)
    TitleBar selectTitleBar;
    @BindView(R.id.return_product_date)
    TextView returnProductDate;
    @BindView(R.id.return_product_number)
    TextView returnProductNumber;
    @BindView(R.id.return_product_price)
    TextView returnProductPrice;
    @BindView(R.id.return_product_all_select)
    ImageView returnProductAllSelect;
    @BindView(R.id.return_product_select)
    TextView returnProductSelect;

    private OrderItem item;

    private boolean isSelect=false;

    private ProductReturnSelectP productReturnSelectP;

    public static final void start(Context context, OrderItem orderItem) {
        Intent intent = new Intent(context, ProductReturnSelectActivity.class);
        intent.putExtra(EXTRA_OUTTRADENO, orderItem);
        JumpUtil.startActivity(context, intent);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        item = (OrderItem) getIntent().getSerializableExtra(EXTRA_OUTTRADENO);
        productReturnSelectP = new ProductReturnSelectP(this, item);
        productReturnSelectP.getGoodsInfo();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new LinearItemDecoration(getContext(), R.color.gray_c10,
                R.dimen.product_detail_divider));
        returnProductDate.setText(DateUtils.formatString(new Date(),DateUtils.TIME_DEFAULT_ONE));
        returnProductPrice.setText(item.getTotalPrice());
        returnProductNumber.setText("数量：0");
        returnProductSelect.setText("已选择商品：0");

    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_product_return_select;
    }


    @Override
    public void GoodsInfo(BaseRecyclerAdapter mAdapter) {

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void selectNumber(Integer number) {
        returnProductSelect.setText("已选择商品："+number);
        if (number==mRecyclerView.getAdapter().getItemCount()){
            returnProductAllSelect.setImageResource(R.mipmap.is_select);
        }else{
            returnProductAllSelect.setImageResource(R.mipmap.is_select_normal);
        }
    }

    @Override
    public void selectGoods(OrderItem orderItem) {
        if (orderItem.getSkuList().size()==0){
            Toast.makeText(this,"请选择商品",Toast.LENGTH_SHORT).show();
        }else {
            ApplyReturnActivity.start(this,orderItem);
            finish();
        }
    }


    @OnClick({R.id.return_product_all_select,R.id.select_ok_btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.return_product_all_select:
                if (isSelect){
                    returnProductAllSelect.setImageResource(R.mipmap.is_select_normal);
                    isSelect=false;
                    returnProductSelect.setText("已选择商品：0");
                }else {
                    returnProductAllSelect.setImageResource(R.mipmap.is_select);
                    isSelect=true;
                    returnProductSelect.setText("已选择商品："+mRecyclerView.getAdapter().getItemCount());
                }
                productReturnSelectP.onClickAllSelect(isSelect);
                break;
            case R.id.select_ok_btn:
                productReturnSelectP.getSelectGood();
                break;

        }
    }


    @OnClick(R.id.select_title_bar)
    public void onClickBack(View v) {
        onBackButtonClick();
    }

}
