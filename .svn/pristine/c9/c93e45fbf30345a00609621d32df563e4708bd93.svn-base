package com.a1magway.bgg.v.search;

import android.support.v7.widget.RecyclerView;

import com.a1magway.bgg.v.IView;

import java.io.Serializable;

/**
 * Created by jph on 2017/8/3.
 */
public interface IFilterV extends IView {

    void setCate1RecyclerViewAdapter(RecyclerView.Adapter adapter);

    void showSelectedBrandTxt(String brand, boolean canSelect);

    /**
     * 显示筛选2级分类界面
     *
     * @param cate1 选择的1级分类，将会传递到2级界面
     */
    void showFilterCateFragment(Serializable cate1);

    /**
     * 关闭筛选界面
     *
     * @param rule 最后选择的筛选规则
     */
    void closeFilterFragment(Serializable rule);

    /**
     * 显示选择的分类信息
     */
    void showSelectedCateInfo(String cate1, String gender, String cate2);

    /**
     * 显示选择一级分类的布局
     */
    void showCate1Layout();

    /**
     * 使界面恢复到初始化状态
     */
    void resetView();
}
