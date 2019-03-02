package com.a1magway.bgg.v.cart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.BaseActivity;
import com.a1magway.bgg.util.JumpUtil;
import com.a1magway.bgg.widget.TitleBar;

import butterknife.BindView;

/**
 * 承载购物车的Activity
 * Created by jph on 2017/8/16.
 */
public class CartActivity extends BaseActivity {
    @BindView(R.id.cart_title_bar)
    TitleBar mTitleBar;

    public static void start(Context context) {
        Intent starter = new Intent(context, CartActivity.class);
        JumpUtil.startActivity(context, starter);
    }

    @Override
    public int getContentViewLayoutId() {
        return R.layout.cart_activity_cart;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        mTitleBar.setLeftImgClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //显示实际的内容
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.cart_layout_frag, CartFragment.newInstance(false), "Cart");
        ft.commit();
    }
}
