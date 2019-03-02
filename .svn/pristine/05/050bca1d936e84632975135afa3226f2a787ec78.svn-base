package com.a1magway.bgg.util.dialog;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.a1magway.bgg.R;
import com.a1magway.bgg.data.entity.MorePingtuanData;
import com.a1magway.bgg.eventbus.event.PingtuanBuyEvent;
import com.a1magway.bgg.p.personal.PersonalFeatureAdapter;
import com.a1magway.bgg.p.product.PingtuanAdapter;
import com.a1magway.bgg.util.AndroidUtil;
import com.a1magway.bgg.v.product.ProductDetailsActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 可参与的拼单对话框
 * Created by enid on 2018/8/14.
 */

public class CrowdorderingListDialog extends DialogFragment {


    @BindView(R.id.crowdordering_list_recyclerView)
    RecyclerView crowdorderingListRecyclerView;
    Unbinder unbinder;
    private List<MorePingtuanData.CollageOrderBean> collageOrderBeanList;
    private PingtuanAdapter pingtuanAdapter;

    public static void show(Activity activity,List<MorePingtuanData.CollageOrderBean> collageOrderBeanList){
        CrowdorderingListDialog dialog = new CrowdorderingListDialog();
        dialog.collageOrderBeanList = collageOrderBeanList;
        dialog.setCancelable(false);
        dialog.show(activity.getFragmentManager(),activity.getLocalClassName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_crowdordering_list, container, false);
        unbinder = ButterKnife.bind(this, layout);
        initAdapter();
        return layout;
    }

    private void initAdapter(){
        pingtuanAdapter = new PingtuanAdapter(getActivity(),collageOrderBeanList);
        crowdorderingListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        crowdorderingListRecyclerView.setAdapter(pingtuanAdapter);
        pingtuanAdapter.addPingtuanBuyListener(new PingtuanAdapter.PingtuanBuyClickListener() {
            @Override
            public void goPingtuan(MorePingtuanData.CollageOrderBean collageOrderBean) {
                dismiss();
                EventBus.getDefault().post(new PingtuanBuyEvent(collageOrderBean.getId()));
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.crowdordering_list_close)
    public void onViewClicked() {
        pingtuanAdapter.destroyCountDownView();
        dismiss();
    }
}
