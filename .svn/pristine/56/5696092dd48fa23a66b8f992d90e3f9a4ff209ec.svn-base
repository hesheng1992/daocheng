package com.a1magway.bgg.common.adapter;

import android.view.View;

import com.a1magway.bgg.common.adapter.attach.AttachRecyclerVH;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * 通过ViewHolder绑定到RecyclerView的生命周期来管理RxJava的生命周期
 * Created by jph on 2017/7/27.
 */
public abstract class RxRecyclerVH extends AttachRecyclerVH implements
        LifecycleProvider<RxRecyclerVH.VHEvent> {

    private final BehaviorSubject<VHEvent> lifecycleSubject = BehaviorSubject.create();

    public RxRecyclerVH(View itemView) {
        super(itemView);
    }

    public void resetRxLife() {
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

    @Override
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return bindUntilEvent(VHEvent.DETACH);
    }

    @Override
    public <T> LifecycleTransformer<T> bindUntilEvent(VHEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }


    @Override
    public final Observable<VHEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    public enum VHEvent {
        ATTACH,
        DETACH
    }
}
