package com.a1magway.bgg.refactor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.a1magway.bgg.R;
import com.a1magway.bgg.util.AppManager;
import com.a1magway.bgg.util.SoftInputUtil;

/**
 * author: Beaven
 * date: 2017/10/28 12:44
 */

@SuppressLint("Registered") public class AnimTransitionActivity extends BaseNewActivity {

    public enum AnimType {
        ANIM_TYPE_RIGHT_IN, // 右侧滑动进入
        ANIM_TYPE_UP_IN// 从页面底部进入
    }


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        overrideTransition();
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        setBackListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void overrideTransition() {
        if (getAnimType() != null) {
            switch (getAnimType()) {
                case ANIM_TYPE_RIGHT_IN:
                    overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                    break;
                case ANIM_TYPE_UP_IN:
                    overridePendingTransition(R.anim.slide_up_in, R.anim.slide_stay);
                    break;
                default:
                    break;
            }
        }
    }


    protected AnimType getAnimType() {
        return null;
    }


    @Override public void onBackPressed() {
        SoftInputUtil.hideSoftInput(this);
        if (preExitPage()) {
            return;
        }
        super.onBackPressed();
    }


    public void finishWithAnim() {
        if (getAnimType() != null) {
            switch (getAnimType()) {
                case ANIM_TYPE_RIGHT_IN:
                    finishWithAnimRightOut();
                    break;
                case ANIM_TYPE_UP_IN:
                    finishWithAnimDownOut();
                    break;
                default:
                    finish();
                    break;
            }
        } else {
            finish();
        }
    }


    private void finishWithAnimRightOut() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }


    private void finishWithAnimDownOut() {
        finish();
        overridePendingTransition(0, R.anim.slide_down_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().finishActivity(this);
    }
}
