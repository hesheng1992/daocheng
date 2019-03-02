package com.a1magway.bgg.v.personal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.data.entity.AddressSelected;
import com.a1magway.bgg.data.entity.AddressSelectedDataBean;
import com.a1magway.bgg.eventbus.event.SelectedCityEvent;
import com.a1magway.bgg.p.personal.AddressSelectedAdapter;
import com.a1magway.bgg.widget.LoadMoreRecyclerView;
import com.a1magway.bgg.widget.divider.LinearItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/7/21.
 */


public class SelectedAddressActivity extends BaseActivity implements AddressSelectedAdapter.OnItemClickListener {
    public static final String DATA = "data";
    public static final String GRADE = "grade";

    @BindView(R.id.select_back)
    ImageView selectBack;
    @BindView(R.id.address_recyclerView)
    LoadMoreRecyclerView addressRecyclerView;
    private List<AddressSelected> addressSelectedList;
    private AddressSelectedDataBean dataBean0;
    private List<AddressSelectedDataBean.DataBean> dataBeanList0;//州
    private AddressSelectedDataBean.DataBean dataBean1;
    private List<AddressSelectedDataBean.DataBean.SonListBeanX> dataBeanList1;//市
    private AddressSelectedDataBean.DataBean.SonListBeanX dataBean2;
    private List<AddressSelectedDataBean.DataBean.SonListBeanX.SonListBean> dataBeanList2;//县
    private int grade;
    private AddressSelectedAdapter addressSelectedAdapter;


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_selected_address;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        //得到几级页面
        grade = getIntent().getIntExtra(GRADE, -1);
        switch (grade) {
            case 0:
                //一级页面(省)
                dataBean0 = getIntent().getParcelableExtra(DATA);
                dataBeanList0 = dataBean0.getData();
                break;
            case 1:
                //二级页面(市)
                dataBean1 = getIntent().getParcelableExtra(DATA);
                dataBeanList1 = dataBean1.getSonList();
                break;
            case 2:
                //三级页面(县)
                dataBean2 = getIntent().getParcelableExtra(DATA);
                dataBeanList2 = dataBean2.getSonList();
                break;
        }
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        addressRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addressRecyclerView.addItemDecoration(new LinearItemDecoration(getContext(), R.color.gray_ee,
                R.dimen.order_list_divider));
        //封装数据
        getAdapterData();
        addressSelectedAdapter = new AddressSelectedAdapter(this, addressSelectedList);
        addressSelectedAdapter.addItemListener(this);
        addressRecyclerView.setAdapter(addressSelectedAdapter);
    }


    @OnClick(R.id.select_back)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onItemClick(int position) {
        List<AddressSelected> adapterData = addressSelectedAdapter.getData();
        //发送消息到新建收货地址页面
        sendSelectedMessageEvent(grade, adapterData.get(position).getId(), adapterData.get(position).getName());
        if (dataBeanList0 != null && dataBeanList0.size() != 0) {
            if (dataBeanList0.get(position).getSonList() == null
                    || dataBeanList0.get(position).getSonList().size() == 0) {
                //如香港，只有一级
                finish();
                return;
            }
            //点击省到市
            Intent intent = new Intent(this, SelectedAddressActivity.class);
            intent.putExtra(DATA, dataBeanList0.get(position));
            intent.putExtra(GRADE, 1);
            startActivity(intent);
            finish();
        } else if (dataBeanList1 != null && dataBeanList1.size() != 0) {
            if (dataBeanList1.get(position).getSonList() == null ||
                    dataBeanList1.get(position).getSonList().size() == 0) {
                //如北京，只有两级(市、县)，关闭
                finish();
                return;
            }
            //点击市到县
            Intent intent = new Intent(this, SelectedAddressActivity.class);
            intent.putExtra(DATA, dataBeanList1.get(position));
            intent.putExtra(GRADE, 2);
            startActivity(intent);
            finish();
        } else if (dataBeanList2 != null && dataBeanList2.size() != 0) {
            //点击县关闭
            finish();
        }
    }

    //封装adapter数据
    private void getAdapterData() {
        addressSelectedList = new ArrayList<>();
        if (dataBeanList0 != null) {
            //省数据(一级)
            for (AddressSelectedDataBean.DataBean dataBean : dataBeanList0) {
                addressSelectedList.add(new AddressSelected(dataBean.getId(), dataBean.getName()));
            }
        } else if (dataBeanList1 != null) {
            //市数据(二级)
            for (AddressSelectedDataBean.DataBean.SonListBeanX dataBean : dataBeanList1) {
                addressSelectedList.add(new AddressSelected(dataBean.getId(), dataBean.getName()));
            }
        } else if (dataBeanList2 != null) {
            //县数据(三级)
            for (AddressSelectedDataBean.DataBean.SonListBeanX.SonListBean dataBean : dataBeanList2) {
                addressSelectedList.add(new AddressSelected(dataBean.getId(), dataBean.getName()));
            }
        }
    }

    private void sendSelectedMessageEvent(int grade, int cityId, String cityName) {
        SelectedCityEvent selectedCityEvent = new SelectedCityEvent(grade, cityId, cityName);
        EventBus.getDefault().post(selectedCityEvent);
    }
}
