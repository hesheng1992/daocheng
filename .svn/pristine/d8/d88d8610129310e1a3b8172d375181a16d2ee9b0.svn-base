package com.a1magway.bgg.v.productReturn;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PFragment;
import com.a1magway.bgg.data.entity.OrderItem;
import com.a1magway.bgg.data.entity.SalesReturn;
import com.a1magway.bgg.p.productReturn.PhotoSelectadapter;
import com.a1magway.bgg.p.productReturn.ReturnProductListdapter;
import com.a1magway.bgg.v.order.LogisticsActivity;
import com.almagway.common.utils.StringUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReturnProductInfoFragment extends PFragment {

    private static final String EXTRA_OUTTRADENO = "EXTRA_OUTTRADENO";
    private static final String SALES_RETURN = "SALES_RETURN";
    @BindView(R.id.return_content_goods)
    RecyclerView returnContentGoods;
    @BindView(R.id.return_content_phone)
    TextView returnContentPhone;
    @BindView(R.id.return_content_reason)
    TextView returnContentReason;
    @BindView(R.id.return_content_descibe)
    TextView returnContentDescibe;
    @BindView(R.id.return_content_money)
    TextView returnContentMoney;
    @BindView(R.id.return_content_code)
    TextView returnContentCode;

    /**
     * 寄回快递的物流信息入口
     */
    @BindView(R.id.liner_logs)
    LinearLayout linearLayout;
    //寄回快递的编号
    @BindView(R.id.text_log_number)
    TextView text_log_number;

    @BindView(R.id.image_right)
    ImageView image_right;


    private OrderItem orderItem;
    private SalesReturn salesReturn;
    @BindView(R.id.return_content_img_info)
    RecyclerView returnContentImgInfo;
    private PhotoSelectadapter mPhotoAdapter;

    public static ReturnProductInfoFragment newIntance(OrderItem item, SalesReturn salesReturn) {
        // Required empty public constructor
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_OUTTRADENO,item);
        bundle.putSerializable(SALES_RETURN,salesReturn);
        ReturnProductInfoFragment fragment = new ReturnProductInfoFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.fragment_return_product_info;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        super.initView(savedInstanceState, contentView);
        orderItem= (OrderItem) getArguments().getSerializable(EXTRA_OUTTRADENO);
        salesReturn= (SalesReturn) getArguments().getSerializable(SALES_RETURN);

        returnContentGoods.setLayoutManager(new LinearLayoutManager(getContext()));
        ReturnProductListdapter mGoodsAdapter=new ReturnProductListdapter(orderItem.getSkuList());
        mGoodsAdapter.setHideSelct(true);
        returnContentGoods.setAdapter(mGoodsAdapter);
        returnContentImgInfo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        if (salesReturn.getImgs()==null){
            mPhotoAdapter=new PhotoSelectadapter(new ArrayList<String>());
        }else{
            mPhotoAdapter=new PhotoSelectadapter(salesReturn.getImgs());
        }
        returnContentImgInfo.setAdapter(mPhotoAdapter);

        //跳转到物流详情页面
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),RefundGoodsLogInfoActivity.class);
                intent.putExtra("refundId",String.valueOf(orderItem.getRefundId()));
                getActivity().startActivity(intent);
            }
        });


    }
    //获取显示退货详情
    public void setSalesReturn(SalesReturn salesReturn,String loginfoNumber,int flag){
        if (salesReturn!=null){
            this.salesReturn=salesReturn;
            returnContentPhone.setText("联系电话: "+salesReturn.getPhone());
            returnContentMoney.setText("退款金额: "+salesReturn.getMoney());
            //退货
            if (flag==1){
                returnContentReason.setText("退货原因: "+salesReturn.getReason());
                returnContentCode.setText("退货编号: "+salesReturn.getCode());
                returnContentDescibe.setText("退货说明: "+salesReturn.getDescribe());
            }else if(flag==3){
                returnContentCode.setText("退款编号: "+salesReturn.getCode());
                returnContentReason.setText("退款原因: "+salesReturn.getReason());
                returnContentDescibe.setText("退款说明: "+salesReturn.getDescribe());
            }

            if (!StringUtil.isEmpty(loginfoNumber)){
                linearLayout.setVisibility(View.VISIBLE);
                text_log_number.setText("寄回物流编号: "+loginfoNumber);
            }
            if (salesReturn.getImgs()!=null && salesReturn.getImgs().size()>0){
                mPhotoAdapter.setList(salesReturn.getImgs());
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
