package com.a1magway.bgg.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.a1magway.bgg.R;
import com.a1magway.bgg.v.main.MainSubPages;

import butterknife.ButterKnife;

/**
 * 主页下方TabBar的控件
 * Created by jph on 2017/7/20.
 */
public class TabBar extends FrameLayout {

    private IOnTabCheckListener mOnTabCheckListener;

    RadioGroup mRGroup;

    private RadioButton mainHomeRadioButton;
    private RadioButton cateRadioButton;
    private RadioButton cartRadioButton;
    private RadioButton foundRadioButton;
    private RadioButton personalRadioButton;

    public TabBar(Context context) {
        super(context);
        init();
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TabBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.v_tab_bar, this, true);
        mRGroup = ButterKnife.findById(this, R.id.tab_rgroup);
        mainHomeRadioButton = ButterKnife.findById(this, R.id.tab_rbtn_main_home);
        cateRadioButton = ButterKnife.findById(this, R.id.tab_rbtn_cate);
        cartRadioButton = ButterKnife.findById(this, R.id.tab_rbtn_cart);
        foundRadioButton = ButterKnife.findById(this, R.id.tab_rbtn_found);
        personalRadioButton = ButterKnife.findById(this, R.id.tab_rbtn_personal);
        mRGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (mOnTabCheckListener == null) {
                    return;
                }

                switch (checkedId) {
                    case R.id.tab_rbtn_main_home:
                        mOnTabCheckListener.onCheckMainHomeTab();
                        setTextColor(MainSubPages.MAIN_HOME);
                        break;
//                    case R.id.tab_rbtn_home:
//                        mOnTabCheckListener.onCheckHomeTab();
//                        break;
                    case R.id.tab_rbtn_cate:
                        mOnTabCheckListener.onCheckCateTab();
                        setTextColor(MainSubPages.CATE);
                        break;
                    case R.id.tab_rbtn_cart:
                        mOnTabCheckListener.onCheckCartTab();
                        setTextColor(MainSubPages.CART);
                        break;
                    case R.id.tab_rbtn_personal:
                        mOnTabCheckListener.onCheckPersonalTab();
                        setTextColor(MainSubPages.PERSONAL);
                        break;
                    case R.id.tab_rbtn_found:
                        mOnTabCheckListener.onCheckFoundTAB();
                        setTextColor(MainSubPages.FOUND);
                        break;
                }
            }
        });
    }

    public void setOnTabCheckListener(IOnTabCheckListener onTabCheckListener) {
        mOnTabCheckListener = onTabCheckListener;
    }

    /**
     * 选中Tab
     *
     * @param tab {@link com.a1magway.bgg.v.main.MainSubPages}
     */
    public void selectTab(String tab) {
        switch (tab) {
            case MainSubPages.MAIN_HOME:
                mRGroup.check(R.id.tab_rbtn_main_home);
                setTextColor(MainSubPages.MAIN_HOME);
                break;
//            case MainSubPages.HOME:
//                mRGroup.check(R.id.tab_rbtn_home);
//                break;
            case MainSubPages.CATE:
                mRGroup.check(R.id.tab_rbtn_cate);
                setTextColor(MainSubPages.CATE);
                break;
            case MainSubPages.CART:
                mRGroup.check(R.id.tab_rbtn_cart);
                setTextColor(MainSubPages.CART);
                break;
            case MainSubPages.PERSONAL:
                mRGroup.check(R.id.tab_rbtn_personal);
                setTextColor(MainSubPages.PERSONAL);
                break;
            case MainSubPages.FOUND:
                mRGroup.check(R.id.tab_rbtn_found);
                setTextColor(MainSubPages.FOUND);
                break;
        }
    }

    private void setTextColor(@MainSubPages String selectTab) {
        mainHomeRadioButton.setTextColor(getResources().getColor(R.color.white));
        cateRadioButton.setTextColor(getResources().getColor(R.color.white));
        cartRadioButton.setTextColor(getResources().getColor(R.color.white));
        foundRadioButton.setTextColor(getResources().getColor(R.color.white));
        personalRadioButton.setTextColor(getResources().getColor(R.color.white));
        switch (selectTab) {
            case MainSubPages.MAIN_HOME:
                mainHomeRadioButton.setTextColor(getResources().getColor(R.color.golden));
                break;
            case MainSubPages.CATE:
                cateRadioButton.setTextColor(getResources().getColor(R.color.golden));
                break;
            case MainSubPages.FOUND:
                foundRadioButton.setTextColor(getResources().getColor(R.color.golden));
                break;
            case MainSubPages.CART:
                cartRadioButton.setTextColor(getResources().getColor(R.color.golden));
                break;
            case MainSubPages.PERSONAL:
                personalRadioButton.setTextColor(getResources().getColor(R.color.golden));
                break;
        }
    }

    /**
     * Tab选中回调
     */
    public interface IOnTabCheckListener {

        void onCheckMainHomeTab();

        void onCheckHomeTab();

        void onCheckCateTab();

        void onCheckCartTab();

        void onCheckPersonalTab();

        void onCheckFoundTAB();
    }
}
