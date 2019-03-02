package com.a1magway.bgg.common;

/**
 * 加载控制
 * Created by jph on 2017/7/19.
 */
public interface ILoading {

    /**
     * 显示个页面的loading
     */
    void showFullLoading();

    /**
     * 隐藏整个页面的loading
     */
    void hideFullLoading();

    /**
     * 显示空提示
     */
    void showNoneLayout();

    /**
     * 显示空提示当未登录的时候显示登录按钮
     */
    void showNoneLayout4NotLogin();

    /**
     * 隐藏空提示
     */
    void hideNoneLayout();

    /**
     * 显示加载弹窗
     */
    void showLoadingDialog();


    void showLoadingDialog(String msg);

    /**
     * 隐藏加载弹窗
     */
    void hideLoadingDialog();

    /**
     * 隐藏所有 包括未登录的登录按钮
     */
    void hideLoginLoading();
}
