package com.a1magway.bgg.v.articleManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1magway.bgg.R;
import com.a1magway.bgg.common.PActivity;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerAticleManagerComponent;
import com.a1magway.bgg.di.module.AticleManagerModule;
import com.a1magway.bgg.okhttp.LogUtil;
import com.a1magway.bgg.p.articleManager.ArticleManagerP;
import com.a1magway.bgg.util.GlobalData;
import com.almagway.common.AppConfig;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by enid on 2018/8/21.
 */

public class ArticleManagerActivity extends PActivity<ArticleManagerP> implements AticleManagerContract.View {

    @BindView(R.id.article_recyclerView)
    RecyclerView articleRecyclerView;
    @BindView(R.id.create_article)
    LinearLayout createArticle;
    @BindView(R.id.title_img_left)
    AppCompatImageView titleImgLeft;
    @BindView(R.id.title_txt_title)
    AppCompatTextView titleTxtTitle;


    @Override
    public int getContentViewLayoutId() {
        return R.layout.activity_article_manager;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, ArticleManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        titleTxtTitle.setText("文章管理");
        titleImgLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);
        DaggerAticleManagerComponent.builder()
                .appComponent(appComponent)
                .aticleManagerModule(new AticleManagerModule(this))
                .build()
                .inject(this);
    }

    @OnClick(R.id.create_article)
    public void onViewClicked() {
        String url = AppConfig.FOUND_WEB_URL + "/ArticleAdd?title=123&type=" + AppConfig.BASE_URL + "&user_id="
                + GlobalData.getInstance().getLoginData().getId();
        LogUtil.e("article_url", url);
        ArticleManagerWebActivity.start(this, url, "文章管理");
    }


    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        articleRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        articleRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showCreateAticle() {
        createArticle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mPresenter.loadData(false);
    }

}
