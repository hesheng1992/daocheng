package com.almagway.common.recycler;

import android.support.annotation.NonNull;
import android.view.View;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 通过ViewHolder绑定到RecyclerView的生命周期来管理RxJava的生命周期
 * Created by jph on 2017/7/27.
 */
public abstract class RecyclerRxLifeVH extends RecyclerAttachVH implements
        LifecycleProvider<RecyclerRxLifeVH.VHEvent> {

    private final BehaviorSubject<VHEvent> lifecycleSubject = BehaviorSubject.create();

    public RecyclerRxLifeVH(View itemView) {
        super(itemView);
    }

    void resetRxLife() {
        //被复用显示时重置Rx生命周期
        lifecycleSubject.onNext(VHEvent.ATTACH);
    }

    @Override
    public void onViewAttachedToWindow() {
        lifecycleSubject.onNext(VHEvent.ATTACH);
    }

    @Override
    public void onViewDetachedFromWindow() {
        lifecycleSubject.onNext(VHEvent.DETACH);
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return bindUntilEvent(VHEvent.DETACH);
    }

    @NonNull
    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull VHEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }


    @NonNull
    @Override
    public final Observable<VHEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    enum VHEvent {
        ATTACH,
        DETACH
    }
}
