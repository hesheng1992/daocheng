package com.a1magway.bgg.p;

import android.support.annotation.NonNull;
import com.a1magway.bgg.refactor.BaseContract;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * presenter的基类，实现了rx生命周期处理的逻辑
 * Created by jph on 2017/7/24.
 */
public abstract class RxPresenter
    implements BaseContract.BasePresenter, LifecycleProvider<RxPresenter.PresenterEvent> {

    private final BehaviorSubject<PresenterEvent> lifecycleSubject = BehaviorSubject.create();


    @Override
    public void onCreate() {
        lifecycleSubject.onNext(PresenterEvent.CREATE);
    }


    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(PresenterEvent.DESTROY);
    }


    public <T> LifecycleTransformer<T> bindToDestroyEvent() {
        return bindUntilEvent(PresenterEvent.DESTROY);
    }


    @NonNull @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return bindUntilEvent(PresenterEvent.DESTROY);
    }


    @NonNull @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull PresenterEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }


    @NonNull @Override
    public final Observable<PresenterEvent> lifecycle() {
        return lifecycleSubject.hide();
    }


    public enum PresenterEvent {
        CREATE,
        DESTROY

    }



}
