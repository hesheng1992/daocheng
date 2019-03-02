package com.a1magway.bgg.v.guide;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import butterknife.BindView;
import com.a1magway.bgg.R;
import com.a1magway.bgg.common.AppConstants;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.util.SharedPrefUtils;
import com.a1magway.bgg.v.TestActivity;
import com.a1magway.bgg.v.main.MainActivity;
import io.reactivex.Observable;
import java.util.concurrent.TimeUnit;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * 闪屏页
 * Created by jph on 2017/8/26.
 */
@RuntimePermissions
public class SplashActivity extends BaseActivity {

    @BindView(R.id.splash_img)
    ImageView mImg;

    @Override
    public int getContentViewLayoutId() {
        return R.layout.guide_activity_splash;
    }

    @Override
    public void onCreateV(@Nullable Bundle savedInstanceState) {
        super.onCreateV(savedInstanceState);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mImg.setImageResource(R.mipmap.image_splash);
        SplashActivityPermissionsDispatcher.requestStorageWithPermissionCheck(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SplashActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA})
    void requestStorage() {
        showNextPage();
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void deniedStorage() {
        finish();
    }

    private void showNextPage() {
        Observable.timer(3, TimeUnit.SECONDS)
                .compose(this.<Long>bindToDestroyEvent())
                .subscribe(new SimpleObserver<Long>(getContext()) {
                    @Override
                    public void onComplete() {
                        super.onComplete();
                        if (AppConstants.VERSION_NAME.equals(
                                SharedPrefUtils.getOpenedVersionName(getContext()))) {
                            //之前已经打开过app
                            MainActivity.start(getContext());
//                            startActivity(new Intent(SplashActivity.this, TestActivity.class));
                        } else {
                            GuideActivity.start(getContext());
                        }
                        finish();
                        SharedPrefUtils.setOpenedVersionName(getContext(),
                                AppConstants.VERSION_NAME);
                    }
                });
    }


    @Override
    protected boolean fitsSystemWindows() {
        return true;
    }
}
