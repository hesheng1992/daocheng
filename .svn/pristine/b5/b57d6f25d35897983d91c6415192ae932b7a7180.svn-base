package com.a1magway.bgg.v.invitation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.refactor.AnimTransitionActivity;
import com.a1magway.bgg.util.IntentKey;
import com.a1magway.bgg.v.main.MainActivity;
import com.a1magway.bgg.v.main.MainSubPages;

import butterknife.OnClick;

/**
 * Created by enid on 2018/8/3.
 */

public class InvitationGuideActivity extends AnimTransitionActivity {
    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_bind_invitation_guide;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        String title = getIntent().getStringExtra(IntentKey.TITLE);
        int backId = getIntent().getIntExtra(IntentKey.BACK_ID, 0);

        setTextTitle(title);
        setImageBack(true, backId);
        setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(InvitationGuideActivity.this, MainSubPages.PERSONAL);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        MainActivity.start(InvitationGuideActivity.this, MainSubPages.PERSONAL);
    }

    @OnClick(R.id.to_bind_btn)
    public void onViewClicked() {
        InvitationActivity.startComeInvitationGuideActivity(this);
        finish();
    }
}
