package com.a1magway.bgg.refactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.a1magway.bgg.common.SimpleObserver;
import com.a1magway.bgg.p.BaseLoadP;
import com.almagway.common.utils.CollectionUtil;
import io.reactivex.Observable;
import java.util.Collection;

/**
 * author: Beaven
 * date: 2017/10/31 14:48
 */

public abstract class BaseLoadPresenter<V extends BaseContract.BaseLoadView, T>
    extends BasePresenter<V> {

    private BaseContract.BaseLoading loading;


    public BaseLoadPresenter(@NonNull V view) {
        super(view);
        loading = view;
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

        if (showFullLoading && loading != null) {
            loading.showLoading(null);
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
     */
    protected void onLoadError(Throwable e) {
        if (loading == null) {
            return;
        }
        loading.showError();
    }


    /**
     * 加载成功,对应{@link BaseLoadP#loadData()}
     */
    protected void onLoadSuccess(T t) {

    }


    /**
     * 加载结束,对应{@link BaseLoadP#loadData()}
     */
    protected void onLoadFinish() {
        if (loading == null) {
            return;
        }

        loading.hideLoading();
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
     */
    public void checkShowNoneData(T t) {
        //判断是否无数据
        if (checkNoneData(t)) {
            mView.showEmpty();
        } else {
            mView.hideLoading();
        }
    }


    private boolean checkNoneData(T t) {
        if (t instanceof Collection) {
            return CollectionUtil.isEmpty((Collection) t);
        }

        return t == null;
    }
}
