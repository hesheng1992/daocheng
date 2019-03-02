package com.a1magway.bgg.v.cate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import butterknife.BindView;
import com.a1magway.bgg.R;
import com.a1magway.bgg.di.component.AppComponent;
import com.a1magway.bgg.di.component.DaggerBrandMuseumComponent;
import com.a1magway.bgg.di.module.BrandMuseumModule;
import com.a1magway.bgg.p.brand.BrandMuseumP;
import com.a1magway.bgg.refactor.PresenterActivity;
import com.a1magway.bgg.util.JumpUtil;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * 品牌街
 * Created by jph on 2017/7/29.
 */
public class BrandMuseumActivity extends PresenterActivity<BrandMuseumP>
    implements BrandMuseumContract.View {

    public static final String EXTRA_IS_SELECT = "extra_is_select";

    /**
     * 从activity result 中取出选中的品牌model
     */
    public static final String EXTRA_SELECTED_BRAND = "extra_selected_brand";

    @BindView(R.id.museum_layout_index)
    IndexableLayout mIndexLayout;


    public static void start(Context context) {
        Intent starter = new Intent(context, BrandMuseumActivity.class);
        JumpUtil.startActivity(context.getApplicationContext(), starter);
    }


    public static void startForResult(Fragment fragment, int requestCode) {
        Intent intent = new Intent(fragment.getContext(), BrandMuseumActivity.class);
        intent.putExtra(EXTRA_IS_SELECT, true);
        fragment.startActivityForResult(intent, requestCode);
    }


    @Override
    public void injectComponent(AppComponent appComponent) {
        super.injectComponent(appComponent);

        DaggerBrandMuseumComponent.builder()
            .appComponent(appComponent)
            .brandMuseumModule(new BrandMuseumModule(this, getIntent()))
            .build()
            .inject(this);
    }


    @Override
    public int getContentViewLayoutId() {
        return R.layout.brand_activity_museum;
    }


    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        setTextTitle(R.string.brand_title_museum);
        mIndexLayout.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void setIndexLayoutAdapter(IndexableAdapter adapter) {
        mIndexLayout.setAdapter(adapter);
    }


    @Override public void showLoading(@Nullable String text) {

    }


    @Override public void hideLoading() {

    }


    @Override public void showError() {

    }


    @Override public void showEmpty() {

    }
}
