package com.a1magway.bgg.v.search;

import android.support.annotation.StringRes;

import com.a1magway.bgg.v.IView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jph on 2017/8/3.
 */
public interface IFilterCateV extends IView {

    void setCate1Text(String cate1Name);

    void setManText(@StringRes int resId);

    void setWomanText(@StringRes int resId);

    void showWomanCate2Content(List<String> strList);

    void showManCate2Content(List<String> strList);

    /**
     * 返回筛选界面
     *
     * @param selectedCate2 选择的二级分类
     */
    void backFilterFragment(Serializable selectedCate2);
}
