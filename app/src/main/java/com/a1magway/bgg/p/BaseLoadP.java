package com.a1magway.bgg.p;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.a1magway.bgg.common.ILoading;
import com.a1magway.bgg.common.ILoadingV;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.util.GlobalData;
import com.almagway.common.utils.CollectionUtil;

import java.util.Collection;

import io.reactivex.Observable;

/**
 * 可加载整个界面数据的Presenter基类
 *
 * @param <T>
 */
public abstract class BaseLoadP<T, V extends ILoadingV> extends BasePresenter<V> {

    private ILoading mLoading;

    public BaseLoadP(@NonNull V view) {
        super(view);
        mLoading = view;
    }

    @Nullable
    public abstract Observable<T> getDataObservable();

    public void loadData() {
        loadData(true);
    }

    public void loadData(boolean showFullLoading) {
        if (getDataObservable() == null) {
            return;
        }

        if (showFullLoading && mLoading != null) {
            mLoading.showFullLoading();
        }

        getDataObservable()
                .compose(this.<T>bindToDestroyEvent())//根据生命周期取消订阅
                .subscribe(new SimpleObserver<T>(getContext()) {
                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        onLoadError(e);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        onLoadFinish();
                    }

                    @Override
                    public void onNext(@NonNull T t) {
                        onLoadSuccess(t);
                    }

                });
    }

    /**
     * 加载失败,对应{@link BaseLoadP#loadData()}
     *
     * @param e
     */
    protected void onLoadError(Throwable e) {
        if (mLoading == null) {
            return;
        }

        mLoading.hideFullLoading();
    }

    /**
     * 加载成功,对应{@link BaseLoadP#loadData()}
     *
     * @param t
     */
    protected void onLoadSuccess(T t) {

    }

    /**
     * 加载结束,对应{@link BaseLoadP#loadData()}
     */
    protected void onLoadFinish() {
        if (mLoading == null) {
            return;
        }

        mLoading.hideFullLoading();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 判断数据是否为空，来控制是否显示空布局
     *
     * @param t
     */
    public void checkShowNoneData(T t) {
        //判断是否无数据
        if (checkNoneData(t)) {
            mView.showNoneLayout();
            if (GlobalData.getInstance().getLoginData() == null) {
                mView.showNoneLayout4NotLogin();
            }
        } else {
            mView.hideNoneLayout();
        }
    }

    private boolean checkNoneData(T t) {
        if (t instanceof Collection) {
            return CollectionUtil.isEmpty((Collection) t);
        }

        return t == null;
    }
}
