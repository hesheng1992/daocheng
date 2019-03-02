package com.a1magway.bgg.v.upgrade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.eventbus.event.SwitchToUpgradePagerEvent;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.ActivityIntentUtil;
import com.a1magway.bgg.util.AppManager;
import com.a1magway.bgg.util.ImageLoaderUtil;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;
import com.a1magway.bgg.widget.dialog.MProgressDialog;
import com.almagway.common.AppConfig;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by enid on 2018/8/4.
 */

public class UpgradeGuidePager2Activity extends AnimTransitionActivity {

    @BindView(R.id.top_image)
    ImageView topImage;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;

    private boolean isUpgradeMS;//true升级为买手,false升级为BD
    private volatile int loadingCompleteImage;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_upgrade_user_page2;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle("升级账号");
        String baseUrl = AppConfig.BASE_URL;
        String type = getIntent().getStringExtra("type");
        if (type.equals("MS")) {
            isUpgradeMS = true;
        } else {
            isUpgradeMS = false;
        }
        String topImageUrl;
        String goodsImageUrl;
        String payImageUrl;
        if (isUpgradeMS) {
            topImageUrl = baseUrl + "/pictures/uplevel/MS@2x.png";
            goodsImageUrl = baseUrl + "/pictures/uplevel/MS1@2x.png";
            payImageUrl = baseUrl + "/pictures/uplevel/MS2@2x.png";
        } else {
            topImageUrl = baseUrl + "/pictures/uplevel/BD@2x.png";
            goodsImageUrl = baseUrl + "/pictures/uplevel/BD1@2x.png";
            payImageUrl = baseUrl + "/pictures/uplevel/BD2@2x.png";
        }
        MProgressDialog.showProgress(this);
        img1.setEnabled(false);
        img2.setEnabled(false);
        ImageLoaderUtil.displayImageFixWith(topImage, topImageUrl, new ImageLoaderUtil.LoadCompleteListener() {
            @Override
            public void imgLoadComplete() {
                loadingCompleteImage++;
                loadComplete();
            }
        });
        ImageLoaderUtil.displayImageFixWith(img1, goodsImageUrl, new ImageLoaderUtil.LoadCompleteListener() {
            @Override
            public void imgLoadComplete() {
                loadingCompleteImage++;
                loadComplete();
            }
        });
        ImageLoaderUtil.displayImageFixWith(img2, payImageUrl, new ImageLoaderUtil.LoadCompleteListener() {
            @Override
            public void imgLoadComplete() {
                loadingCompleteImage++;
                loadComplete();
            }
        });

    }

    private void loadComplete(){
        if (loadingCompleteImage == 3) {
            MProgressDialog.dismissProgress();
            img1.setEnabled(true);
            img2.setEnabled(true);
        }
    }


    @OnClick({R.id.img1, R.id.img2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img1:
                if (isUpgradeMS) {
                    //购买商品升级
                    EventBus.getDefault().post(new SwitchToUpgradePagerEvent());
                    MainActivity.start(this, MainSubPages.MAIN_HOME);
                } else {
                    //支付钱升级
                    ActivityIntentUtil.toUpgradePayActivity(this,20000);
                }

                break;
            case R.id.img2:
                if (isUpgradeMS) {
                    //支付钱升级
                    ActivityIntentUtil.toUpgradePayActivity(this,999);
                } else {
                    //购买商品升级
                    EventBus.getDefault().post(new SwitchToUpgradePagerEvent());
                    MainActivity.start(this, MainSubPages.MAIN_HOME);
                }
                break;
        }
    }
}
