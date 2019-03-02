package com.a1magway.bgg.v.upgrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.a1magway.bgg.R;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.widget.dialog.LoadingDialogFragment;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.AppConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/8/4.
 */

public class UpgradeGuidePager1Activity extends AnimTransitionActivity {
    @BindView(R.id.img_vip)
    ImageView imgVip;
    @BindView(R.id.upgrade_buyer)
    LinearLayout upgradeBuyer;
    @BindView(R.id.upgrade_agency)
    LinearLayout upgradeAgency;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_upgrade_user_page1;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("升级账号");
        upgradeBuyer.setVisibility(View.GONE);
        upgradeAgency.setVisibility(View.GONE);
        String imgUrl = AppConfig.BASE_URL + "/pictures/uplevel/VIP@3x.png";
        MProgressDialog.showProgress(this);
        ImageLoaderUtil.displayImageFixWith(imgVip, imgUrl, new ImageLoaderUtil.LoadCompleteListener() {
            @Override
            public void imgLoadComplete() {
                upgradeBuyer.setVisibility(View.VISIBLE);
                upgradeAgency.setVisibility(View.VISIBLE);
                MProgressDialog.dismissProgress();
            }
        });
    }



    @OnClick({R.id.upgrade_buyer, R.id.upgrade_agency})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upgrade_buyer:
                //去升级到买手
                ActivityIntentUtil.toUpgradeGuidePager2Activity(this,"MS");
//                finish();
                break;
            case R.id.upgrade_agency:
                //去升级到BD
                ActivityIntentUtil.toUpgradeGuidePager2Activity(this,"BD");
//                finish();
                break;
        }
    }
}
